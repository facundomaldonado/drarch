package org.design.rules4Java.engine.coreEngine;

import org.design.rules4Java.engine.coreEngine.engineModel.KnowledgeBase;
import org.design.rules4Java.engine.coreEngine.engineModel.QueryEngine;
import org.design.rules4Java.engine.coreEngine.engineModel.ResultSet;
import org.design.rules4Java.engine.coreEngine.engineModel.Suggest;
import org.design.rules4Java.engine.exceptions.DrarchEngineModelException;
import org.design.rules4Java.engine.ruleModel.Fact;
import org.design.rules4Java.engine.ruleModel.FactSet;
import org.design.rules4Java.engine.ruleModel.Rule;

import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public abstract class StepAction {

	protected Rule	        currentRule;
	protected List<FactSet>	listFacts;
	protected RuleManager	ruleManager;
	protected List<FactSet>	lastRuleFacts;
	protected List<Suggest>	currentRuleSuggests;
	
	protected QueryEngine queryEngine;
	protected KnowledgeBase knowledgeBase;

	public StepAction() {
		//TODO: change this
		ruleManager = RuleManager.getInstance();
	}

	/**
     * @param rule
     */
    public void setRule(Rule rule) {
    	currentRule = rule;
    }
	
	public void setKnowledgeBase(KnowledgeBase knowledgeBase) {
    	this.knowledgeBase = knowledgeBase;
    }

	public void setQueryEngine(QueryEngine queryEngine) {
    	this.queryEngine = queryEngine;
    }

	/**
	 * @param facts
	 */
	public void loadFacts(List<FactSet> facts) {
		listFacts = facts;
	}

	@SuppressWarnings("unchecked")
	public void applyFacts() throws DrarchEngineModelException {
		if (listFacts.size() > 0) {
			for (Iterator<FactSet> iFacts = listFacts.iterator(); iFacts
			        .hasNext();) {
				FactSet set = iFacts.next();
				for (Iterator<Fact> ifacts = set.getFactTemplates().iterator(); 
						ifacts.hasNext();) {
					knowledgeBase.addFact(ifacts.next().getFactText());
				}
			}
		}
	}

	public List<FactSet> getPublishedFacts() {
		lastRuleFacts = ruleManager.getFacts(currentRule, currentRuleSuggests);
		return lastRuleFacts;
	}

	public List<Suggest> getSuggests() {
		return currentRuleSuggests;
	}

	public void run() throws DrarchEngineModelException {
		runBeforeApplyFacts();
		applyFacts();
		runBeforeEvaluatingCurrentRule();
		ResultSet queryResult = ruleManager.evaluateRule(currentRule);
		runBeforeGettingSuggests();
		currentRuleSuggests = ruleManager.getSuggests(currentRule, queryResult);
	}
	
	protected abstract void runBeforeApplyFacts();
	protected abstract void runBeforeEvaluatingCurrentRule();
	protected abstract void runBeforeGettingSuggests();
	

}
