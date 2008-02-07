/**
 * 
 */
package org.drarch.engine.stepEngine;

/**
 * @author
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public class PhaseManager implements IPhaseManager {

	IPhase PHASE_CHAIN_CURSOR;

	IPhase PHASE_CHAIN_HEAD;
	
	IPhase PHASE_CHAIN_LAST;

	public PhaseManager() {
		PHASE_CHAIN_HEAD = null;
		PHASE_CHAIN_CURSOR = null;
		PHASE_CHAIN_LAST = null; 
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.design.rules4Java.engine.coreEngine.IStepManager#executeNextStep()
	 */
	public IPhase executeNextPhase() {
		if (null != PHASE_CHAIN_CURSOR) {
			IPhase current = PHASE_CHAIN_CURSOR;
			PHASE_CHAIN_CURSOR = PHASE_CHAIN_CURSOR.nextPhase();
			current.executePhase();
			return current;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.design.rules4Java.engine.stepEngine.IPhaseManager#addNewPhase(org.design.rules4Java.engine.stepEngine.IPhase)
	 */
	public void addNewPhase(IPhase newPhase) {
		if (null == PHASE_CHAIN_HEAD) {
			PHASE_CHAIN_HEAD = newPhase;
			PHASE_CHAIN_CURSOR = PHASE_CHAIN_HEAD;
			PHASE_CHAIN_LAST = PHASE_CHAIN_CURSOR;
		} else {
			PHASE_CHAIN_LAST.addPhase(newPhase);
			PHASE_CHAIN_LAST = PHASE_CHAIN_LAST.nextPhase();
			PHASE_CHAIN_CURSOR = PHASE_CHAIN_LAST;
		}

	}
}
