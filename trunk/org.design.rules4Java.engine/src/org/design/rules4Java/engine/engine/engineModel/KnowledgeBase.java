package org.design.rules4Java.engine.engine.engineModel;

import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * 
 * @author pelado
 */
public interface KnowledgeBase {

	/**
	 * @param predicate
	 */
	void addFact(String predicate);
	/**
	 * @param predicate
	 * @return true if predicate exist in the data base
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	boolean exist(String predicate) throws Exception;
	/**
	 * @param predicate
	 */
	void removeFact(String predicate);
	void generateFile();
}
