package ca.jrvs.apps.twitter.example;

import com.google.gdata.util.common.base.PercentEscaper;
import java.io.IOException;
import java.util.Arrays;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class TwitterApiTest {

  private static String CONSUMER_KEY = System.getenv("consumerKey");
  private static String CONSUMER_SECRET = System.getenv("consumerSecret");
  private static String ACCESS_TOKEN = System.getenv("accessToken");
  private static String TOKEN_SECRET = System.getenv("tokenSecret");


  public static void main(String[] args)
      throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException, IOException {

//    Map<String, String> env = System.getenv();
//
//    // Java 8
//    env.forEach((k, v) -> System.out.println(k + ":" + v));

    //setup oauth
    OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
    consumer.setTokenWithSecret(ACCESS_TOKEN, TOKEN_SECRET);

    //create an HTTP GET request
    String status = "today is a good day";
    PercentEscaper percentEscaper = new PercentEscaper("", false);
    HttpPost request = new HttpPost("https://api.twitter.com/2/tweets");

    String json = "{\"text\":\"" + status + "\"}";
    StringEntity requestEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
    request.setEntity(requestEntity);

    //sign the request (add headers)
    consumer.sign(request);

    System.out.println("Http Request Headers:");
    Arrays.stream(request.getAllHeaders()).forEach(System.out::println);

    //send the request
    HttpClient httpClient = HttpClientBuilder.create().build();
    HttpResponse response = httpClient.execute(request);
    System.out.println(EntityUtils.toString(response.getEntity()));


  }


}
