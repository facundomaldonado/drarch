/**
 * <copyright> </copyright>
 * 
 * $Id: UcmModelPackage.java,v 1.1 2006/10/07 19:05:14 nfrontini Exp $
 */
package org.drarch.diagram.DiagramModel.ucmModel;

import org.drarch.diagram.DiagramModel.ucmModel.impl.UcmModelPackageImpl;
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
 * @see org.design.drarch.diagram.DiagramModel.ucmModel.UcmModelFactory
 * @model kind="package"
 * @generated
 */
public interface UcmModelPackage extends EPackage {
  /**
   * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  String eNAME = "ucmModel";

  /**
   * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  String eNS_URI = "http:///drarch/diagram/ucmModel.ecore";

  /**
   * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  String eNS_PREFIX = "drarch.diagram.ucmModel";

  /**
   * The singleton instance of the package. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   */
  UcmModelPackage eINSTANCE = UcmModelPackageImpl.init();

  /**
   * The meta object id for the '{@link org.design.drarch.diagram.DiagramModel.ucmModel.impl.ComponentRoleImpl <em>Component Role</em>}'
   * class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see org.design.drarch.diagram.DiagramModel.ucmModel.impl.ComponentRoleImpl
   * @see org.design.drarch.diagram.DiagramModel.ucmModel.impl.UcmModelPackageImpl#getComponentRole()
   * @generated
   */
  int COMPONENT_ROLE = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPONENT_ROLE__NAME = 0;

  /**
   * The feature id for the '<em><b>Defined Class</b></em>' attribute. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPONENT_ROLE__DEFINED_CLASS = 1;

  /**
   * The feature id for the '<em><b>Behavior Name</b></em>' attribute. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPONENT_ROLE__BEHAVIOR_NAME = 2;

  /**
   * The feature id for the '<em><b>Component</b></em>' attribute. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPONENT_ROLE__COMPONENT = 3;

  /**
   * The number of structural features of the the '<em>Component Role</em>'
   * class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int COMPONENT_ROLE_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.design.drarch.diagram.DiagramModel.ucmModel.impl.PathImpl <em>Path</em>}'
   * class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see org.design.drarch.diagram.DiagramModel.ucmModel.impl.PathImpl
   * @see org.design.drarch.diagram.DiagramModel.ucmModel.impl.UcmModelPackageImpl#getPath()
   * @generated
   */
  int PATH = 1;

  /**
   * The feature id for the '<em><b>Nodes</b></em>' reference list. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PATH__NODES = 0;

  /**
   * The feature id for the '<em><b>Start Nodes</b></em>' reference list.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PATH__START_NODES = 1;

  /**
   * The feature id for the '<em><b>End Nodes</b></em>' reference list.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PATH__END_NODES = 2;

  /**
   * The number of structural features of the the '<em>Path</em>' class.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PATH_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.design.drarch.diagram.DiagramModel.ucmModel.impl.PathNodeImpl <em>Path Node</em>}'
   * class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see org.design.drarch.diagram.DiagramModel.ucmModel.impl.PathNodeImpl
   * @see org.design.drarch.diagram.DiagramModel.ucmModel.impl.UcmModelPackageImpl#getPathNode()
   * @generated
   */
  int PATH_NODE = 2;

  /**
   * The feature id for the '<em><b>Path</b></em>' reference. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PATH_NODE__PATH = 0;

  /**
   * The feature id for the '<em><b>Previous</b></em>' reference list. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PATH_NODE__PREVIOUS = 1;

  /**
   * The feature id for the '<em><b>Next</b></em>' reference list. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PATH_NODE__NEXT = 2;

  /**
   * The number of structural features of the the '<em>Path Node</em>'
   * class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int PATH_NODE_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.design.drarch.diagram.DiagramModel.ucmModel.impl.UCMDiagramImpl <em>UCM Diagram</em>}'
   * class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see org.design.drarch.diagram.DiagramModel.ucmModel.impl.UCMDiagramImpl
   * @see org.design.drarch.diagram.DiagramModel.ucmModel.impl.UcmModelPackageImpl#getUCMDiagram()
   * @generated
   */
  int UCM_DIAGRAM = 3;

