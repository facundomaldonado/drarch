package org.design.drarch.diagram.action;

import org.apache.log4j.Logger;
import org.design.drarch.diagram.DiagramModel.componentModel.ComponentModel;
import org.design.drarch.diagram.DiagramModel.componentModel.generator.ComponentModelGenerator;
import org.design.drarch.diagram.DiagramModel.ucmModel.UCMModel;
import org.design.drarch.diagram.DiagramModel.ucmModel.generator.UCMModelGenerator;
import org.design.drarch.diagram.flabot.DiagramManager;
import org.design.rules4Java.engine.coreEngine.engineModel.QueryEngine;
import org.design.rules4Java.engine.coreEngine.engineModel.jqueryImpl.QueryEngineImpl;

/**
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public class LoadModelAction {

	private static Logger logger = Logger.getLogger(LoadModelAction.class.getName());

	private QueryEngine	queryEngine;

	public LoadModelAction(QueryEngine theQueryEngine) {
		queryEngine = theQueryEngine;
		((QueryEngineImpl) queryEngine).getWorkingSetNode().reloadRules();
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
