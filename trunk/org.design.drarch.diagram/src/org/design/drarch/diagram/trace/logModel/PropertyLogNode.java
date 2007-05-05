package org.design.drarch.diagram.trace.logModel;

/**
 * 
 * @model
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public interface PropertyLogNode extends LogNode{

  /**
   * @model
   * @return
   */
  String getValue();
  /**
   * Sets the value of the '{@link org.design.drarch.diagram.trace.logModel.PropertyLogNode#getValue <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Value</em>' attribute.
   * @see #getValue()
   * @generated
   */
  void setValue(String value);
}