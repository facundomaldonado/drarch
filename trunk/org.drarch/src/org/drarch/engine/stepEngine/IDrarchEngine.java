/**
 * 
 */
package org.drarch.engine.stepEngine;

/**
 * @author @author maldonadofacundo@gmail.com (Facundo Maldonado)
 *
 */
public interface IDrarchEngine {

	public void addNewPhase(IPhase newPhase);
	public IPhase executePhase();
	
}
