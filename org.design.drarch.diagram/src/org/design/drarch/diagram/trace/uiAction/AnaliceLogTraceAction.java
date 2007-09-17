package org.design.drarch.diagram.trace.uiAction;

import org.design.drarch.diagram.DiagramModel.ucmModel.UCMModel;
import org.design.drarch.diagram.DiagramModel.ucmModel.generator.UCMModelGenerator;
import org.design.drarch.diagram.flabot.DiagramManager;
import org.design.rules4Java.engine.coreEngine.engineModel.KnowledgeBase;
import org.design.rules4Java.engine.coreEngine.engineModel.QueryEngine;
import org.design.rules4Java.engine.coreEngine.engineModel.exceptions.DrarchEngineModelException;
import org.eclipse.jface.action.Action;

/**
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public class AnaliceLogTraceAction extends Action {

	private KnowledgeBase	knowledgeBase;
	private QueryEngine	    queryEngine;
	
  public AnaliceLogTraceAction(QueryEngine queryEngine, KnowledgeBase base) {
	  super();
	  this.knowledgeBase = base;
	  this.queryEngine = queryEngine;
  }

  public void run() {
    LoadLogFactsAction loadLogFacts = new LoadLogFactsAction(queryEngine, knowledgeBase);
    try {
	    loadLogFacts.run();
    } catch (DrarchEngineModelException e) {
    	//TODO add exception handler
	    e.printStackTrace();
    }

    //TODO: try to obtain the modelGenerator by a factory
    UCMModelGenerator ucmGenerator = new UCMModelGenerator(queryEngine);
    ucmGenerator.make();

    // alternativeModel
    UCMModel alternativeModel = ucmGenerator.getAlternativeModel();
    DiagramManager.getInstance().createUCMDiagram(alternativeModel);

    UCMModel model = ucmGenerator.getModel();
    DiagramManager.getInstance().createUCMDiagram(model);
  }
}
