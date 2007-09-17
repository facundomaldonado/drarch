package org.design.rules4Java.engine.coreEngine.engineModel;

import org.design.rules4Java.engine.coreEngine.engineModel.exceptions.DrarchEngineModelException;
import org.design.rules4Java.engine.ruleModel.Query;

/**
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public interface QueryEngine {

//  /**
//   * @return WorkingSetManager
//   */
//  IWorkingSetManager getWorkingSetManager();
//
//  void setWorkingSetManager(IWorkingSetManager value);
//
//  IWorkingSet getWorkingSet();
//
//  void setWorkingSet(IWorkingSet value);

  ResultSet evaluateQuery(Query q) throws DrarchEngineModelException;
} 
