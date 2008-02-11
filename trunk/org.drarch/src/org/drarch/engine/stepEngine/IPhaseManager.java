/**
 * 
 */
package org.drarch.engine.stepEngine;

/**
 * @author @author maldonadofacundo@gmail.com (Facundo Maldonado)
 *
 */
public interface IPhaseManager {
	
	//public IPhase executeNextPhase();
	public void executePhase();
	public void nextPhase();
	public IPhase getCurrentPhase();
	public void addNewPhase(IPhase newPhase);
	
}
