/**
 * 
 */
package org.drarch.engine.stepEngine;

import java.util.Collections;
import java.util.Set;

import org.drarch.engine.ruleEngine.Suggest;

/**
 * @author @author maldonadofacundo@gmail.com (Facundo Maldonado)
 *
 */
@SuppressWarnings("unchecked")
public class InteractivePhase extends Phase {

	Set<Suggest> lastStepSuggests = Collections.EMPTY_SET;

	/**
     * @param chain_head
     */
    public InteractivePhase(IStep chain_head) {
	    super(chain_head);
    }

	/* (non-Javadoc)
     * @see org.design.rules4Java.engine.coreEngine.IStep#executeStep()
     */
    public void executePhase() {
    	if (hasNextStep()) {
    		IStep step = getNextStep();
       		step.setLastPhaseSuggests(lastStepSuggests);
    		lastStepSuggests =	step.execute();
    		return;
    	}
    	lastStepSuggests = getNextStep().execute();
    }

	/* (non-Javadoc)
     * @see org.design.rules4Java.engine.coreEngine.IStep#nextStep()
     */
    public IPhase nextPhase() {
    	if (hasNextStep())
    		return this;
	    return NEXT_PHASE;
    }

	public Set<Suggest> getLastStepSuggests() {
    	return this.lastStepSuggests;
    }
	
	public String getName() {
		return "";
	}
}
