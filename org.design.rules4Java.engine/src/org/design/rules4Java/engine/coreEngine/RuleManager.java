package org.design.rules4Java.engine.coreEngine;

import java.util.List;

import org.design.rules4Java.engine.coreEngine.engineModel.QueryEngine;
import org.design.rules4Java.engine.coreEngine.engineModel.ResultSet;
import org.design.rules4Java.engine.coreEngine.engineModel.Suggest;
import org.design.rules4Java.engine.exceptions.DrarchEngineModelException;
import org.design.rules4Java.engine.ruleModel.FactSet;
import org.design.rules4Java.engine.ruleModel.Rule;
import org.design.rules4Java.engine.ruleModel.Var;

/**
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public class RuleManager {

	private QueryEngine	       queryEngine;
	private SuggestMaker	   suggestMaker;
	private FactsGenerator	   factsGenerator	= new FactsGenerator();

	private static RuleManager INSTANCE = null;

	public static RuleManager getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new RuleManager();
		}
		return INSTANCE;
	}

	/**
	 * @param rule
	 * @return the result of evaluate that rule
	 * @throws DrarchEngineModelException 
	 */
	ResultSet evaluateRule(Rule rule) throws DrarchEngineModelException {
		ResultSet queryResult;
		queryResult = queryEngine.evaluateQuery(rule.getQuery());
		return queryResult;
	}

	/**
	 * @param rule
	 * @param queryResult
	 * @return a list with the parsed suggests
	 */
	@SuppressWarnings("unchecked")
    public List<Suggest> getSuggests(Rule rule, ResultSet queryResult) {
		List<Var> vars = rule.getQuery().getChosenVars();
		List<Suggest> suggests = suggestMaker
						.getSuggests(queryResult, rule.getSuggestTemplate(), vars);
		return suggests;
	}

	/**
	 * @param rule
	 * @param suggests
	 * @return given the suggest list return a list of Facts
	 */
	@SuppressWarnings("unchecked")
    List<FactSet> getFacts(Rule rule, List<Suggest> suggests) {
		List<Var> vars = rule.getQuery().getChosenVars();
		List<FactSet> facts = factsGenerator
						.getFacts(suggests, rule.getFactSet(), vars);
		return facts;
	}

//	/**
//	 * @return query engine
//	 */
//	QueryEngine getQueryEngine() {
//		return queryEngine;
//	}

	public void setQueryEngine(QueryEngine queryEngine) {
		this.queryEngine = queryEngine;
	}

	/**
	 * @return suggest maker
	 */
	public SuggestMaker getSuggestMaker() {
		return suggestMaker;
	}

	public void setSuggestMaker(SuggestMaker suggestMaker) {
		this.suggestMaker = suggestMaker;
	}
}
