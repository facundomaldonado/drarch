package org.drarch.engine.stepEngine.drarch;

import java.util.Set;

import org.drarch.engine.ruleEngine.Suggest;
import org.drarch.engine.stepEngine.Step;

public class DrarchRuleEmptyStep extends Step {

	public DrarchRuleEmptyStep() {
	}

	public Set<Suggest> execute() {
		if (null != before()) {
			((DrarchRuleStep) before()).applyFacts();
		}
		return null;
	}

	protected void applyFacts() {
	}

	public String getName() {
		return "";
	}

}
