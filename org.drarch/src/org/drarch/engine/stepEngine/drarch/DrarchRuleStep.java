package org.drarch.engine.stepEngine.drarch;

import java.util.Set;

import org.drarch.engine.ruleEngine.RuleManager;
import org.drarch.engine.ruleEngine.Suggest;
import org.drarch.engine.ruleModel.FactSet;
import org.drarch.engine.ruleModel.Rule;
import org.drarch.engine.stepEngine.Step;

public class DrarchRuleStep extends Step {

	private RuleManager ruleManager;

	private Rule stepRule;

	private Set<Suggest> stepSuggests;

	public DrarchRuleStep(Rule rule, RuleManager ruleManager) {
    	this.ruleManager = ruleManager; 
    	stepRule = rule;
	}

	public Set<Suggest> execute() {
		if (null != before()) {
			((DrarchRuleStep) before()).applyFacts();
		}
		stepSuggests = ruleManager.evaluateRule(stepRule);

		return stepSuggests;
	}
	
	protected void applyFacts() {
		Set<FactSet> facts = ruleManager.getFacts(stepRule, stepSuggests);
    	ruleManager.applyFacts(facts);
	}

	public String getName() {
		return stepRule.getDescription();
	}

}
