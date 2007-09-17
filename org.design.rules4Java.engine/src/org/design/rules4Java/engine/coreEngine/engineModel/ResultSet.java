package org.design.rules4Java.engine.coreEngine.engineModel;

/**
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public interface ResultSet {

  /**
   * @return true if has more elements
   */
  boolean hasMoreElements();

  /**
   * @return next result
   */
  QueryResult next();
}
