/**
 * <copyright> </copyright>
 * 
 * $Id: LogModelPackage.java,v 1.1 2006/10/07 19:05:15 nfrontini Exp $
 */
package org.drarch.diagram.trace.logModel;

import org.drarch.diagram.trace.logModel.impl.LogModelPackageImpl;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains
 * accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * 
 * @see org.design.drarch.diagram.trace.logModel.LogModelFactory
 * @model kind="package"
 * @generated
 */
public interface LogModelPackage extends EPackage {
  /**
   * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  String eNAME = "logModel";

  /**
   * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  String eNS_URI = "http:///drarch/diagram/trace/logModel.ecore";

  /**
   * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  String eNS_PREFIX = "drarch.diagram.trace.logModel";

  /**
   * The singleton instance of the package. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   */
  LogModelPackage eINSTANCE = LogModelPackageImpl.init();

  /**
   * The meta object id for the '{@link org.design.drarch.diagram.trace.logModel.impl.LogNodeImpl <em>Log Node</em>}'
   * class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see org.design.drarch.diagram.trace.logModel.impl.LogNodeImpl
   * @see org.design.drarch.diagram.trace.logModel.impl.LogModelPackageImpl#getLogNode()
   * @generated
   */
  int LOG_NODE = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int LOG_NODE__NAME = 0;

  /**
   * The number of structural features of the the '<em>Log Node</em>' class.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int LOG_NODE_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.design.drarch.diagram.trace.logModel.impl.InnerTagImpl <em>InnerTag</em>}'
   * class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see org.design.drarch.diagram.trace.logModel.impl.InnerTagImpl
   * @see org.design.drarch.diagram.trace.logModel.impl.LogModelPackageImpl#getMaterialization()
   * @generated
   */
  int INNERTAG = 1;

