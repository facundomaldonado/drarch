package org.design.rules4Java.engine.engine;

import java.util.Iterator;
import java.util.List;

import org.design.rules4Java.engine.engine.engineModel.KnowledgeBase;
import org.design.rules4Java.engine.engine.engineModel.ResultSet;
import org.design.rules4Java.engine.ruleModel.Fact;
import org.design.rules4Java.engine.ruleModel.FactSet;
import org.design.rules4Java.engine.ruleModel.Rule;


/**
 * 
 * @author pelado
 */
public abstract class StepAction {
	
	protected Rule currentRule;
	protected List<FactSet> listFacts;
	protected RuleManager ruleManager;
	protected KnowledgeBase base;
	
	protected List lastRuleFacts;
	protected List currentRuleSuggests;
	
	public StepAction(Rule r){
		currentRule = r;
		ruleManager=RuleManager.getInstance();
	}
	
	/**
	 * @param facts
	 */
	@SuppressWarnings("unchecked")
	public void loadFacts(List facts){
		listFacts = facts;
	}
	
	public void applyFacts(){
		if (listFacts.size()>0){
			base= getKnowledgeBase();
			Iterator iFacts = listFacts.iterator();
			while (iFacts.hasNext()) {
				FactSet set = (FactSet) iFacts.next();
				Iterator ifacts= set.getFactTemplates().iterator();
				while (ifacts.hasNext()) {
					Fact fact = (Fact) ifacts.next();
					base.addFact(fact.getFactText());
				}
			}
			
		}
	}

	public List getPublishedFacts(){
		lastRuleFacts      = ruleManager.getFacts(currentRule,currentRuleSuggests);
		return lastRuleFacts;
	}
	
	public List getSuggests(){
		return currentRuleSuggests;
	}
	
	public void run(){
		applyFacts();
		ResultSet queryResult = ruleManager.evaluateRule(currentRule);
		currentRuleSuggests   = ruleManager.getSuggests(currentRule,queryResult);
	}
	
	
	protected abstract KnowledgeBase getKnowledgeBase();
}