package org.design.drarch.manager;

import org.design.drarch.Application;
import org.design.drarch.diagram.action.LoadModelAction;
import org.design.rules4Java.engine.engine.StepAction;
import org.design.rules4Java.engine.engine.engineModel.KnowledgeBase;
import org.design.rules4Java.engine.ruleModel.Rule;

/**
 * 
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public class StepActionImpl extends StepAction {

  public StepActionImpl(Rule r) {
    super(r);
  }

  public void applyFacts(){
    super.applyFacts();
    if (listFacts.size()>0){
      loadModel();
    }
  }
  protected void loadModel() {
    LoadModelAction action= new LoadModelAction(Application.getInstance()
        .getQueryEngine());
    action.run();
  }

  @Override
  protected KnowledgeBase getKnowledgeBase() {
    return Application.getInstance().getKnowledegeBase();
  }
}
