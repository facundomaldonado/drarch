package org.design.rules4Java.engine.engine.engineModel.impl;

import org.design.rules4Java.engine.engine.engineModel.QueryResult;
import org.design.rules4Java.engine.engine.engineModel.Suggest;

public class SuggestImpl implements Suggest{

	protected boolean apply=false;
	protected static final String SUGGEST_EDEFAULT = "";
	protected String suggest = SUGGEST_EDEFAULT;
	protected QueryResult result = null;

	public SuggestImpl() {
		super();
	}
	
	public boolean getApply() {
		return apply;
	}
	
	public void setApply(boolean newState) {
		apply=newState;
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
		result=value;
	}

}
