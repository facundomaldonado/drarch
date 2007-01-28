package org.design.rules4Java.engine.ruleModel;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * @model
 * @author pelado
 */
public interface FactSet extends EObject {
	
	/**
	 * @model type="Fact" containment="true"
	 */
	EList getFactTemplates();

}
