package org.drarch.diagram.DiagramModel.ucmModel;

import org.drarch.diagram.DiagramModel.componentModel.Component;
import org.eclipse.emf.ecore.EObject;

/**
 * 
 * 
 * @model
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public interface ComponentRole extends EObject {

  /**
   * Nombre del Componente
   * 
   * @model
   * @return The name of the ComponentRole.
   */
  String getName();

  /**
   * Sets the value of the '{@link org.design.drarch.diagram.DiagramModel
   * .ucmModel.ComponentRole#getName <em>Name</em>}' attribute. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Nombre de la clase que define la materializacion de la responsabilidad
   * 
   * @model
   * @return The name of the defined class.
   */
  String getDefinedClass();

  /**
   * Sets the value of the '{@link org.design.drarch.diagram.DiagramModel
   * .ucmModel.ComponentRole#getDefinedClass <em>Defined Class</em>}'
   * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @param value the new value of the '<em>Defined Class</em>' attribute.
   * @see #getDefinedClass()
   * @generated
   */
  void setDefinedClass(String value);

  /**
   * Nombre del metodo que se ejecuto
   * 
   * @model
   * @return The Begavior name.
   */
  String getBehaviorName();

  /**
   * Sets the value of the '{@link org.design.drarch.diagram.DiagramModel
   * .ucmModel.ComponentRole#getBehaviorName <em>Behavior Name</em>}'
   * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @param value the new value of the '<em>Behavior Name</em>' attribute.
   * @see #getBehaviorName()
   * @generated
   */
  void setBehaviorName(String value);

  /**
   * @model
   * @return The Component.
   */
  Component getComponent();

  /**
   * Sets the value of the '{@link org.design.drarch.diagram.DiagramModel
   * .ucmModel.ComponentRole#getComponent <em>Component</em>}' attribute.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @param value the new value of the '<em>Component</em>' attribute.
   * @see #getComponent()
   * @generated
   */
  void setComponent(Component value);
}
