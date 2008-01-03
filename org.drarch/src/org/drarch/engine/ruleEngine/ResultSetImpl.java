package org.drarch.engine.ruleEngine;

import tyRuBa.engine.Frame;
import tyRuBa.util.ElementSource;

public class ResultSetImpl implements ResultSet {

  protected ElementSource queryResult;

  protected ResultSetImpl() {
  }

  public boolean hasMoreElements() {
    return queryResult.hasMoreElements();
  }

  public QueryResult next() {
    QueryResult nextResult = EngineFactory.createQueryResult();
    
    ((QueryResultImpl)nextResult).setFrame((Frame) queryResult.nextElement());
    return nextResult;
  }

  public ElementSource getQueryResult() {
    return queryResult;
  }

  public void setQueryResult(ElementSource queryResult) {
    this.queryResult = queryResult;
  }
}
