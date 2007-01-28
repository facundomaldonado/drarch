package org.design.rules4Java.engine.ruleModel;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * @model
 * @author pelado
 */
public interface Query extends EObject{

	/**
	 * @model
	 * @return query string 
	 */
	String getQueryString();
	/**
	 * Sets the value of the '{@link org.design.rules4Java.engine.ruleModel.Query#getQueryString <em>Query String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Query String</em>' attribute.
	 * @see #getQueryString()
	 * @generated
	 */
	void setQueryString(String value);

	/**
	 * @model type="Var" containment="true"
	 * @return vars of the query
	 */
	EList getChosenVars();
	
}
