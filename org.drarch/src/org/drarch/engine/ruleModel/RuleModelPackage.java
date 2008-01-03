/**
 * <copyright> </copyright>
 * 
 * $Id: RuleModelPackage.java,v 1.1 2006/10/07 19:08:23 nfrontini Exp $
 */
package org.drarch.engine.ruleModel;

import org.drarch.engine.ruleModel.impl.RuleModelPackageImpl;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
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
 * @see org.design.rules4Java.engine.ruleModel.RuleModelFactory
 * @model kind="package"
 * @generated
 */
public interface RuleModelPackage extends EPackage {
  /**
   * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  String eNAME = "ruleModel";

  /**
   * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  String eNS_URI = "http:///rules4Java/engine/ruleModel.ecore";

  /**
   * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  String eNS_PREFIX = "rules4Java.engine.ruleModel";

  /**
   * The singleton instance of the package. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   */
  RuleModelPackage eINSTANCE = RuleModelPackageImpl
      .init();

  /**
   * The meta object id for the '{@link org.design.rules4Java.engine.ruleModel.impl.DrarchFileModelImpl <em>Drarch File Model</em>}'
   * class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see org.design.rules4Java.engine.ruleModel.impl.DrarchFileModelImpl
   * @see org.design.rules4Java.engine.ruleModel.impl.RuleModelPackageImpl#getDrarchFileModel()
   * @generated
   */
  int DRARCH_FILE_MODEL = 0;

  /**
   * The feature id for the '<em><b>Rules</b></em>' containment reference
   * list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int DRARCH_FILE_MODEL__RULES = 0;

  /**
   * The feature id for the '<em><b>File Name</b></em>' attribute. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int DRARCH_FILE_MODEL__FILE_NAME = 1;

  /**
   * The number of structural features of the the '<em>Drarch File Model</em>'
   * class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int DRARCH_FILE_MODEL_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.design.rules4Java.engine.ruleModel.impl.FactImpl <em>Fact</em>}'
   * class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see org.design.rules4Java.engine.ruleModel.impl.FactImpl
   * @see org.design.rules4Java.engine.ruleModel.impl.RuleModelPackageImpl#getFact()
   * @generated
   */
  int FACT = 1;

  /**
   * The feature id for the '<em><b>Fact Text</b></em>' attribute. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int FACT__FACT_TEXT = 0;

  /**
   * The number of structural features of the the '<em>Fact</em>' class.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int FACT_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.design.rules4Java.engine.ruleModel.impl.FactSetImpl <em>Fact Set</em>}'
   * class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see org.design.rules4Java.engine.ruleModel.impl.FactSetImpl
   * @see org.design.rules4Java.engine.ruleModel.impl.RuleModelPackageImpl#getFactSet()
   * @generated
   */
  int FACT_SET = 2;

  /**
   * The feature id for the '<em><b>Fact Templates</b></em>' containment
   * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int FACT_SET__FACT_TEMPLATES = 0;

  /**
   * The number of structural features of the the '<em>Fact Set</em>' class.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int FACT_SET_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.design.rules4Java.engine.ruleModel.impl.QueryImpl <em>Query</em>}'
   * class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see org.design.rules4Java.engine.ruleModel.impl.QueryImpl
   * @see org.design.rules4Java.engine.ruleModel.impl.RuleModelPackageImpl#getQuery()
   * @generated
   */
  int QUERY = 3;

  /**
   * The feature id for the '<em><b>Query String</b></em>' attribute. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int QUERY__QUERY_STRING = 0;

  /**
   * The feature id for the '<em><b>Chosen Vars</b></em>' containment
   * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int QUERY__CHOSEN_VARS = 1;

  /**
   * The number of structural features of the the '<em>Query</em>' class.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int QUERY_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.design.rules4Java.engine.ruleModel.impl.RuleImpl <em>Rule</em>}'
   * class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see org.design.rules4Java.engine.ruleModel.impl.RuleImpl
   * @see org.design.rules4Java.engine.ruleModel.impl.RuleModelPackageImpl#getRule()
   * @generated
   */
  int RULE = 4;

  /**
   * The feature id for the '<em><b>Query</b></em>' containment reference.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int RULE__QUERY = 0;

  /**
   * The feature id for the '<em><b>Fact Set</b></em>' containment
   * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int RULE__FACT_SET = 1;

  /**
   * The feature id for the '<em><b>Suggest Template</b></em>' attribute.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int RULE__SUGGEST_TEMPLATE = 2;

  /**
   * The feature id for the '<em><b>Description</b></em>' attribute. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int RULE__DESCRIPTION = 3;

  /**
   * The number of structural features of the the '<em>Rule</em>' class.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int RULE_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.design.rules4Java.engine.ruleModel.impl.VarImpl <em>Var</em>}'
   * class. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see org.design.rules4Java.engine.ruleModel.impl.VarImpl
   * @see org.design.rules4Java.engine.ruleModel.impl.RuleModelPackageImpl#getVar()
   * @generated
   */
  int VAR = 5;

  /**
   * The feature id for the '<em><b>Var Text</b></em>' attribute. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int VAR__VAR_TEXT = 0;

  /**
   * The number of structural features of the the '<em>Var</em>' class. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */
  int VAR_FEATURE_COUNT = 1;


  /**
   * Returns the meta object for class '{@link org.design.rules4Java.engine.ruleModel.DrarchFileModel <em>Drarch File Model</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for class '<em>Drarch File Model</em>'.
   * @see org.design.rules4Java.engine.ruleModel.DrarchFileModel
   * @generated
   */
  EClass getDrarchFileModel();

