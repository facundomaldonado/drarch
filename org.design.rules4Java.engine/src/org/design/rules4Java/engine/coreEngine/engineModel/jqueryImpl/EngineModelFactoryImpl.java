package org.design.rules4Java.engine.coreEngine.engineModel.jqueryImpl;

import org.design.rules4Java.engine.coreEngine.engineModel.EngineModelFactory;
import org.design.rules4Java.engine.coreEngine.engineModel.QueryEngine;
import org.design.rules4Java.engine.coreEngine.engineModel.QueryResult;
import org.design.rules4Java.engine.coreEngine.engineModel.ResultSet;
import org.design.rules4Java.engine.coreEngine.engineModel.Suggest;
import org.design.rules4Java.engine.coreEngine.engineModel.defaultImpl.SuggestImpl;

public class EngineModelFactoryImpl implements EngineModelFactory {

	public QueryEngine createQueryEngine() {
		QueryEngine queryEngine = new QueryEngineImpl();
		return queryEngine;
	}

	public QueryResult createQueryResult() {
		QueryResult queryResult = new QueryResultImpl();
		return queryResult;
	}

	public ResultSet createResultSet() {
		ResultSet resultSet = new ResultSetImpl();
		return resultSet;
	}

	public Suggest createSuggest() {
		Suggest suggest = new SuggestImpl();
		return suggest;
	}

	/* (non-Javadoc)
     * @see org.design.rules4Java.engine.coreEngine.engineModel.EngineModelFactory#createEmptyResultSet()
     */
    public ResultSet createEmptyResultSet() {
	    return new EmptyResultSetImpl();
	    
    }

}
