/**
 * <copyright>
 * </copyright>
 *
 * $Id: FactSetImpl.java,v 1.1 2006/10/07 19:08:21 nfrontini Exp $
 */
package org.design.rules4Java.engine.ruleModel.impl;

import java.util.Collection;

import org.design.rules4Java.engine.ruleModel.Fact;
import org.design.rules4Java.engine.ruleModel.FactSet;
import org.design.rules4Java.engine.ruleModel.RuleModelPackage;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Fact Set</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.design.rules4Java.engine.ruleModel.impl.FactSetImpl#getFactTemplates <em>Fact Templates</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FactSetImpl extends EObjectImpl implements FactSet {
	/**
	 * The cached value of the '{@link #getFactTemplates() <em>Fact Templates</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFactTemplates()
	 * @generated
	 * @ordered
	 */
	protected EList factTemplates = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FactSetImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return RuleModelPackage.eINSTANCE.getFactSet();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getFactTemplates() {
		if (factTemplates == null) {
			factTemplates = new EObjectContainmentEList(Fact.class, this, RuleModelPackage.FACT_SET__FACT_TEMPLATES);
		}
		return factTemplates;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case RuleModelPackage.FACT_SET__FACT_TEMPLATES:
					return ((InternalEList)getFactTemplates()).basicRemove(otherEnd, msgs);
				default:
					return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
			}
		}
		return eBasicSetContainer(null, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case RuleModelPackage.FACT_SET__FACT_TEMPLATES:
				return getFactTemplates();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case RuleModelPackage.FACT_SET__FACT_TEMPLATES:
				getFactTemplates().clear();
				getFactTemplates().addAll((Collection)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case RuleModelPackage.FACT_SET__FACT_TEMPLATES:
				getFactTemplates().clear();
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case RuleModelPackage.FACT_SET__FACT_TEMPLATES:
				return factTemplates != null && !factTemplates.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

} //FactSetImpl
