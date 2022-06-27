package ca.jrvs.apps.twitter.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import ca.jrvs.apps.twitter.util.TweetUtil;
import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterDaoUnitTest {


  @Mock
  HttpHelper mockHelper;

  @InjectMocks
  TwitterDao dao;

  @Test
  public void postTweet() throws IOException {
    //test failed request
    String hashTag = "#abc";
    String text = "@someone sometext " + hashTag + " " + System.currentTimeMillis();
    Double lon = -1d;
    Double lat = 1d;
    //exception is expected here
    when(mockHelper.httpPost(isNotNull())).thenThrow(new RuntimeException("mock"));
    try {
      dao.create(TweetUtil.buildTweet(text, lon, lat));
      fail();
    } catch (RuntimeException e) {
      assertTrue(true);
    }

    //Test correct case
    String tweetJsonStr = "{\n"
        + " \"created_at\":\"Mon Feb 18 21:24:39 +0000 2019\",\n"
        + " \"id\":1097607853932564480,\n"
        + " \"id_str\":\"1097607853932564480\",\n"
        + " \"text\":\"test with loc223\",\n"
        + " \"entities\":{\n"
        + "     \"hashtags\":[],"
        + "     \"user_mentions\":[]"
        + "  },\n"
        + "  \"coordinates\":null,"
        + "  \"retweet_count\":0,\n"
        + "  \"favorite_count\":0,\n"
        + "  \"favorited\":false,\n"
        + "  \"retweeted\":false\n"
        + "}";

    when(mockHelper.httpPost(isNotNull())).thenReturn(null);
    TwitterDao spyDao = Mockito.spy(dao);
    Tweet expectedTweet = JsonUtil.toObjectFromJson(tweetJsonStr, Tweet.class);
    //mock parseResponseBody
    doReturn(expectedTweet).when(spyDao).parseResponseBody(any(), anyInt());
    Tweet tweet = spyDao.create(TweetUtil.buildTweet(text, lon, lat));
    assertNotNull(tweet);
    assertNotNull(tweet.getText());
  }

  @Test
  public void showTweet() throws IOException {
    //test failed request
    long id = 210462857140252672L;
    //exception is expected here
    when(mockHelper.httpGet(isNotNull())).thenThrow(new RuntimeException("mock"));
    try {
      dao.findById(id);
      fail();
    } catch (RuntimeException e) {
      assertTrue(true);
    }

    //Test correct case
    String tweetJsonStr = "{\n"
        + " \"created_at\":\"Mon Feb 18 21:24:39 +0000 2019\",\n"
        + " \"id\":1097607853932564480,\n"
        + " \"id_str\":\"1097607853932564480\",\n"
        + " \"text\":\"test with loc223\",\n"
        + " \"entities\":{\n"
        + "     \"hashtags\":[],"
        + "     \"user_mentions\":[]"
        + "  },\n"
        + "  \"coordinates\":null,"
        + "  \"retweet_count\":0,\n"
        + "  \"favorite_count\":0,\n"
        + "  \"favorited\":false,\n"
        + "  \"retweeted\":false\n"
        + "}";

    when(mockHelper.httpGet(isNotNull())).thenReturn(null);
    TwitterDao spyDao = Mockito.spy(dao);
    Tweet expectedTweet = JsonUtil.toObjectFromJson(tweetJsonStr, Tweet.class);
    //mock parseResponseBody
    doReturn(expectedTweet).when(spyDao).parseResponseBody(any(), anyInt());
    Tweet tweet = spyDao.findById(id);
    assertNotNull(tweet);
    assertNotNull(tweet.getText());
  }

  @Test
  public void deleteTweet() throws IOException {
    //test failed request
    long id = 210462857140252672L;
    //exception is expected here
    when(mockHelper.httpPost(isNotNull())).thenThrow(new RuntimeException("mock"));
    try {
      dao.deleteById(id);
      fail();
    } catch (RuntimeException e) {
      assertTrue(true);
    }

    //Test correct case
    String tweetJsonStr = "{\n"
        + " \"created_at\":\"Mon Feb 18 21:24:39 +0000 2019\",\n"
        + " \"id\":1097607853932564480,\n"
        + " \"id_str\":\"1097607853932564480\",\n"
        + " \"text\":\"test with loc223\",\n"
        + " \"entities\":{\n"
        + "     \"hashtags\":[],"
        + "     \"user_mentions\":[]"
        + "  },\n"
        + "  \"coordinates\":null,"
        + "  \"retweet_count\":0,\n"
        + "  \"favorite_count\":0,\n"
        + "  \"favorited\":false,\n"
        + "  \"retweeted\":false\n"
        + "}";

    when(mockHelper.httpPost(isNotNull())).thenReturn(null);
    TwitterDao spyDao = Mockito.spy(dao);
    Tweet expectedTweet = JsonUtil.toObjectFromJson(tweetJsonStr, Tweet.class);
    //mock parseResponseBody
    doReturn(expectedTweet).when(spyDao).parseResponseBody(any(), anyInt());
    Tweet tweet = spyDao.deleteById(id);
    assertNotNull(tweet);
    assertNotNull(tweet.getText());
  }
}