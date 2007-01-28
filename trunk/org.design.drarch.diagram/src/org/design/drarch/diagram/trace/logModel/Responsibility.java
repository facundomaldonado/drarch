package org.design.drarch.diagram.trace.logModel;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * @model
 * @author pela
 *
 */
public interface Responsibility extends EObject{

	/**
	 * @model
	 * @return context name
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.design.drarch.diagram.trace.logModel.Responsibility#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * @model type="InnerTag"
	 * @return
	 */
	EList getExecutions();

}
