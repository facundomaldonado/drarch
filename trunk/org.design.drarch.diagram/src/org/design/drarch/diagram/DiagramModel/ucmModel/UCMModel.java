package org.design.drarch.diagram.DiagramModel.ucmModel;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * 
 * 
 * @model
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public interface UCMModel extends EObject {

  /**
   * @model
   * @return
   */
  String getName();

  /**
   * Sets the value of the '{@link org.design.drarch.diagram.DiagramModel.ucmModel.UCMModel#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * @model type="Path" 
   * @return
   */
  EList getPaths();

  /**
   * @model type="ComponentRole"
   * @return
   */
  EList getComponentRoles();
}