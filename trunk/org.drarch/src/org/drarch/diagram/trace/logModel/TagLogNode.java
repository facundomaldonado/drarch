package org.drarch.diagram.trace.logModel;

import org.isistan.flabot.trace.log.Tag;

/**
 * 
 * @model
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public interface TagLogNode extends LogNode {

  /**
   * @model Type"LogNode" containment="true"
   * @return an array of LogNode
   */
  LogNode[] getChildrens();

  /**
   * Sets the value of the '{@link org.design.drarch.diagram.trace.logModel.TagLogNode#getChildrens <em>Childrens</em>}'
   * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @param value the new value of the '<em>Childrens</em>' attribute.
   * @see #getChildrens()
   * @generated
   */
  void setChildrens(LogNode[] value);

  /**
   * @model type="Tag"
   * @return
   */
  Tag getTag();

  /**
   * Sets the value of the '{@link org.design.drarch.diagram.trace.logModel.TagLogNode#getTag <em>Tag</em>}'
   * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @param value the new value of the '<em>Tag</em>' reference.
   * @see #getTag()
   * @generated
   */
  void setTag(Tag value);
}
