package org.design.rules4Java.engine.jqueryImpl.engineModel;

import ca.ubc.jquery.gui.results.WorkingSetNode;

import org.design.rules4Java.engine.engine.engineModel.QueryEngine;
import org.design.rules4Java.engine.engine.engineModel.ResultSet;
import org.design.rules4Java.engine.ruleModel.Query;
import org.eclipse.ui.IWorkingSet;
import org.eclipse.ui.IWorkingSetManager;

import tyRuBa.modes.TypeModeError;
import tyRuBa.parser.ParseException;

public class QueryEngineImpl implements QueryEngine {
  protected IWorkingSetManager workingSetManager;
  protected IWorkingSet workingSet;
  protected WorkingSetNode workingSetNode;
  protected ca.ubc.jquery.query.Query query;

  public QueryEngineImpl() {
    super();
  }

  public void init(){
    workingSetNode = new WorkingSetNode(getWorkingSet());
    query= new ca.ubc.jquery.query.Query(workingSetNode);
  }

  public IWorkingSetManager getWorkingSetManager() {
    return workingSetManager;
  }

  public void setWorkingSetManager(IWorkingSetManager value) {
    workingSetManager=value;
  }

  public WorkingSetNode getWorkingSetNode() {
    return workingSetNode;
  }

  public IWorkingSet getWorkingSet() {
    return workingSet;
  }

  public void setWorkingSet(IWorkingSet value) {
    workingSet = value;
  }

  public ResultSet evaluateQuery(Query q) {
    query.setQuery(q.getQueryString());
    try {
      ResultSet result= createResultSet();
      ((ResultSetImpl)result).setQueryResult(query.execute());
      return result;
    } catch (ParseException e) {
      e.printStackTrace();
    } catch (TypeModeError e) {
      e.printStackTrace();
    }
    return null;
  }

  private ResultSet createResultSet() {
    return new ResultSetImpl();
  }
}
