package org.drarch.engine.ruleEngine;


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
