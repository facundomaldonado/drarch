package org.design.rules4Java.engine.engine.engineModel;

import org.design.rules4Java.engine.ruleModel.Query;
import org.eclipse.ui.IWorkingSet;
import org.eclipse.ui.IWorkingSetManager;

/**
 * 
 * 
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public interface QueryEngine {

  /**
   * @return WorkingSetManager
   */
  IWorkingSetManager getWorkingSetManager();
  void setWorkingSetManager(IWorkingSetManager value);
  IWorkingSet getWorkingSet();
  void setWorkingSet(IWorkingSet value);
  ResultSet evaluateQuery(Query q);
}
