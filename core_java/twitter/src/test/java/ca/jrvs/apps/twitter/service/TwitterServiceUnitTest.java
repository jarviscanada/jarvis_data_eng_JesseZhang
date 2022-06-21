package ca.jrvs.apps.twitter.service;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import ca.jrvs.apps.twitter.util.TweetUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterServiceUnitTest {

  @Mock
  CrdDao dao;

  @InjectMocks
  TwitterService service;

  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void postValidTweet() {
    when(dao.create(any())).thenReturn(new Tweet());
    Tweet tweet = service.postTweet(TweetUtil.buildTweet("test", 50.0, 0.0));
    assertNotNull(tweet);
  }


  @Test(expected = IllegalArgumentException.class)
  public void postInvalidCoorTweet() throws JsonProcessingException {
    String hashTag = "#abc";
    String text =
        "create a tweet from service " + hashTag + " " + System.currentTimeMillis();
    Double lon = -181d, lat = 1d;
    Tweet postTweet = TweetUtil.buildTweet(text, lon, lat);
    System.out.println(JsonUtil.toJson(postTweet, false, false));
    when(dao.create(any())).thenReturn(new Tweet());
    Tweet tweet = service.postTweet(postTweet);
  }

  @Test
  public void showValidTweet() {
    when(dao.findById(any())).thenReturn(new Tweet());
    Tweet tweet = service.showTweet("210462857140252672", null);
    assertNotNull(tweet);
  }

  @Test(expected = IllegalArgumentException.class)
  public void showInvalidTweet() {
    when(dao.findById(any())).thenReturn(new Tweet());
    service.showTweet("210462857140252672", new String[]{"invalid"});
  }

  @Test
  public void deleteValidTweets() {
    when(dao.deleteById(any())).thenReturn(new Tweet());
    List<Tweet> tweets  = service.deleteTweets(new String[]{"210462857140252672", "1539018180282261517"});
    assertNotNull(tweets);
  }

  @Test(expected = IllegalArgumentException.class)
  public void deleteInvalidTweets() {
    when(dao.deleteById(any())).thenReturn(new Tweet());
    List<Tweet> tweets  = service.deleteTweets(new String[]{"210462857140252672234235234523454", "1539018180282261517"});
  }
}