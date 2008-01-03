/**
 * <copyright> </copyright>
 * 
 * $Id: UcmModelAdapterFactory.java,v 1.1 2006/10/07 19:05:19 nfrontini Exp $
 */
package org.drarch.diagram.DiagramModel.ucmModel.util;

import org.drarch.diagram.DiagramModel.ucmModel.ComponentRole;
import org.drarch.diagram.DiagramModel.ucmModel.Path;
import org.drarch.diagram.DiagramModel.ucmModel.PathNode;
import org.drarch.diagram.DiagramModel.ucmModel.UCMDiagram;
import org.drarch.diagram.DiagramModel.ucmModel.UCMModel;
import org.drarch.diagram.DiagramModel.ucmModel.UcmModelPackage;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;


/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It
 * provides an adapter <code>createXXX</code> method for each class of the
 * model. <!-- end-user-doc -->
 * 
 * @see org.design.drarch.diagram.DiagramModel.ucmModel.UcmModelPackage
 * @generated
 */
public class UcmModelAdapterFactory extends AdapterFactoryImpl {
  /**
   * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected static UcmModelPackage modelPackage;

  /**
   * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   */
  public UcmModelAdapterFactory() {
    if (modelPackage == null) {
      modelPackage = UcmModelPackage.eINSTANCE;
    }
  }

  /**
   * Returns whether this factory is applicable for the type of the object. <!--
   * begin-user-doc --> This implementation returns <code>true</code> if the
   * object is either the model's package or is an instance object of the model.
   * <!-- end-user-doc -->
   * 
   * @return whether this factory is applicable for the type of the object.
   * @generated
   */
  public boolean isFactoryForType(Object object) {
    if (object == modelPackage) {
      return true;
    }
    if (object instanceof EObject) {
      return ((EObject) object).eClass().getEPackage() == modelPackage;
    }
    return false;
  }

  /**
   * The switch the delegates to the <code>createXXX</code> methods. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected UcmModelSwitch modelSwitch = new UcmModelSwitch() {
    public Object caseComponentRole(ComponentRole object) {
      return createComponentRoleAdapter();
    }

    public Object casePath(Path object) {
      return createPathAdapter();
    }

    public Object casePathNode(PathNode object) {
      return createPathNodeAdapter();
    }

    public Object caseUCMDiagram(UCMDiagram object) {
      return createUCMDiagramAdapter();
    }

    public Object caseUCMModel(UCMModel object) {
      return createUCMModelAdapter();
    }

    public Object defaultCase(EObject object) {
      return createEObjectAdapter();
    }
  };

  /**
   * Creates an adapter for the <code>target</code>. <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * 
   * @param target the object to adapt.
   * @return the adapter for the <code>target</code>.
   * @generated
   */
  public Adapter createAdapter(Notifier target) {
    return (Adapter) modelSwitch.doSwitch((EObject) target);
  }


  /**
   * Creates a new adapter for an object of class '{@link org.design.drarch.diagram.DiagramModel.ucmModel.ComponentRole <em>Component Role</em>}'.
   * <!-- begin-user-doc --> This default implementation returns null so that we
   * can easily ignore cases; it's useful to ignore a case when inheritance will
   * catch all the cases anyway. <!-- end-user-doc -->
   * 
   * @return the new adapter.
   * @see org.design.drarch.diagram.DiagramModel.ucmModel.ComponentRole
   * @generated
   */
  public Adapter createComponentRoleAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.design.drarch.diagram.DiagramModel.ucmModel.Path <em>Path</em>}'.
   * <!-- begin-user-doc --> This default implementation returns null so that we
   * can easily ignore cases; it's useful to ignore a case when inheritance will
   * catch all the cases anyway. <!-- end-user-doc -->
   * 
   * @return the new adapter.
   * @see org.design.drarch.diagram.DiagramModel.ucmModel.Path
   * @generated
   */
  public Adapter createPathAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.design.drarch.diagram.DiagramModel.ucmModel.PathNode <em>Path Node</em>}'.
   * <!-- begin-user-doc --> This default implementation returns null so that we
   * can easily ignore cases; it's useful to ignore a case when inheritance will
   * catch all the cases anyway. <!-- end-user-doc -->
   * 
   * @return the new adapter.
   * @see org.design.drarch.diagram.DiagramModel.ucmModel.PathNode
   * @generated
   */
  public Adapter createPathNodeAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.design.drarch.diagram.DiagramModel.ucmModel.UCMDiagram <em>UCM Diagram</em>}'.
   * <!-- begin-user-doc --> This default implementation returns null so that we
   * can easily ignore cases; it's useful to ignore a case when inheritance will
   * catch all the cases anyway. <!-- end-user-doc -->
   * 
   * @return the new adapter.
   * @see org.design.drarch.diagram.DiagramModel.ucmModel.UCMDiagram
   * @generated
   */
  public Adapter createUCMDiagramAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.design.drarch.diagram.DiagramModel.ucmModel.UCMModel <em>UCM Model</em>}'.
   * <!-- begin-user-doc --> This default implementation returns null so that we
   * can easily ignore cases; it's useful to ignore a case when inheritance will
   * catch all the cases anyway. <!-- end-user-doc -->
   * 
   * @return the new adapter.
   * @see org.design.drarch.diagram.DiagramModel.ucmModel.UCMModel
   * @generated
   */
  public Adapter createUCMModelAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for the default case. <!-- begin-user-doc --> This
   * default implementation returns null. <!-- end-user-doc -->
   * 
   * @return the new adapter.
   * @generated
   */
  public Adapter createEObjectAdapter() {
    return null;
  }

} // UcmModelAdapterFactory
