package org.design.rules4Java.engine.engine.engineModel;

/**
 * @author pelado
 */
public interface Suggest{
  boolean getApply();
  void setApply(boolean newState);
  String getSuggest();
  void setSuggest(String value);
  QueryResult getResult();
  void setResult(QueryResult value);
}