package ca.jrvs.apps.twitter.dao;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import junit.framework.TestCase;
import org.apache.http.HttpResponse;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class TwitterHttpHelperTest extends TestCase {

  String consumerKey;
  String consumerSecret;
  String accessToken;
  String tokenSecret;
  HttpHelper httpHelper;


  public void setup() {
    consumerKey = System.getenv("consumerKey");
    consumerSecret = System.getenv("consumerSecret");
    accessToken = System.getenv("accessToken");
    tokenSecret = System.getenv("tokenSecret");
    httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken,
        tokenSecret);
  }

  @Test
  public void testHttpPost() throws URISyntaxException, IOException {
    setup();
    HttpResponse response = httpHelper.httpPost(new URI("https://api.twitter.com/2/tweets"),
        new StringEntity("{\"text\":\"test from main again\"}", ContentType.APPLICATION_JSON));
    System.out.println(EntityUtils.toString(response.getEntity()));
  }

  @Test
  public void testHttpGet() throws IOException, URISyntaxException {
    setup();
    HttpResponse response2 = httpHelper.httpGet(
        new URI("https://api.twitter.com/2/users/44196397/tweets"));
    System.out.println(EntityUtils.toString(response2.getEntity()));
  }


}