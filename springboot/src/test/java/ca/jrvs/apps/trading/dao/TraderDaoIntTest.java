package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.assertEquals;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Trader;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.util.Lists;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class TraderDaoIntTest {

  @Autowired
  private TraderDao traderDao;

  private Trader savedTrader;

  @Before
  public void insertOne() {
    savedTrader = new Trader();
    savedTrader.setCountry("voluptate");
    savedTrader.setDob(new java.util.Date("10/10/2009"));
    savedTrader.setEmail("irure deserunt do Excepteur");
    savedTrader.setId(85843197);
    savedTrader.setFirstName("labore anim laborum");
    savedTrader.setLastName("esse");
    traderDao.save(savedTrader);
  }

  @After
  public void deleteOne() {
    traderDao.deleteById(savedTrader.getId());
  }

  @Test
  public void findAllById() {
    List<Trader> traders = Lists.newArrayList(traderDao.findAllById(
        Arrays.asList(savedTrader.getId(), -1)));
    assertEquals(1, traders.size());
    assertEquals(savedTrader.getCountry(), traders.get(0).getCountry());
  }
}