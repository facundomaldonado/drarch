package org.design.rules4Java.engine.engine;

import org.design.rules4Java.engine.engine.engineModel.KnowledgeBase;
import org.design.rules4Java.engine.engine.engineModel.ResultSet;
import org.design.rules4Java.engine.engine.engineModel.Suggest;
import org.design.rules4Java.engine.ruleModel.Fact;
import org.design.rules4Java.engine.ruleModel.FactSet;
import org.design.rules4Java.engine.ruleModel.Rule;

import java.util.Iterator;
import java.util.List;


/**
 * 
 * @author pelado
 */
public abstract class StepAction {

  protected Rule currentRule;
  protected List<FactSet> listFacts;
  protected RuleManager ruleManager;
  protected KnowledgeBase base;
  protected List<FactSet> lastRuleFacts;
  protected List<Suggest> currentRuleSuggests;
  
  protected abstract KnowledgeBase getKnowledgeBase();
  
  public StepAction(Rule r){
    currentRule = r;
    ruleManager = RuleManager.getInstance();
  }

  /**
   * @param facts
   */
  @SuppressWarnings("unchecked")
  public void loadFacts(List<FactSet> facts){
    listFacts = facts;
  }

  public void applyFacts(){
    if (listFacts.size() > 0) {
      base = getKnowledgeBase();
      for (Iterator<FactSet> iFacts = listFacts.iterator(); iFacts
          .hasNext(); ) {
        FactSet set = iFacts.next();
        for (Iterator<Fact> ifacts = set.getFactTemplates().iterator(); ifacts
            .hasNext(); ) {
          base.addFact(ifacts.next().getFactText());
        }
      }
    }
  }

  public List<FactSet> getPublishedFacts(){
    lastRuleFacts = ruleManager.getFacts(currentRule, currentRuleSuggests);
    return lastRuleFacts;
  }

  public List<Suggest> getSuggests(){
    return currentRuleSuggests;
  }

  public void run(){
    applyFacts();
    ResultSet queryResult = ruleManager.evaluateRule(currentRule);
    currentRuleSuggests = ruleManager.getSuggests(currentRule, queryResult);
  }
}