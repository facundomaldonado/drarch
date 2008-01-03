package org.drarch.engine.ruleEngine;


/**
 * @author pelado
 */
public interface Suggest {

	boolean isApply();
	void setApply(boolean newState);
	
	String getSuggest();
	void setSuggest(String value);
	
	QueryResult getResult();
	void setResult(QueryResult value);
}
