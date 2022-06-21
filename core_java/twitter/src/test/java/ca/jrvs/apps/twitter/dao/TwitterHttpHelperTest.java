package ca.jrvs.apps.twitter.dao;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import junit.framework.TestCase;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;

public class TwitterHttpHelperTest extends TestCase {


  HttpHelper httpHelper;

  @Before
  public void setup() {
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");
    this.httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken,
        tokenSecret);
  }

  @Test
  public void testHttpPost() throws URISyntaxException, IOException {
    setup();
    HttpResponse response = httpHelper.httpPost(new URI("https://api.twitter.com/1.1/statuses/update.json?status=test_for_v1.1_again_from_Junit"));
    System.out.println(EntityUtils.toString(response.getEntity()));
  }

  @Test
  public void testHttpGet() throws IOException, URISyntaxException {
    setup();
    HttpResponse response2 = httpHelper.httpGet(
        new URI("https://api.twitter.com/1.1/statuses/show.json?id=1050118621198921728&include_entities=false"));
    System.out.println(EntityUtils.toString(response2.getEntity()));
  }


}