  /**
   * The feature id for the '<em><b>Tags</b></em>' map. <!-- begin-user-doc
   * --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int INNERTAG__TAGS = 0;

  /**
   * The number of structural features of the the '<em>InnerTag</em>' class.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int INNERTAG_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.design.drarch.diagram.trace.logModel.impl.PropertyLogNodeImpl <em>Property Log Node</em>}'
   * class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see org.design.drarch.diagram.trace.logModel.impl.PropertyLogNodeImpl
   * @see org.design.drarch.diagram.trace.logModel.impl.LogModelPackageImpl#getPropertyLogNode()
   * @generated
   */
  int PROPERTY_LOG_NODE = 2;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PROPERTY_LOG_NODE__NAME = LOG_NODE__NAME;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PROPERTY_LOG_NODE__VALUE = LOG_NODE_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the the '<em>Property Log Node</em>'
   * class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PROPERTY_LOG_NODE_FEATURE_COUNT = LOG_NODE_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.design.drarch.diagram.trace.logModel.impl.ResponsibilityImpl <em>Responsibility</em>}'
   * class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see org.design.drarch.diagram.trace.logModel.impl.ResponsibilityImpl
   * @see org.design.drarch.diagram.trace.logModel.impl.LogModelPackageImpl#getResponsibility()
   * @generated
   */
  int RESPONSIBILITY = 3;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int RESPONSIBILITY__NAME = 0;

  /**
   * The feature id for the '<em><b>Materializations</b></em>' reference
   * list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int RESPONSIBILITY__EXECUTIONS = 1;

  /**
   * The number of structural features of the the '<em>Responsibility</em>'
   * class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int RESPONSIBILITY_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.design.drarch.diagram.trace.logModel.impl.TagLogNodeImpl <em>Tag Log Node</em>}'
   * class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see org.design.drarch.diagram.trace.logModel.impl.TagLogNodeImpl
   * @see org.design.drarch.diagram.trace.logModel.impl.LogModelPackageImpl#getTagLogNode()
   * @generated
   */
  int TAG_LOG_NODE = 4;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int TAG_LOG_NODE__NAME = LOG_NODE__NAME;

  /**
   * The feature id for the '<em><b>Childrens</b></em>' attribute. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int TAG_LOG_NODE__CHILDRENS = LOG_NODE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Tag</b></em>' reference. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int TAG_LOG_NODE__TAG = LOG_NODE_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the the '<em>Tag Log Node</em>'
   * class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int TAG_LOG_NODE_FEATURE_COUNT = LOG_NODE_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.design.drarch.diagram.trace.logModel.impl.EStringToLogNodeMapEntryImpl <em>EString To Log Node Map Entry</em>}'
   * class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see org.design.drarch.diagram.trace.logModel.impl.EStringToLogNodeMapEntryImpl
   * @see org.design.drarch.diagram.trace.logModel.impl.LogModelPackageImpl#getEStringToLogNodeMapEntry()
   * @generated
   */
  int ESTRING_TO_LOG_NODE_MAP_ENTRY = 5;

  /**
   * The feature id for the '<em><b>Key</b></em>' attribute. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ESTRING_TO_LOG_NODE_MAP_ENTRY__KEY = 0;

  /**
   * The feature id for the '<em><b>Value</b></em>' reference. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ESTRING_TO_LOG_NODE_MAP_ENTRY__VALUE = 1;

  /**
   * The number of structural features of the the '<em>EString To Log Node Map Entry</em>'
   * class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int ESTRING_TO_LOG_NODE_MAP_ENTRY_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '<em>Log Node Array</em>' data type. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see org.design.drarch.diagram.trace.logModel.impl.LogModelPackageImpl#getLogNodeArray()
   * @generated
   */
  int LOG_NODE_ARRAY = 6;


  /**
   * Returns the meta object for class '{@link org.design.drarch.diagram.trace.logModel.LogNode <em>Log Node</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for class '<em>Log Node</em>'.
   * @see org.design.drarch.diagram.trace.logModel.LogNode
   * @generated
   */
  EClass getLogNode();

  /**
   * Returns the meta object for the attribute '{@link org.design.drarch.diagram.trace.logModel.LogNode#getName <em>Name</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.design.drarch.diagram.trace.logModel.LogNode#getName()
   * @see #getLogNode()
   * @generated
   */
  EAttribute getLogNode_Name();

  /**
   * Returns the meta object for class '{@link org.design.drarch.diagram.trace.logModel.InnerTag <em>InnerTag</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for class '<em>InnerTag</em>'.
   * @see org.design.drarch.diagram.trace.logModel.InnerTag
   * @generated
   */
  EClass getMaterialization();

  /**
   * Returns the meta object for the map '{@link org.design.drarch.diagram.trace.logModel.InnerTag#getTags <em>Tags</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the map '<em>Tags</em>'.
   * @see org.design.drarch.diagram.trace.logModel.InnerTag#getTags()
   * @see #getMaterialization()
   * @generated
   */
  EReference getMaterialization_Tags();

  /**
   * Returns the meta object for class '{@link org.design.drarch.diagram.trace.logModel.PropertyLogNode <em>Property Log Node</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for class '<em>Property Log Node</em>'.
   * @see org.design.drarch.diagram.trace.logModel.PropertyLogNode
   * @generated
   */
  EClass getPropertyLogNode();

  /**
   * Returns the meta object for the attribute '{@link org.design.drarch.diagram.trace.logModel.PropertyLogNode#getValue <em>Value</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see org.design.drarch.diagram.trace.logModel.PropertyLogNode#getValue()
   * @see #getPropertyLogNode()
   * @generated
   */
  EAttribute getPropertyLogNode_Value();

  /**
   * Returns the meta object for class '{@link org.design.drarch.diagram.trace.logModel.Responsibility <em>Responsibility</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for class '<em>Responsibility</em>'.
   * @see org.design.drarch.diagram.trace.logModel.Responsibility
   * @generated
   */
  EClass getResponsibility();

  /**
   * Returns the meta object for the attribute '{@link org.design.drarch.diagram.trace.logModel.Responsibility#getName <em>Name</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.design.drarch.diagram.trace.logModel.Responsibility#getName()
   * @see #getResponsibility()
   * @generated
   */
  EAttribute getResponsibility_Name();

  /**
   * Returns the meta object for the reference list '{@link org.design.drarch.diagram.trace.logModel.Responsibility#getMaterializations <em>Materializations</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the reference list '<em>Materializations</em>'.
   * @see org.design.drarch.diagram.trace.logModel.Responsibility#getExecutions()
   * @see #getResponsibility()
   * @generated
   */
  EReference getResponsibility_Materializations();

  /**
   * Returns the meta object for class '{@link org.design.drarch.diagram.trace.logModel.TagLogNode <em>Tag Log Node</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for class '<em>Tag Log Node</em>'.
   * @see org.design.drarch.diagram.trace.logModel.TagLogNode
   * @generated
   */
  EClass getTagLogNode();

  /**
   * Returns the meta object for the attribute '{@link org.design.drarch.diagram.trace.logModel.TagLogNode#getChildrens <em>Childrens</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the attribute '<em>Childrens</em>'.
   * @see org.design.drarch.diagram.trace.logModel.TagLogNode#getChildrens()
   * @see #getTagLogNode()
   * @generated
   */
  EAttribute getTagLogNode_Childrens();

  /**
   * Returns the meta object for the reference '{@link org.design.drarch.diagram.trace.logModel.TagLogNode#getTag <em>Tag</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the reference '<em>Tag</em>'.
   * @see org.design.drarch.diagram.trace.logModel.TagLogNode#getTag()
   * @see #getTagLogNode()
   * @generated
   */
  EReference getTagLogNode_Tag();

  /**
   * Returns the meta object for class '{@link java.util.Map.Entry <em>EString To Log Node Map Entry</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for class '<em>EString To Log Node Map Entry</em>'.
   * @see java.util.Map.Entry
   * @model keyType="java.lang.String"
   *        valueType="drarch.diagram.trace.logModel.LogNode"
   * @generated
   */
  EClass getEStringToLogNodeMapEntry();

  /**
   * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the attribute '<em>Key</em>'.
   * @see java.util.Map.Entry
   * @see #getEStringToLogNodeMapEntry()
   * @generated
   */
  EAttribute getEStringToLogNodeMapEntry_Key();

  /**
   * Returns the meta object for the reference '{@link java.util.Map.Entry <em>Value</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the reference '<em>Value</em>'.
   * @see java.util.Map.Entry
   * @see #getEStringToLogNodeMapEntry()
   * @generated
   */
  EReference getEStringToLogNodeMapEntry_Value();

  /**
   * Returns the meta object for data type '<em>Log Node Array</em>'. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for data type '<em>Log Node Array</em>'.
   * @model instanceClass="drarch.diagram.trace.logModel.LogNode[]"
   * @generated
   */
  EDataType getLogNodeArray();

  /**
   * Returns the factory that creates the instances of the model. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the factory that creates the instances of the model.
   * @generated
   */
  LogModelFactory getLogModelFactory();

} // LogModelPackage
