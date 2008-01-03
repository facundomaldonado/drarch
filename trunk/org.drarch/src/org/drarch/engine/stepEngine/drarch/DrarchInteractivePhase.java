/**
 * 
 */
package org.drarch.engine.stepEngine.drarch;

import org.drarch.engine.stepEngine.InteractivePhase;
import org.drarch.engine.stepEngine.Step;

/**
 * @author @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public class DrarchInteractivePhase extends InteractivePhase {
	
	@SuppressWarnings("unused")
    private DrarchPhaseHelper phaseHelper;
	
	/**
     * @param chain_head
     */
    public DrarchInteractivePhase(DrarchPhaseHelper newPhaseHelper) {
	    super(newPhaseHelper.generateStepChain());
	    this.phaseHelper = newPhaseHelper;
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
