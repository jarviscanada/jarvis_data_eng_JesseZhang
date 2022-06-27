package ca.jrvs.apps.twitter.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import ca.jrvs.apps.twitter.dao.HttpHelper;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.TwitterService;
import ca.jrvs.apps.twitter.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;

public class TwitterControllerIntTest {

  private TwitterController controller;

  @Before
  public void setUp() throws Exception {
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");
    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken,
        tokenSecret);
    TwitterDao dao = new TwitterDao(httpHelper);
    TwitterService service = new TwitterService(dao);
    this.controller = new TwitterController(service);
  }

  @Test
  public void postTweet() throws JsonProcessingException {
    String hashTag = "#abc";
    String text =
        "create a tweet from controller " + hashTag + " " + System.currentTimeMillis();
//    String text ="";
    String lat_lon = "43:79";
    String[] args = new String[]{"post", text, lat_lon};
    Tweet tweet = controller.postTweet(args);
    System.out.println(JsonUtil.toJson(tweet, false, false));
    assertEquals(text, tweet.getText());
    assertTrue(hashTag.contains(tweet.getEntities().getHashtags()[0].getText()));
  }

  @Test
  public void showTweet() throws JsonProcessingException {
    String[] args = new String[]{"show", "210462857140252672"};
    Tweet tweet = controller.showTweet(args);
    System.out.println(JsonUtil.toJson(tweet, false, false));
    assertFalse(tweet.getFavorited());
  }

//  @Test
//  public void deleteTweet() throws JsonProcessingException {
//    String [] args = new String   []{"delete", "1539666429951229952"};
//    List<Tweet> tweets = controller.deleteTweet(args);
//    System.out.println(JsonUtil.toJson(tweets.get(0), false, false));
//    assertFalse(tweets.get(0).getFavorited());
//  }
}