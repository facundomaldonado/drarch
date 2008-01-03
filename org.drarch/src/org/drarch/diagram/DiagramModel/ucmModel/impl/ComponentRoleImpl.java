/**
 * <copyright> </copyright>
 * 
 * $Id: ComponentRoleImpl.java,v 1.1 2006/10/07 19:05:13 nfrontini Exp $
 */
package org.drarch.diagram.DiagramModel.ucmModel.impl;

import org.drarch.diagram.DiagramModel.componentModel.Component;
import org.drarch.diagram.DiagramModel.ucmModel.ComponentRole;
import org.drarch.diagram.DiagramModel.ucmModel.UcmModelPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;


/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Component Role</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.design.drarch.diagram.DiagramModel.ucmModel.impl.ComponentRoleImpl#getName <em>Name</em>}</li>
 * <li>{@link org.design.drarch.diagram.DiagramModel.ucmModel.impl.ComponentRoleImpl#getDefinedClass <em>Defined Class</em>}</li>
 * <li>{@link org.design.drarch.diagram.DiagramModel.ucmModel.impl.ComponentRoleImpl#getBehaviorName <em>Behavior Name</em>}</li>
 * <li>{@link org.design.drarch.diagram.DiagramModel.ucmModel.impl.ComponentRoleImpl#getComponent <em>Component</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class ComponentRoleImpl extends EObjectImpl implements ComponentRole {
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
   * The default value of the '{@link #getDefinedClass() <em>Defined Class</em>}'
   * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getDefinedClass()
   * @generated
   * @ordered
   */
  protected static final String DEFINED_CLASS_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getDefinedClass() <em>Defined Class</em>}'
   * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getDefinedClass()
   * @generated
   * @ordered
   */
  protected String definedClass = DEFINED_CLASS_EDEFAULT;

  /**
   * The default value of the '{@link #getBehaviorName() <em>Behavior Name</em>}'
   * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getBehaviorName()
   * @generated
   * @ordered
   */
  protected static final String BEHAVIOR_NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getBehaviorName() <em>Behavior Name</em>}'
   * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getBehaviorName()
   * @generated
   * @ordered
   */
  protected String behaviorName = BEHAVIOR_NAME_EDEFAULT;

  /**
   * The default value of the '{@link #getComponent() <em>Component</em>}'
   * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getComponent()
   * @generated
   * @ordered
   */
  protected static final Component COMPONENT_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getComponent() <em>Component</em>}'
   * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getComponent()
   * @generated
   * @ordered
   */
  protected Component component = COMPONENT_EDEFAULT;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected ComponentRoleImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected EClass eStaticClass() {
    return UcmModelPackage.eINSTANCE.getComponentRole();
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
          UcmModelPackage.COMPONENT_ROLE__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public String getDefinedClass() {
    return definedClass;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public void setDefinedClass(String newDefinedClass) {
    String oldDefinedClass = definedClass;
    definedClass = newDefinedClass;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          UcmModelPackage.COMPONENT_ROLE__DEFINED_CLASS, oldDefinedClass,
          definedClass));
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public String getBehaviorName() {
    return behaviorName;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public void setBehaviorName(String newBehaviorName) {
    String oldBehaviorName = behaviorName;
    behaviorName = newBehaviorName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          UcmModelPackage.COMPONENT_ROLE__BEHAVIOR_NAME, oldBehaviorName,
          behaviorName));
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public Component getComponent() {
    return component;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public void setComponent(Component newComponent) {
    Component oldComponent = component;
    component = newComponent;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          UcmModelPackage.COMPONENT_ROLE__COMPONENT, oldComponent, component));
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public Object eGet(EStructuralFeature eFeature, boolean resolve) {
    switch (eDerivedStructuralFeatureID(eFeature)) {
    case UcmModelPackage.COMPONENT_ROLE__NAME:
      return getName();
    case UcmModelPackage.COMPONENT_ROLE__DEFINED_CLASS:
      return getDefinedClass();
    case UcmModelPackage.COMPONENT_ROLE__BEHAVIOR_NAME:
      return getBehaviorName();
    case UcmModelPackage.COMPONENT_ROLE__COMPONENT:
      return getComponent();
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
    case UcmModelPackage.COMPONENT_ROLE__NAME:
      setName((String) newValue);
      return;
    case UcmModelPackage.COMPONENT_ROLE__DEFINED_CLASS:
      setDefinedClass((String) newValue);
      return;
    case UcmModelPackage.COMPONENT_ROLE__BEHAVIOR_NAME:
      setBehaviorName((String) newValue);
      return;
    case UcmModelPackage.COMPONENT_ROLE__COMPONENT:
      setComponent((Component) newValue);
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
    case UcmModelPackage.COMPONENT_ROLE__NAME:
      setName(NAME_EDEFAULT);
      return;
    case UcmModelPackage.COMPONENT_ROLE__DEFINED_CLASS:
      setDefinedClass(DEFINED_CLASS_EDEFAULT);
      return;
    case UcmModelPackage.COMPONENT_ROLE__BEHAVIOR_NAME:
      setBehaviorName(BEHAVIOR_NAME_EDEFAULT);
      return;
    case UcmModelPackage.COMPONENT_ROLE__COMPONENT:
      setComponent(COMPONENT_EDEFAULT);
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
    case UcmModelPackage.COMPONENT_ROLE__NAME:
      return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
    case UcmModelPackage.COMPONENT_ROLE__DEFINED_CLASS:
      return DEFINED_CLASS_EDEFAULT == null ? definedClass != null
          : !DEFINED_CLASS_EDEFAULT.equals(definedClass);
    case UcmModelPackage.COMPONENT_ROLE__BEHAVIOR_NAME:
      return BEHAVIOR_NAME_EDEFAULT == null ? behaviorName != null
          : !BEHAVIOR_NAME_EDEFAULT.equals(behaviorName);
    case UcmModelPackage.COMPONENT_ROLE__COMPONENT:
      return COMPONENT_EDEFAULT == null ? component != null
          : !COMPONENT_EDEFAULT.equals(component);
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
    result.append(", definedClass: ");
    result.append(definedClass);
    result.append(", behaviorName: ");
    result.append(behaviorName);
    result.append(", component: ");
    result.append(component);
    result.append(')');
    return result.toString();
  }

} // ComponentRoleImpl
