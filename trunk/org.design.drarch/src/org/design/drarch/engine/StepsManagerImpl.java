package org.design.drarch.engine;

import java.util.List;

import org.apache.log4j.Logger;
import org.design.drarch.DrarchPlugin;
import org.design.rules4Java.engine.coreEngine.StepAction;
import org.design.rules4Java.engine.coreEngine.StepsManager;
import org.design.rules4Java.engine.ruleModel.Rule;

/**
 * 
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public class StepsManagerImpl extends StepsManager {

	private static Logger logger = Logger.getLogger(DrarchPlugin.class.getName());
	
	/**
     * @param rules
     */
    public StepsManagerImpl(List<Rule> rules) {
	    super(rules);
    }

	/* (non-Javadoc)
     * @see org.design.rules4Java.engine.coreEngine.StepsManager#createStepAction()
     */
    @Override
    protected StepAction createStepAction() {
    	logger.info("creating StepAction --> StepActionImpl");
	    return new StepActionImpl();
    }
}
