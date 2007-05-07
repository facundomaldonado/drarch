/**
 * <copyright> </copyright>
 * 
 * $Id: RuleModelFactory.java,v 1.1 2006/10/07 19:08:22 nfrontini Exp $
 */
package org.design.rules4Java.engine.ruleModel;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a
 * create method for each non-abstract class of the model. <!-- end-user-doc -->
 * 
 * @see org.design.rules4Java.engine.ruleModel.RuleModelPackage
 * @generated
 */
public interface RuleModelFactory extends EFactory {
  /**
   * The singleton instance of the factory. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   */
  RuleModelFactory eINSTANCE = new org.design.rules4Java.engine.ruleModel.impl.RuleModelFactoryImpl();

  /**
   * Returns a new object of class '<em>Drarch File Model</em>'. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return a new object of class '<em>Drarch File Model</em>'.
   * @generated
   */
  DrarchFileModel createDrarchFileModel();

  /**
   * Returns a new object of class '<em>Fact</em>'. <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * 
   * @return a new object of class '<em>Fact</em>'.
   * @generated
   */
  Fact createFact();

  /**
   * Returns a new object of class '<em>Fact Set</em>'. <!-- begin-user-doc
   * --> <!-- end-user-doc -->
   * 
   * @return a new object of class '<em>Fact Set</em>'.
   * @generated
   */
  FactSet createFactSet();

  /**
   * Returns a new object of class '<em>Query</em>'. <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * 
   * @return a new object of class '<em>Query</em>'.
   * @generated
   */
  Query createQuery();

  /**
   * Returns a new object of class '<em>Rule</em>'. <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * 
   * @return a new object of class '<em>Rule</em>'.
   * @generated
   */
  Rule createRule();

  /**
   * Returns a new object of class '<em>Var</em>'. <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * 
   * @return a new object of class '<em>Var</em>'.
   * @generated
   */
  Var createVar();

  /**
   * Returns the package supported by this factory. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @return the package supported by this factory.
   * @generated
   */
  RuleModelPackage getRuleModelPackage();

} // RuleModelFactory
