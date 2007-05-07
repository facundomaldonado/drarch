/**
 * <copyright> </copyright>
 * 
 * $Id: ResponsibilityImpl.java,v 1.2 2006/10/12 06:59:36 nfrontini Exp $
 */
package org.design.drarch.diagram.trace.logModel.impl;

import java.util.Collection;

import org.design.drarch.diagram.trace.logModel.InnerTag;
import org.design.drarch.diagram.trace.logModel.LogModelPackage;
import org.design.drarch.diagram.trace.logModel.Responsibility;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;


/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Responsibility</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.design.drarch.diagram.trace.logModel.impl.ResponsibilityImpl#getName <em>Name</em>}</li>
 * <li>{@link org.design.drarch.diagram.trace.logModel.impl.ResponsibilityImpl#getMaterializations <em>Materializations</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class ResponsibilityImpl extends EObjectImpl implements Responsibility {
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
   * The cached value of the '{@link #getExecutions() <em>Executions</em>}'
   * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getExecutions()
   * @generated
   * @ordered
   */
  protected EList executions = null;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected ResponsibilityImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected EClass eStaticClass() {
    return LogModelPackage.eINSTANCE.getResponsibility();
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
          LogModelPackage.RESPONSIBILITY__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EList getExecutions() {
    if (executions == null) {
      executions = new EObjectResolvingEList(InnerTag.class, this,
          LogModelPackage.RESPONSIBILITY__EXECUTIONS);
    }
    return executions;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public Object eGet(EStructuralFeature eFeature, boolean resolve) {
    switch (eDerivedStructuralFeatureID(eFeature)) {
    case LogModelPackage.RESPONSIBILITY__NAME:
      return getName();
    case LogModelPackage.RESPONSIBILITY__EXECUTIONS:
      return getExecutions();
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
    case LogModelPackage.RESPONSIBILITY__NAME:
      setName((String) newValue);
      return;
    case LogModelPackage.RESPONSIBILITY__EXECUTIONS:
      getExecutions().clear();
      getExecutions().addAll((Collection) newValue);
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
    case LogModelPackage.RESPONSIBILITY__NAME:
      setName(NAME_EDEFAULT);
      return;
    case LogModelPackage.RESPONSIBILITY__EXECUTIONS:
      getExecutions().clear();
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
    case LogModelPackage.RESPONSIBILITY__NAME:
      return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
    case LogModelPackage.RESPONSIBILITY__EXECUTIONS:
      return executions != null && !executions.isEmpty();
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

} // ResponsibilityImpl
