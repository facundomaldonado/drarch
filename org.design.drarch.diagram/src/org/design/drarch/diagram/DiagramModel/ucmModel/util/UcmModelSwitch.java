/**
 * <copyright> </copyright>
 * 
 * $Id: UcmModelSwitch.java,v 1.1 2006/10/07 19:05:19 nfrontini Exp $
 */
package org.design.drarch.diagram.DiagramModel.ucmModel.util;

import java.util.List;

import org.design.drarch.diagram.DiagramModel.ucmModel.ComponentRole;
import org.design.drarch.diagram.DiagramModel.ucmModel.Path;
import org.design.drarch.diagram.DiagramModel.ucmModel.PathNode;
import org.design.drarch.diagram.DiagramModel.ucmModel.UCMDiagram;
import org.design.drarch.diagram.DiagramModel.ucmModel.UCMModel;
import org.design.drarch.diagram.DiagramModel.ucmModel.UcmModelPackage;
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
 * @see org.design.drarch.diagram.DiagramModel.ucmModel.UcmModelPackage
 * @generated
 */
public class UcmModelSwitch {
  /**
   * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected static UcmModelPackage modelPackage;

  /**
   * Creates an instance of the switch. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   */
  public UcmModelSwitch() {
    if (modelPackage == null) {
      modelPackage = UcmModelPackage.eINSTANCE;
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
    case UcmModelPackage.COMPONENT_ROLE: {
      ComponentRole componentRole = (ComponentRole) theEObject;
      Object result = caseComponentRole(componentRole);
      if (result == null) result = defaultCase(theEObject);
      return result;
    }
    case UcmModelPackage.PATH: {
      Path path = (Path) theEObject;
      Object result = casePath(path);
      if (result == null) result = defaultCase(theEObject);
      return result;
    }
    case UcmModelPackage.PATH_NODE: {
      PathNode pathNode = (PathNode) theEObject;
      Object result = casePathNode(pathNode);
      if (result == null) result = defaultCase(theEObject);
      return result;
    }
    case UcmModelPackage.UCM_DIAGRAM: {
      UCMDiagram ucmDiagram = (UCMDiagram) theEObject;
      Object result = caseUCMDiagram(ucmDiagram);
      if (result == null) result = defaultCase(theEObject);
      return result;
    }
    case UcmModelPackage.UCM_MODEL: {
      UCMModel ucmModel = (UCMModel) theEObject;
      Object result = caseUCMModel(ucmModel);
      if (result == null) result = defaultCase(theEObject);
      return result;
    }
    default:
      return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Component Role</em>'.
   * <!-- begin-user-doc --> This implementation returns null; returning a
   * non-null result will terminate the switch. <!-- end-user-doc -->
   * 
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Component Role</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseComponentRole(ComponentRole object) {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Path</em>'.
   * <!-- begin-user-doc --> This implementation returns null; returning a
   * non-null result will terminate the switch. <!-- end-user-doc -->
   * 
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Path</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object casePath(Path object) {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Path Node</em>'.
   * <!-- begin-user-doc --> This implementation returns null; returning a
   * non-null result will terminate the switch. <!-- end-user-doc -->
   * 
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Path Node</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object casePathNode(PathNode object) {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>UCM Diagram</em>'.
   * <!-- begin-user-doc --> This implementation returns null; returning a
   * non-null result will terminate the switch. <!-- end-user-doc -->
   * 
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>UCM Diagram</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseUCMDiagram(UCMDiagram object) {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>UCM Model</em>'.
   * <!-- begin-user-doc --> This implementation returns null; returning a
   * non-null result will terminate the switch. <!-- end-user-doc -->
   * 
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>UCM Model</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseUCMModel(UCMModel object) {
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

} // UcmModelSwitch
