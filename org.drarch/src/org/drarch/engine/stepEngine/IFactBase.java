/**
 * 
 */
package org.drarch.engine.stepEngine;

import java.util.Set;

import org.drarch.engine.ruleModel.Fact;

/**
 * @author @author maldonadofacundo@gmail.com (Facundo Maldonado)
 *
 */
public interface IFactBase {

	public void addFacts(Set<Fact> newFacts);
	public void addFact(Fact newFact);
	public void removeFact(Fact oldFact);
	
}
