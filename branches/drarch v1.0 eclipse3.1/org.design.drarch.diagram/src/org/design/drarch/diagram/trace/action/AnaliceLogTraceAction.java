package org.design.drarch.diagram.trace.action;

import org.design.drarch.diagram.DiagramModel.ucmModel.UCMModel;
import org.design.drarch.diagram.DiagramModel.ucmModel.generator.UCMModelGenerator;
import org.design.drarch.diagram.flabot.DiagramManager;
import org.eclipse.jface.action.Action;

/**
 * 
 * 
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public class AnaliceLogTraceAction extends Action {

  public AnaliceLogTraceAction() {
  }

  public void run() {
    LoadLogFactsAction loadLogFacts = new LoadLogFactsAction();
    loadLogFacts.run();

    UCMModelGenerator ucmGenerator = new UCMModelGenerator();
    ucmGenerator.make();


    // alternativeModel
    UCMModel alternativeModel = ucmGenerator.getAlternativeModel();
    DiagramManager.getInstance().createUCMDiagram(alternativeModel);


    UCMModel model = ucmGenerator.getModel();
    DiagramManager.getInstance().createUCMDiagram(model);
  }
}
