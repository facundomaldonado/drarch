package org.design.rules4Java.engine.engine.engineModel;

/**
 * 
 * 
 * @model
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public interface KnowledgeBase {

  /**
   * @param predicate
   */
  void addFact(String predicate);
  
  /**
   * @param predicate
   * @return true if predicate exist in the data base
   * @throws Exception 
   */
  boolean exist(String predicate) throws Exception;
  
  /**
   * @param predicate
   */
  void removeFact(String predicate);
  void generateFile();
}