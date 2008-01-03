/**
 * 
 */
package org.drarch.engine.ruleEngine;

import java.io.File;
import java.util.Map;

import ca.ubc.jquery.gui.results.WorkingSetNode;

/**
 * @author @author maldonadofacundo@gmail.com (Facundo Maldonado)
 *
 */
public class EngineFactory {

	public static QueryEngine createQueryEngine(WorkingSetNode workingSetNode) {
		return new QueryEngine(workingSetNode);
	};
	
	public static KnowledgeBase createKnowledgeBase(WorkingSetNode workingSetNode, Map<String, File> factBaseFiles) {
		return new KnowledgeBase(factBaseFiles, workingSetNode);
	}
	
	public static RuleManager createRuleManager(QueryEngine queryEngine, KnowledgeBase knowledgeBase, String phaseName) {
		return new RuleManager(queryEngine, knowledgeBase, phaseName);
	}
	
	public static QueryResult createQueryResult() {
		QueryResult queryResult = new QueryResultImpl();
		return queryResult;
	}

	public static ResultSet createResultSet() {
		ResultSet resultSet = new ResultSetImpl();
		return resultSet;
	}

	public static Suggest createSuggest() {
		Suggest suggest = new SuggestImpl();
		return suggest;
	}

    public static ResultSet createEmptyResultSet() {
	    return new EmptyResultSetImpl();
	    
    }
}
