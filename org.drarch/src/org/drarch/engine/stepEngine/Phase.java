/**
 * 
 */
package org.drarch.engine.stepEngine;

/**
 * @author
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 * 
 */
public abstract class Phase implements IPhase {

	protected StepIterationController stepController;

	private IPhase NEXT_PHASE = null;

	private IPhase BEFORE_PHASE = null;

	public Phase(IStep chain_head) {
		stepController = new StepIterationController(chain_head);
	}

	public abstract void executePhase();

	public void addPhase(IPhase nextPhase) {
		this.NEXT_PHASE = nextPhase;
	}

	public IPhase nextPhase() {
		return NEXT_PHASE;
	}

	public IPhase beforePhase() {
		return BEFORE_PHASE;
	}

	protected IStep getNextStep() {
		return stepController.getNextStep();
	}
	
	protected IStep getBeforeStep() {
		return stepController.getNextStep();
	}

	protected boolean hasNextStep() {
		return stepController.hasNextStep();
	}
	
}
