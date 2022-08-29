package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Position;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import java.util.List;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class SecurityOrderDao extends JdbcCrudDao<SecurityOrder> {

  private static final Logger logger = LoggerFactory.getLogger(SecurityOrderDao.class);

  private final String TABLE_NAME = "security_order";
  private final String ID_COLUMN = "id";

  private JdbcTemplate jdbcTemplate;
  private SimpleJdbcInsert simpleJdbcInsert;

  @Autowired
  public SecurityOrderDao(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
    this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME)
        .usingGeneratedKeyColumns(ID_COLUMN);
  }

  @Override
  public JdbcTemplate getJdbcTemplate() {
    return jdbcTemplate;
  }

  @Override
  public SimpleJdbcInsert getSimpleJdbcInsert() {
    return simpleJdbcInsert;
  }

  @Override
  public String getTableName() {
    return TABLE_NAME;
  }

  @Override
  public String getIdColumnName() {
    return ID_COLUMN;
  }

  @Override
  Class<SecurityOrder> getEntityClass() {
    return SecurityOrder.class;
  }

  @Override
  public int updateOne(SecurityOrder entity) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void delete(SecurityOrder entity) {
    String deleteSql = "DELETE FROM " + getTableName() + " WHERE "+ID_COLUMN+" =?";
    getJdbcTemplate().update(deleteSql, entity.getId());
  }

  @Override
  public void deleteAll(Iterable<? extends SecurityOrder> entities) {
    throw new UnsupportedOperationException("Not implemented");
  }

  public List<SecurityOrder> findAllByAccountId(Integer integer){
    List<SecurityOrder> securityOrders = null;
    String selectSql =
        "SELECT * FROM " + TABLE_NAME + " WHERE account_id =?";

    try {
      securityOrders = jdbcTemplate
          .query(selectSql, BeanPropertyRowMapper.newInstance(SecurityOrder.class), integer);
    } catch (EmptyResultDataAccessException e) {
      logger.debug("Can't find account id:" + integer);
    }

    return securityOrders;
  }

}
