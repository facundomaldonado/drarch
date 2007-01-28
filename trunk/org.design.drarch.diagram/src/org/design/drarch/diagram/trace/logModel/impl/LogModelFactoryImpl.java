/**
 * <copyright>
 * </copyright>
 *
 * $Id: LogModelFactoryImpl.java,v 1.1 2006/10/07 19:05:16 nfrontini Exp $
 */
package org.design.drarch.diagram.trace.logModel.impl;

import java.util.Map;

import org.design.drarch.diagram.trace.logModel.InnerTag;
import org.design.drarch.diagram.trace.logModel.LogModelFactory;
import org.design.drarch.diagram.trace.logModel.LogModelPackage;
import org.design.drarch.diagram.trace.logModel.LogNode;
import org.design.drarch.diagram.trace.logModel.PropertyLogNode;
import org.design.drarch.diagram.trace.logModel.Responsibility;
import org.design.drarch.diagram.trace.logModel.TagLogNode;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EFactoryImpl;



/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class LogModelFactoryImpl extends EFactoryImpl implements LogModelFactory {
	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LogModelFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case LogModelPackage.LOG_NODE: return createLogNode();
			case LogModelPackage.INNERTAG: return createInnerTag();
			case LogModelPackage.PROPERTY_LOG_NODE: return createPropertyLogNode();
			case LogModelPackage.RESPONSIBILITY: return createResponsibility();
			case LogModelPackage.TAG_LOG_NODE: return createTagLogNode();
			case LogModelPackage.ESTRING_TO_LOG_NODE_MAP_ENTRY: return (EObject)createEStringToLogNodeMapEntry();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case LogModelPackage.LOG_NODE_ARRAY:
				return createLogNodeArrayFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case LogModelPackage.LOG_NODE_ARRAY:
				return convertLogNodeArrayToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LogNode createLogNode() {
		LogNodeImpl logNode = new LogNodeImpl();
		return logNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InnerTag createInnerTag() {
		InnerTagImpl materialization = new InnerTagImpl();
		return materialization;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PropertyLogNode createPropertyLogNode() {
		PropertyLogNodeImpl propertyLogNode = new PropertyLogNodeImpl();
		return propertyLogNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Responsibility createResponsibility() {
		ResponsibilityImpl responsibility = new ResponsibilityImpl();
		return responsibility;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TagLogNode createTagLogNode() {
		TagLogNodeImpl tagLogNode = new TagLogNodeImpl();
		return tagLogNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry createEStringToLogNodeMapEntry() {
		EStringToLogNodeMapEntryImpl eStringToLogNodeMapEntry = new EStringToLogNodeMapEntryImpl();
		return eStringToLogNodeMapEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LogNode[] createLogNodeArrayFromString(EDataType eDataType, String initialValue) {
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLogNodeArrayToString(EDataType eDataType, Object instanceValue) {
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LogModelPackage getLogModelPackage() {
		return (LogModelPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	public static LogModelPackage getPackage() {
		return LogModelPackage.eINSTANCE;
	}

} //LogModelFactoryImpl
