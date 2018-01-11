package se.itu.systemet.rest;

/**
 * A Factory class for creating an ApiAccess object.
 */
public class ApiAccessFactory {
  /**
   * Returns an implementation of ApiAccess.
   * @return An implementation of ApiAccess.
   */
  public static ApiAccess getApiAccess() {
    return new FakeApiAccess();
  }
}
