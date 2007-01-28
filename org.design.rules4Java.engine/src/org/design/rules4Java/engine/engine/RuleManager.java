package org.design.rules4Java.engine.engine;

import java.util.List;

import org.design.rules4Java.engine.engine.engineModel.QueryEngine;
import org.design.rules4Java.engine.engine.engineModel.ResultSet;
import org.design.rules4Java.engine.ruleModel.Rule;
import org.design.rules4Java.engine.ruleModel.Var;

/**
 * 
 * @author pelado
 */
public class RuleManager {
	
	private QueryEngine queryEngine;
	private SuggestMaker suggestMaker;
	private FactsGenerator factsGenerator= new FactsGenerator();
	private static RuleManager instance;
	
	public RuleManager(){
		super();
	}
	
	public static RuleManager getInstance(){
		if (instance == null){
			instance = new RuleManager();
		}
		return instance;
	}
	
	/**
	 * @param rule
	 * @return the result of evaluate that rule
	 */
	ResultSet evaluateRule(Rule rule){
		ResultSet queryResult=queryEngine.evaluateQuery(rule.getQuery());
		return queryResult;
	}
	
	/**
	 * @param rule
	 * @param queryResult
	 * @return a list with the parsed suggests
	 */
	@SuppressWarnings("unchecked") List getSuggests(Rule rule, ResultSet queryResult){
		List<Var> vars=rule.getQuery().getChosenVars();
		List suggests = suggestMaker.getSuggests(queryResult, rule.getSuggestTemplate(),vars);
		return suggests;
	}
	
	/**
	 * @param rule
	 * @param suggests
	 * @return given the suggest list return  a list of Facts
	 */
	List getFacts(Rule rule, List suggests){
		List vars= rule.getQuery().getChosenVars();
		List facts= factsGenerator.getFacts(suggests,rule.getFactSet(),vars);
		return facts;
	}
	
	/**
	 * @return query engine
	 */
	QueryEngine getQueryEngine(){
		return queryEngine;
	}
	
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
