package org.drarch.engine.stepEngine.drarch;

import org.drarch.engine.stepEngine.NonInteractivePhase;
import org.drarch.engine.stepEngine.Step;

public class DrarchNonInteractivePhase extends NonInteractivePhase {

	private DrarchPhaseHelper phaseHelper;
	
	/**
     * @param chain_head
     */
    public DrarchNonInteractivePhase(DrarchPhaseHelper newPhaseHelper) {
	    super(newPhaseHelper.generateStepChain());
	    phaseHelper = newPhaseHelper;
    }

    @Override
    public String getName() {
    	return phaseHelper.getPhaseName();
    }
    
    public Step getCurrentStep() {
    	return (Step) stepController.getCurrentStep();
    }

	public DrarchPhaseHelper getPhaseHelper() {
		return phaseHelper;
	}
	
}
