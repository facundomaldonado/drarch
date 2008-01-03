/**
 * 
 */
package org.drarch.engine.stepEngine.test;

import junit.framework.TestCase;

import org.drarch.engine.ruleEngine.EngineFactory;
import org.drarch.engine.ruleEngine.Suggest;

/**
 * @author @author maldonadofacundo@gmail.com (Facundo Maldonado)
 *
 */
public class SuggestTest extends TestCase {

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
	}

	/**
	 * Test method for {@link org.design.rules4Java.engine.coreEngine.engineModel.defaultImpl.SuggestImpl#equals(java.lang.Object)}.
	 */
	public void testEqualsObject() {
		Suggest suggestOne = EngineFactory.createSuggest();
		suggestOne.setSuggest("Sugerencia uno");
		
		Suggest suggestDos = EngineFactory.createSuggest();
		suggestDos.setSuggest("Sugerencia dos");
		
		assertFalse("Son distintos", suggestOne.equals(suggestDos));
		suggestDos.setSuggest("Sugerencia uno");
		assertTrue(suggestOne.equals(suggestDos));
	}

}
