/**
 * 
 */
package org.drarch.engine.ruleEngine;


/**
 * @author pelado
 *
 */
class EmptyResultSetImpl implements ResultSet {

	/* (non-Javadoc)
	 * @see org.design.rules4Java.engine.coreEngine.engineModel.ResultSet#hasMoreElements()
	 */
	public boolean hasMoreElements() {
		return false;

	}

	/* (non-Javadoc)
	 * @see org.design.rules4Java.engine.coreEngine.engineModel.ResultSet#next()
	 */
	public QueryResult next() {
		// TODO pelado: not yet implemented - next
		return null;

	}

}
