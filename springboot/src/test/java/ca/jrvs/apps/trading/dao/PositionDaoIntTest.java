package ca.jrvs.apps.trading.dao;


import static org.junit.Assert.assertEquals;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Position;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import ca.jrvs.apps.trading.model.domain.SecurityOrder.Status;
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
public class PositionDaoIntTest {

  @Autowired
  private SecurityOrderDao securityOrderDao;

  @Autowired
  private AccountDao accountDao;

  @Autowired
  private QuoteDao quoteDao;

  @Autowired
  private TraderDao traderDao;

  @Autowired
  private PositionDao positionDao;

  private Trader savedTrader;
  private Account savedAccount;
  private Quote savedQuote;
  private SecurityOrder savedSecurityOrder;
  private SecurityOrder savedSecurityOrder2;


  @Before
  public void insertOne() {
    savedQuote = new Quote();
    savedQuote.setAskPrice(10f);
    savedQuote.setAskSize(10);
    savedQuote.setBidPrice(10.2f);
    savedQuote.setBidSize(10);
    savedQuote.setId("aapl");
    savedQuote.setLastPrice(10.1f);
    quoteDao.save(savedQuote);

    savedQuote.setAskPrice(20f);
    savedQuote.setAskSize(20);
    savedQuote.setBidPrice(20.2f);
    savedQuote.setBidSize(20);
    savedQuote.setId("MSFT");
    savedQuote.setLastPrice(20.1f);
    quoteDao.save(savedQuote);

    savedTrader = new Trader();
    savedTrader.setCountry("voluptate");
    savedTrader.setDob(new java.util.Date("10/10/2009"));
    savedTrader.setEmail("irure deserunt do Excepteur");
    savedTrader.setId(85843197);
    savedTrader.setFirstName("labore anim laborum");
    savedTrader.setLastName("esse");
    traderDao.save(savedTrader);

    savedAccount = new Account();
    savedAccount.setId(1);
    savedAccount.setAmount(100.0F);
    savedAccount.setTrader_id(1);
    accountDao.save(savedAccount);

    savedSecurityOrder = new SecurityOrder();
    savedSecurityOrder.setAccount_id(1);
    savedSecurityOrder.setTicker("AAPL");
    savedSecurityOrder.setStatus(Status.FILLED);
    savedSecurityOrder.setNotes("integration test");
    savedSecurityOrder.setPrice(10.1F);
    savedSecurityOrder.setSize(100);
    securityOrderDao.save(savedSecurityOrder);

  savedSecurityOrder2 = new SecurityOrder();
    savedSecurityOrder2.setAccount_id(1);
    savedSecurityOrder2.setTicker("MSFT");
    savedSecurityOrder2.setStatus(Status.FILLED);
    savedSecurityOrder2.setNotes("integration test");
    savedSecurityOrder2.setPrice(20.1F);
    savedSecurityOrder2.setSize(100);
    securityOrderDao.save(savedSecurityOrder2);



  }


  @After
  public void deleteOne() {
    securityOrderDao.deleteById(savedSecurityOrder.getId());
    securityOrderDao.deleteById(savedSecurityOrder2.getId());
    accountDao.deleteById(savedAccount.getId());
    traderDao.deleteById(savedTrader.getId());
    quoteDao.deleteById(savedQuote.getId());
  }


  @Test
  public void findAllTest() {
    Iterable<Position> positionList = positionDao.findAll();
    assertEquals(2, ((List<Position>) positionList).size());
  }


  @Test
  public void findAllByAccountIDTest() {
    Iterable<Position> positionList = positionDao.findAllByAccountId(1);
    assertEquals(2, ((List<Position>) positionList).size());
  }


}
