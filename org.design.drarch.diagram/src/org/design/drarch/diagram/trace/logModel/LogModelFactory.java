/**
 * <copyright> </copyright>
 * 
 * $Id: LogModelFactory.java,v 1.1 2006/10/07 19:05:15 nfrontini Exp $
 */
package org.design.drarch.diagram.trace.logModel;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a
 * create method for each non-abstract class of the model. <!-- end-user-doc -->
 * 
 * @see org.design.drarch.diagram.trace.logModel.LogModelPackage
 * @generated
 */
public interface LogModelFactory extends EFactory {
  /**
   * The singleton instance of the factory. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   */
  LogModelFactory eINSTANCE = new org.design.drarch.diagram.trace.logModel.impl.LogModelFactoryImpl();

  /**
   * Returns a new object of class '<em>Log Node</em>'. <!-- begin-user-doc
   * --> <!-- end-user-doc -->
   * 
   * @return a new object of class '<em>Log Node</em>'.
   * @generated
   */
  LogNode createLogNode();

  /**
   * Returns a new object of class '<em>InnerTag</em>'. <!-- begin-user-doc
   * --> <!-- end-user-doc -->
   * 
   * @return a new object of class '<em>InnerTag</em>'.
   * @generated
   */
  InnerTag createInnerTag();

  /**
   * Returns a new object of class '<em>Property Log Node</em>'. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return a new object of class '<em>Property Log Node</em>'.
   * @generated
   */
  PropertyLogNode createPropertyLogNode();

  /**
   * Returns a new object of class '<em>Responsibility</em>'. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return a new object of class '<em>Responsibility</em>'.
   * @generated
   */
  Responsibility createResponsibility();

  /**
   * Returns a new object of class '<em>Tag Log Node</em>'. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return a new object of class '<em>Tag Log Node</em>'.
   * @generated
   */
  TagLogNode createTagLogNode();

  /**
   * Returns the package supported by this factory. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @return the package supported by this factory.
   * @generated
   */
  LogModelPackage getLogModelPackage();

} // LogModelFactory
