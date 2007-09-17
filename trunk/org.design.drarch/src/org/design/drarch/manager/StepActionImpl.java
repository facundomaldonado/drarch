package org.design.drarch.manager;

import org.apache.log4j.Logger;
import org.design.drarch.DrarchPlugin;
import org.design.drarch.diagram.action.LoadModelAction;
import org.design.rules4Java.engine.coreEngine.StepAction;
import org.design.rules4Java.engine.coreEngine.engineModel.KnowledgeBase;
import org.design.rules4Java.engine.coreEngine.engineModel.QueryEngine;
import org.design.rules4Java.engine.coreEngine.engineModel.exceptions.DrarchEngineModelException;
import org.design.rules4Java.engine.ruleModel.Rule;

/**
 * 
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public class StepActionImpl extends StepAction {

	private static Logger logger = Logger.getLogger(DrarchPlugin.class.getName());

	private QueryEngine queryEngine;
	private KnowledgeBase knowledgeBase;

	public StepActionImpl(QueryEngine queryEngine, KnowledgeBase knowledgeBase, Rule r) {
		super(r);
		this.queryEngine = queryEngine;
		this.knowledgeBase = knowledgeBase;
	}

	public void applyFacts() {
		logger.debug("StepActionImpl method: applyFacts() calling to super.applyFacts");
		try {
	        super.applyFacts();
	        if (listFacts.size() > 0) {
				logger.debug("StepActionImpl.applyFacts() list of facts from previous iteration greater than 0. ");
				logger.debug("StepActionImpl.applyFacts() calling to loadModel()");
				loadModel();
			}
        } catch (/*DrarchEngineModel*/Exception e) {
        	logger.error("DrarchEngineModelException in applyFacts method of StepActionImpl ", e);
        }
		
	}

	protected void loadModel() {
		// TODO: remove the commented code below
		// LoadModelAction action = new LoadModelAction(Application.getInstance().getQueryEngine());
		LoadModelAction action = new LoadModelAction(queryEngine);
		logger.debug("StepActionImpl.loadModel() loadModelAction created, param: queryEngine -> "
						+ queryEngine.toString());
		logger.debug("StepActionImpl.loadModel()  -> action.run()");
		action.run();
	}

	@Override
	public KnowledgeBase getKnowledgeBase() {
		return knowledgeBase;
	}
}
