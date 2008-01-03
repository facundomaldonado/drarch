package org.drarch.diagram.DiagramModel.ucmModel;

import org.drarch.diagram.DiagramModel.componentModel.Component;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * 
 * 
 * @model
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public interface PathNode extends EObject {

  /**
   * @model
   */
  String getResponsibilityName();

  /**
   * Sets the value of the '{@link org.design.drarch.diagram.DiagramModel
   * .ucmModel.PathNode#getResponsibilityName <em>Responsibility Name</em>}'
   * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @param value the new value of the '<em>Responsibility Name</em>'
   *        attribute.
   * @see #getResponsibilityName()
   * @generated
   */
  void setResponsibilityName(String value);

  /**
   * @model opposite = "nodes"
   * @return The Path.
   */
  Path getPath();

  /**
   * Sets the value of the '{@link org.design.drarch.diagram.DiagramModel
   * .ucmModel.PathNode#getPath <em>Path</em>}' reference. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @param value the new value of the '<em>Path</em>' reference.
   * @see #getPath()
   * @generated
   */
  void setPath(Path value);

  /**
   * @model type = "PathNode" opposite = "next"
   * @return The provious list.
   */
  EList getPrevious();

  /**
   * @model type = "PathNode" opposite = "previous"
   * @return The next PathNode.
   */
  EList getNext();

  boolean addPrevious(PathNode previous);

  boolean removePrevious(PathNode previous);

  boolean addNext(PathNode next);

  boolean removeNext(PathNode next);

  boolean isStart();

  boolean isEnd();

  void addComment(String string);

  String getComment();
  
  Component getAsociatedComponent();
  
  void setAsociatedComponent(Component component);
}
