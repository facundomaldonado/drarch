/**
 * 
 */
package org.design.drarch;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.design.drarch.engine.KnowledgeBaseImpl;
import org.design.drarch.manager.StepActionImpl;
import org.design.drarch.manager.StepsManagerImpl;
import org.design.rules4Java.engine.coreEngine.RuleManager;
import org.design.rules4Java.engine.coreEngine.StepAction;
import org.design.rules4Java.engine.coreEngine.StepsManager;
import org.design.rules4Java.engine.coreEngine.SuggestMaker;
import org.design.rules4Java.engine.coreEngine.engineModel.EngineModelFactory;
import org.design.rules4Java.engine.coreEngine.engineModel.KnowledgeBase;
import org.design.rules4Java.engine.coreEngine.engineModel.QueryEngine;
import org.design.rules4Java.engine.coreEngine.engineModel.exceptions.DrarchEngineModelException;
import org.design.rules4Java.engine.coreEngine.engineModel.jqueryImpl.QueryEngineImpl;
import org.design.rules4Java.engine.parser.RulesFileParser;
import org.design.rules4Java.engine.ruleModel.Rule;
import org.design.rules4Java.util.ResourceLocator;
import org.design.rules4Java.util.Util;
import org.eclipse.ui.IWorkingSet;
import org.eclipse.ui.IWorkingSetManager;

import ca.ubc.jquery.gui.results.WorkingSetNode;

/**
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public class DrarchEngine {

	private static final Logger logger = Logger.getLogger(DrarchEngine.class.getName());
	
	public static DrarchEngine INSTANCE = new DrarchEngine();
	
	private List current_rule_list = Collections.EMPTY_LIST;
	private QueryEngine queryEngine;
	private RuleManager ruleManager;
	private KnowledgeBase knowledgeBase;
	private StepsManager stepsManager;

	public void initialize(){
		  queryEngine = initializeQueryEngine(Application.getInstance().workingSetManager);
		  ruleManager = initializeRuleManager(queryEngine);
		  current_rule_list = getRulesFromRulesFile();
		  ResourceLocator.INSTANCE.registerCurrentRules(current_rule_list);
		  stepsManager = initializeStepsManager();
		  try{
			  knowledgeBase = initializeKnowledgeBase(queryEngine);
		  } catch (DrarchEngineModelException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
		  }catch(RuntimeException e){
			  //TODO
			  e.printStackTrace();
        }
	}
	
	private QueryEngine initializeQueryEngine(IWorkingSetManager workingSetManager){
		
		queryEngine = EngineModelFactory.INSTANCE.createQueryEngine();
	    ((QueryEngineImpl)queryEngine).setWorkingSet(ResourceLocator.INSTANCE.getCurrentWorkingSet());
	    ((QueryEngineImpl)queryEngine).setWorkingSetManager(workingSetManager);
	    ((QueryEngineImpl) queryEngine).init();
	    
	     return queryEngine;
	}
	
	private RuleManager initializeRuleManager(QueryEngine queryEngine){
		ruleManager = RuleManager.getInstance();
	    ruleManager.setQueryEngine(queryEngine);
	    SuggestMaker suggestMaker = new SuggestMaker();
	    ruleManager.setSuggestMaker(suggestMaker);
	    return ruleManager;
	}
	
    public List getRulesFromRulesFile() {
    	String path = ResourceLocator.INSTANCE.getRuleFilePath();
		RulesFileParser parser = new RulesFileParser(path);
		current_rule_list = parser.getParsedRules();
		return current_rule_list;
	}

    private StepsManager initializeStepsManager(){
    	return StepsManagerImpl.getInstance();
    }
    
    private void resetStepsManager(){
    	StepsManagerImpl.getInstance().restart();
    }

	/**
     * @return
	 * @throws DrarchEngineModelException 
     */
    private KnowledgeBase initializeKnowledgeBase(QueryEngine queryEngine) throws DrarchEngineModelException {
    	WorkingSetNode workingSetNode = ((QueryEngineImpl) queryEngine).getWorkingSetNode();
	    knowledgeBase = new KnowledgeBaseImpl(workingSetNode);
	    knowledgeBase.generateFile();
	    ((KnowledgeBaseImpl)knowledgeBase).setInclude();
	    return knowledgeBase;
    }

	public StepAction createStepAction(Rule rule){
		return new StepActionImpl(queryEngine, knowledgeBase, rule);
	}
	
	private RulesFileParser getRulesParser(String pathToRulesFile){
		return new RulesFileParser(pathToRulesFile);
	}
}
