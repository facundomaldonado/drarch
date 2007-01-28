/**
 * <copyright>
 * </copyright>
 *
 * $Id: PathImpl.java,v 1.2 2006/10/12 06:59:35 nfrontini Exp $
 */
package org.design.drarch.diagram.DiagramModel.ucmModel.impl;

import java.util.Collection;

import org.design.drarch.diagram.DiagramModel.ucmModel.Path;
import org.design.drarch.diagram.DiagramModel.ucmModel.PathNode;
import org.design.drarch.diagram.DiagramModel.ucmModel.UcmModelPackage;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Path</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.design.drarch.diagram.DiagramModel.ucmModel.impl.PathImpl#getNodes <em>Nodes</em>}</li>
 *   <li>{@link org.design.drarch.diagram.DiagramModel.ucmModel.impl.PathImpl#getStartNodes <em>Start Nodes</em>}</li>
 *   <li>{@link org.design.drarch.diagram.DiagramModel.ucmModel.impl.PathImpl#getEndNodes <em>End Nodes</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PathImpl extends EObjectImpl implements Path {
	/**
	 * The cached value of the '{@link #getNodes() <em>Nodes</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNodes()
	 * @generated
	 * @ordered
	 */
	protected EList nodes = null;

	/**
	 * The cached value of the '{@link #getStartNodes() <em>Start Nodes</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartNodes()
	 * @generated
	 * @ordered
	 */
	protected EList startNodes = null;

	/**
	 * The cached value of the '{@link #getEndNodes() <em>End Nodes</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEndNodes()
	 * @generated
	 * @ordered
	 */
	protected EList endNodes = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PathImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return UcmModelPackage.eINSTANCE.getPath();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getNodes() {
		if (nodes == null) {
			nodes = new EObjectWithInverseResolvingEList(PathNode.class, this, UcmModelPackage.PATH__NODES, UcmModelPackage.PATH_NODE__PATH);
		}
		return nodes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getStartNodes() {
		if (startNodes == null) {
			startNodes = new EObjectResolvingEList(PathNode.class, this, UcmModelPackage.PATH__START_NODES);
		}
		return startNodes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getEndNodes() {
		if (endNodes == null) {
			endNodes = new EObjectResolvingEList(PathNode.class, this, UcmModelPackage.PATH__END_NODES);
		}
		return endNodes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case UcmModelPackage.PATH__NODES:
					return ((InternalEList)getNodes()).basicAdd(otherEnd, msgs);
				default:
					return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
			}
		}
		if (eContainer != null)
			msgs = eBasicRemoveFromContainer(msgs);
		return eBasicSetContainer(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case UcmModelPackage.PATH__NODES:
					return ((InternalEList)getNodes()).basicRemove(otherEnd, msgs);
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
			case UcmModelPackage.PATH__NODES:
				return getNodes();
			case UcmModelPackage.PATH__START_NODES:
				return getStartNodes();
			case UcmModelPackage.PATH__END_NODES:
				return getEndNodes();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case UcmModelPackage.PATH__NODES:
				getNodes().clear();
				getNodes().addAll((Collection)newValue);
				return;
			case UcmModelPackage.PATH__START_NODES:
				getStartNodes().clear();
				getStartNodes().addAll((Collection)newValue);
				return;
			case UcmModelPackage.PATH__END_NODES:
				getEndNodes().clear();
				getEndNodes().addAll((Collection)newValue);
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
			case UcmModelPackage.PATH__NODES:
				getNodes().clear();
				return;
			case UcmModelPackage.PATH__START_NODES:
				getStartNodes().clear();
				return;
			case UcmModelPackage.PATH__END_NODES:
				getEndNodes().clear();
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
			case UcmModelPackage.PATH__NODES:
				return nodes != null && !nodes.isEmpty();
			case UcmModelPackage.PATH__START_NODES:
				return startNodes != null && !startNodes.isEmpty();
			case UcmModelPackage.PATH__END_NODES:
				return endNodes != null && !endNodes.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

} //PathImpl
