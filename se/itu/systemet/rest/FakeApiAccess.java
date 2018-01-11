package se.itu.systemet.rest;

import java.util.List;
import java.util.ArrayList;
import se.itu.systemet.domain.Product;

public class FakeApiAccess implements ApiAccess {
  public List<Product> fetch(Query query) {
    return new ArrayList<Product>();
  }
}
