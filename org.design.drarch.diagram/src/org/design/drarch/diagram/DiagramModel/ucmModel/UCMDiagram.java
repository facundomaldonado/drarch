package org.design.drarch.diagram.DiagramModel.ucmModel;

import org.eclipse.emf.ecore.EObject;

/**
 * 
 * 
 * @model
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public interface UCMDiagram extends EObject {

  /**
   * @model
   * @return The ucm model.
   */
  UCMModel getModel();

  /**
   * Sets the value of the '{@link org.design.drarch.diagram.DiagramModel.ucmModel.UCMDiagram#getModel <em>Model</em>}'
   * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @param value the new value of the '<em>Model</em>' reference.
   * @see #getModel()
   * @generated
   */
  void setModel(UCMModel value);
}
