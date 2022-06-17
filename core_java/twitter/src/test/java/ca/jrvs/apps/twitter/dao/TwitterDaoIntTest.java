package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import ca.jrvs.apps.twitter.util.TweetUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class TwitterDaoIntTest extends TestCase {

  private TwitterDao dao;

  @Before
  public void setUp() {
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");
    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken,
        tokenSecret);
    this.dao = new TwitterDao(httpHelper);
  }

  @Test
  public void testCreate() throws JsonProcessingException {
    String hashTag = "#abc";
    String text =
        "create a tweet in integration test " + hashTag + " " + System.currentTimeMillis();
    float lon = -1f, lat = 1f;
    Tweet postTweet = TweetUtil.buildTweet(text, lon, lat);
    System.out.println(JsonUtil.toJson(postTweet, false, false));
    Tweet tweet = dao.create(postTweet);

    System.out.println(JsonUtil.toJson(tweet, false, false));

    assertEquals(text, tweet.getText());
    assertTrue(hashTag.contains(tweet.getEntities().getHashtags()[0].getText()));
    // https://stackoverflow.com/questions/58148675/coordinates-field-in-tweet-objects
//    assertNotNull(tweet.getCoordinates());
//    assertEquals(2, tweet.getCoordinates().getCoordinates().length);
//    assertEquals(lon, tweet.getCoordinates().getCoordinates()[0]);
//    assertEquals(lat, tweet.getCoordinates().getCoordinates()[1]);
  }

  @Test
  public void testFindById() throws JsonProcessingException {

    Tweet tweet = dao.findById(210462857140252672L);
    System.out.println(JsonUtil.toJson(tweet, false, false));
    assertFalse(tweet.getFavorited());
  }


  @Test
  public void testDeleteById() throws JsonProcessingException {

    Tweet tweet = dao.deleteById(1537770774051885057L);
    System.out.println(JsonUtil.toJson(tweet, false, false));
    assertFalse(tweet.getFavorited());
  }
}