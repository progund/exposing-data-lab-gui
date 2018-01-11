package se.itu.systemet.rest;

/**
 * <p>Creates an implementation of a Query, to be used with an ApiAccess implementation.</p>
 * <p>Typical use:
 *<pre>
 * Query query = QueryFactory.getQuery();
 * query.addParam(new Param("min_price", "100"));
 *</pre>
 * </p>
 */
public class QueryFactory {

  /**
   * Creates an implementation of Query.
   * @return An implementation of Query.
   */
  public static Query getQuery() {
    return new RestQuery();
  }
}
