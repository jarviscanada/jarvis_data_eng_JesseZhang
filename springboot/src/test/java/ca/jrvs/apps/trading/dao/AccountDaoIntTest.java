package ca.jrvs.apps.trading.dao;


import static org.junit.Assert.assertEquals;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Trader;
import java.util.List;
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
public class AccountDaoIntTest {

  @Autowired
  private AccountDao accountDao;
  @Autowired
  private TraderDao traderDao;


  private Account savedAccount;
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
    savedAccount = new Account();
    savedAccount.setId(10);
    savedAccount.setAmount(100.0F);
    savedAccount.setTrader_id(1);
    accountDao.save(savedAccount);
  }

  @After
  public void deleteOne() {
    accountDao.deleteById(savedAccount.getId());
    traderDao.deleteById(savedTrader.getId());
  }

  @Test
  public void findAllTest() {
    Iterable<Account> accountList = accountDao.findAll();
    assertEquals(1, ((List<Account>) accountList).size());
  }

}
