/**
 * 
 */
package org.drarch.engine.stepEngine;


/**
 * @author @author maldonadofacundo@gmail.com (Facundo Maldonado)
 *
 */
public abstract class Phase implements IPhase {
	
	protected StepIterationController stepController;
	IPhase NEXT_PHASE = null;
	IFactBase factBase;
	
	/**
     * @param step_action_chain_head
     */
    public Phase(IStep chain_head) {
    	stepController = new StepIterationController(chain_head);
    }

    public abstract void executePhase();
    
    public void addPhase(IPhase nextStep) {
    	this.NEXT_PHASE = nextStep;
    }
    
	/* (non-Javadoc)
	 * @see org.design.rules4Java.engine.coreEngine.IStep#getNextStepAction()
	 */
	public IStep getNextStep() {
		return stepController.getNextStep();
	}
	
	public boolean hasNextStep() {
		return stepController.hasNextStep();
	}
	
	public IPhase nextPhase() {
		return NEXT_PHASE;
	}
	
	protected void setFactBase(IFactBase factBase) {
    	this.factBase = factBase;
    }

}
