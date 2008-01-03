/**
 * <copyright>
 * </copyright>
 *
 * $Id: FactImpl.java,v 1.1 2006/10/07 19:08:21 nfrontini Exp $
 */
package org.drarch.engine.ruleModel.impl;

import org.drarch.engine.ruleModel.Fact;
import org.drarch.engine.ruleModel.RuleModelPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Fact</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.design.rules4Java.engine.ruleModel.impl.FactImpl#getFactText <em>Fact Text</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FactImpl extends EObjectImpl implements Fact {
	/**
	 * The default value of the '{@link #getFactText() <em>Fact Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFactText()
	 * @generated
	 * @ordered
	 */
	protected static final String FACT_TEXT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFactText() <em>Fact Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFactText()
	 * @generated
	 * @ordered
	 */
	protected String factText = FACT_TEXT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FactImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return RuleModelPackage.eINSTANCE.getFact();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFactText() {
		return factText;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFactText(String newFactText) {
		String oldFactText = factText;
		factText = newFactText;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RuleModelPackage.FACT__FACT_TEXT, oldFactText, factText));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case RuleModelPackage.FACT__FACT_TEXT:
				return getFactText();
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
			case RuleModelPackage.FACT__FACT_TEXT:
				setFactText((String)newValue);
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
			case RuleModelPackage.FACT__FACT_TEXT:
				setFactText(FACT_TEXT_EDEFAULT);
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
			case RuleModelPackage.FACT__FACT_TEXT:
				return FACT_TEXT_EDEFAULT == null ? factText != null : !FACT_TEXT_EDEFAULT.equals(factText);
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
		result.append(" (factText: ");
		result.append(factText);
		result.append(')');
		return result.toString();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
	    return this.getFactText().equals(((Fact)obj).getFactText());
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
	    return getFactText().hashCode();
	}
	
} //FactImpl
