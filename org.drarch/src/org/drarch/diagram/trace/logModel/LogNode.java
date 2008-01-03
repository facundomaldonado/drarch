package org.drarch.diagram.trace.logModel;

import org.eclipse.emf.ecore.EObject;

/**
 * 
 * @model
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public interface LogNode extends EObject {

  /**
   * @model
   * @return The name of the node.
   */
  String getName();

  /**
   * Sets the value of the '{@link org.design.drarch.diagram.trace.logModel.LogNode#getName <em>Name</em>}'
   * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);
}
