/**
 * 
 */
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

	/* (non-Javadoc)
     * @see org.design.rules4Java.engine.coreEngine.IStep#executeStep()
     */
    public void executePhase() {
    	while (hasNextStep())
    		getNextStep().execute();
    	getNextStep().execute();
    }
    
    public String getName() {
    	return "";
    }
    
}
