package org.design.drarch.manager;

import java.util.List;
import org.design.drarch.Application;
import org.design.rules4Java.engine.engine.StepAction;
import org.design.rules4Java.engine.engine.StepsManager;
import org.design.rules4Java.engine.ruleModel.Rule;

/**
 * 
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public class StepsManagerImpl extends StepsManager {

  public StepsManagerImpl() {
    super();
  }

  public static StepsManager getInstance() {
    if (instance == null) {
      instance = new StepsManagerImpl();
    }
    return instance;
  }

  @Override
  protected List<Rule> getDefaultList() {
    return Application.getInstance().getDefaultList();
  }

  @Override
  protected StepAction createStepAction(Rule rule) {
    return new StepActionImpl(rule);
  }
}
