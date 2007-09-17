package org.design.rules4Java.engine.coreEngine.engineModel.defaultImpl;

import org.design.rules4Java.engine.coreEngine.engineModel.QueryResult;
import org.design.rules4Java.engine.coreEngine.engineModel.Suggest;

public class SuggestImpl implements Suggest {
	
  protected boolean apply = false;
  protected String suggest = "";
  protected QueryResult result = null;

  public SuggestImpl() {
  }

  public boolean isApply() {
    return apply;
  }

  public void setApply(boolean newState) {
    this.apply = newState;
  }

  public String getSuggest() {
    return suggest;
  }

  public void setSuggest(String newSuggest) {
    suggest = newSuggest;
  }

  public QueryResult getResult() {
    return result;
  }

  public void setResult(QueryResult value) {
    this.result = value;
  }
  
}