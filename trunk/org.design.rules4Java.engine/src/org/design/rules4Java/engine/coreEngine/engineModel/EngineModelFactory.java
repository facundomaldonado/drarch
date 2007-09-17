package org.design.rules4Java.engine.coreEngine.engineModel;

import org.design.rules4Java.engine.coreEngine.engineModel.jqueryImpl.EngineModelFactoryImpl;


public interface EngineModelFactory {

	public static EngineModelFactory INSTANCE = new EngineModelFactoryImpl();
	
	public QueryEngine createQueryEngine();
	public QueryResult createQueryResult();
	public ResultSet createResultSet();
	public ResultSet createEmptyResultSet();
	public Suggest createSuggest();
	
}
