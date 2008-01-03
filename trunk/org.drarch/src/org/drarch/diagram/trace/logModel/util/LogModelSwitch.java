/**
 * <copyright> </copyright>
 * 
 * $Id: LogModelSwitch.java,v 1.1 2006/10/07 19:05:17 nfrontini Exp $
 */
package org.drarch.diagram.trace.logModel.util;

import java.util.List;
import java.util.Map;

import org.drarch.diagram.trace.logModel.InnerTag;
import org.drarch.diagram.trace.logModel.LogModelPackage;
import org.drarch.diagram.trace.logModel.LogNode;
import org.drarch.diagram.trace.logModel.PropertyLogNode;
import org.drarch.diagram.trace.logModel.Responsibility;
import org.drarch.diagram.trace.logModel.TagLogNode;
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
 * @see org.design.drarch.diagram.trace.logModel.LogModelPackage
 * @generated
 */
public class LogModelSwitch {
  /**
   * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected static LogModelPackage modelPackage;

  /**
   * Creates an instance of the switch. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   */
  public LogModelSwitch() {
    if (modelPackage == null) {
      modelPackage = LogModelPackage.eINSTANCE;
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
    case LogModelPackage.LOG_NODE: {
      LogNode logNode = (LogNode) theEObject;
      Object result = caseLogNode(logNode);
      if (result == null) result = defaultCase(theEObject);
      return result;
    }
    case LogModelPackage.INNERTAG: {
      InnerTag materialization = (InnerTag) theEObject;
      Object result = caseMaterialization(materialization);
      if (result == null) result = defaultCase(theEObject);
      return result;
    }
    case LogModelPackage.PROPERTY_LOG_NODE: {
      PropertyLogNode propertyLogNode = (PropertyLogNode) theEObject;
      Object result = casePropertyLogNode(propertyLogNode);
      if (result == null) result = caseLogNode(propertyLogNode);
      if (result == null) result = defaultCase(theEObject);
      return result;
    }
    case LogModelPackage.RESPONSIBILITY: {
      Responsibility responsibility = (Responsibility) theEObject;
      Object result = caseResponsibility(responsibility);
      if (result == null) result = defaultCase(theEObject);
      return result;
    }
    case LogModelPackage.TAG_LOG_NODE: {
      TagLogNode tagLogNode = (TagLogNode) theEObject;
      Object result = caseTagLogNode(tagLogNode);
      if (result == null) result = caseLogNode(tagLogNode);
      if (result == null) result = defaultCase(theEObject);
      return result;
    }
    case LogModelPackage.ESTRING_TO_LOG_NODE_MAP_ENTRY: {
      Map.Entry eStringToLogNodeMapEntry = (Map.Entry) theEObject;
      Object result = caseEStringToLogNodeMapEntry(eStringToLogNodeMapEntry);
      if (result == null) result = defaultCase(theEObject);
      return result;
    }
    default:
      return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Log Node</em>'.
   * <!-- begin-user-doc --> This implementation returns null; returning a
   * non-null result will terminate the switch. <!-- end-user-doc -->
   * 
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Log Node</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseLogNode(LogNode object) {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>InnerTag</em>'.
   * <!-- begin-user-doc --> This implementation returns null; returning a
   * non-null result will terminate the switch. <!-- end-user-doc -->
   * 
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>InnerTag</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseMaterialization(InnerTag object) {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Property Log Node</em>'.
   * <!-- begin-user-doc --> This implementation returns null; returning a
   * non-null result will terminate the switch. <!-- end-user-doc -->
   * 
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Property Log Node</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object casePropertyLogNode(PropertyLogNode object) {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Responsibility</em>'.
   * <!-- begin-user-doc --> This implementation returns null; returning a
   * non-null result will terminate the switch. <!-- end-user-doc -->
   * 
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Responsibility</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseResponsibility(Responsibility object) {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Tag Log Node</em>'.
   * <!-- begin-user-doc --> This implementation returns null; returning a
   * non-null result will terminate the switch. <!-- end-user-doc -->
   * 
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Tag Log Node</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseTagLogNode(TagLogNode object) {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>EString To Log Node Map Entry</em>'.
   * <!-- begin-user-doc --> This implementation returns null; returning a
   * non-null result will terminate the switch. <!-- end-user-doc -->
   * 
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>EString To Log Node Map Entry</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseEStringToLogNodeMapEntry(Map.Entry object) {
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

} // LogModelSwitch
