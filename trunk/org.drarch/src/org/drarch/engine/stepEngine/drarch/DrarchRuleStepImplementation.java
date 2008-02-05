/**
 * 
 */
package org.drarch.engine.stepEngine.drarch;

import java.util.Set;

import org.drarch.engine.ruleEngine.RuleManager;
import org.drarch.engine.ruleEngine.Suggest;
import org.drarch.engine.ruleModel.FactSet;
import org.drarch.engine.ruleModel.Rule;
import org.drarch.engine.stepEngine.IStepImplementation;

/**
 * @author @author maldonadofacundo@gmail.com (Facundo Maldonado)
 * @deprecated
 */
public class DrarchRuleStepImplementation implements IStepImplementation {

	private RuleManager ruleManager;
	private Rule stepRule;
	private Set<Suggest> lastSuggests;
	/**
	 * 
	 * @param rule
	 * @param ruleManager
	 */
    public DrarchRuleStepImplementation(Rule rule, RuleManager ruleManager) {
    	this.ruleManager = ruleManager; 
    	stepRule = rule;
    }

    /* (non-Javadoc)
     * @see org.design.rules4Java.engine.stepEngine.IStepImplementation#setLastPhaseFacts(java.util.Set)
     */
    public void setLastStepSuggests(Set<Suggest> lastStepSuggests) {
    	lastSuggests = lastStepSuggests;
    }
    
	/* (non-Javadoc)
     * @see org.design.rules4Java.engine.stepEngine.IStepImplementation#execute()
     */
    public Set<Suggest> execute(Rule lastRule) {
    	Set<FactSet> facts = ruleManager.getFacts(lastRule, lastSuggests);
    	ruleManager.applyFacts(facts);
    	
 //   	ruleManager.loadGraphicModel();
    	
		//	runBeforeEvaluatingCurrentRule();-->loadModel
		Set<Suggest> currentSuggests = ruleManager.evaluateRule(stepRule);
		return currentSuggests;
	}
    
    /* (non-Javadoc)
     * @see org.design.rules4Java.engine.stepEngine.IStepImplementation#getName()
     */
    public String getName() {
        return stepRule.getDescription();
    }
    
    public Rule getStepRule() {
    	return stepRule;
    }

}