  /**
   * The feature id for the '<em><b>Model</b></em>' reference. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int UCM_DIAGRAM__MODEL = 0;

  /**
   * The number of structural features of the the '<em>UCM Diagram</em>'
   * class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int UCM_DIAGRAM_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.design.drarch.diagram.DiagramModel.ucmModel.impl.UCMModelImpl <em>UCM Model</em>}'
   * class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see org.design.drarch.diagram.DiagramModel.ucmModel.impl.UCMModelImpl
   * @see org.design.drarch.diagram.DiagramModel.ucmModel.impl.UcmModelPackageImpl#getUCMModel()
   * @generated
   */
  int UCM_MODEL = 4;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int UCM_MODEL__NAME = 0;

  /**
   * The feature id for the '<em><b>Paths</b></em>' reference list. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int UCM_MODEL__PATHS = 1;

  /**
   * The feature id for the '<em><b>Component Roles</b></em>' reference
   * list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int UCM_MODEL__COMPONENT_ROLES = 2;

  /**
   * The number of structural features of the the '<em>UCM Model</em>'
   * class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int UCM_MODEL_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '<em>Component</em>' data type. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see org.design.drarch.diagram.DiagramModel.componentModel.Component
   * @see org.design.drarch.diagram.DiagramModel.ucmModel.impl.UcmModelPackageImpl#getComponent()
   * @generated
   */
  int COMPONENT = 5;

  int PATH_NODE__RESPONSIBILITY_NAME = 6;


  /**
   * Returns the meta object for class '{@link org.design.drarch.diagram.DiagramModel.ucmModel.ComponentRole <em>Component Role</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for class '<em>Component Role</em>'.
   * @see org.design.drarch.diagram.DiagramModel.ucmModel.ComponentRole
   * @generated
   */
  EClass getComponentRole();

  /**
   * Returns the meta object for the attribute '{@link org.design.drarch.diagram.DiagramModel.ucmModel.ComponentRole#getName <em>Name</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.design.drarch.diagram.DiagramModel.ucmModel.ComponentRole#getName()
   * @see #getComponentRole()
   * @generated
   */
  EAttribute getComponentRole_Name();

  /**
   * Returns the meta object for the attribute '{@link org.design.drarch.diagram.DiagramModel.ucmModel.ComponentRole#getDefinedClass <em>Defined Class</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the attribute '<em>Defined Class</em>'.
   * @see org.design.drarch.diagram.DiagramModel.ucmModel.ComponentRole#getDefinedClass()
   * @see #getComponentRole()
   * @generated
   */
  EAttribute getComponentRole_DefinedClass();

  /**
   * Returns the meta object for the attribute '{@link org.design.drarch.diagram.DiagramModel.ucmModel.ComponentRole#getBehaviorName <em>Behavior Name</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the attribute '<em>Behavior Name</em>'.
   * @see org.design.drarch.diagram.DiagramModel.ucmModel.ComponentRole#getBehaviorName()
   * @see #getComponentRole()
   * @generated
   */
  EAttribute getComponentRole_BehaviorName();

  /**
   * Returns the meta object for the attribute '{@link org.design.drarch.diagram.DiagramModel.ucmModel.ComponentRole#getComponent <em>Component</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the attribute '<em>Component</em>'.
   * @see org.design.drarch.diagram.DiagramModel.ucmModel.ComponentRole#getComponent()
   * @see #getComponentRole()
   * @generated
   */
  EAttribute getComponentRole_Component();

  /**
   * Returns the meta object for class '{@link org.design.drarch.diagram.DiagramModel.ucmModel.Path <em>Path</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for class '<em>Path</em>'.
   * @see org.design.drarch.diagram.DiagramModel.ucmModel.Path
   * @generated
   */
  EClass getPath();

  /**
   * Returns the meta object for the reference list '{@link org.design.drarch.diagram.DiagramModel.ucmModel.Path#getNodes <em>Nodes</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the reference list '<em>Nodes</em>'.
   * @see org.design.drarch.diagram.DiagramModel.ucmModel.Path#getNodes()
   * @see #getPath()
   * @generated
   */
  EReference getPath_Nodes();

  /**
   * Returns the meta object for the reference list '{@link org.design.drarch.diagram.DiagramModel.ucmModel.Path#getStartNodes <em>Start Nodes</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the reference list '<em>Start Nodes</em>'.
   * @see org.design.drarch.diagram.DiagramModel.ucmModel.Path#getStartNodes()
   * @see #getPath()
   * @generated
   */
  EReference getPath_StartNodes();

  /**
   * Returns the meta object for the reference list '{@link org.design.drarch.diagram.DiagramModel.ucmModel.Path#getEndNodes <em>End Nodes</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the reference list '<em>End Nodes</em>'.
   * @see org.design.drarch.diagram.DiagramModel.ucmModel.Path#getEndNodes()
   * @see #getPath()
   * @generated
   */
  EReference getPath_EndNodes();

