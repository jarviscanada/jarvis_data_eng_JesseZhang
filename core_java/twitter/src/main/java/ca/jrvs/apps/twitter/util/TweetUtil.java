package ca.jrvs.apps.twitter.util;

import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;

public class TweetUtil {

  public static Tweet buildTweet(String text, Double lon, Double lat) {
    Tweet tweet = new Tweet();
    Coordinates coordinates = new Coordinates();
    tweet.setCoordinates(coordinates);
    tweet.setText(text);
    coordinates.setCoordinates(new Double[]{lon, lat});
    coordinates.setType("Point");

    return tweet;
  }

}
