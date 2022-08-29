package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public abstract class JdbcCrudDao<T extends Entity<Integer>> implements CrudRepository<T, Integer> {

  private static final Logger logger = LoggerFactory.getLogger(JdbcCrudDao.class);

  abstract public JdbcTemplate getJdbcTemplate();

  abstract public SimpleJdbcInsert getSimpleJdbcInsert();

  abstract public String getTableName();

  abstract public String getIdColumnName();

  abstract Class<T> getEntityClass();


  /**
   * Save an entity and update auto-generated integer ID
   *
   * @param entity must not be {@literal null}. to be saved
   * @return save entity
   */
  public <S extends T> S save(S entity) {
    if (existsById(entity.getId())) {
      if (updateOne(entity) != 1) {
        throw new DataRetrievalFailureException("Unable to update quote");
      }
    } else {
      addOne(entity);
    }
    return entity;
  }

  /**
   * helper method that saves one quote
   *
   * @param entity
   * @param <S>
   */
  private <S extends T> void addOne(S entity) {
    SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(entity);

    Number newId = getSimpleJdbcInsert().executeAndReturnKey(parameterSource);
    entity.setId(newId.intValue());
  }

  /**
   * helper method that updates one quote
   */
  abstract public int updateOne(T entity);


  @Override
  public Optional<T> findById(Integer id) {
    Optional<T> entity = Optional.empty();
    String selectSql = "SELECT * FROM " + getTableName() + " WHERE " + getIdColumnName() + " =?";

    try {
      entity = Optional.ofNullable((T) getJdbcTemplate().queryForObject(selectSql,
          BeanPropertyRowMapper.newInstance(getEntityClass()), id));
    } catch (IncorrectResultSizeDataAccessException e) {
      logger.debug("Can't find trader id:" + id, e);
    }
    return entity;
  }

  @Override
  public boolean existsById(Integer integer) {
    return findById(integer).isPresent();
  }

  @Override
  public Iterable<T> findAll() {
    String selectSql = "SELECT * FROM " + getTableName();
    List<T> rows = null;
    try {
      rows = getJdbcTemplate()
          .query(selectSql, BeanPropertyRowMapper.newInstance(getEntityClass()));
    } catch (DataAccessException e) {
      logger.debug("Failed to query: ", e);
      throw e;
    }

    return rows;

  }

  @Override
  public Iterable<T> findAllById(Iterable<Integer> integers) {
    List<T> rows = new ArrayList<>();
    integers.forEach(integer -> {
      Optional<T> row = findById(integer);
      row.ifPresent(rows::add);
    });

    return rows;
  }

  @Override
  public long count() {
    String selectSql = "SELECT COUNT(*) FROM " + getTableName();
    Object nums = getJdbcTemplate()
        .queryForObject(selectSql, Long.class);
    if (nums == null) {
      return 0;
    }
    return (long) nums;
  }

  @Override
  public void deleteById(Integer integer) {
    if (integer == null) {
      throw new IllegalArgumentException("ID can't be null");
    }
    String deleteSql = "DELETE FROM " + getTableName() + " WHERE " + getIdColumnName() + " =?";
    getJdbcTemplate().update(deleteSql, integer);
  }


  @Override
  public void deleteAll() {
    String deleteSql = "DELETE FROM " + getTableName();
    getJdbcTemplate().update(deleteSql);
  }

  @Override
  public <S extends T> List<S> saveAll(Iterable<S> quotes) {
    throw new UnsupportedOperationException("Not implemented");
  }

//  @Override
//  public void delete(T entity) {
//    throw new UnsupportedOperationException("Not implemented");
//  }

//  @Override
//  public void deleteAll(Iterable<? extends T> entities) {
//    throw new UnsupportedOperationException("Not implemented");
//  }
}
