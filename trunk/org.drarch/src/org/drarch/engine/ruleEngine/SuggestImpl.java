package org.drarch.engine.ruleEngine;


public class SuggestImpl implements Suggest {

	protected boolean	  apply	   = false;
	protected String	  suggest	= "";
	protected QueryResult	result	= null;

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

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		Suggest suggest = (Suggest) obj;
		return suggest.getSuggest().equals(this.getSuggest());
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
	    return getSuggest().hashCode();
	}

}