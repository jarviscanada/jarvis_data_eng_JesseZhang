package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

public class TwitterDao<T, ID> implements CrdDao<T, ID> {


  //URI constants
  private static final String API_BASE_URI = "https://api.twitter.com";
  private static final String POST_PATH = "/1.1/statuses/update.json";
  private static final String SHOW_PATH = "/1.1/statuses/show.json";
  private static final String DELETE_PATH = "/1.1/statuses/destroy";

  //URI symbols
  private static final String QUERY_SYM = "?";
  private static final String AMPERSAND = "&";
  private static final String EQUAL = "=";

  //Response code
  private static final int HTTP_OK = 200;

  private HttpHelper httpHelper;


  public TwitterDao(HttpHelper httpHelper) {
    this.httpHelper = httpHelper;
  }

  /**
   * Create an entity(Tweet) to the underlying storage
   *
   * @param tweet entity that to be created
   * @return created entity
   */
  @Override
  public Tweet create(Tweet tweet) {
    //Construct URI
    URI uri;
    try {
      uri = getPostUri(tweet);
    } catch (URISyntaxException e) {
      throw new IllegalArgumentException("Invalid tweet input", e);
    }

    //Execute HTTP Request
    HttpResponse response = httpHelper.httpPost(uri);

    //Validate response and deserialize response to Tweet object
    return parseResponseBody(response, HTTP_OK);

  }

  Tweet parseResponseBody(HttpResponse response, Integer expectedStatusCode) {
    Tweet tweet = null;

    //Check response status
    int status = response.getStatusLine().getStatusCode();
    if (status != expectedStatusCode) {
      try {
        System.out.println(EntityUtils.toString(response.getEntity()));
      } catch (IOException e) {
        System.out.println("Response has no entity");
      }
      throw new RuntimeException("Unexpected HTTP status:" + status);
    }

    //Convert response entity to string
    String jsonStr;
    try {
      jsonStr = EntityUtils.toString(response.getEntity());
    } catch (IOException e) {
      throw new RuntimeException("Failed to convert entity to String", e);
    }

    //Deserialize JSON string to Tweet object
    try {
      tweet = JsonUtil.toObjectFromJson(jsonStr, Tweet.class);
    } catch (IOException e) {
      throw new RuntimeException("Unable to convert JSON string to Object", e);
    }
    return tweet;
  }

  private URI getPostUri(Tweet tweet) throws URISyntaxException {
    return new URI(API_BASE_URI + POST_PATH + QUERY_SYM + "status" + EQUAL + URLEncoder.encode(
        tweet.getText()) + AMPERSAND + "long=" + tweet.getCoordinates().getCoordinates()[0]
        + AMPERSAND + "lat=" + tweet.getCoordinates().getCoordinates()[1]);
  }


  /**
   * Find an entity(Tweet) by its id
   *
   * @param id entity id
   * @return Tweet entity
   */
  @Override
  public Tweet findById(ID id) {
    //Construct URI
    URI uri;
    try {
      uri = getGetUri(id);
    } catch (URISyntaxException e) {
      throw new IllegalArgumentException("Invalid tweet input", e);
    }

    //Execute HTTP Request
    HttpResponse response = httpHelper.httpGet(uri);

    //Validate response and deserialize response to Tweet object
    return parseResponseBody(response, HTTP_OK);
  }

  private URI getGetUri(ID id) throws URISyntaxException {
    return new URI(API_BASE_URI + SHOW_PATH + QUERY_SYM + "id" + EQUAL + id);
  }

  /**
   * Delete an entity(Tweet) by its ID
   *
   * @param id of the entity to be deleted
   * @return deleted entity
   */
  @Override
  public Tweet deleteById(ID id) {
    //Construct URI
    URI uri;
    try {
      uri = getDeleteUri(id);
    } catch (URISyntaxException e) {
      throw new IllegalArgumentException("Invalid tweet input", e);
    }

    //Execute HTTP Request
    HttpResponse response = httpHelper.httpPost(uri);

    //Validate response and deserialize response to Tweet object
    return parseResponseBody(response, HTTP_OK);
  }

  private URI getDeleteUri(ID id) throws URISyntaxException {
    return new URI(API_BASE_URI + DELETE_PATH + "/" + id + ".json");
  }


}
