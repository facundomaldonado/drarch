/**
 * <copyright> </copyright>
 * 
 * $Id: PathNodeImpl.java,v 1.2 2006/10/12 06:59:35 nfrontini Exp $
 */
package org.design.drarch.diagram.DiagramModel.ucmModel.impl;

import java.util.Collection;

import org.design.drarch.diagram.DiagramModel.componentModel.Component;
import org.design.drarch.diagram.DiagramModel.ucmModel.Path;
import org.design.drarch.diagram.DiagramModel.ucmModel.PathNode;
import org.design.drarch.diagram.DiagramModel.ucmModel.UcmModelPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;


/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Path Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.design.drarch.diagram.DiagramModel.ucmModel.impl.PathNodeImpl#getPath <em>Path</em>}</li>
 * <li>{@link org.design.drarch.diagram.DiagramModel.ucmModel.impl.PathNodeImpl#getPrevious <em>Previous</em>}</li>
 * <li>{@link org.design.drarch.diagram.DiagramModel.ucmModel.impl.PathNodeImpl#getNext <em>Next</em>}</li>
 * <li>{@link org.design.drarch.diagram.DiagramModel.ucmModel.impl.PathNodeImpl#getResponsibilityName <em>Responsibility Name</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class PathNodeImpl extends EObjectImpl implements PathNode {
  /**
   * The cached value of the '{@link #getPath() <em>Path</em>}' reference.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getPath()
   * @generated
   * @ordered
   */
  protected Path path = null;

  /**
   * The cached value of the '{@link #getPrevious() <em>Previous</em>}'
   * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getPrevious()
   * @generated
   * @ordered
   */
  protected EList previous = null;

  /**
   * The cached value of the '{@link #getNext() <em>Next</em>}' reference
   * list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getNext()
   * @generated
   * @ordered
   */
  protected EList next = null;

  /**
   * The default value of the '{@link #getResponsibilityName() <em>Responsibility Name</em>}'
   * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getResponsibilityName()
   * @generated
   * @ordered
   */
  protected static final String RESPONSIBILITY_NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getResponsibilityName() <em>Responsibility Name</em>}'
   * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getResponsibilityName()
   * @generated
   * @ordered
   */
  protected String responsibilityName = RESPONSIBILITY_NAME_EDEFAULT;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected PathNodeImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected EClass eStaticClass() {
    return UcmModelPackage.eINSTANCE.getPathNode();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public Path getPath() {
    if (path != null && path.eIsProxy()) {
      Path oldPath = path;
      path = (Path) eResolveProxy((InternalEObject) path);
      if (path != oldPath) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE,
              UcmModelPackage.PATH_NODE__PATH, oldPath, path));
      }
    }
    return path;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public Path basicGetPath() {
    return path;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public NotificationChain basicSetPath(Path newPath, NotificationChain msgs) {
    Path oldPath = path;
    path = newPath;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this,
          Notification.SET, UcmModelPackage.PATH_NODE__PATH, oldPath, newPath);
      if (msgs == null)
        msgs = notification;
      else
        msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public void setPath(Path newPath) {
    if (newPath != path) {
      NotificationChain msgs = null;
      if (path != null)
        msgs = ((InternalEObject) path).eInverseRemove(this,
            UcmModelPackage.PATH__NODES, Path.class, msgs);
      if (newPath != null)
        msgs = ((InternalEObject) newPath).eInverseAdd(this,
            UcmModelPackage.PATH__NODES, Path.class, msgs);
      msgs = basicSetPath(newPath, msgs);
      if (msgs != null) msgs.dispatch();
    } else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          UcmModelPackage.PATH_NODE__PATH, newPath, newPath));
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EList getPrevious() {
    if (previous == null) {
      previous = new EObjectWithInverseResolvingEList.ManyInverse(
          PathNode.class, this, UcmModelPackage.PATH_NODE__PREVIOUS,
          UcmModelPackage.PATH_NODE__NEXT);
    }
    return previous;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EList getNext() {
    if (next == null) {
      next = new EObjectWithInverseResolvingEList.ManyInverse(PathNode.class,
          this, UcmModelPackage.PATH_NODE__NEXT,
          UcmModelPackage.PATH_NODE__PREVIOUS);
    }
    return next;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public String getResponsibilityName() {
    return responsibilityName;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public void setResponsibilityName(String newResponsibilityName) {
    String oldResponsibilityName = responsibilityName;
    responsibilityName = newResponsibilityName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          UcmModelPackage.PATH_NODE__RESPONSIBILITY_NAME,
          oldResponsibilityName, responsibilityName));
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID,
      Class baseClass, NotificationChain msgs) {
    if (featureID >= 0) {
      switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
      case UcmModelPackage.PATH_NODE__PATH:
        if (path != null)
          msgs = ((InternalEObject) path).eInverseRemove(this,
              UcmModelPackage.PATH__NODES, Path.class, msgs);
        return basicSetPath((Path) otherEnd, msgs);
      case UcmModelPackage.PATH_NODE__PREVIOUS:
        return ((InternalEList) getPrevious()).basicAdd(otherEnd, msgs);
      case UcmModelPackage.PATH_NODE__NEXT:
        return ((InternalEList) getNext()).basicAdd(otherEnd, msgs);
      default:
        return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
      }
    }
    if (eContainer != null) msgs = eBasicRemoveFromContainer(msgs);
    return eBasicSetContainer(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public NotificationChain eInverseRemove(InternalEObject otherEnd,
      int featureID, Class baseClass, NotificationChain msgs) {
    if (featureID >= 0) {
      switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
      case UcmModelPackage.PATH_NODE__PATH:
        return basicSetPath(null, msgs);
      case UcmModelPackage.PATH_NODE__PREVIOUS:
        return ((InternalEList) getPrevious()).basicRemove(otherEnd, msgs);
      case UcmModelPackage.PATH_NODE__NEXT:
        return ((InternalEList) getNext()).basicRemove(otherEnd, msgs);
      default:
        return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
      }
    }
    return eBasicSetContainer(null, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public Object eGet(EStructuralFeature eFeature, boolean resolve) {
    switch (eDerivedStructuralFeatureID(eFeature)) {
    case UcmModelPackage.PATH_NODE__PATH:
      if (resolve) return getPath();
      return basicGetPath();
    case UcmModelPackage.PATH_NODE__PREVIOUS:
      return getPrevious();
    case UcmModelPackage.PATH_NODE__NEXT:
      return getNext();
    case UcmModelPackage.PATH_NODE__RESPONSIBILITY_NAME:
      return getResponsibilityName();
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
    case UcmModelPackage.PATH_NODE__PATH:
      setPath((Path) newValue);
      return;
    case UcmModelPackage.PATH_NODE__PREVIOUS:
      getPrevious().clear();
      getPrevious().addAll((Collection) newValue);
      return;
    case UcmModelPackage.PATH_NODE__NEXT:
      getNext().clear();
      getNext().addAll((Collection) newValue);
      return;
    case UcmModelPackage.PATH_NODE__RESPONSIBILITY_NAME:
      setResponsibilityName((String) newValue);
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
    case UcmModelPackage.PATH_NODE__PATH:
      setPath((Path) null);
      return;
    case UcmModelPackage.PATH_NODE__PREVIOUS:
      getPrevious().clear();
      return;
    case UcmModelPackage.PATH_NODE__NEXT:
      getNext().clear();
      return;
    case UcmModelPackage.PATH_NODE__RESPONSIBILITY_NAME:
      setResponsibilityName(RESPONSIBILITY_NAME_EDEFAULT);
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
    case UcmModelPackage.PATH_NODE__PATH:
      return path != null;
    case UcmModelPackage.PATH_NODE__PREVIOUS:
      return previous != null && !previous.isEmpty();
    case UcmModelPackage.PATH_NODE__NEXT:
      return next != null && !next.isEmpty();
    case UcmModelPackage.PATH_NODE__RESPONSIBILITY_NAME:
      return RESPONSIBILITY_NAME_EDEFAULT == null ? responsibilityName != null
          : !RESPONSIBILITY_NAME_EDEFAULT.equals(responsibilityName);
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
    result.append(" (responsibilityName: ");
    result.append(responsibilityName);
    result.append(')');
    return result.toString();
  }

  @SuppressWarnings("unchecked")
  public boolean addPrevious(PathNode previous) {
    return getPrevious().add(previous);
  }

  public boolean removePrevious(PathNode previous) {
    return getPrevious().remove(previous);
  }

  @SuppressWarnings("unchecked")
  public boolean addNext(PathNode next) {
    return getNext().add(next);
  }

  public boolean removeNext(PathNode next) {
    return getNext().remove(next);
  }

  public boolean isStart() {
    return getPrevious().size() == 0;
  }

  public boolean isEnd() {
    return getNext().size() == 0;
  }

  protected String comment = "Suggested names:\n";

  public void addComment(String string) {
    this.comment = this.comment + string;

  }


  public String getComment() {
    return this.comment;
  }

  private Component component;
  public Component getAsociatedComponent() {
	  return this.component;
  }

  public void setAsociatedComponent(Component component) {
 	this.component = component;
  }
} // PathNodeImpl
