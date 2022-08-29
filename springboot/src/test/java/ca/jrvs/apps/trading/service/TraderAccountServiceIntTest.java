package ca.jrvs.apps.trading.service;

import static org.junit.Assert.assertEquals;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Trader;
import ca.jrvs.apps.trading.model.view.TraderAccountView;
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
public class TraderAccountServiceIntTest {

  private TraderAccountView savedView;
  @Autowired
  private TraderAccountService traderAccountService;
  @Autowired
  private TraderDao traderDao;
  @Autowired
  private AccountDao accountDao;


  Trader savedTrader = new Trader();
  TraderAccountView traderAccountView;
  Account account;

  @Before
  public void createOne() {

    savedTrader = new Trader();
    savedTrader.setCountry("voluptate");
    savedTrader.setDob(new java.util.Date("10/10/2009"));
    savedTrader.setEmail("irure deserunt do Excepteur");
    savedTrader.setFirstName("labore anim laborum");
    savedTrader.setLastName("esse");
    traderAccountView = traderAccountService.createTraderAndAccount(savedTrader);
  }


  @After
  public void deleteOne() {
//    traderAccountService.deleteTraderById(savedTrader.getId());
//    assertFalse(traderDao.findById(savedTrader.getId()).isPresent());
  }


  @Test
  public void createTraderAndAccount() {
    assertEquals(savedTrader.getCountry(), traderAccountView.getTrader().getCountry());
    assertEquals(savedTrader.getId(), traderAccountView.getAccount().getTrader_id());
  }

  @Test
  public void depositAndWithdraw() {
    account = traderAccountService.deposit(1, 50d);
    account = traderAccountService.deposit(1, 50d);
    System.out.println(accountDao.findByTraderId(1));
    assertEquals((Float) 100f, account.getAmount());
    Account account = traderAccountService.withdraw(1, 100d);

    assertEquals((Float) 0f, account.getAmount());

  }


}
