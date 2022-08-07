package ca.jrvs.apps.trading.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.model.domain.Quote;
import java.util.Arrays;
import java.util.List;
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
public class QuoteServiceIntTest {

  @Autowired
  private QuoteService quoteService;

  @Autowired
  private QuoteDao quoteDao;

  @Before
  public void setup() {
    quoteDao.deleteAll();
  }

  @Test
  public void findIexQuoteByTicker() {
    IexQuote iexQuote = quoteService.findIexQuoteByTicker("MSFT");
    assertNotNull(iexQuote);
    assertEquals("MSFT", iexQuote.getSymbol());
  }

  @Test
  public void updateMarketData() {
    Quote savedQuote = new Quote();
    savedQuote.setAskPrice(10f);
    savedQuote.setAskSize(10);
    savedQuote.setBidPrice(10.2f);
    savedQuote.setBidSize(10);
    savedQuote.setId("aapl");
    savedQuote.setLastPrice(10.1f);
    quoteDao.save(savedQuote);
    savedQuote = new Quote();
    savedQuote.setId("msft");
    savedQuote.setAskPrice(20f);
    savedQuote.setAskSize(20);
    savedQuote.setBidPrice(20.2f);
    savedQuote.setBidSize(20);
    savedQuote.setLastPrice(20.1f);
    quoteDao.save(savedQuote);
    quoteService.updateMarketData();
    List<Quote> quotes = quoteService.findAllQuotes();
    assertEquals(2, quotes.size());
    assertEquals("AAPL", quotes.get(0).getTicker());
    assertEquals("MSFT", quotes.get(1).getTicker());
  }

  @Test
  public void saveQuotes() {
    List<String> tickers = Arrays.asList("aapl", "msft");
    quoteService.saveQuotes(tickers);
    List<Quote> quotes = quoteService.findAllQuotes();
    assertEquals(2, quotes.size());
    assertEquals("AAPL", quotes.get(0).getTicker());
    assertEquals("MSFT", quotes.get(1).getTicker());
  }

  @Test
  public void saveQuote() {
    String ticker = "aapl";
    quoteService.saveQuote(ticker);
    List<Quote> quotes = quoteService.findAllQuotes();
    assertEquals(1, quotes.size());
    assertEquals("AAPL", quotes.get(0).getTicker());
  }

  @Test
  public void findAllQuotes() {
    Quote savedQuote = new Quote();
    savedQuote.setAskPrice(10f);
    savedQuote.setAskSize(10);
    savedQuote.setBidPrice(10.2f);
    savedQuote.setBidSize(10);
    savedQuote.setId("aapl");
    savedQuote.setLastPrice(10.1f);
    quoteDao.save(savedQuote);
    List<Quote> quotes = quoteService.findAllQuotes();
    assertEquals(1, quotes.size());
    assertEquals("AAPL", quotes.get(0).getTicker());
  }
}
