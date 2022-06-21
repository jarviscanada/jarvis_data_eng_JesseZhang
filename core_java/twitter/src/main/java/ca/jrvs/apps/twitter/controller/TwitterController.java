package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import java.util.List;

public class TwitterController implements Controller {

  private static final String COORD_SEP = ":";
  private static final String COMMA = ",";

  private Service service;

  @Override
  public Tweet postTweet(String[] args) {

    Tweet tweet = new Tweet();
//    tweet.setText();
    service.postTweet(tweet);

    return null;
  }

  @Override
  public Tweet showTweet(String[] args) {
    return null;
  }

  @Override
  public List<Tweet> deleteTweet(String[] args) {
    return null;
  }
}
