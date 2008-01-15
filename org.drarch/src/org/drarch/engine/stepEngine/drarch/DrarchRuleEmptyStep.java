package org.drarch.engine.stepEngine.drarch;

import java.util.Set;

import org.drarch.engine.ruleEngine.RuleManager;
import org.drarch.engine.ruleEngine.Suggest;
import org.drarch.engine.stepEngine.Step;

public class DrarchRuleEmptyStep extends Step {

	RuleManager ruleManager;
	public DrarchRuleEmptyStep(RuleManager ruleManager) {
    	this.ruleManager = ruleManager; 
	}

	public Set<Suggest> execute() {
		if (null != before()) {
			((DrarchRuleStep) before()).applyFacts();
			ruleManager.loadGraphicModel();
		}

		return null;
	}

	protected void applyFacts() {
	}

	public String getName() {
		return "";
	}

}
