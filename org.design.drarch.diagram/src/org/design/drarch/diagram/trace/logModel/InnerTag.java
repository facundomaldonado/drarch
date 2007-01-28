package org.design.drarch.diagram.trace.logModel;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

/**
 * @model
 * @author pela
 *
 */
public interface InnerTag extends EObject {
	
	/**
	 * get properties and tags
	 * @model keyType="String" valueType="LogNode"
	 * @return
	 */
	EMap getTags();
}
