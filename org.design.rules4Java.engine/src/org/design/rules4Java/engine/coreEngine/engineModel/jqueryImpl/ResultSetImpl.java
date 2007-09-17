package org.design.rules4Java.engine.coreEngine.engineModel.jqueryImpl;

import org.design.rules4Java.engine.coreEngine.engineModel.EngineModelFactory;
import org.design.rules4Java.engine.coreEngine.engineModel.QueryResult;
import org.design.rules4Java.engine.coreEngine.engineModel.ResultSet;

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
    QueryResult nextResult = EngineModelFactory.INSTANCE.createQueryResult();
    
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