  /**
   * Returns the meta object for class '{@link org.design.drarch.diagram.DiagramModel.ucmModel.PathNode <em>Path Node</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for class '<em>Path Node</em>'.
   * @see org.design.drarch.diagram.DiagramModel.ucmModel.PathNode
   * @generated
   */
  EClass getPathNode();

  /**
   * Returns the meta object for the reference '{@link org.design.drarch.diagram.DiagramModel.ucmModel.PathNode#getPath <em>Path</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the reference '<em>Path</em>'.
   * @see org.design.drarch.diagram.DiagramModel.ucmModel.PathNode#getPath()
   * @see #getPathNode()
   * @generated
   */
  EReference getPathNode_Path();

  /**
   * Returns the meta object for the reference list '{@link org.design.drarch.diagram.DiagramModel.ucmModel.PathNode#getPrevious <em>Previous</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the reference list '<em>Previous</em>'.
   * @see org.design.drarch.diagram.DiagramModel.ucmModel.PathNode#getPrevious()
   * @see #getPathNode()
   * @generated
   */
  EReference getPathNode_Previous();

  /**
   * Returns the meta object for the reference list '{@link org.design.drarch.diagram.DiagramModel.ucmModel.PathNode#getNext <em>Next</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the reference list '<em>Next</em>'.
   * @see org.design.drarch.diagram.DiagramModel.ucmModel.PathNode#getNext()
   * @see #getPathNode()
   * @generated
   */
  EReference getPathNode_Next();

  /**
   * Returns the meta object for class '{@link org.design.drarch.diagram.DiagramModel.ucmModel.UCMDiagram <em>UCM Diagram</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for class '<em>UCM Diagram</em>'.
   * @see org.design.drarch.diagram.DiagramModel.ucmModel.UCMDiagram
   * @generated
   */
  EClass getUCMDiagram();

  /**
   * Returns the meta object for the reference '{@link org.design.drarch.diagram.DiagramModel.ucmModel.UCMDiagram#getModel <em>Model</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the reference '<em>Model</em>'.
   * @see org.design.drarch.diagram.DiagramModel.ucmModel.UCMDiagram#getModel()
   * @see #getUCMDiagram()
   * @generated
   */
  EReference getUCMDiagram_Model();

  /**
   * Returns the meta object for class '{@link org.design.drarch.diagram.DiagramModel.ucmModel.UCMModel <em>UCM Model</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for class '<em>UCM Model</em>'.
   * @see org.design.drarch.diagram.DiagramModel.ucmModel.UCMModel
   * @generated
   */
  EClass getUCMModel();

  /**
   * Returns the meta object for the attribute '{@link org.design.drarch.diagram.DiagramModel.ucmModel.UCMModel#getName <em>Name</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.design.drarch.diagram.DiagramModel.ucmModel.UCMModel#getName()
   * @see #getUCMModel()
   * @generated
   */
  EAttribute getUCMModel_Name();

  /**
   * Returns the meta object for the reference list '{@link org.design.drarch.diagram.DiagramModel.ucmModel.UCMModel#getPaths <em>Paths</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the reference list '<em>Paths</em>'.
   * @see org.design.drarch.diagram.DiagramModel.ucmModel.UCMModel#getPaths()
   * @see #getUCMModel()
   * @generated
   */
  EReference getUCMModel_Paths();

  /**
   * Returns the meta object for the reference list '{@link org.design.drarch.diagram.DiagramModel.ucmModel.UCMModel#getComponentRoles <em>Component Roles</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the reference list '<em>Component Roles</em>'.
   * @see org.design.drarch.diagram.DiagramModel.ucmModel.UCMModel#getComponentRoles()
   * @see #getUCMModel()
   * @generated
   */
  EReference getUCMModel_ComponentRoles();

  /**
   * Returns the meta object for data type '{@link org.design.drarch.diagram.DiagramModel.componentModel.Component <em>Component</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for data type '<em>Component</em>'.
   * @see org.design.drarch.diagram.DiagramModel.componentModel.Component
   * @model instanceClass="drarch.diagram.cModel.Component"
   * @generated
   */
  EDataType getComponent();

  /**
   * Returns the factory that creates the instances of the model. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the factory that creates the instances of the model.
   * @generated
   */
  UcmModelFactory getUcmModelFactory();

} // UcmModelPackage
