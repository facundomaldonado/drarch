/**
 * 
 */
package org.drarch.engine.stepEngine;

import java.util.Set;

import org.drarch.engine.ruleEngine.Suggest;
import org.drarch.engine.ruleModel.Rule;

/**
 * @author @author maldonadofacundo@gmail.com (Facundo Maldonado)
 *
 */
public interface IStepImplementation {

	public Set<Suggest> execute(Rule rule);

	/**
     * @param lastPhaseFacts
     */
    public void setLastStepSuggests(Set<Suggest> lastStepSuggests);
	public String getName();

	public Rule getStepRule();
    
//    public Set<Suggest> getLastStepSuggests();
}
