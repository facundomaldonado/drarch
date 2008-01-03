/**
 * 
 */
package org.drarch.engine.stepEngine;


/**
 * @author @author maldonadofacundo@gmail.com (Facundo Maldonado)
 *
 */
public interface IPhase {
	
	String getName();
	
	IStep getNextStep();
	boolean hasNextStep();
	
	public IPhase nextPhase();
	public void executePhase();
	public void addPhase(IPhase nextStep);
	
}
