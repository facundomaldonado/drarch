package org.drarch.engine.stepEngine;

import java.util.Collections;
import java.util.Set;

import org.drarch.engine.ruleEngine.Suggest;

/**
 * @author @author maldonadofacundo@gmail.com (Facundo Maldonado)
 *
 */
public class NonInteractivePhase extends Phase {

	Set<Suggest> lastStepSuggests = Collections.emptySet();
	
	/**
     * @param chain_head
     */
    public NonInteractivePhase(IStep chain_head) {
	    super(chain_head);
    }

    public void executePhase() {
    	while (hasNextStep()) {
    		lastStepSuggests = getNextStep().execute();
    		for (Suggest suggest : lastStepSuggests) {
				suggest.setApply(true);
			}
    	}
    	lastStepSuggests = getNextStep().execute();
    }
    
    public String getName() {
    	return "";
    }
    
}
