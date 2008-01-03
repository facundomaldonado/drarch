package org.drarch.engine.ruleModel;

import org.eclipse.emf.ecore.EObject;

/**
 * 
 * @model
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public interface Rule extends EObject {

  /**
   * @model containment="true"
   * @return query
   */
  Query getQuery();

  /**
   * Sets the value of the '{@link org.design.rules4Java.engine.ruleModel.Rule#getQuery <em>Query</em>}'
   * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @param value the new value of the '<em>Query</em>' containment
   *        reference.
   * @see #getQuery()
   * @generated
   */
  void setQuery(Query value);

  /**
   * @model containment="true"
   */
  FactSet getFactSet();

  /**
   * Sets the value of the '{@link org.design.rules4Java.engine.ruleModel.Rule#getFactSet <em>Fact Set</em>}'
   * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @param value the new value of the '<em>Fact Set</em>' containment
   *        reference.
   * @see #getFactSet()
   * @generated
   */
  void setFactSet(FactSet value);

  /**
   * @model
   */
  String getSuggestTemplate();

  /**
   * Sets the value of the '{@link org.design.rules4Java.engine.ruleModel.Rule#getSuggestTemplate <em>Suggest Template</em>}'
   * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @param value the new value of the '<em>Suggest Template</em>'
   *        attribute.
   * @see #getSuggestTemplate()
   * @generated
   */
  void setSuggestTemplate(String value);

  /**
   * @model
   * @return description of the rule
   */
  String getDescription();

  /**
   * Sets the value of the '{@link org.design.rules4Java.engine.ruleModel.Rule#getDescription <em>Description</em>}'
   * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @param value the new value of the '<em>Description</em>' attribute.
   * @see #getDescription()
   * @generated
   */
  void setDescription(String value);
}
