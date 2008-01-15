/**
 * 
 */
package org.drarch.engine.stepEngine.drarch;

import java.util.List;

import org.drarch.engine.ruleEngine.RuleManager;
import org.drarch.engine.ruleModel.Rule;
import org.drarch.engine.stepEngine.IStep;

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
    	if (rulesList.size() == 0) {
    		return null;
    	}
    	Rule firstRule = rulesList.get(0);
    	IStep headStep = new DrarchRuleStep(firstRule, ruleManager);
    	IStep cursor = null;
    	IStep before = headStep;
    	for (int i = 1; i < rulesList.size(); i++) {
			Rule rule = rulesList.get(i);
    		DrarchRuleStep ruleStep = 
    			new	DrarchRuleStep(rule, ruleManager);
    		cursor = ruleStep;
    		before.addNext(cursor);
    		cursor.addBefore(before);
    		
    		before = cursor;
        }
    	IStep lastStep = new DrarchRuleEmptyStep(ruleManager);
    	
    	cursor = lastStep;
		before.addNext(cursor);
		cursor.addBefore(before);
    	
	    return headStep;
    }

}
