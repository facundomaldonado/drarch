/**
 * 
 */
package org.drarch.engine.stepEngine.drarch;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.drarch.engine.ruleEngine.EngineFactory;
import org.drarch.engine.ruleEngine.KnowledgeBase;
import org.drarch.engine.ruleEngine.QueryEngine;
import org.drarch.engine.ruleEngine.RuleManager;
import org.drarch.engine.stepEngine.IAlgorithm;
import org.drarch.engine.stepEngine.IStep;
import org.eclipse.ui.IWorkingSet;

import ca.ubc.jquery.gui.results.WorkingSetNode;

/**
 * @author @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public class DrarchPhaseHelper extends PhaseHelper {

	private StepChainGenerator stepChainGenerator;
	
//	private String flabotFileName;
	private List rulesList;
//	private Map<String, File> factBaseFiles;
	private WorkingSetNode phaseWorkingSetNode;
//	private KnowledgeBase knowledgebase;
	private RuleManager ruleManager;
    private	QueryEngine queryEngine;
//    private IAlgorithm algorithm;
	
	/**
     * @param flabotFileName
     * @param rulesList
     * @param factBaseFiles
     * @param workingSet
     */
    public DrarchPhaseHelper(String currentPhaseName, String flabotFileName, List rulesList,
    		Map<String, File> factFiles, IWorkingSet workingSet) {
    	
    	super(currentPhaseName, flabotFileName, factFiles, workingSet);
    	
	    this.rulesList = rulesList;
	    
	    phaseWorkingSetNode = new WorkingSetNode(workingSet);
	    stepChainGenerator = new StepChainGenerator();
	    queryEngine = EngineFactory.createQueryEngine(phaseWorkingSetNode);
	    ruleManager = EngineFactory.createRuleManager(queryEngine, knowledgebase, phaseName);
    }
    
	/**
     * @return
     */
    @SuppressWarnings("unchecked")
    public IStep generateStepChain() {
    	return stepChainGenerator.generateChain(rulesList, ruleManager);
    }
	
	protected RuleManager getPhaseRuleManager() {
		return ruleManager;
	}
    
	public QueryEngine getQueryEngine() {
		return queryEngine;
	}
	
//	public IAlgorithm getAlgorithm() {
//		return algorithm;
//	}
	
}
