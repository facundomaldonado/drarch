package org.design.drarch.diagram.DiagramModel.ucmModel;

import org.design.drarch.diagram.DiagramModel.ucmModel.impl.UcmModelFactoryImpl;
import org.eclipse.emf.ecore.EFactory;



/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.design.drarch.diagram.DiagramModel.ucmModel.UcmModelPackage
 * @generated
 */
public interface UcmModelFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	UcmModelFactoryImpl eINSTANCE = new org.design.drarch.diagram.DiagramModel.ucmModel.impl.UcmModelFactoryImpl();

	/**
	 * Returns a new object of class '<em>Component Role</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Component Role</em>'.
	 * @generated
	 */
	ComponentRole createComponentRole();

	/**
	 * Returns a new object of class '<em>Path</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Path</em>'.
	 * @generated
	 */
	Path createPath();

	/**
	 * Returns a new object of class '<em>Path Node</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Path Node</em>'.
	 * @generated
	 */
	PathNode createPathNode();

	/**
	 * Returns a new object of class '<em>UCM Diagram</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>UCM Diagram</em>'.
	 * @generated
	 */
	UCMDiagram createUCMDiagram();

	/**
	 * Returns a new object of class '<em>UCM Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>UCM Model</em>'.
	 * @generated
	 */
	UCMModel createUCMModel();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	UcmModelPackage getUcmModelPackage();

} //UcmModelFactory
