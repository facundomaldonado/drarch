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
	
	public IPhase nextPhase();
	public IPhase beforePhase();
	public void executePhase();
	public void addPhase(IPhase nextPhase);
	
}
