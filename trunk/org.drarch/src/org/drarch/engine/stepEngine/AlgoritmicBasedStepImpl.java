/**
 * 
 */
package org.drarch.engine.stepEngine;

import java.util.Collections;
import java.util.Set;

import org.drarch.engine.ruleEngine.Suggest;

/**
 * @author @author maldonadofacundo@gmail.com (Facundo Maldonado)
 * @deprecated
 */
public abstract class AlgoritmicBasedStepImpl implements IStepImplementation {

	/* (non-Javadoc)
	 * @see org.design.rules4Java.engine.newEngine.IStepActionImplementation#run()
	 */
	@SuppressWarnings("unchecked")
    public Set<Suggest> execute() {
		//no debe retornar nada, se ejecuta el step, se toman las
		//sugerencias y se aceptan todas, se crean los hechos y se
		//agregan a la base
		executeAlgorithm();
		return Collections.EMPTY_SET; 
	}

	/**
     * 
     */
    protected void executeAlgorithm() {
    	//hook method para que las subclases ejecuten el algoritmo deseado.
    }

	/* (non-Javadoc)
	 * @see org.design.rules4Java.engine.stepEngine.IStepImplementation#setLastStepSuggests(java.util.Set)
	 */
	public void setLastStepSuggests(Set<Suggest> lastStepSuggests) {
	    // no se tendria que implementar
	}
}
