/**
 * 
 */
package org.drarch.engine.stepEngine.drarch;

import org.drarch.engine.jdt.JdtInformation;
import org.eclipse.ui.IWorkingSet;


/**
 * @author @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public class JdtSearchAlgorithm extends DrarchAlgorithm {

	IWorkingSet workingSet;
	
	private static String name = "Jdt Search Phase"; 
	
	public JdtSearchAlgorithm(IWorkingSet aWorkingSet) {
		workingSet = aWorkingSet;
	}
	
	public void run() {
		/*List<String> facts = */JdtInformation.generateFacts(workingSet);
	}
	
	public String getName() {
		return name;
	}
	
}
