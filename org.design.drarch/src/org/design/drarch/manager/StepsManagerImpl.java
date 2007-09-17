package org.design.drarch.manager;

import java.util.List;

import org.apache.log4j.Logger;
import org.design.drarch.DrarchEngine;
import org.design.drarch.DrarchPlugin;
import org.design.rules4Java.engine.coreEngine.StepAction;
import org.design.rules4Java.engine.coreEngine.StepsManager;
import org.design.rules4Java.engine.ruleModel.Rule;
import org.design.rules4Java.util.ResourceLocator;

/**
 * 
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public class StepsManagerImpl extends StepsManager {

	private static Logger logger = Logger.getLogger(DrarchPlugin.class.getName());

	private StepsManagerImpl() {
		super();
	}

	public static StepsManager getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new StepsManagerImpl();
		}
		logger.debug("StepsManager -> getting StepsManager instance");
		return INSTANCE;
	}

	@SuppressWarnings("unchecked")
    @Override
	protected List<Rule> getRuleList() {
		return ResourceLocator.INSTANCE.getCurrentRuleList();
	}

	@Override
	protected StepAction createStepAction(Rule rule) {
		return DrarchEngine.INSTANCE.createStepAction(rule);
	}
}
