package org.drarch.diagram.action;

import org.apache.log4j.Logger;
import org.drarch.diagram.DiagramModel.componentModel.ComponentModel;
import org.drarch.diagram.DiagramModel.componentModel.generator.ComponentModelGenerator;
import org.drarch.diagram.DiagramModel.ucmModel.UCMModel;
import org.drarch.diagram.DiagramModel.ucmModel.generator.UCMModelGenerator;
import org.drarch.diagram.flabot.DiagramManager;
import org.drarch.engine.ruleEngine.QueryEngine;

/**
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public class LoadModelAction {

	private static Logger logger = Logger.getLogger(LoadModelAction.class.getName());

	private QueryEngine	queryEngine;

	public LoadModelAction(QueryEngine theQueryEngine) {
		queryEngine = theQueryEngine;
		queryEngine.reloadRules();
	}
	
	public void run() {
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
	}
}
