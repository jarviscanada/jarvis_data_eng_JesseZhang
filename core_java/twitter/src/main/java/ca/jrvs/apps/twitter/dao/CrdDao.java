package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.model.Tweet;

public interface CrdDao<T, ID> {

  /**
   * Create an entity(Tweet) to the underlying storage
   *
   * @param tweet entity that to be created
   * @return created entity
   */
  Tweet create(Tweet tweet);

  /**
   * Find an entity(Tweet) by its id
   *
   * @param id entity id
   * @return Tweet entity
   */
  Tweet findById(long id);

  /**
   * Delete an entity(Tweet) by its ID
   *
   * @param id of the entity to be deleted
   * @return deleted entity
   */
  Tweet deleteById(long id);
}
