/**
 * <copyright> </copyright>
 * 
 * $Id: UCMDiagramImpl.java,v 1.1 2006/10/07 19:05:13 nfrontini Exp $
 */
package org.design.drarch.diagram.DiagramModel.ucmModel.impl;

import org.design.drarch.diagram.DiagramModel.ucmModel.UCMDiagram;
import org.design.drarch.diagram.DiagramModel.ucmModel.UCMModel;
import org.design.drarch.diagram.DiagramModel.ucmModel.UcmModelPackage;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;


/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>UCM Diagram</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.design.drarch.diagram.DiagramModel.ucmModel.impl.UCMDiagramImpl#getModel <em>Model</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class UCMDiagramImpl extends EObjectImpl implements UCMDiagram {
  /**
   * The cached value of the '{@link #getModel() <em>Model</em>}' reference.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getModel()
   * @generated
   * @ordered
   */
  protected UCMModel model = null;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected UCMDiagramImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected EClass eStaticClass() {
    return UcmModelPackage.eINSTANCE.getUCMDiagram();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public UCMModel getModel() {
    if (model != null && model.eIsProxy()) {
      UCMModel oldModel = model;
      model = (UCMModel) eResolveProxy((InternalEObject) model);
      if (model != oldModel) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE,
              UcmModelPackage.UCM_DIAGRAM__MODEL, oldModel, model));
      }
    }
    return model;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public UCMModel basicGetModel() {
    return model;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public void setModel(UCMModel newModel) {
    UCMModel oldModel = model;
    model = newModel;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          UcmModelPackage.UCM_DIAGRAM__MODEL, oldModel, model));
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public Object eGet(EStructuralFeature eFeature, boolean resolve) {
    switch (eDerivedStructuralFeatureID(eFeature)) {
    case UcmModelPackage.UCM_DIAGRAM__MODEL:
      if (resolve) return getModel();
      return basicGetModel();
    }
    return eDynamicGet(eFeature, resolve);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public void eSet(EStructuralFeature eFeature, Object newValue) {
    switch (eDerivedStructuralFeatureID(eFeature)) {
    case UcmModelPackage.UCM_DIAGRAM__MODEL:
      setModel((UCMModel) newValue);
      return;
    }
    eDynamicSet(eFeature, newValue);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public void eUnset(EStructuralFeature eFeature) {
    switch (eDerivedStructuralFeatureID(eFeature)) {
    case UcmModelPackage.UCM_DIAGRAM__MODEL:
      setModel((UCMModel) null);
      return;
    }
    eDynamicUnset(eFeature);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public boolean eIsSet(EStructuralFeature eFeature) {
    switch (eDerivedStructuralFeatureID(eFeature)) {
    case UcmModelPackage.UCM_DIAGRAM__MODEL:
      return model != null;
    }
    return eDynamicIsSet(eFeature);
  }

} // UCMDiagramImpl
