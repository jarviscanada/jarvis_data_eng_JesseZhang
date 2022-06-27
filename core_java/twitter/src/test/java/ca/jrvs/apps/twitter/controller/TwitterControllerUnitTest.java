package ca.jrvs.apps.twitter.controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.TwitterService;
import ca.jrvs.apps.twitter.util.TweetUtil;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterControllerUnitTest {

  @Mock
  TwitterService service;

  @InjectMocks
  TwitterController controller;


  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void postTweet() {
    when(service.postTweet(any())).thenReturn(new Tweet());
    Tweet tweet = service.postTweet(TweetUtil.buildTweet("test", 50.0, 0.0));
    assertNotNull(tweet);
  }

  @Test
  public void showTweet() {
    when(service.showTweet(any(), any())).thenReturn(new Tweet());
    Tweet tweet = service.showTweet("210462857140252672", null);
    assertNotNull(tweet);
  }

  @Test
  public void deleteTweet() {
    when(service.deleteTweets(any())).thenReturn(new ArrayList<>());
    List<Tweet> tweets = service.deleteTweets(
        new String[]{"210462857140252672", "1539018180282261517"});
    assertNotNull(tweets);
  }
}