package se.itu.systemet.rest;

import java.util.List;
import se.itu.systemet.domain.Product;

/**
 * Represents an object for accessing an API.
 */
public interface ApiAccess {
  /**
   * Returns a List&lt;Product&gt; using a Query.
   * @param query The Query used for fetching Products from the API.
   * @return A List&lt;Product&gt; using the specified Query.
   */
  public List<Product> fetch(Query query);

}
