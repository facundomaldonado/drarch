package org.drarch.engine.ruleModel;

import org.eclipse.emf.ecore.EObject;

/**
 * 
 * @model
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public interface Var extends EObject {

  /**
   * @model
   * @return var string value
   */
  String getVarText();

  /**
   * Sets the value of the '{@link org.design.rules4Java.engine.ruleModel.Var#getVarText <em>Var Text</em>}'
   * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @param value the new value of the '<em>Var Text</em>' attribute.
   * @see #getVarText()
   * @generated
   */
  void setVarText(String value);
}
