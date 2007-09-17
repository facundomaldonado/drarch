/**
 * <copyright> </copyright>
 * 
 * $Id: LogModelAdapterFactory.java,v 1.1 2006/10/07 19:05:17 nfrontini Exp $
 */
package org.design.drarch.diagram.trace.logModel.util;

import java.util.Map;

import org.design.drarch.diagram.trace.logModel.InnerTag;
import org.design.drarch.diagram.trace.logModel.LogModelPackage;
import org.design.drarch.diagram.trace.logModel.LogNode;
import org.design.drarch.diagram.trace.logModel.PropertyLogNode;
import org.design.drarch.diagram.trace.logModel.Responsibility;
import org.design.drarch.diagram.trace.logModel.TagLogNode;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;


/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It
 * provides an adapter <code>createXXX</code> method for each class of the
 * model. <!-- end-user-doc -->
 * 
 * @see org.design.drarch.diagram.trace.logModel.LogModelPackage
 * @generated
 */
public class LogModelAdapterFactory extends AdapterFactoryImpl {
  /**
   * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected static LogModelPackage modelPackage;

  /**
   * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!--
   * end-user-doc -->
   * 
   * @generated
   */
  public LogModelAdapterFactory() {
    if (modelPackage == null) {
      modelPackage = LogModelPackage.eINSTANCE;
    }
  }

  /**
   * Returns whether this factory is applicable for the type of the object. <!--
   * begin-user-doc --> This implementation returns <code>true</code> if the
   * object is either the model's package or is an instance object of the model.
   * <!-- end-user-doc -->
   * 
   * @return whether this factory is applicable for the type of the object.
   * @generated
   */
  public boolean isFactoryForType(Object object) {
    if (object == modelPackage) {
      return true;
    }
    if (object instanceof EObject) {
      return ((EObject) object).eClass().getEPackage() == modelPackage;
    }
    return false;
  }

  /**
   * The switch the delegates to the <code>createXXX</code> methods. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected LogModelSwitch modelSwitch = new LogModelSwitch() {
    public Object caseLogNode(LogNode object) {
      return createLogNodeAdapter();
    }

    public Object caseMaterialization(InnerTag object) {
      return createMaterializationAdapter();
    }

    public Object casePropertyLogNode(PropertyLogNode object) {
      return createPropertyLogNodeAdapter();
    }

    public Object caseResponsibility(Responsibility object) {
      return createResponsibilityAdapter();
    }

    public Object caseTagLogNode(TagLogNode object) {
      return createTagLogNodeAdapter();
    }

    public Object caseEStringToLogNodeMapEntry(Map.Entry object) {
      return createEStringToLogNodeMapEntryAdapter();
    }

    public Object defaultCase(EObject object) {
      return createEObjectAdapter();
    }
  };

  /**
   * Creates an adapter for the <code>target</code>. <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * 
   * @param target the object to adapt.
   * @return the adapter for the <code>target</code>.
   * @generated
   */
  public Adapter createAdapter(Notifier target) {
    return (Adapter) modelSwitch.doSwitch((EObject) target);
  }


  /**
   * Creates a new adapter for an object of class '{@link org.design.drarch.diagram.trace.logModel.LogNode <em>Log Node</em>}'.
   * <!-- begin-user-doc --> This default implementation returns null so that we
   * can easily ignore cases; it's useful to ignore a case when inheritance will
   * catch all the cases anyway. <!-- end-user-doc -->
   * 
   * @return the new adapter.
   * @see org.design.drarch.diagram.trace.logModel.LogNode
   * @generated
   */
  public Adapter createLogNodeAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.design.drarch.diagram.trace.logModel.InnerTag <em>InnerTag</em>}'.
   * <!-- begin-user-doc --> This default implementation returns null so that we
   * can easily ignore cases; it's useful to ignore a case when inheritance will
   * catch all the cases anyway. <!-- end-user-doc -->
   * 
   * @return the new adapter.
   * @see org.design.drarch.diagram.trace.logModel.InnerTag
   * @generated
   */
  public Adapter createMaterializationAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.design.drarch.diagram.trace.logModel.PropertyLogNode <em>Property Log Node</em>}'.
   * <!-- begin-user-doc --> This default implementation returns null so that we
   * can easily ignore cases; it's useful to ignore a case when inheritance will
   * catch all the cases anyway. <!-- end-user-doc -->
   * 
   * @return the new adapter.
   * @see org.design.drarch.diagram.trace.logModel.PropertyLogNode
   * @generated
   */
  public Adapter createPropertyLogNodeAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.design.drarch.diagram.trace.logModel.Responsibility <em>Responsibility</em>}'.
   * <!-- begin-user-doc --> This default implementation returns null so that we
   * can easily ignore cases; it's useful to ignore a case when inheritance will
   * catch all the cases anyway. <!-- end-user-doc -->
   * 
   * @return the new adapter.
   * @see org.design.drarch.diagram.trace.logModel.Responsibility
   * @generated
   */
  public Adapter createResponsibilityAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.design.drarch.diagram.trace.logModel.TagLogNode <em>Tag Log Node</em>}'.
   * <!-- begin-user-doc --> This default implementation returns null so that we
   * can easily ignore cases; it's useful to ignore a case when inheritance will
   * catch all the cases anyway. <!-- end-user-doc -->
   * 
   * @return the new adapter.
   * @see org.design.drarch.diagram.trace.logModel.TagLogNode
   * @generated
   */
  public Adapter createTagLogNodeAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>EString To Log Node Map Entry</em>}'.
   * <!-- begin-user-doc --> This default implementation returns null so that we
   * can easily ignore cases; it's useful to ignore a case when inheritance will
   * catch all the cases anyway. <!-- end-user-doc -->
   * 
   * @return the new adapter.
   * @see java.util.Map.Entry
   * @generated
   */
  public Adapter createEStringToLogNodeMapEntryAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for the default case. <!-- begin-user-doc --> This
   * default implementation returns null. <!-- end-user-doc -->
   * 
   * @return the new adapter.
   * @generated
   */
  public Adapter createEObjectAdapter() {
    return null;
  }

} // LogModelAdapterFactory
