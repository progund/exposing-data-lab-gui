package se.itu.systemet.rest;

public interface Query {
  public void addParam(Param param);
  public String toQueryString();
  public String get(String key);
}
