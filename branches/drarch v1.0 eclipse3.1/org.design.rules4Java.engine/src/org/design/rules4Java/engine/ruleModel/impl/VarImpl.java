/**
 * <copyright>
 * </copyright>
 *
 * $Id: VarImpl.java,v 1.1 2006/10/07 19:08:21 nfrontini Exp $
 */
package org.design.rules4Java.engine.ruleModel.impl;

import org.design.rules4Java.engine.ruleModel.RuleModelPackage;
import org.design.rules4Java.engine.ruleModel.Var;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Var</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.design.rules4Java.engine.ruleModel.impl.VarImpl#getVarText <em>Var Text</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class VarImpl extends EObjectImpl implements Var {
	/**
	 * The default value of the '{@link #getVarText() <em>Var Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVarText()
	 * @generated
	 * @ordered
	 */
	protected static final String VAR_TEXT_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getVarText() <em>Var Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVarText()
	 * @generated
	 * @ordered
	 */
	protected String varText = VAR_TEXT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected VarImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return RuleModelPackage.eINSTANCE.getVar();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getVarText() {
		return varText;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVarText(String newVarText) {
		String oldVarText = varText;
		varText = newVarText;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RuleModelPackage.VAR__VAR_TEXT, oldVarText, varText));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case RuleModelPackage.VAR__VAR_TEXT:
				return getVarText();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case RuleModelPackage.VAR__VAR_TEXT:
				setVarText((String)newValue);
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
			case RuleModelPackage.VAR__VAR_TEXT:
				setVarText(VAR_TEXT_EDEFAULT);
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
			case RuleModelPackage.VAR__VAR_TEXT:
				return VAR_TEXT_EDEFAULT == null ? varText != null : !VAR_TEXT_EDEFAULT.equals(varText);
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (varText: ");
		result.append(varText);
		result.append(')');
		return result.toString();
	}

} //VarImpl
