package org.design.drarch.engine;

import org.apache.log4j.Logger;
import org.design.drarch.DrarchPlugin;
import org.design.drarch.diagram.action.LoadModelAction;
import org.design.rules4Java.engine.coreEngine.StepAction;

/**
 * 
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public class StepActionImpl extends StepAction {

	private static Logger logger = Logger.getLogger(DrarchPlugin.class.getName());

	protected void loadModel() {
		LoadModelAction action = new LoadModelAction(queryEngine);
		logger.info("StepActionImpl.loadModel() loadModelAction created, param: queryEngine -> "
						+ queryEngine.toString());
		action.run();
	}

	/* (non-Javadoc)
     * @see org.design.rules4Java.engine.coreEngine.StepAction#runBeforeApplyFacts()
     */
    @Override
    protected void runBeforeApplyFacts() {
	    // NOOP
    }

	/* (non-Javadoc)
     * @see org.design.rules4Java.engine.coreEngine.StepAction#runBeforeEvaluatingCurrentRule()
     */
    @Override
    protected void runBeforeEvaluatingCurrentRule() {
    	logger.debug("StepActionImpl method: runBeforeEvaluatingCurrentRule() " +
    			"executed after applying facts ");
    	if (listFacts.size() > 0) {
			logger.debug("StepActionImpl.applyFacts() list of facts from previous iteration greater than 0. ");
			logger.debug("StepActionImpl.applyFacts() calling to loadModel()");
			loadModel();
		}
    }

	/* (non-Javadoc)
     * @see org.design.rules4Java.engine.coreEngine.StepAction#runBeforeGettingSuggests()
     */
    @Override
    protected void runBeforeGettingSuggests() {
	    // NOOP
    }

}
