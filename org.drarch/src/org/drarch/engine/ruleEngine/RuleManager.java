package org.drarch.engine.ruleEngine;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.drarch.engine.ruleModel.Fact;
import org.drarch.engine.ruleModel.FactSet;
import org.drarch.engine.ruleModel.Rule;
import org.drarch.engine.ruleModel.Var;

/**
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public class RuleManager {

	private QueryEngine	   queryEngine;
	private SuggestMaker	suggestMaker;
	private FactsGenerator	factsGenerator;
	private KnowledgeBase	knowledgeBase;
	private String	       phaseName;

	public RuleManager(QueryEngine queryEngine, KnowledgeBase knowledgeBase, String phaseName) {
		factsGenerator = new FactsGenerator();
		suggestMaker = new SuggestMaker();
		this.queryEngine = queryEngine;
		this.knowledgeBase = knowledgeBase;
		this.phaseName = phaseName;
	}

	/**
	 * @param rule
	 * @return the result of evaluate that rule
	 * @throws DrarchEngineModelException 
	 */
	public Set<Suggest> evaluateRule(Rule rule) {
		ResultSet queryResult;
		queryResult = queryEngine.evaluateQuery(rule.getQuery());
		return getSuggests(rule, queryResult);
	}

	/**
	 * @param rule
	 * @param queryResult
	 * @return a list with the parsed suggests
	 */
	@SuppressWarnings("unchecked")
	private Set<Suggest> getSuggests(Rule rule, ResultSet queryResult) {
		List<Var> vars = rule.getQuery().getChosenVars();
		Set<Suggest> suggests = suggestMaker.getSuggests(queryResult, rule.getSuggestTemplate(), vars);
		return suggests;
	}

	/**
	 * @param rule
	 * @param suggests
	 * @return given the suggest list return a list of Facts
	 */
	@SuppressWarnings("unchecked")
	public Set<FactSet> getFacts(Rule rule, Set<Suggest> suggests) {
		if (rule != null) {
		List<Var> vars = rule.getQuery().getChosenVars();
		Set<FactSet> facts = factsGenerator.getFacts(suggests, rule.getFactSet(), vars);
		return facts;
		} return Collections.<FactSet>emptySet();
	}

	@SuppressWarnings("unchecked")
    public void applyFacts(Set<FactSet> facts) {
		for (FactSet factSet : facts) {
			List factTemplates = factSet.getFactTemplates();
			for (Iterator<Fact> i = factTemplates.iterator(); i.hasNext();) {
				Fact fact = i.next();
				knowledgeBase.addFact(fact.getFactText(), phaseName);
			}
		}
	}
	
	public void loadGraphicModel() {
		
	}
}
