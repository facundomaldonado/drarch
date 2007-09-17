package org.design.rules4Java.engine.coreEngine.engineModel;

import org.design.rules4Java.engine.coreEngine.engineModel.exceptions.DrarchEngineModelException;

import ca.ubc.jquery.gui.results.WorkingSetNode;

/**
 * @model
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public interface KnowledgeBase {

	/**
	 * @param predicate
	 * @throws DrarchEngineModelException 
	 */
	void addFact(String predicate) throws DrarchEngineModelException;

	/**
	 * @param predicate
	 * @return true if predicate exist in the data base
	 * @throws DrarchEngineModelException 
	 */
	boolean exist(String predicate) throws DrarchEngineModelException;

	/**
	 * @param predicate
	 * @throws DrarchEngineModelException 
	 */
	void removeFact(String predicate) throws DrarchEngineModelException;

	void generateFile();
}
