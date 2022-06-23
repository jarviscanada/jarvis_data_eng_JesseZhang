package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Tweet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TwitterService implements Service {


  private CrdDao dao;

  //@Autowired
  public TwitterService(CrdDao dao) {
    this.dao = dao;
  }

  @Override
  public Tweet postTweet(Tweet tweet) {

    //check text length, lon/lat
    validatePostTweet(tweet);

    //create tweet via dao
    return dao.create(tweet);
  }

  private void validatePostTweet(Tweet tweet) {

    if (tweet == null) {
      throw new IllegalArgumentException("invalid tweet");
    }
    if (tweet.getText().length() > 140) {
      throw new IllegalArgumentException("the tweet text is more than 140 characters");
    }

    double lon = tweet.getCoordinates().getCoordinates()[0];
    double lat = tweet.getCoordinates().getCoordinates()[1];
    if (lon < -180.0 || lon > 180.0 || lat < -90.0 || lat > 90.0) {
      throw new IllegalArgumentException("longitude or latitude in the tweet is out of range");
    }


  }

  @Override
  public Tweet showTweet(String id, String[] fields) {

    //check id and fields
    validateId(id);
    if (fields != null) {
      validateFields(fields);
    }

    //show the tweet via dao
    Tweet tweet = dao.findById(id);

    //set fields not in the list to null
    return processTweetResponse(tweet, fields);
  }


  private void validateFields(String[] fields) {
    if (fields != null) {
      Set<String> field_words = new HashSet<>(Arrays.asList("created_at", "id",
          "id_str", "text", "entities", "coordinates", "retweet_count", "favorite_count",
          "favorited", "retweeted"));
      for (String s : fields) {
        if (!field_words.contains(s)) {
          throw new IllegalArgumentException("filed(s) invalid");
        }
      }
    }
  }

  private Tweet processTweetResponse(Tweet tweet, String[] fields) {
    Tweet tweet_template = new Tweet();
    if (fields != null) {
      for (String field : fields) {
        switch (field) {
          case "created_at":
            tweet_template.setCreated_at(tweet.getCreated_at());
            break;
          case "id":
            tweet_template.setId(tweet.getId());
            break;
          case "id_str":
            tweet_template.setId_str(tweet.getId_str());
            break;
          case "text":
            tweet_template.setText(tweet.getText());
            break;
          case "entities":
            tweet_template.setEntities(tweet.getEntities());
            break;
          case "coordinates":
            tweet_template.setCoordinates(tweet.getCoordinates());
            break;
          case "retweet_count":
            tweet_template.setRetweet_count(tweet.getRetweet_count());
            break;
          case "favorite_count":
            tweet_template.setFavorite_count(tweet.getFavorite_count());
            break;
          case "favorited":
            tweet_template.setFavorited(tweet.getFavorited());
            break;
          case "retweeted":
            tweet_template.setRetweeted(tweet.getRetweeted());
            break;
        }
      }
      return tweet_template;
    } else {
      return tweet;
    }
  }

  private String validateFields2(String[] fields) {
    StringBuilder query_fields = new StringBuilder();
    if (fields != null) {
      Set<String> field_words = new HashSet<>(Arrays.asList("trim_user", "include_my_retweet",
          "include_entities", "include_ext_alt_text", "include_card_uri"));
      for (String s : fields) {
        if (!field_words.contains(s)) {
          throw new IllegalArgumentException("filed(s) invalid");
        } else {
          query_fields.append("&").append(s).append("=true");
        }
      }
      List<String> nulls = field_words.stream()
          .filter(word -> !Arrays.asList(fields).contains(word)).collect(Collectors.toList());
      nulls.forEach(field -> query_fields.append("&").append(field).append("=null"));
    }
    return query_fields.toString();
  }

  private void validateId(String id) {
    if (id.length() > 20) {
      throw new IllegalArgumentException("tweet id is invalid");
    }
    for (char c : id.toCharArray()) {
      if (!Character.isDigit(c)) {
        throw new IllegalArgumentException("tweet id is invalid");
      }
    }
  }

  @Override
  public List<Tweet> deleteTweets(String[] ids) {

    //check ids
    Arrays.stream(ids).forEach(this::validateId);

    //delete tweets
    List<Tweet> tweets = new ArrayList<>();
    for (String id : ids) {
      tweets.add(dao.deleteById(id));
    }

    return tweets;
  }
}
