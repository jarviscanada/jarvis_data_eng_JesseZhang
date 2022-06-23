package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.util.TweetUtil;
import java.util.List;

public class TwitterController implements Controller {

  private static final String COORD_SEP = ":";
  private static final String COMMA = ",";

  private Service service;

  public TwitterController(Service service) {
    this.service = service;
  }

  @Override
  public Tweet postTweet(String[] args) {

    if (args.length != 3 || args[1].isEmpty()) {
      throw new IllegalArgumentException(
          "USAGE: TwitterCLIApp post \"tweet_text\" \"latitude:longitude\"");
    }

    String text = args[1];
    String[] coordinates_array = args[2].split(COORD_SEP);
    if (coordinates_array.length != 2) {
      throw new IllegalArgumentException("Invalid location format\n"
          + "USAGE: TwitterCLIApp post \"tweet_text\" \"latitude:longitude\"");
    }
    Double lat;
    Double lon;
    try {
      lat = Double.valueOf(coordinates_array[0]);
      lon = Double.valueOf(coordinates_array[1]);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid location format\n"
          + "USAGE: TwitterCLIApp post \"tweet_text\" \"latitude:longitude\"");
    }
    Tweet tweet = TweetUtil.buildTweet(text, lon, lat);

    return service.postTweet(tweet);
  }

  @Override
  public Tweet showTweet(String[] args) {

    if (args.length != 3 && args.length != 2) {
      throw new IllegalArgumentException(
          "USAGE: TwitterCLIApp post \"tweet_text\" \"latitude:longitude\"");
    }
    String id_str = args[1];
    String[] fields = null;
    if (args.length == 3) {
      fields = args[2].split(COMMA);
    }
    return service.showTweet(id_str, fields);
  }

  @Override
  public List<Tweet> deleteTweet(String[] args) {
    if (args.length != 2) {
      throw new IllegalArgumentException(
          "USAGE: TwitterCLIApp post \"tweet_text\" \"latitude:longitude\"");
    }
    String[] ids = args[1].split(COMMA);
    return service.deleteTweets(ids);
  }
}
