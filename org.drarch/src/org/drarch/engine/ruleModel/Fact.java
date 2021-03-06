package org.drarch.engine.ruleModel;

import org.eclipse.emf.ecore.EObject;

/**
 * 
 * @model
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public interface Fact extends EObject {

  /**
   * @model
   * @return fact string
   */
  String getFactText();

  /**
   * Sets the value of the '{@link org.design.rules4Java.engine.ruleModel.Fact#getFactText <em>Fact Text</em>}'
   * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @param value the new value of the '<em>Fact Text</em>' attribute.
   * @see #getFactText()
   * @generated
   */
  void setFactText(String value);
}
