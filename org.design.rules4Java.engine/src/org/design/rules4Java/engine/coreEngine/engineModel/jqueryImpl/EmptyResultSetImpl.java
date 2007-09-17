/**
 * 
 */
package org.design.rules4Java.engine.coreEngine.engineModel.jqueryImpl;

import org.design.rules4Java.engine.coreEngine.engineModel.QueryResult;
import org.design.rules4Java.engine.coreEngine.engineModel.ResultSet;

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
