/**
 * 
 */
package org.drarch.engine.stepEngine.drarch;

import org.apache.log4j.Logger;
import org.drarch.engine.ruleEngine.QueryEngine;

/**
 * @author @author maldonadofacundo@gmail.com (Facundo Maldonado)
 *
 */
public class LoadDiagramModelsAction {
	private static Logger logger = Logger.getLogger(LoadDiagramModelsAction.class.getName());

	private QueryEngine	queryEngine;

	public LoadDiagramModelsAction(QueryEngine theQueryEngine) {
		queryEngine = theQueryEngine;
		queryEngine.reloadRules();
	}
	
	/*public void run() {
		DiagramManager diagramManager = DiagramManager.getInstance();
		
		// Generate the component model.
		ComponentModelGenerator componentGenerator = new ComponentModelGenerator(queryEngine);
		ComponentModel componentModel = componentGenerator.getModel();
		diagramManager.createComponentDiagram(componentModel);
		
		// Generate the ucm model.
	    UCMModelGenerator ucmGenerator = new UCMModelGenerator(queryEngine);
	    UCMModel ucmModel = ucmGenerator.getModel();
	    diagramManager.createUCMDiagram(ucmModel);
	    
	    diagramManager.update(true);
	}*/

}
