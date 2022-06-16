package ca.jrvs.apps.twitter.dao;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class TwitterHttpHelper implements HttpHelper {

  /**
   * Dependencies are specified as private member variables
   */
  private OAuthConsumer consumer;
  private HttpClient httpClient;

  /**
   * Constructor
   * Setup dependencies using secrets
   * @param consumerKey
   * @param consumerSecret
   * @param accessToken
   * @param tokenSecret
   */
  public TwitterHttpHelper(String consumerKey, String consumerSecret, String accessToken, String tokenSecret) {
    consumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecret);
    consumer.setTokenWithSecret(accessToken, tokenSecret);
    httpClient = new DefaultHttpClient();
  }

  /**
   * Execute a HTTP Post call
   *
   * @param uri
   * @Param stringEntity
   * @return
   */
  @Override
  public HttpResponse httpPost(URI uri) {
    HttpPost request = new HttpPost(uri);
    try {
      consumer.sign(request);
      return httpClient.execute(request);
    } catch (OAuthException | IOException e) {
      throw new RuntimeException("Failed to execute POST", e);
    }
  }

  /**
   * Execute a HTTP Get call
   *
   * @param uri
   * @return
   */
  @Override
  public HttpResponse httpGet(URI uri) {
    HttpGet request = new HttpGet(uri);
    try {
      consumer.sign(request);
      return httpClient.execute(request);
    } catch (OAuthException | IOException e) {
      throw new RuntimeException("Failed to execute GET", e);
    }
  }


  public static void main(String[] args) throws URISyntaxException, IOException {
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");

    //Create components
    HttpHelper httpHelper= new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret  );
    HttpResponse response = httpHelper.httpPost(new URI("https://api.twitter.com/1.1/statuses/update.json?status=test_for_v1.1"));
    System.out.println(EntityUtils.toString(response.getEntity()));
    HttpResponse response2 = httpHelper.httpGet(new URI("https://api.twitter.com/1.1/statuses/show.json?id=210462857140252672"));
    System.out.println(EntityUtils.toString(response2.getEntity()));
  }
}
