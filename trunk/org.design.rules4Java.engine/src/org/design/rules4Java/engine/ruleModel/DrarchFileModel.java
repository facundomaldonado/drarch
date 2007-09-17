package org.design.rules4Java.engine.ruleModel;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * 
 * @model
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public interface DrarchFileModel extends EObject {

  /**
   * @return list of rules of the model
   * @model type = "Rule" containment = "true"
   */
  EList getRules();

  /**
   * Returns the value of the '<em><b>File Name</b></em>' attribute. The
   * default value is <code>""</code>. <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>File Name</em>' attribute isn't clear, there
   * really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * 
   * @return the value of the '<em>File Name</em>' attribute.
   * @see #setFileName(String)
   * @see org.design.rules4Java.engine.ruleModel.RuleModelPackage#getDrarchFileModel_FileName()
   * @model default=""
   * @generated
   */
  String getFileName();

  /**
   * Sets the value of the '{@link org.design.rules4Java.engine.ruleModel.DrarchFileModel#getFileName <em>File Name</em>}'
   * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @param value the new value of the '<em>File Name</em>' attribute.
   * @see #getFileName()
   * @generated
   */
  void setFileName(String value);

  /**
   * @model
   */
  void addRule(Rule r);
}