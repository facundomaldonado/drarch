package org.design.rules4Java.engine.coreEngine.engineModel;

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
