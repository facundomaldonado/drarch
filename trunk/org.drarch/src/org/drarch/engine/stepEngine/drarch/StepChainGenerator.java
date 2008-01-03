/**
 * 
 */
package org.drarch.engine.stepEngine.drarch;

import java.util.List;

import org.drarch.engine.ruleEngine.RuleManager;
import org.drarch.engine.ruleModel.Rule;
import org.drarch.engine.stepEngine.IStep;
import org.drarch.engine.stepEngine.Step;

/**
 * @author @author maldonadofacundo@gmail.com (Facundo Maldonado)
 *
 */
public class StepChainGenerator {

	/**
     * @param rulesList
     * @return
     */
    public IStep generateChain(final List<Rule> rulesList, RuleManager ruleManager) {
    	Rule firstRule = rulesList.remove(0);
    	IStep headStep = new Step(new	DrarchRuleStepImplementation(firstRule, ruleManager));
    	IStep cursor = null;
    	IStep before = headStep;
    	for (Rule rule : rulesList) {
    		DrarchRuleStepImplementation ruleStep = 
    			new	DrarchRuleStepImplementation(rule, ruleManager);
    		cursor = new Step(ruleStep);
    		before.addNext(cursor);
    		cursor.addBefore(before);
    		
    		before = cursor;
        }
	    return headStep;
    }

}
