package org.design.rules4Java.engine.coreEngine.engineModel.jqueryImpl;

import org.design.rules4Java.engine.coreEngine.engineModel.QueryResult;

import tyRuBa.engine.Frame;
import tyRuBa.engine.RBRepAsJavaObjectCompoundTerm;
import tyRuBa.engine.RBVariable;

public class QueryResultImpl implements QueryResult {

	private Frame frame;

	protected QueryResultImpl() {
		super();
	}
    
	public String getValueOfVar(String var) {
		String result = "";
		if (frame.containsKey(RBVariable.make(var))) {
			
			Object res = frame.get(RBVariable.make(var));
			// Posiblemente esto no quede asi.
			if (res instanceof RBRepAsJavaObjectCompoundTerm) {
				result = (String) ((RBRepAsJavaObjectCompoundTerm) res).getValue();
			} else {
				result = res.toString();
			}
		}
		return result.replace("#", "");
	}

	public void setFrame(Frame frame) {
		this.frame = frame;
	}
}
