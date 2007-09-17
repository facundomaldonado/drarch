/**
 * <copyright>
 * </copyright>
 *
 * $Id: RuleImpl.java,v 1.1 2006/10/07 19:08:21 nfrontini Exp $
 */
package org.design.rules4Java.engine.ruleModel.impl;

import org.design.rules4Java.engine.ruleModel.FactSet;
import org.design.rules4Java.engine.ruleModel.Query;
import org.design.rules4Java.engine.ruleModel.Rule;
import org.design.rules4Java.engine.ruleModel.RuleModelPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Rule</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.design.rules4Java.engine.ruleModel.impl.RuleImpl#getQuery <em>Query</em>}</li>
 *   <li>{@link org.design.rules4Java.engine.ruleModel.impl.RuleImpl#getFactSet <em>Fact Set</em>}</li>
 *   <li>{@link org.design.rules4Java.engine.ruleModel.impl.RuleImpl#getSuggestTemplate <em>Suggest Template</em>}</li>
 *   <li>{@link org.design.rules4Java.engine.ruleModel.impl.RuleImpl#getDescription <em>Description</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RuleImpl extends EObjectImpl implements Rule {
	/**
	 * The cached value of the '{@link #getQuery() <em>Query</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQuery()
	 * @generated
	 * @ordered
	 */
	protected Query query = null;

	/**
	 * The cached value of the '{@link #getFactSet() <em>Fact Set</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFactSet()
	 * @generated
	 * @ordered
	 */
	protected FactSet factSet = null;

	/**
	 * The default value of the '{@link #getSuggestTemplate() <em>Suggest Template</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSuggestTemplate()
	 * @generated
	 * @ordered
	 */
	protected static final String SUGGEST_TEMPLATE_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getSuggestTemplate() <em>Suggest Template</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSuggestTemplate()
	 * @generated
	 * @ordered
	 */
	protected String suggestTemplate = SUGGEST_TEMPLATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected String description = DESCRIPTION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RuleImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return RuleModelPackage.eINSTANCE.getRule();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Query getQuery() {
		return query;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetQuery(Query newQuery, NotificationChain msgs) {
		Query oldQuery = query;
		query = newQuery;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RuleModelPackage.RULE__QUERY, oldQuery, newQuery);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setQuery(Query newQuery) {
		if (newQuery != query) {
			NotificationChain msgs = null;
			if (query != null)
				msgs = ((InternalEObject)query).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RuleModelPackage.RULE__QUERY, null, msgs);
			if (newQuery != null)
				msgs = ((InternalEObject)newQuery).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RuleModelPackage.RULE__QUERY, null, msgs);
			msgs = basicSetQuery(newQuery, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RuleModelPackage.RULE__QUERY, newQuery, newQuery));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FactSet getFactSet() {
		return factSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFactSet(FactSet newFactSet, NotificationChain msgs) {
		FactSet oldFactSet = factSet;
		factSet = newFactSet;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RuleModelPackage.RULE__FACT_SET, oldFactSet, newFactSet);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFactSet(FactSet newFactSet) {
		if (newFactSet != factSet) {
			NotificationChain msgs = null;
			if (factSet != null)
				msgs = ((InternalEObject)factSet).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RuleModelPackage.RULE__FACT_SET, null, msgs);
			if (newFactSet != null)
				msgs = ((InternalEObject)newFactSet).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RuleModelPackage.RULE__FACT_SET, null, msgs);
			msgs = basicSetFactSet(newFactSet, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RuleModelPackage.RULE__FACT_SET, newFactSet, newFactSet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSuggestTemplate() {
		return suggestTemplate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSuggestTemplate(String newSuggestTemplate) {
		String oldSuggestTemplate = suggestTemplate;
		suggestTemplate = newSuggestTemplate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RuleModelPackage.RULE__SUGGEST_TEMPLATE, oldSuggestTemplate, suggestTemplate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RuleModelPackage.RULE__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case RuleModelPackage.RULE__QUERY:
					return basicSetQuery(null, msgs);
				case RuleModelPackage.RULE__FACT_SET:
					return basicSetFactSet(null, msgs);
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
			case RuleModelPackage.RULE__QUERY:
				return getQuery();
			case RuleModelPackage.RULE__FACT_SET:
				return getFactSet();
			case RuleModelPackage.RULE__SUGGEST_TEMPLATE:
				return getSuggestTemplate();
			case RuleModelPackage.RULE__DESCRIPTION:
				return getDescription();
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
			case RuleModelPackage.RULE__QUERY:
				setQuery((Query)newValue);
				return;
			case RuleModelPackage.RULE__FACT_SET:
				setFactSet((FactSet)newValue);
				return;
			case RuleModelPackage.RULE__SUGGEST_TEMPLATE:
				setSuggestTemplate((String)newValue);
				return;
			case RuleModelPackage.RULE__DESCRIPTION:
				setDescription((String)newValue);
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
			case RuleModelPackage.RULE__QUERY:
				setQuery((Query)null);
				return;
			case RuleModelPackage.RULE__FACT_SET:
				setFactSet((FactSet)null);
				return;
			case RuleModelPackage.RULE__SUGGEST_TEMPLATE:
				setSuggestTemplate(SUGGEST_TEMPLATE_EDEFAULT);
				return;
			case RuleModelPackage.RULE__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
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
			case RuleModelPackage.RULE__QUERY:
				return query != null;
			case RuleModelPackage.RULE__FACT_SET:
				return factSet != null;
			case RuleModelPackage.RULE__SUGGEST_TEMPLATE:
				return SUGGEST_TEMPLATE_EDEFAULT == null ? suggestTemplate != null : !SUGGEST_TEMPLATE_EDEFAULT.equals(suggestTemplate);
			case RuleModelPackage.RULE__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
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
		result.append(" (suggestTemplate: ");
		result.append(suggestTemplate);
		result.append(", description: ");
		result.append(description);
		result.append(')');
		return result.toString();
	}

} //RuleImpl
