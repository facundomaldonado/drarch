/**
 * <copyright> </copyright>
 * 
 * $Id: RuleModelSwitch.java,v 1.1 2006/10/07 19:08:20 nfrontini Exp $
 */
package org.drarch.engine.ruleModel.util;

import java.util.List;

import org.drarch.engine.ruleModel.DrarchFileModel;
import org.drarch.engine.ruleModel.Fact;
import org.drarch.engine.ruleModel.FactSet;
import org.drarch.engine.ruleModel.Query;
import org.drarch.engine.ruleModel.Rule;
import org.drarch.engine.ruleModel.RuleModelPackage;
import org.drarch.engine.ruleModel.Var;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;


/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance
 * hierarchy. It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object and proceeding up the
 * inheritance hierarchy until a non-null result is returned, which is the
 * result of the switch. <!-- end-user-doc -->
 * 
 * @see org.design.rules4Java.engine.ruleModel.RuleModelPackage
 * @generated
 */
public class RuleModelSwitch {
  /**
   * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected static RuleModelPackage modelPackage;

  /**
   * Creates an instance of the switch. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   */
  public RuleModelSwitch() {
    if (modelPackage == null) {
      modelPackage = RuleModelPackage.eINSTANCE;
    }
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns
   * a non null result; it yields that result. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @return the first non-null result returned by a <code>caseXXX</code>
   *         call.
   * @generated
   */
  public Object doSwitch(EObject theEObject) {
    return doSwitch(theEObject.eClass(), theEObject);
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns
   * a non null result; it yields that result. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @return the first non-null result returned by a <code>caseXXX</code>
   *         call.
   * @generated
   */
  protected Object doSwitch(EClass theEClass, EObject theEObject) {
    if (theEClass.eContainer() == modelPackage) {
      return doSwitch(theEClass.getClassifierID(), theEObject);
    } else {
      List eSuperTypes = theEClass.getESuperTypes();
      return eSuperTypes.isEmpty() ? defaultCase(theEObject) : doSwitch(
          (EClass) eSuperTypes.get(0), theEObject);
    }
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns
   * a non null result; it yields that result. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @return the first non-null result returned by a <code>caseXXX</code>
   *         call.
   * @generated
   */
  protected Object doSwitch(int classifierID, EObject theEObject) {
    switch (classifierID) {
    case RuleModelPackage.DRARCH_FILE_MODEL: {
      DrarchFileModel drarchFileModel = (DrarchFileModel) theEObject;
      Object result = caseDrarchFileModel(drarchFileModel);
      if (result == null) result = defaultCase(theEObject);
      return result;
    }
    case RuleModelPackage.FACT: {
      Fact fact = (Fact) theEObject;
      Object result = caseFact(fact);
      if (result == null) result = defaultCase(theEObject);
      return result;
    }
    case RuleModelPackage.FACT_SET: {
      FactSet factSet = (FactSet) theEObject;
      Object result = caseFactSet(factSet);
      if (result == null) result = defaultCase(theEObject);
      return result;
    }
    case RuleModelPackage.QUERY: {
      Query query = (Query) theEObject;
      Object result = caseQuery(query);
      if (result == null) result = defaultCase(theEObject);
      return result;
    }
    case RuleModelPackage.RULE: {
      Rule rule = (Rule) theEObject;
      Object result = caseRule(rule);
      if (result == null) result = defaultCase(theEObject);
      return result;
    }
    case RuleModelPackage.VAR: {
      Var var = (Var) theEObject;
      Object result = caseVar(var);
      if (result == null) result = defaultCase(theEObject);
      return result;
    }
    default:
      return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Drarch File Model</em>'.
   * <!-- begin-user-doc --> This implementation returns null; returning a
   * non-null result will terminate the switch. <!-- end-user-doc -->
   * 
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Drarch File Model</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseDrarchFileModel(DrarchFileModel object) {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Fact</em>'.
   * <!-- begin-user-doc --> This implementation returns null; returning a
   * non-null result will terminate the switch. <!-- end-user-doc -->
   * 
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Fact</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseFact(Fact object) {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Fact Set</em>'.
   * <!-- begin-user-doc --> This implementation returns null; returning a
   * non-null result will terminate the switch. <!-- end-user-doc -->
   * 
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Fact Set</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseFactSet(FactSet object) {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Query</em>'.
   * <!-- begin-user-doc --> This implementation returns null; returning a
   * non-null result will terminate the switch. <!-- end-user-doc -->
   * 
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Query</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseQuery(Query object) {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Rule</em>'.
   * <!-- begin-user-doc --> This implementation returns null; returning a
   * non-null result will terminate the switch. <!-- end-user-doc -->
   * 
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Rule</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseRule(Rule object) {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Var</em>'.
   * <!-- begin-user-doc --> This implementation returns null; returning a
   * non-null result will terminate the switch. <!-- end-user-doc -->
   * 
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Var</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseVar(Var object) {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>EObject</em>'.
   * <!-- begin-user-doc --> This implementation returns null; returning a
   * non-null result will terminate the switch, but this is the last case
   * anyway. <!-- end-user-doc -->
   * 
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>EObject</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject)
   * @generated
   */
  public Object defaultCase(EObject object) {
    return null;
  }

} // RuleModelSwitch
