package org.design.drarch.diagram.DiagramModel.ucmModel;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * @model
 * @author pela
 *
 */
public interface PathNode extends EObject{
	/**
	 * @model
	 */
	String getResponsibilityName();
	/**
	 * Sets the value of the '{@link org.design.drarch.diagram.ucmModel.PathNode#getResponsibilityName <em>Responsibility Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Responsibility Name</em>' attribute.
	 * @see #getResponsibilityName()
	 * @generated
	 */
	void setResponsibilityName(String value);

	/**
	 * @model opposite="nodes"
	 * @return
	 */
	Path getPath();
	
	/**
	 * Sets the value of the '{@link org.design.drarch.diagram.ucmModel.PathNode#getPath <em>Path</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Path</em>' reference.
	 * @see #getPath()
	 * @generated
	 */
	void setPath(Path value);

	/**
	 * @model type="PathNode" opposite="next"
	 * @return
	 */
	EList getPrevious();
	
	/**
	 * @model type="PathNode" opposite="previous"
	 * @return
	 */
	EList getNext();
	
	boolean addPrevious(PathNode previous);
	boolean removePrevious(PathNode previous);
	boolean addNext(PathNode next);
	boolean removeNext(PathNode next);
	boolean isStart();
	boolean isEnd();
	void addComment(String string);
	String getComment();
	
}
