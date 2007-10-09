/**
 * 
 */
package org.design.rules4Java.engine;

import java.util.List;

import org.apache.log4j.Logger;
import org.design.rules4Java.engine.coreEngine.RuleManager;
import org.design.rules4Java.engine.coreEngine.StepsManager;
import org.design.rules4Java.engine.coreEngine.SuggestMaker;
import org.design.rules4Java.engine.coreEngine.engineModel.EngineModelFactory;
import org.design.rules4Java.engine.coreEngine.engineModel.KnowledgeBase;
import org.design.rules4Java.engine.coreEngine.engineModel.QueryEngine;
import org.design.rules4Java.engine.coreEngine.engineModel.jqueryImpl.QueryEngineImpl;
import org.design.rules4Java.engine.parser.RulesFileParser;
import org.design.rules4Java.engine.ruleModel.Rule;
import org.eclipse.ui.IWorkingSet;
import org.eclipse.ui.IWorkingSetManager;

import ca.ubc.jquery.gui.results.WorkingSetNode;

/**
 * @author
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public abstract class DrarchEngine {

	private static final Logger	logger	= Logger.getLogger(DrarchEngine.class
	                                           .getName());
	private EnginePlugin	    plugin	= EnginePlugin.getDefault();
	private QueryEngine	        queryEngine;
	private RuleManager	        ruleManager;
	private KnowledgeBase	    knowledgeBase;
	private StepsManager	    stepsManager;

	public void startEngine(){
		logger.info("Starting engine");
		if(isSetupComplete()) {
			logger.info("Starting engine  -->  setup complete");
			queryEngine = initializeQueryEngine();
			ruleManager = initializeRuleManager();
			knowledgeBase = initializeKnowledgeBase();
			stepsManager = initializeStepsManager(knowledgeBase, queryEngine);
		}
	}

	private QueryEngine initializeQueryEngine() {
		logger.info("Initializing query engine");
		queryEngine = EngineModelFactory.INSTANCE.createQueryEngine();
		IWorkingSetManager workingSetManager = plugin.getWorkbench()
		        .getWorkingSetManager();
		queryEngine.setWorkingSetManager(workingSetManager);
		/**
		 * se delega a la implementacion del engine la seleccion del workingset
		 * cuando 
		 */
		queryEngine.setWorkingSet(getCurrentWorkingSet());
		queryEngine.init();

		return queryEngine;
	}

	private RuleManager initializeRuleManager() {
		logger.info("Initializing Rule Manager");
		ruleManager = RuleManager.getInstance();
		ruleManager.setQueryEngine(queryEngine);
		SuggestMaker suggestMaker = new SuggestMaker();
		ruleManager.setSuggestMaker(suggestMaker);
		return ruleManager;
	}

	protected abstract boolean isSetupComplete();
	protected abstract IWorkingSet getCurrentWorkingSet();
	protected abstract String getPathToRulesFile();
	protected abstract StepsManager createStepsManager(List<Rule> listOfRules);
    protected abstract KnowledgeBase createAndInitializeKnowledgeBase(WorkingSetNode workingSetNode);
	
	/**
	 * la lista de reglas debe pasarse al stepsmanager
	 * 
	 * @return
	 */
	private StepsManager initializeStepsManager(KnowledgeBase knowledgeBase, QueryEngine queryEngine) {
		logger.info("Initializing steps manager");
		// crear el stepas manager y setearle las reglas
		String pathToRulesFile = getPathToRulesFile();
		RulesFileParser parser = new RulesFileParser(pathToRulesFile);
		List<Rule> listOfRules = parser.getParsedRules();
		stepsManager = createStepsManager(listOfRules);
		stepsManager.setKnowledgeBase(knowledgeBase);
		stepsManager.setQueryEngine(queryEngine);
		stepsManager.createStepsFromRules();
		return stepsManager;
	}

	private KnowledgeBase initializeKnowledgeBase() {
		logger.info("Initializing KnowledgeBase");
		WorkingSetNode workingSetNode = ((QueryEngineImpl) queryEngine)
		        .getWorkingSetNode();
		knowledgeBase = createAndInitializeKnowledgeBase(workingSetNode);
		return knowledgeBase;
	}

	public StepsManager getStepsManager() {
    	return this.stepsManager;
    }

	public KnowledgeBase getKnowledgeBase() {
    	return this.knowledgeBase;
    }

	public QueryEngine getQueryEngine() {
    	return this.queryEngine;
    } 

}
