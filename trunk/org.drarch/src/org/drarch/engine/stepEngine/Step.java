/**
 * 
 */
package org.drarch.engine.stepEngine;

import java.util.Set;

import org.drarch.engine.ruleEngine.Suggest;
import org.drarch.engine.ruleModel.Rule;

/**
 * @author @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public class Step implements IStep {

	IStepImplementation stepImpl;
	
	IStep NEXT = null;//next action in the chain
	IStep BEFORE = null;
	
	/**
     * Constructor with the implementation as argument
     */
    public Step(IStepImplementation implementation) {
    	stepImpl = implementation;
    }

	/* (non-Javadoc)
	 * @see org.design.rules4Java.engine.coreEngine.IStepAction#next()
	 */
	public IStep next() {
		return NEXT;
	}

	/* (non-Javadoc)
	 * @see org.design.rules4Java.engine.stepEngine.IStep#before()
	 */
	public IStep before() {
	    return BEFORE;
	}
	
	/* (non-Javadoc)
	 * @see org.design.rules4Java.engine.coreEngine.IStepAction#run()
	 */
	public Set<Suggest> execute() {
		Rule rule = null;
		if (before()!= null) 
			rule = ((Step)before()).getStepRule();
		return stepImpl.execute(rule);
	}
	
	private Rule getStepRule(){
		return stepImpl.getStepRule();
	}

	public void addNext(IStep nextStep) {
	   NEXT = nextStep;
    }
	
	/* (non-Javadoc)
	 * @see org.design.rules4Java.engine.stepEngine.IStep#addBefore(org.design.rules4Java.engine.stepEngine.IStep)
	 */
	public void addBefore(IStep beforeStep) {
		BEFORE = beforeStep;
	}
	/* (non-Javadoc)
	 * @see org.design.rules4Java.engine.stepEngine.IStep#setLastStepFacts(java.util.Set)
	 */
	public void setLastPhaseSuggests(Set<Suggest> lastStepSuggests) {
		stepImpl.setLastStepSuggests(lastStepSuggests);
	}
	
	/* (non-Javadoc)
	 * @see org.design.rules4Java.engine.stepEngine.IStep#getName()
	 */
	public String getName() {
	    return stepImpl.getName();
	}
	
//	public Set<Suggest> getLastStepSuggests(){
//		return stepImpl.getLastStepSuggests();
//	}
}
