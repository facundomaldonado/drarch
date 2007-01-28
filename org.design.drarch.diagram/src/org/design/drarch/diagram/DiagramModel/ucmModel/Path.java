package org.design.drarch.diagram.DiagramModel.ucmModel;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;


/**
 * @model
 * @author pela
 *
 */
public interface Path extends EObject {
	
	/**
	 * @model type="PathNode" opposite="path"
	 * @return
	 */
	EList getNodes();
	
	/**
	 * @model type="PathNode"
	 * @return
	 */
	EList getStartNodes();
	
	/**
	 * @model type="PathNode"
	 * @return
	 */
	EList getEndNodes();
	
}
