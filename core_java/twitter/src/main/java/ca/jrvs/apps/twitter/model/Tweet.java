package ca.jrvs.apps.twitter.model;

import java.util.Date;

public class Tweet {

  //https://developer.twitter.com/en/docs/twitter-api/data-dictionary/object-model/tweet
  private Date created_at;

  private long id;

  private String id_str;

  private String text;
  private  Entities entities;
  private Geo geo;
  private int retweet_count;
  private Integer like_count;
  private Boolean possibly_sensitive;


}
