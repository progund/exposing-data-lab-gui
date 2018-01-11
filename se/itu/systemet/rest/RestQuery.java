package se.itu.systemet.rest;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * An implementation of Query which represents the GET parameters
 * for a query call to a REST API with key-value pairs separated
 * by ampersand.
 */
public class RestQuery implements Query {

  List<Param> params;
  Map<String, String> keyToValue;
  // No instantiation outside this package, please
  RestQuery() {
    params = new ArrayList<>();
    keyToValue = new TreeMap<>();
  }

  /**
   * Adds a Param to this Query.
   * @param param The Param to add to this Query.
   */
  public void addParam(Param param){
    params.add(param);
    keyToValue.put(param.key(), param.value());
  }

  /**
   * Returns a query string as a java.lang.String from this Query.
   * @return A query string as a String from this Query
   * on the format
   *<pre>key1=value1&key2=value2&key3=value3</pre>
   */
  public String toQueryString() {
    StringBuilder queryString = new StringBuilder();
    Iterator<Param> iterator = params.iterator();
    while (iterator.hasNext()) {
      queryString.append(iterator.next());
      if (iterator.hasNext()) {
        queryString.append("&");
      }
    }
    return queryString.toString();
  }

  /**
   * Returns the value connected to the specified key, as a String.
   * Could be used for testing and debugging.
   * @param key The key whose value to get.
   * @return The value connected to the specified key.
   */
  public String get(String key) {
    return keyToValue.get(key);
  }
}
