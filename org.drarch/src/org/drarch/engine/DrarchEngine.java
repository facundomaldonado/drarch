/**
 * 
 */
package org.drarch.engine;

import org.drarch.engine.stepEngine.IDrarchEngine;
import org.drarch.engine.stepEngine.IPhase;
import org.drarch.engine.stepEngine.PhaseManager;

/**
 * @author @author maldonadofacundo@gmail.com (Facundo Maldonado)
 *
 */
public class DrarchEngine implements IDrarchEngine{
	
	public static DrarchEngine INSTANCE = new DrarchEngine();
	
	private PhaseManager phaseManager;
	
	private IPhase currentPhase;
	
	private DrarchEngine() {
		phaseManager = new PhaseManager();
	}

	/* (non-Javadoc)
     * @see org.design.rules4Java.engine.stepEngine.IDrarchEngine#addNewPhase(org.design.rules4Java.engine.stepEngine.IPhase)
     */
    public void addNewPhase(IPhase newPhase) {
    	phaseManager.addNewPhase(newPhase);
    }

	/* (non-Javadoc)
     * @see org.design.rules4Java.engine.stepEngine.IDrarchEngine#executePhase()
     */
    public IPhase executePhase() {
    	currentPhase = phaseManager.executeNextPhase();
    	return currentPhase;
    }

	public IPhase getCurrentPhase() {
		return currentPhase;
	}
}
