/**
 * <copyright> </copyright>
 * 
 * $Id: InnerTagImpl.java,v 1.2 2006/10/12 06:59:36 nfrontini Exp $
 */
package org.design.drarch.diagram.trace.logModel.impl;

import java.util.Collection;

import org.design.drarch.diagram.trace.logModel.InnerTag;
import org.design.drarch.diagram.trace.logModel.LogModelPackage;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;


/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>InnerTag</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.design.drarch.diagram.trace.logModel.impl.InnerTagImpl#getTags <em>Tags</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class InnerTagImpl extends EObjectImpl implements InnerTag {
  /**
   * The cached value of the '{@link #getTags() <em>Tags</em>}' map. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getTags()
   * @generated
   * @ordered
   */
  protected EMap tags = null;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected InnerTagImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected EClass eStaticClass() {
    return LogModelPackage.eINSTANCE.getMaterialization();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EMap getTags() {
    if (tags == null) {
      tags = new EcoreEMap(LogModelPackage.eINSTANCE
          .getEStringToLogNodeMapEntry(), EStringToLogNodeMapEntryImpl.class,
          this, LogModelPackage.INNERTAG__TAGS);
    }
    return tags;
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
      case LogModelPackage.INNERTAG__TAGS:
        return ((InternalEList) getTags()).basicRemove(otherEnd, msgs);
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
    case LogModelPackage.INNERTAG__TAGS:
      return getTags();
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
    case LogModelPackage.INNERTAG__TAGS:
      getTags().clear();
      getTags().addAll((Collection) newValue);
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
    case LogModelPackage.INNERTAG__TAGS:
      getTags().clear();
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
    case LogModelPackage.INNERTAG__TAGS:
      return tags != null && !tags.isEmpty();
    }
    return eDynamicIsSet(eFeature);
  }

} // InnerTagImpl
