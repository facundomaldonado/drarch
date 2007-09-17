package org.design.rules4Java.engine.jqueryImpl.engineModel;

import org.design.rules4Java.engine.engine.engineModel.QueryResult;
import org.design.rules4Java.engine.engine.engineModel.ResultSet;

import tyRuBa.engine.Frame;
import tyRuBa.util.ElementSource;

public class ResultSetImpl implements ResultSet {

  protected ElementSource queryResult;

  protected ResultSetImpl() {
    super();
  }

  public boolean hasMoreElements() {
    return queryResult.hasMoreElements();
  }

  public QueryResult next() {
    QueryResult nextResult = createQueryResult();
    ((QueryResultImpl) nextResult).setFrame((Frame) queryResult.nextElement());
    return nextResult;
  }

  private QueryResult createQueryResult() {
    return new QueryResultImpl();
  }

  public ElementSource getQueryResult() {
    return queryResult;
  }

  public void setQueryResult(ElementSource queryResult) {
    this.queryResult = queryResult;
  }
}
