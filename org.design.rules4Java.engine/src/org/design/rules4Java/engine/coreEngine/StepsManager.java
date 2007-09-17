package org.design.rules4Java.engine.coreEngine;

import org.design.rules4Java.engine.coreEngine.engineModel.Suggest;
import org.design.rules4Java.engine.coreEngine.engineModel.exceptions.DrarchEngineModelException;
import org.design.rules4Java.engine.ruleModel.FactSet;
import org.design.rules4Java.engine.ruleModel.Rule;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public abstract class StepsManager {

  private List<StepAction> steps;
  private int currentIndexStep;
  private StepAction currentStep;
  protected static StepsManager INSTANCE;

  /**
   * getRuleList method delegate to the subclasses of StepsManager 
   * the responsibilito of obtain the list of rules selected by the user
   * @return List<Rule> list of Rules
   */
  protected abstract List<Rule> getRuleList();

  protected abstract StepAction createStepAction(Rule rule);

  protected StepsManager() {
    currentIndexStep = 0;
    steps = new ArrayList<StepAction>();
    loadStepsfromFile();
  }

  public void nextStep() {
    if (steps.size() > currentIndexStep) {
      currentIndexStep++;
    }
  }

  public boolean hasNext() {
//    return (steps.size() >= currentIndexStep);
	  return (steps.size() > currentIndexStep);
  }

  public void startStep() throws DrarchEngineModelException {
	  //TODO: REVISAR 
    if (steps.size() > currentIndexStep) {
      currentStep = steps.get(currentIndexStep);
    } else {
      currentStep = steps.get(currentIndexStep - 1);
    }
    if (hasBackStep()) {
      StepAction backStep = steps.get(currentIndexStep - 1);
      currentStep.loadFacts(backStep.getPublishedFacts());
    } else {
      currentStep.loadFacts(new LinkedList<FactSet>());
    }
    currentStep.run();
  }

  private boolean hasBackStep() {
    return (currentIndexStep > 0);
  }

  public List<Suggest> getStepSuggests() {
    return (steps.get(currentIndexStep)).getSuggests();
  }

  public void loadStepsfromFile() {
    List<Rule> rules = getRuleList();
    for (Iterator<Rule> iter = rules.iterator(); iter.hasNext();) {
      Rule rule = iter.next();
      StepAction step = createStepAction(rule);
      steps.add(step);
    }
  }

  public int getNumberStep() {
    return this.currentIndexStep;
  }

  public void restart() {
    INSTANCE = null;
  }
}
