package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.model.Tweet;
import java.net.URI;

public class TwitterDao<T, ID> implements CrdDao<T, ID> {


  //URI constants
  private static final String API_BASE_URI = "https://api.twitter.com";
  private static final String PATH_URI = "/2/tweets";

  //Response code
  private static final int HTTP_OK = 200;

  private HttpHelper httpHelper;


  /**
   * Create an entity(Tweet) to the underlying storage
   *
   * @param entity entity that to be created
   * @return created entity
   */
  @Override
  public T create(T entity) {

    URI uri;
return null;

  }


  /**
   * Find an entity(Tweet) by its id
   *
   * @param id entity id
   * @return Tweet entity
   */
  @Override
  public T findById(ID id) {
    return null;
  }

  /**
   * Delete an entity(Tweet) by its ID
   *
   * @param id of the entity to be deleted
   * @return deleted entity
   */
  @Override
  public T deleteById(ID id) {
    return null;
  }
}
