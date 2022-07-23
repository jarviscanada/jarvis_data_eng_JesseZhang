package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * MarketDataDao is responsible for getting Quotes from IEX
 */
@Repository
public class MarketDataDao implements CrudRepository<IexQuote, String> {


  private static final String IEX_BATCH_PATH = "/stock/market/batch?symbols=%s&types=quote&token=";
  private final String IEX_BATCH_URL;

  private Logger logger = LoggerFactory.getLogger(MarketDataDao.class);
  private HttpClientConnectionManager httpClientConnectionManager;

  @Autowired
  public MarketDataDao(HttpClientConnectionManager httpClientConnectionManager,
      MarketDataConfig marketDataConfig) {
    this.httpClientConnectionManager = httpClientConnectionManager;
    IEX_BATCH_URL = marketDataConfig.getHost() + IEX_BATCH_PATH + marketDataConfig.getToken();
  }


  /**
   * Get an IexQuote (helper method which class findAllById)
   *
   * @param ticker must not be {@literal null}.
   * @return IexQuote object
   * @throws IllegalArgumentException      if a given ticker is invalid
   * @throws DataRetrievalFailureException if HTTP request failed
   */
  @Override
  public Optional<IexQuote> findById(String ticker) {
    Optional<IexQuote> iexQuote;
    List<IexQuote> quotes = findAllById(Collections.singletonList(ticker));

    if (quotes.size() == 0) {
      return Optional.empty();
    } else if (quotes.size() == 1) {
      iexQuote = Optional.of(quotes.get(0));
    } else {
      throw new DataRetrievalFailureException("Unexpected number of quotes");
    }
    return iexQuote;
  }


  /**
   * Get quotes from IEX
   *
   * @param tickers is a list of tickers
   * @return a list of IexQuote object
   * @throws IllegalArgumentException      if any ticker is invalid or tickers is empty
   * @throws DataRetrievalFailureException if HTTP request failed
   */
  @Override
  public List<IexQuote> findAllById(Iterable<String> tickers) {
    List<IexQuote> quotes = new ArrayList<>();
    String symbols = String.join(",", tickers);
    String uri = String.format(IEX_BATCH_URL, symbols);

    //HTTP response
    String response = executeHttpGet(uri)
        .orElseThrow(() -> new IllegalArgumentException("Invalid ticker"));

    //Array of JSON documents
    JSONObject IexQuotesJson = new JSONObject(response);

    //Get number of documents
    if (IexQuotesJson.length() == 0) {
      throw new IllegalArgumentException("Invalid ticker");
    }

    logger.debug(IexQuotesJson.toString());
    logger.debug(String.valueOf(IexQuotesJson.length()));

    ObjectMapper m = new ObjectMapper();
    try {
      for (String key : IexQuotesJson.keySet()) {
        JSONObject quote = IexQuotesJson.getJSONObject(key).getJSONObject("quote");
        System.out.println(quote);
        quotes.add(m.readValue(quote.toString(), IexQuote.class));
      }

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    //IexQuotesJson.toMap().values().forEach(e->m.readValue(e, IexQuote.class));
    return quotes;
  }

  /**
   * Execute a get and return http entity/body as a string
   *
   * @param url resource URL
   * @return http response body or Optional.empty for 404 response
   * @throws DataRetrievalFailureException if HTTP failed or status code is unexpected
   */
  private Optional<String> executeHttpGet(String url) {

    CloseableHttpClient httpClient = getHttpClient();
    HttpGet request = new HttpGet(url);
    try {
      CloseableHttpResponse response = httpClient.execute(request);
      String response_body_json = EntityUtils.toString(response.getEntity());
      return Optional.of(response_body_json);
    } catch (IOException e) {
      logger.debug("Failed to execute Get");
      throw new RuntimeException("Failed to execute Get", e);
    }

  }


  /**
   * Borrow an HTTP client from the httpClientConnectionManager
   *
   * @return a httpClient
   */
  private CloseableHttpClient getHttpClient() {
    return HttpClients.custom().setConnectionManager(httpClientConnectionManager)
        //prevent connectionManager shutdown when calling httpClient.close()
        .setConnectionManagerShared(true).build();
  }


  //Unimplemented methods
  @Override
  public boolean existsById(String s) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public Iterable<IexQuote> findAll() {
    throw new UnsupportedOperationException("Not implemented");
  }


  @Override
  public long count() {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void deleteById(String s) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void delete(IexQuote entity) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void deleteAll(Iterable<? extends IexQuote> entities) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void deleteAll() {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public <S extends IexQuote> S save(S entity) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public <S extends IexQuote> Iterable<S> saveAll(Iterable<S> entities) {
    throw new UnsupportedOperationException("Not implemented");
  }

}
