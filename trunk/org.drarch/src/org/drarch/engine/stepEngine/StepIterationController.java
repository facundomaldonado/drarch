/**
 * 
 */
package org.drarch.engine.stepEngine;

/**
 * @author @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public class StepIterationController {
	
	IStep STEP_CHAIN_HEAD;
	IStep STEP_CHAIN_CURSOR = null;
	IStep STEP_CHAIN_CURRENT = null;
	
	public StepIterationController(IStep chain_head) {
	    this.STEP_CHAIN_HEAD = chain_head;
	    this.STEP_CHAIN_CURSOR = STEP_CHAIN_HEAD;
    }
	
	public boolean hasNextStep() {
		return STEP_CHAIN_CURSOR.next() != null;
	}
	
	public IStep getCurrentStep() {
		return STEP_CHAIN_CURRENT;
	}
	
	public IStep getNextStep() {
	IStep current = STEP_CHAIN_CURSOR;
	STEP_CHAIN_CURSOR = STEP_CHAIN_CURSOR.next();
	STEP_CHAIN_CURRENT = current;
	return current;
}
	
}
