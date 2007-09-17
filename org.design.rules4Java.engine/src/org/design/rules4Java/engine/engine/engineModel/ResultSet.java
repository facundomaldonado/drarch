package org.design.rules4Java.engine.engine.engineModel;

/**
 * 
 * @author pelado
 */
public interface ResultSet {

  /**
   * @retur true if has mode elements
   */
  boolean hasMoreElements();

  /**
   * @return next result
   */
  QueryResult next();
}
