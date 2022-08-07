package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Position;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class PositionDao implements CrudRepository<Position, Integer> {

  private static final String TABLE_NAME = "position";
  private static final String ID_COLUMN_NAME_1 = "account_id";
  private static final String ID_COLUMN_NAME_2 = "ticker";


  private static final Logger logger = LoggerFactory.getLogger(PositionDao.class);
  private JdbcTemplate jdbcTemplate;
  private SimpleJdbcInsert simpleJdbcInsert;

  @Autowired
  public PositionDao(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
    this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME);
  }


  @Override
  public <S extends Position> S save(S entity) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public <S extends Position> Iterable<S> saveAll(Iterable<S> entities) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public Optional<Position> findById(Integer integer) {
    Optional<Position> position = Optional.empty();
    String selectSql = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID_COLUMN_NAME_1 + " =?";

    try {
      position = Optional.ofNullable(jdbcTemplate.queryForObject(selectSql,
          BeanPropertyRowMapper.newInstance(Position.class), integer));
    } catch (EmptyResultDataAccessException e) {
      logger.debug("Can't find account id:" + integer);
    }

    return position;
  }


  public Optional<Position> findByIds(Integer integer, String ticker) {
    Optional<Position> position = Optional.empty();
    String selectSql =
        "SELECT * FROM " + TABLE_NAME + " WHERE " + ID_COLUMN_NAME_1 + " =? AND" + ID_COLUMN_NAME_2
            + " =?";

    try {
      position = Optional.ofNullable(jdbcTemplate.queryForObject(selectSql,
          BeanPropertyRowMapper.newInstance(Position.class), integer, ticker));
    } catch (EmptyResultDataAccessException e) {
      logger.debug("Can't find account id:" + integer);
    }

    return position;
  }


  @Override
  public boolean existsById(Integer integer) {
    return findById(integer).isPresent();
  }

  @Override
  public Iterable<Position> findAll() {
    String selectSql = "SELECT * FROM " + TABLE_NAME;
    List<Position> positions = null;
    try {
      positions = jdbcTemplate
          .query(selectSql, BeanPropertyRowMapper.newInstance(Position.class));
    } catch (DataAccessException e) {
      logger.debug("Failed to query: ", e);
      throw e;
    }

    return positions;
  }

  @Override
  public Iterable<Position> findAllById(Iterable<Integer> integers) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public long count() {
    return 0;
  }

  @Override
  public void deleteById(Integer integer) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void delete(Position entity) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void deleteAll(Iterable<? extends Position> entities) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void deleteAll() {
    throw new UnsupportedOperationException("Not implemented");
  }
}
