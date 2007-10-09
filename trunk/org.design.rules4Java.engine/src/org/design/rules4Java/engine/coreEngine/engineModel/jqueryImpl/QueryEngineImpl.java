package org.design.rules4Java.engine.coreEngine.engineModel.jqueryImpl;

import org.apache.log4j.Logger;
import org.design.rules4Java.engine.EnginePlugin;
import org.design.rules4Java.engine.coreEngine.engineModel.EngineModelFactory;
import org.design.rules4Java.engine.coreEngine.engineModel.QueryEngine;
import org.design.rules4Java.engine.coreEngine.engineModel.ResultSet;
import org.design.rules4Java.engine.exceptions.DrarchEngineModelException;
import org.design.rules4Java.engine.ruleModel.Query;
import org.eclipse.ui.IWorkingSet;
import org.eclipse.ui.IWorkingSetManager;

import tyRuBa.modes.TypeModeError;
import tyRuBa.parser.ParseException;
import ca.ubc.jquery.gui.results.WorkingSetNode;

public class QueryEngineImpl implements QueryEngine {

	private static Logger logger = Logger.getLogger(EnginePlugin.class.getName());

	private IWorkingSetManager workingSetManager;
	private IWorkingSet workingSet;
	private WorkingSetNode workingSetNode;
	private ca.ubc.jquery.query.Query query;

	public QueryEngineImpl() {
	}

	public void init() {
		logger.debug("Initializing QueryEngine - QueryEngineImpl init method");
		workingSetNode = new WorkingSetNode(getWorkingSet());
		query = new ca.ubc.jquery.query.Query(workingSetNode);
	}

	public IWorkingSetManager getWorkingSetManager() {
		return workingSetManager;
	}

	public void setWorkingSetManager(IWorkingSetManager value) {
		workingSetManager = value;
	}

	public WorkingSetNode getWorkingSetNode() {
		return workingSetNode;
	}

	public IWorkingSet getWorkingSet() {
		return workingSet;
	}

	public void setWorkingSet(IWorkingSet value) {
		workingSet = value;
	}

	public ResultSet evaluateQuery(Query q) throws DrarchEngineModelException{
		//logger.info("evaluating query " + q.getQueryString() + "in QueryEngineImpl");
		query.setQuery(q.getQueryString());
		try {
			ResultSet result = EngineModelFactory.INSTANCE.createResultSet();
			((ResultSetImpl) result).setQueryResult(query.execute());
			return result;
		} catch (ParseException e) {
			logger.debug("ParseException evaluating query: "+ query.getQueryString());
			throw new DrarchEngineModelException("ParseException in method evaluateQuery in class QueryEngineImpl", e);
		} catch (TypeModeError e) {
			throw new DrarchEngineModelException("TypeModeError in method evaluateQuery in class QueryEngineImpl", e);
		}
	}
}
