package ca.jrvs.apps.twitter.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import ca.jrvs.apps.twitter.dao.HttpHelper;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import ca.jrvs.apps.twitter.util.TweetUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;

public class TwitterServiceIntTest {


  private TwitterService service;

  @Before
  public void setUp() throws Exception {
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");
    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken,
        tokenSecret);
    TwitterDao dao = new TwitterDao(httpHelper);
    this.service = new TwitterService(dao);
  }

  @Test
  public void postValidTweet() throws JsonProcessingException {
    String hashTag = "#abc";
    String text =
        "create a tweet from service " + hashTag + " " + System.currentTimeMillis();
    Double lon = -1d, lat = 1d;
    Tweet postTweet = TweetUtil.buildTweet(text, lon, lat);
    System.out.println(JsonUtil.toJson(postTweet, false, false));
    Tweet tweet = service.postTweet(postTweet);

    System.out.println(JsonUtil.toJson(tweet, false, false));

    assertEquals(text, tweet.getText());
    assertTrue(hashTag.contains(tweet.getEntities().getHashtags()[0].getText()));
  }

  @Test(expected = IllegalArgumentException.class)
  public void postInvalidTweet() throws JsonProcessingException {
    String hashTag = "#abc";
    String text =
        "create a tweet from service but the text exceeds 140 characters "
            + "create a tweet from service but the text exceeds 140 characters "
            + "create a tweet from service but the text exceeds 140 characters "
            + "create a tweet from service but the text exceeds 140 characters "
            + "create a tweet from service but the text exceeds 140 characters "
            + "create a tweet from service but the text exceeds 140 characters "
            + "create a tweet from service but the text exceeds 140 characters" + hashTag + " "
            + System.currentTimeMillis();
    Double lon = -1d, lat = 1d;
    Tweet postTweet = TweetUtil.buildTweet(text, lon, lat);
    System.out.println(JsonUtil.toJson(postTweet, false, false));
    Tweet tweet = service.postTweet(postTweet);
  }


  @Test(expected = IllegalArgumentException.class)
  public void postInvalidCoorTweet() throws JsonProcessingException {
    String hashTag = "#abc";
    String text =
        "create a tweet from service " + hashTag + " " + System.currentTimeMillis();
    Double lon = -181d, lat = 1d;
    Tweet postTweet = TweetUtil.buildTweet(text, lon, lat);
    System.out.println(JsonUtil.toJson(postTweet, false, false));
    Tweet tweet = service.postTweet(postTweet);

    System.out.println(JsonUtil.toJson(tweet, false, false));

    assertEquals(text, tweet.getText());
    assertTrue(hashTag.contains(tweet.getEntities().getHashtags()[0].getText()));
  }


  @Test
  public void showTweet() throws JsonProcessingException {
    Tweet tweet = service.showTweet("210462857140252672", new String[]{"favorited"});
    System.out.println(JsonUtil.toJson(tweet, false, false));
    assertFalse(tweet.getFavorited());
  }

  @Test(expected = IllegalArgumentException.class)
  public void showInvalidIDTweet() throws JsonProcessingException {
    Tweet tweet = service.showTweet("2104628571402526724123412341234", null);
    System.out.println(JsonUtil.toJson(tweet, false, false));
    assertFalse(tweet.getFavorited());
  }

  @Test(expected = IllegalArgumentException.class)
  public void showInvalidFieldTweet() throws JsonProcessingException {
    Tweet tweet = service.showTweet("2104628571402526724123412341234", new String[]{"invalid"});
    System.out.println(JsonUtil.toJson(tweet, false, false));
    assertFalse(tweet.getFavorited());
  }

//  @Test
//  public void deleteTweets() throws JsonProcessingException {
//    List<Tweet> tweets = service.deleteTweets(new String[]{"1537768655412396032", "1537767407481237504"});
//    System.out.println(JsonUtil.toJson(tweets.get(0), false, false));
//    System.out.println(JsonUtil.toJson(tweets.get(1), false, false));
//    assertFalse(tweets.get(0).getFavorited());
//    assertFalse(tweets.get(1).getFavorited());
//  }

}