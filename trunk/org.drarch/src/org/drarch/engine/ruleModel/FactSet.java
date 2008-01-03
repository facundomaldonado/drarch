package org.drarch.engine.ruleModel;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * 
 * @model
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public interface FactSet extends EObject {

  /**
   * @model type = "Fact" containment = "true"
   */
  EList getFactTemplates();
}
