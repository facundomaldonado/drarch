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
import org.drarch.engine.stepEngine.IStep;
import org.eclipse.ui.IWorkingSet;

import ca.ubc.jquery.gui.results.WorkingSetNode;

/**
 * @author @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public class DrarchPhaseHelper {

	private StepChainGenerator stepChainGenerator;
	
	private String flabotFileName;
	private List rulesList;
	private Map<String, File> factBaseFiles;
	private WorkingSetNode phaseWorkingSetNode;
	private KnowledgeBase knowledgebase;
	private RuleManager ruleManager;
	private String phaseName;
    private	QueryEngine queryEngine;
	
	/**
     * @param flabotFileName
     * @param rulesList
     * @param factBaseFiles
     * @param workingSet
     */
    public DrarchPhaseHelper(String currentPhaseName, String flabotFileName, List rulesList,
    		Map<String, File> factFiles, IWorkingSet workingSet) {
    	
    	this.phaseName = currentPhaseName;
	    this.flabotFileName = flabotFileName;
	    this.rulesList = rulesList;
	    this.factBaseFiles = factFiles;
	    
	    phaseWorkingSetNode = new WorkingSetNode(workingSet);
	    stepChainGenerator = new StepChainGenerator();
	    knowledgebase = EngineFactory.createKnowledgeBase(phaseWorkingSetNode, factBaseFiles);
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
	
	protected String getFlabotFileName() {
    	return this.flabotFileName;
    }
	
	protected RuleManager getPhaseRuleManager() {
		return ruleManager;
	}
    
	protected String getPhaseName() {
		return phaseName;
	}

	public KnowledgeBase getKnowledgebase() {
		return knowledgebase;
	}

	public QueryEngine getQueryEngine() {
		return queryEngine;
	}
	
}
