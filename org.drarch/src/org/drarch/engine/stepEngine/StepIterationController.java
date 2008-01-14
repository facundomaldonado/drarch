/**
 * 
 */
package org.drarch.engine.stepEngine;

/**
 * @author
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public class StepIterationController {

	private IStep STEP_CHAIN_HEAD;

	private IStep STEP_CHAIN_CURSOR = null;

	IStep STEP_CHAIN_CURRENT = null;

	public StepIterationController(IStep chain_head) {
		STEP_CHAIN_HEAD = chain_head;
		STEP_CHAIN_CURRENT = STEP_CHAIN_HEAD; 
		STEP_CHAIN_CURSOR = STEP_CHAIN_HEAD;
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
