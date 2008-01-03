/**
 * 
 */
package org.drarch.engine.ruleEngine;

import org.apache.log4j.Logger;
import org.drarch.engine.ruleModel.Query;

import tyRuBa.modes.TypeModeError;
import tyRuBa.parser.ParseException;
import ca.ubc.jquery.gui.results.WorkingSetNode;

/**
 * @author @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public class QueryEngine{

	private ca.ubc.jquery.query.Query query;
	private static final Logger logger = Logger.getLogger(QueryEngine.class.getName());
	private WorkingSetNode workingSetNode;
	/**
     * @param workingSetNode
     * @param query
     */
    public QueryEngine(WorkingSetNode theWorkingSetNode) {
    	workingSetNode = theWorkingSetNode;
		query = new ca.ubc.jquery.query.Query(workingSetNode);
    }

	public ResultSet evaluateQuery(Query q) {
		query.setQuery(q.getQueryString());
		try {
			ResultSet result = EngineFactory.createResultSet();
			logger.debug(query.getQueryString());
			((ResultSetImpl) result).setQueryResult(query.execute());
			logger.debug(((ResultSetImpl) result).hasMoreElements());
			return result;
		} catch (ParseException e) {
			logger.error("ParseException evaluating query: "+ query.getQueryString(), e);
			throw new RuntimeException();
		} catch (TypeModeError e) {
			logger.error("TypeModeErrorException evaluating query: "+ query.getQueryString(), e);
			throw new RuntimeException();
		}
	}

	public void reloadRules() {
		workingSetNode.reloadRules();
	}
	
}
