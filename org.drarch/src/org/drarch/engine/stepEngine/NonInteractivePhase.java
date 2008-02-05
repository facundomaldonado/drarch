package org.drarch.engine.stepEngine;

/**
 * @author @author maldonadofacundo@gmail.com (Facundo Maldonado)
 *
 */
public class NonInteractivePhase extends Phase {

	/**
     * @param chain_head
     */
    public NonInteractivePhase(IStep chain_head) {
	    super(chain_head);
    }

    public void executePhase() {
    	while (hasNextStep())
    		getNextStep().execute();
    	getNextStep().execute();
    }
    
    public String getName() {
    	return "";
    }
    
}
