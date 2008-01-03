package org.drarch.engine.jdt;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.core.search.SearchParticipant;
import org.eclipse.jdt.core.search.SearchPattern;
import org.eclipse.jdt.core.search.SearchRequestor;
import org.eclipse.ui.IWorkingSet;

/**
 * User the JDT core to search into the code.
 */
public class JdtInformation {
	
	public static final Logger logger = Logger.getLogger(JdtInformation.class.getName());
	
	
	/**
	 * Set up the configuration and run the sarch to generate facts.
	 * 
	 * @param workingSet
	 * @return
	 */
	public static List<String> generateFacts(IWorkingSet workingSet) {
		
	    // Create search pattern.
	    SearchPattern pattern = SearchPattern.createPattern(
	    		"*vent*", IJavaSearchConstants.METHOD, IJavaSearchConstants.REFERENCES, 
	    		SearchPattern.R_PATTERN_MATCH);

	    // Create search scope.
	    IAdaptable[] elements = workingSet.getElements();
	    IJavaElement[] javaElements = new IJavaElement[elements.length];
	    for (int i= 0; i < elements.length; i++) {
			IJavaElement javaElement = (IJavaElement) elements[i].getAdapter(IJavaElement.class);
			if (javaElement != null) { 
				javaElements[i] = javaElement;
			}
		}
	    IJavaSearchScope scope = SearchEngine.createJavaSearchScope(javaElements);

	    // Get the search requestor.
	    SearchRequestor requestor = new DrarchSearchRequestor();

	    // Search.
	    SearchEngine searchEngine = new SearchEngine();
	    try {
	    	logger.info("Start the JDT search");
	    	logger.info("Searching...");
			searchEngine.search(pattern, 
					new SearchParticipant[] {SearchEngine.getDefaultSearchParticipant()}, 
					scope, requestor, null);
			logger.info("End the JDT search.");
		} catch (CoreException e) {
			logger.error("Fail the JDT search", e);
		}
		return null;
	}
}
