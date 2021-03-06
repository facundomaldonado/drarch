/**
 * 
 */
package org.drarch.engine.stepEngine;

import java.util.Set;

import org.drarch.engine.ruleEngine.Suggest;

/**
 * @author @author maldonadofacundo@gmail.com (Facundo Maldonado)
 *
 */
public interface IStep {

	public void addNext(IStep nextStep);
	public void addBefore(IStep beforeStep);
	public Set<Suggest> execute();
	public IStep next();
	public IStep before();
	
	public String getName();
	
}
