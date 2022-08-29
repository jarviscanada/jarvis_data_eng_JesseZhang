package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.model.exception.ResourceNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class QuoteService {

  private static final Logger logger = LoggerFactory.getLogger(QuoteService.class);

  private QuoteDao quoteDao;
  private MarketDataDao marketDataDao;

  @Autowired
  public QuoteService(QuoteDao quoteDao, MarketDataDao marketDataDao) {
    this.quoteDao = quoteDao;
    this.marketDataDao = marketDataDao;
  }


  /**
   * Update quote table against IEX source - get all quote from the db - foreach ticker get iexQuote
   * - convert iexQuote to quote entity - persist quote to db
   *
   * @throws ResourceNotFoundException                   if ticker is not found from IEX
   * @throws org.springframework.dao.DataAccessException if unable to retrieve data
   * @throws IllegalArgumentException                    for invalid input
   */
  public List<Quote> updateMarketData() {

    List<Quote> quotes = findAllQuotes();
    List<Quote> updatedQuotes = new ArrayList<>();
    quotes.forEach(quote ->
    {
      IexQuote iexQuote = findIexQuoteByTicker(quote.getId());
      Quote updatedQuote = buildQuoteFromIexQuote(iexQuote);
      updatedQuotes.add(updatedQuote);
      saveQuote(updatedQuote);
    });
    return updatedQuotes;
  }

  /**
   * Helper method. Map a IexQuote to a Quote entity.
   *
   * @param iexQuote an IexQuote object
   * @return a Quote object Note: `iexQuote.getLatestPrice() == null` if the stock market is closed.
   * Make sure set a default value for number field(s).
   */
  protected static Quote buildQuoteFromIexQuote(IexQuote iexQuote) {

    Quote quote = new Quote();
    quote.setTicker(iexQuote.getSymbol());
    quote.setLastPrice((float) iexQuote.getLatestPrice());
    quote.setBidPrice((float) iexQuote.getIexBidPrice());
    quote.setBidSize(iexQuote.getIexBidSize());
    quote.setAskPrice((float) iexQuote.getIexAskPrice());
    quote.setAskSize(iexQuote.getIexAskSize());
//    System.out.println(quote.toString());
    return quote;
  }

  /**
   * Validate (against IEX) and save given tickers to quote table.
   * <p>
   * - Get iexQuote(s) - convert each iexQuote to Quote entity - persist the quote to db
   *
   * @param tickers a list of tickers/symbols
   * @throws IllegalArgumentException if ticker is not found from IEX
   */
  public List<Quote> saveQuotes(List<String> tickers) {

    List<Quote> quotes = new ArrayList<>();
    tickers.forEach(ticker -> {
      quotes.add(saveQuote(ticker));
    });

    return quotes;
  }

  /**
   * helper method
   */
  public Quote saveQuote(String ticker) {
    IexQuote iexQuote = findIexQuoteByTicker(ticker);
    Quote quote = buildQuoteFromIexQuote(iexQuote);
    return saveQuote(quote);
  }


  /**
   * Find an IexQuote
   *
   * @param ticker id
   * @return IexQuote object
   * @throws IllegalArgumentException if ticker is invalid
   */
  public IexQuote findIexQuoteByTicker(String ticker) {
    return marketDataDao.findById(ticker)
        .orElseThrow(() -> new IllegalArgumentException(ticker + " is invalid"));
  }

  /**
   * Update a given quote to quote table without validation
   *
   * @param quote entity
   * @return
   */
  public Quote saveQuote(Quote quote) {
    return quoteDao.save(quote);
  }

  /**
   * Find all quotes from the quote table
   *
   * @return
   */
  public List<Quote> findAllQuotes() {
    return quoteDao.findAll();
  }


}
