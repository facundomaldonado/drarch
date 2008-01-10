/**
 * 
 */
package org.drarch.engine.stepEngine;

import java.util.Set;

import org.drarch.engine.ruleEngine.Suggest;
import org.drarch.engine.ruleModel.FactSet;

/**
 * @author @author maldonadofacundo@gmail.com (Facundo Maldonado)
 *
 */
public interface IStepImplementation {

	public Set<Suggest> execute();

	/**
     * @param lastPhaseFacts
     */
    public void setLastStepSuggests(Set<Suggest> lastStepSuggests);
	public String getName();
    
//    public Set<Suggest> getLastStepSuggests();
}