  /**
   * Returns the meta object for the containment reference list '{@link org.design.rules4Java.engine.ruleModel.DrarchFileModel#getRules <em>Rules</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the containment reference list '<em>Rules</em>'.
   * @see org.design.rules4Java.engine.ruleModel.DrarchFileModel#getRules()
   * @see #getDrarchFileModel()
   * @generated
   */
  EReference getDrarchFileModel_Rules();

  /**
   * Returns the meta object for the attribute '{@link org.design.rules4Java.engine.ruleModel.DrarchFileModel#getFileName <em>File Name</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the attribute '<em>File Name</em>'.
   * @see org.design.rules4Java.engine.ruleModel.DrarchFileModel#getFileName()
   * @see #getDrarchFileModel()
   * @generated
   */
  EAttribute getDrarchFileModel_FileName();

  /**
   * Returns the meta object for class '{@link org.design.rules4Java.engine.ruleModel.Fact <em>Fact</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for class '<em>Fact</em>'.
   * @see org.design.rules4Java.engine.ruleModel.Fact
   * @generated
   */
  EClass getFact();

  /**
   * Returns the meta object for the attribute '{@link org.design.rules4Java.engine.ruleModel.Fact#getFactText <em>Fact Text</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the attribute '<em>Fact Text</em>'.
   * @see org.design.rules4Java.engine.ruleModel.Fact#getFactText()
   * @see #getFact()
   * @generated
   */
  EAttribute getFact_FactText();

  /**
   * Returns the meta object for class '{@link org.design.rules4Java.engine.ruleModel.FactSet <em>Fact Set</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for class '<em>Fact Set</em>'.
   * @see org.design.rules4Java.engine.ruleModel.FactSet
   * @generated
   */
  EClass getFactSet();

  /**
   * Returns the meta object for the containment reference list '{@link org.design.rules4Java.engine.ruleModel.FactSet#getFactTemplates <em>Fact Templates</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the containment reference list '<em>Fact Templates</em>'.
   * @see org.design.rules4Java.engine.ruleModel.FactSet#getFactTemplates()
   * @see #getFactSet()
   * @generated
   */
  EReference getFactSet_FactTemplates();

  /**
   * Returns the meta object for class '{@link org.design.rules4Java.engine.ruleModel.Query <em>Query</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for class '<em>Query</em>'.
   * @see org.design.rules4Java.engine.ruleModel.Query
   * @generated
   */
  EClass getQuery();

  /**
   * Returns the meta object for the attribute '{@link org.design.rules4Java.engine.ruleModel.Query#getQueryString <em>Query String</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the attribute '<em>Query String</em>'.
   * @see org.design.rules4Java.engine.ruleModel.Query#getQueryString()
   * @see #getQuery()
   * @generated
   */
  EAttribute getQuery_QueryString();

  /**
   * Returns the meta object for the containment reference list '{@link org.design.rules4Java.engine.ruleModel.Query#getChosenVars <em>Chosen Vars</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the containment reference list '<em>Chosen Vars</em>'.
   * @see org.design.rules4Java.engine.ruleModel.Query#getChosenVars()
   * @see #getQuery()
   * @generated
   */
  EReference getQuery_ChosenVars();

  /**
   * Returns the meta object for class '{@link org.design.rules4Java.engine.ruleModel.Rule <em>Rule</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for class '<em>Rule</em>'.
   * @see org.design.rules4Java.engine.ruleModel.Rule
   * @generated
   */
  EClass getRule();

  /**
   * Returns the meta object for the containment reference '{@link org.design.rules4Java.engine.ruleModel.Rule#getQuery <em>Query</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the containment reference '<em>Query</em>'.
   * @see org.design.rules4Java.engine.ruleModel.Rule#getQuery()
   * @see #getRule()
   * @generated
   */
  EReference getRule_Query();

  /**
   * Returns the meta object for the containment reference '{@link org.design.rules4Java.engine.ruleModel.Rule#getFactSet <em>Fact Set</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the containment reference '<em>Fact Set</em>'.
   * @see org.design.rules4Java.engine.ruleModel.Rule#getFactSet()
   * @see #getRule()
   * @generated
   */
  EReference getRule_FactSet();

  /**
   * Returns the meta object for the attribute '{@link org.design.rules4Java.engine.ruleModel.Rule#getSuggestTemplate <em>Suggest Template</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the attribute '<em>Suggest Template</em>'.
   * @see org.design.rules4Java.engine.ruleModel.Rule#getSuggestTemplate()
   * @see #getRule()
   * @generated
   */
  EAttribute getRule_SuggestTemplate();

  /**
   * Returns the meta object for the attribute '{@link org.design.rules4Java.engine.ruleModel.Rule#getDescription <em>Description</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the attribute '<em>Description</em>'.
   * @see org.design.rules4Java.engine.ruleModel.Rule#getDescription()
   * @see #getRule()
   * @generated
   */
  EAttribute getRule_Description();

  /**
   * Returns the meta object for class '{@link org.design.rules4Java.engine.ruleModel.Var <em>Var</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for class '<em>Var</em>'.
   * @see org.design.rules4Java.engine.ruleModel.Var
   * @generated
   */
  EClass getVar();

  /**
   * Returns the meta object for the attribute '{@link org.design.rules4Java.engine.ruleModel.Var#getVarText <em>Var Text</em>}'.
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the meta object for the attribute '<em>Var Text</em>'.
   * @see org.design.rules4Java.engine.ruleModel.Var#getVarText()
   * @see #getVar()
   * @generated
   */
  EAttribute getVar_VarText();

  /**
   * Returns the factory that creates the instances of the model. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @return the factory that creates the instances of the model.
   * @generated
   */
  RuleModelFactory getRuleModelFactory();

} // RuleModelPackage
