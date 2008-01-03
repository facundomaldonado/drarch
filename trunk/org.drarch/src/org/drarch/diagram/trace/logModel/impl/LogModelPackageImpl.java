/**
 * <copyright> </copyright>
 * 
 * $Id: LogModelPackageImpl.java,v 1.2 2006/10/12 06:59:36 nfrontini Exp $
 */
package org.drarch.diagram.trace.logModel.impl;

import java.util.Map;

import org.drarch.diagram.trace.logModel.InnerTag;
import org.drarch.diagram.trace.logModel.LogModelFactory;
import org.drarch.diagram.trace.logModel.LogModelPackage;
import org.drarch.diagram.trace.logModel.LogNode;
import org.drarch.diagram.trace.logModel.PropertyLogNode;
import org.drarch.diagram.trace.logModel.Responsibility;
import org.drarch.diagram.trace.logModel.TagLogNode;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.isistan.flabot.trace.log.LogPackage;
import org.isistan.flabot.trace.log.impl.LogPackageImpl;


/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class LogModelPackageImpl extends EPackageImpl implements
    LogModelPackage {
  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private EClass logNodeEClass = null;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private EClass materializationEClass = null;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private EClass propertyLogNodeEClass = null;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private EClass responsibilityEClass = null;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private EClass tagLogNodeEClass = null;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private EClass eStringToLogNodeMapEntryEClass = null;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private EDataType logNodeArrayEDataType = null;

  /**
   * Creates an instance of the model <b>Package</b>, registered with
   * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the
   * package package URI value.
   * <p>
   * Note: the correct way to create the package is via the static factory
   * method {@link #init init()}, which also performs initialization of the
   * package, or returns the registered package, if one already exists. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see org.eclipse.emf.ecore.EPackage.Registry
   * @see drarch.trace.logModel.LogModelPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private LogModelPackageImpl() {
    super(eNS_URI, LogModelFactory.eINSTANCE);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private static boolean isInited = false;

  /**
   * Creates, registers, and initializes the <b>Package</b> for this model, and
   * for any others upon which it depends. Simple dependencies are satisfied by
   * calling this method on all dependent packages before doing anything else.
   * This method drives initialization for interdependent packages directly, in
   * parallel with this package, itself.
   * <p>
   * Of this package and its interdependencies, all packages which have not yet
   * been registered by their URI values are first created and registered. The
   * packages are then initialized in two steps: meta-model objects for all of
   * the packages are created before any are initialized, since one package's
   * meta-model objects may refer to those of another.
   * <p>
   * Invocation of this method will not affect any packages that have already
   * been initialized. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static LogModelPackage init() {
    if (isInited)
      return (LogModelPackage) EPackage.Registry.INSTANCE
          .getEPackage(LogModelPackage.eNS_URI);

    // Obtain or create and register package
    LogModelPackageImpl theLogModelPackage = (LogModelPackageImpl) (EPackage.Registry.INSTANCE
        .getEPackage(eNS_URI) instanceof LogModelPackageImpl
        ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI)
        : new LogModelPackageImpl());

    isInited = true;

    // Initialize simple dependencies
    // EditormodelPackageImpl.init();
    // ComponentmodelPackageImpl.init();
    // UcmmodelPackageImpl.init();
    // CoremodelPackageImpl.init();
    // JavalogtracePackageImpl.init();
    // ExecutionmodelPackageImpl.init();
    // ExecutionstatePackageImpl.init();
    // FiltermodelPackageImpl.init();
    // ConfigPackageImpl.init();
    // LogPackageImpl.init();
    // MappingmodelPackageImpl.init();

    // Create package meta-data objects
    theLogModelPackage.createPackageContents();

    // Initialize created meta-data
    theLogModelPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theLogModelPackage.freeze();

    return theLogModelPackage;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EClass getLogNode() {
    return logNodeEClass;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EAttribute getLogNode_Name() {
    return (EAttribute) logNodeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EClass getMaterialization() {
    return materializationEClass;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EReference getMaterialization_Tags() {
    return (EReference) materializationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EClass getPropertyLogNode() {
    return propertyLogNodeEClass;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EAttribute getPropertyLogNode_Value() {
    return (EAttribute) propertyLogNodeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EClass getResponsibility() {
    return responsibilityEClass;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EAttribute getResponsibility_Name() {
    return (EAttribute) responsibilityEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EClass getTagLogNode() {
    return tagLogNodeEClass;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EAttribute getTagLogNode_Childrens() {
    return (EAttribute) tagLogNodeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EReference getTagLogNode_Tag() {
    return (EReference) tagLogNodeEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EClass getEStringToLogNodeMapEntry() {
    return eStringToLogNodeMapEntryEClass;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EAttribute getEStringToLogNodeMapEntry_Key() {
    return (EAttribute) eStringToLogNodeMapEntryEClass.getEStructuralFeatures()
        .get(0);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EReference getEStringToLogNodeMapEntry_Value() {
    return (EReference) eStringToLogNodeMapEntryEClass.getEStructuralFeatures()
        .get(1);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public EDataType getLogNodeArray() {
    return logNodeArrayEDataType;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public LogModelFactory getLogModelFactory() {
    return (LogModelFactory) getEFactoryInstance();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private boolean isCreated = false;

  /**
   * Creates the meta-model objects for the package. This method is guarded to
   * have no affect on any invocation but its first. <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * 
   * @generated
   */
  public void createPackageContents() {
    if (isCreated) return;
    isCreated = true;

    // Create classes and their features
    logNodeEClass = createEClass(LOG_NODE);
    createEAttribute(logNodeEClass, LOG_NODE__NAME);

    materializationEClass = createEClass(INNERTAG);
    createEReference(materializationEClass, INNERTAG__TAGS);

    propertyLogNodeEClass = createEClass(PROPERTY_LOG_NODE);
    createEAttribute(propertyLogNodeEClass, PROPERTY_LOG_NODE__VALUE);

    responsibilityEClass = createEClass(RESPONSIBILITY);
    createEAttribute(responsibilityEClass, RESPONSIBILITY__NAME);

    tagLogNodeEClass = createEClass(TAG_LOG_NODE);
    createEAttribute(tagLogNodeEClass, TAG_LOG_NODE__CHILDRENS);
    createEReference(tagLogNodeEClass, TAG_LOG_NODE__TAG);

    eStringToLogNodeMapEntryEClass = createEClass(ESTRING_TO_LOG_NODE_MAP_ENTRY);
    createEAttribute(eStringToLogNodeMapEntryEClass,
        ESTRING_TO_LOG_NODE_MAP_ENTRY__KEY);
    createEReference(eStringToLogNodeMapEntryEClass,
        ESTRING_TO_LOG_NODE_MAP_ENTRY__VALUE);

    // Create data types
    logNodeArrayEDataType = createEDataType(LOG_NODE_ARRAY);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private boolean isInitialized = false;

  /**
   * Complete the initialization of the package and its meta-model. This method
   * is guarded to have no affect on any invocation but its first. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @SuppressWarnings("unchecked")
  public void initializePackageContents() {
    if (isInitialized) return;
    isInitialized = true;

    // Initialize package
    setName(eNAME);
    setNsPrefix(eNS_PREFIX);
    setNsURI(eNS_URI);

    // Obtain other dependent packages
    LogPackageImpl theLogPackage = (LogPackageImpl) EPackage.Registry.INSTANCE
        .getEPackage(LogPackage.eNS_URI);

    // Add supertypes to classes
    propertyLogNodeEClass.getESuperTypes().add(this.getLogNode());
    tagLogNodeEClass.getESuperTypes().add(this.getLogNode());

    // Initialize classes and features; add operations and parameters
    initEClass(logNodeEClass, LogNode.class, "LogNode", !IS_ABSTRACT,
        !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getLogNode_Name(), ecorePackage.getEString(), "name", null,
        0, 1, LogNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
        !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(materializationEClass, InnerTag.class, "InnerTag", !IS_ABSTRACT,
        !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getMaterialization_Tags(), this
        .getEStringToLogNodeMapEntry(), null, "tags", null, 0, -1,
        InnerTag.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
        IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);

    initEClass(propertyLogNodeEClass, PropertyLogNode.class, "PropertyLogNode",
        !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getPropertyLogNode_Value(), ecorePackage.getEString(),
        "value", null, 0, 1, PropertyLogNode.class, !IS_TRANSIENT,
        !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
        !IS_DERIVED, IS_ORDERED);

    initEClass(responsibilityEClass, Responsibility.class, "Responsibility",
        !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getResponsibility_Name(), ecorePackage.getEString(), "name",
        null, 0, 1, Responsibility.class, !IS_TRANSIENT, !IS_VOLATILE,
        IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
        IS_ORDERED);

    initEClass(tagLogNodeEClass, TagLogNode.class, "TagLogNode", !IS_ABSTRACT,
        !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getTagLogNode_Childrens(), this.getLogNodeArray(),
        "childrens", null, 0, 1, TagLogNode.class, !IS_TRANSIENT, !IS_VOLATILE,
        IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
        IS_ORDERED);
    initEReference(getTagLogNode_Tag(), theLogPackage.getTag(), null, "tag",
        null, 0, 1, TagLogNode.class, !IS_TRANSIENT, !IS_VOLATILE,
        IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
        IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(eStringToLogNodeMapEntryEClass, Map.Entry.class,
        "EStringToLogNodeMapEntry", !IS_ABSTRACT, !IS_INTERFACE,
        !IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getEStringToLogNodeMapEntry_Key(),
        ecorePackage.getEString(), "key", null, 0, 1, Map.Entry.class,
        !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
        IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getEStringToLogNodeMapEntry_Value(), this.getLogNode(),
        null, "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT,
        !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
        !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    // Initialize data types
    initEDataType(logNodeArrayEDataType, LogNode[].class, "LogNodeArray",
        IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

    // Create resource
    createResource(eNS_URI);
  }

  public EReference getResponsibility_Materializations() {
    return null;
  }

} // LogModelPackageImpl
