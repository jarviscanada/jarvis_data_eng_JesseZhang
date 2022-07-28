package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Quote;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class QuoteDao implements CrudRepository<Quote, String> {

  private static final String TABLE_NAME = "quote";
  private static final String ID_COLUMN_NAME = "ticker";

  private static final Logger logger = LoggerFactory.getLogger(QuoteDao.class);
  private JdbcTemplate jdbcTemplate;
  private SimpleJdbcInsert simpleJdbcInsert;

  @Autowired
  public QuoteDao(DataSource dataSource) {
    jdbcTemplate = new JdbcTemplate(dataSource);
    simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME);
  }

  /**
   * @param quote must not be {@literal null}.
   * @return
   * @throws DataRetrievalFailureException for unexpected SQL result or SQL execution failure
   */
  @Override
  public Quote save(Quote quote) {

    if (existsById(quote.getId())) {
      int updatedRowNo = updateOne(quote);
      if (updatedRowNo != 1) {
        throw new DataRetrievalFailureException("Unable to update quote");
      }
    } else {
      addOne(quote);
    }
    return quote;
  }

  /**
   * helper method that saves one quote
   *
   * @param quote
   */
  private void addOne(Quote quote) {
    SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(quote);
    int row = simpleJdbcInsert.execute(parameterSource);
    if (row != 1) {
      throw new IncorrectResultSizeDataAccessException("Failed to insert", 1, row);
    }
  }

  private int updateOne(Quote quote) {
    String update_sql = "UPDATE quote SET last_price=?, bid_price=?, " +
        "bid_size=?, ask_price=?, ask_size=? WHERE ticker=?";
    return jdbcTemplate.update(update_sql, makeUpdateValues(quote));
  }

  /**
   * helper method that makes sql update values objects
   *
   * @param quote to be updated
   * @return UPDATE_SQL values
   */
  private Object[] makeUpdateValues(Quote quote) {
    Object[] updated = new Object[6];
    updated[0] = quote.getLastPrice();
    updated[1] = quote.getBidPrice();
    updated[2] = quote.getBidSize();
    updated[3] = quote.getAskPrice();
    updated[4] = quote.getAskSize();
    updated[5] = quote.getTicker();
    return updated;
  }


  @Override
  public <S extends Quote> List<S> saveAll(Iterable<S> quotes) {
    List<S> result = new ArrayList<>();
    quotes.forEach(quote -> result.add((S) save(quote)));
    return null;
  }

  /**
   * Find a quote by ticker
   *
   * @param ticker name must not be {@literal null}.
   * @return quote or Optional.empty if not found
   */
  @Override
  public Optional<Quote> findById(String ticker) {

    Optional<Quote> quote;
    String selectSql = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID_COLUMN_NAME + " =?";

    try {
      quote = Optional.ofNullable(jdbcTemplate.queryForObject(selectSql,
          BeanPropertyRowMapper.newInstance(Quote.class), ticker));
    } catch (EmptyResultDataAccessException e) {
      logger.debug("Can't find ticker id:" + ticker);
      return Optional.empty();
    }

    return quote;
  }

  @Override
  public boolean existsById(String s) {
    return findById(s).isPresent();
  }

  /**
   * return all quotes
   *
   * @return DataAccessException if failed to query
   */
  @Override
  public List<Quote> findAll() {
    String selectSql = "SELECT * FROM " + TABLE_NAME;
    List<Quote> quotes = null;
    try {
      quotes = jdbcTemplate
          .query(selectSql, BeanPropertyRowMapper.newInstance(Quote.class));
    } catch (DataAccessException e) {
      logger.debug("Failed to query: ", e);
      throw e;
    }

    return quotes;
  }

  @Override
  public long count() {
    String selectSql = "SELECT COUNT(*) FROM " + TABLE_NAME;
    Object nums = jdbcTemplate
        .queryForObject(selectSql, Long.class);
    if (nums == null) {
      return 0;
    }
    return (long) nums;
  }

  @Override
  public void deleteById(String s) {
    if (s == null) {
      throw new IllegalArgumentException("ID can't be null");
    }
    String deleteSql = "DELETE FROM " + TABLE_NAME + " WHERE " + ID_COLUMN_NAME + " =?";
    jdbcTemplate.update(deleteSql, s);
  }

  @Override
  public void deleteAll() {
    String deleteSql = "DELETE FROM " + TABLE_NAME;
    jdbcTemplate.update(deleteSql);
  }


  @Override
  public Iterable<Quote> findAllById(Iterable<String> strings) {
    throw new UnsupportedOperationException("Not implemented");
  }


  @Override
  public void delete(Quote entity) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void deleteAll(Iterable<? extends Quote> entities) {
    throw new UnsupportedOperationException("Not implemented");
  }


}
