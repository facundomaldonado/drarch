/**
 * 
 */
package org.drarch.engine.stepEngine;

/**
 * @author @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public class PhaseManager implements IPhaseManager {
	
	IPhase PHASE_CHAIN_CURSOR;
	IPhase PHASE_CHAIN_HEAD;
	
    public PhaseManager() {
    	this.PHASE_CHAIN_HEAD = null;
		this.PHASE_CHAIN_CURSOR = PHASE_CHAIN_HEAD;
    }
	
	/* (non-Javadoc)
     * @see org.design.rules4Java.engine.coreEngine.IStepManager#executeNextStep()
     */
    public IPhase executeNextPhase() {
		IPhase current = PHASE_CHAIN_CURSOR;
		PHASE_CHAIN_CURSOR = PHASE_CHAIN_CURSOR.nextPhase();
		current.executePhase();
		return current;
    }
	    
    /* (non-Javadoc)
     * @see org.design.rules4Java.engine.stepEngine.IPhaseManager#addNewPhase(org.design.rules4Java.engine.stepEngine.IPhase)
     */
    public void addNewPhase(IPhase newPhase) {
    	if(null == PHASE_CHAIN_HEAD) {
    		PHASE_CHAIN_HEAD = newPhase;
    		PHASE_CHAIN_CURSOR = PHASE_CHAIN_HEAD;
    	}
    	else {
    		PHASE_CHAIN_CURSOR.addPhase(newPhase);
    		PHASE_CHAIN_CURSOR = PHASE_CHAIN_CURSOR.nextPhase();
    	}
		
    }
}
