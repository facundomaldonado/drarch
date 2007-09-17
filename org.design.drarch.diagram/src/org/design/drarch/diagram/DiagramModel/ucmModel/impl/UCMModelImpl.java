/**
 * <copyright> </copyright>
 * 
 * $Id: UCMModelImpl.java,v 1.2 2006/10/12 06:59:35 nfrontini Exp $
 */
package org.design.drarch.diagram.DiagramModel.ucmModel.impl;

import java.util.Collection;

import org.design.drarch.diagram.DiagramModel.ucmModel.ComponentRole;
import org.design.drarch.diagram.DiagramModel.ucmModel.Path;
import org.design.drarch.diagram.DiagramModel.ucmModel.UCMModel;
import org.design.drarch.diagram.DiagramModel.ucmModel.UcmModelPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;


/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>UCM Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.design.drarch.diagram.DiagramModel.ucmModel.impl.UCMModelImpl#getName <em>Name</em>}</li>
 * <li>{@link org.design.drarch.diagram.DiagramModel.ucmModel.impl.UCMModelImpl#getPaths <em>Paths</em>}</li>
 * <li>{@link org.design.drarch.diagram.DiagramModel.ucmModel.impl.UCMModelImpl#getComponentRoles <em>Component Roles</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class UCMModelImpl extends EObjectImpl implements UCMModel {
  /**
   * The default value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getName()
   * @generated
   * @ordered
   */
  protected static final String NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getName()
   * @generated
   * @ordered
   */
  protected String name = NAME_EDEFAULT;

  /**
   * The cached value of the '{@link #getPaths() <em>Paths</em>}' reference
   * list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getPaths()
   * @generated
   * @ordered
   */
  protected EList paths = null;

  /**
   * The cached value of the '{@link #getComponentRoles() <em>Component Roles</em>}'
   * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getComponentRoles()
   * @generated
   * @ordered
   */
  protected EList componentRoles = null;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected UCMModelImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected EClass eStaticClass() {
    return UcmModelPackage.eINSTANCE.getUCMModel();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public String getName() {
    return name;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public void setName(String newName) {
    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          UcmModelPackage.UCM_MODEL__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EList getPaths() {
    if (paths == null) {
      paths = new EObjectResolvingEList(Path.class, this,
          UcmModelPackage.UCM_MODEL__PATHS);
    }
    return paths;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EList getComponentRoles() {
    if (componentRoles == null) {
      componentRoles = new EObjectResolvingEList(ComponentRole.class, this,
          UcmModelPackage.UCM_MODEL__COMPONENT_ROLES);
    }
    return componentRoles;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public Object eGet(EStructuralFeature eFeature, boolean resolve) {
    switch (eDerivedStructuralFeatureID(eFeature)) {
    case UcmModelPackage.UCM_MODEL__NAME:
      return getName();
    case UcmModelPackage.UCM_MODEL__PATHS:
      return getPaths();
    case UcmModelPackage.UCM_MODEL__COMPONENT_ROLES:
      return getComponentRoles();
    }
    return eDynamicGet(eFeature, resolve);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @SuppressWarnings("unchecked")
  public void eSet(EStructuralFeature eFeature, Object newValue) {
    switch (eDerivedStructuralFeatureID(eFeature)) {
    case UcmModelPackage.UCM_MODEL__NAME:
      setName((String) newValue);
      return;
    case UcmModelPackage.UCM_MODEL__PATHS:
      getPaths().clear();
      getPaths().addAll((Collection) newValue);
      return;
    case UcmModelPackage.UCM_MODEL__COMPONENT_ROLES:
      getComponentRoles().clear();
      getComponentRoles().addAll((Collection) newValue);
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
    case UcmModelPackage.UCM_MODEL__NAME:
      setName(NAME_EDEFAULT);
      return;
    case UcmModelPackage.UCM_MODEL__PATHS:
      getPaths().clear();
      return;
    case UcmModelPackage.UCM_MODEL__COMPONENT_ROLES:
      getComponentRoles().clear();
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
    case UcmModelPackage.UCM_MODEL__NAME:
      return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
    case UcmModelPackage.UCM_MODEL__PATHS:
      return paths != null && !paths.isEmpty();
    case UcmModelPackage.UCM_MODEL__COMPONENT_ROLES:
      return componentRoles != null && !componentRoles.isEmpty();
    }
    return eDynamicIsSet(eFeature);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public String toString() {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (name: ");
    result.append(name);
    result.append(')');
    return result.toString();
  }

} // UCMModelImpl