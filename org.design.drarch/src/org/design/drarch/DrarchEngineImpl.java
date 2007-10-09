/**
 * 
 */
package org.design.drarch;

import java.util.List;

import org.design.drarch.engine.KnowledgeBaseImpl;
import org.design.drarch.engine.StepsManagerImpl;
import org.design.rules4Java.engine.DrarchEngine;
import org.design.rules4Java.engine.coreEngine.StepsManager;
import org.design.rules4Java.engine.coreEngine.engineModel.KnowledgeBase;
import org.design.rules4Java.engine.exceptions.DrarchEngineModelException;
import org.design.rules4Java.engine.ruleModel.Rule;
import org.design.rules4Java.util.ResourceLocator;
import org.eclipse.ui.IWorkingSet;

import ca.ubc.jquery.gui.results.WorkingSetNode;

/**
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 *
 */
public class DrarchEngineImpl extends DrarchEngine {

	private RecoverySession session = null;
	
	/* (non-Javadoc)
     * @see org.design.rules4Java.engine.DrarchEngine#isSetupComplete()
     */
    @Override
    protected boolean isSetupComplete() {
	    return session.isSetupComplete();
    }
	
	protected void setRecoverySession(RecoverySession recoverySession) {
		session = recoverySession;
	}
	
	/* (non-Javadoc)
     * @see org.design.rules4Java.engine.DrarchEngine#createAndInitializeKnowledgeBase(ca.ubc.jquery.gui.results.WorkingSetNode)
     */
    @Override
    protected KnowledgeBase createAndInitializeKnowledgeBase(WorkingSetNode workingSetNode) {
	    // TODO pelado: not yet implemented - createAndInitializeKnowledgeBase
    	KnowledgeBase knowledgeBase = new KnowledgeBaseImpl(workingSetNode);
		knowledgeBase.loadFiles();
		try {
	        ((KnowledgeBaseImpl) knowledgeBase).setInclude();
        } catch (DrarchEngineModelException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
	    return knowledgeBase;
	    
    }

	/* (non-Javadoc)
     * @see org.design.rules4Java.engine.DrarchEngine#createStepsManager(java.util.List)
     */
    @Override
    protected StepsManager createStepsManager(List<Rule> listOfRules) {
	    StepsManager stepsManager = new StepsManagerImpl(listOfRules);
	    return stepsManager;
    }

	/* (non-Javadoc)
     * @see org.design.rules4Java.engine.DrarchEngine#getPathToRulesFile()
     */
    @Override
    protected String getPathToRulesFile() {
    	//TODO es necesario primero haber seleccionado un projecto 
    	//porque el path es relativo
    	String rulesSource;
    	if ("".equals(session.getSelectedRuleSource())) 
    		 rulesSource = session.getDefaultRulesSource();
    	else rulesSource = session.getSelectedRuleSource();
    	String path = ResourceLocator.INSTANCE.getRuleFilePath(rulesSource,
				session.getProjectName()); 
	    return path;
	    
    }

	/* (non-Javadoc)
     * @see org.design.rules4Java.engine.DrarchEngine#setCurrentWorkingSet()
     */
    @Override
    protected IWorkingSet getCurrentWorkingSet() {
	    return session.getWorkingSet();
	    
    }
}
