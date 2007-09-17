/**
 * 
 */
package org.design.rules4Java.util;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.ui.IWorkingSet;

/**
 * @author @author maldonadofacundo@gmail.com (Facundo Maldonado)
 *
 */
public class ResourceLocator {

	public static ResourceLocator INSTANCE = new ResourceLocator();
	private IWorkingSet currentWorkingSet;
	private String selectedRuleSource;
	private List currentRuleList = Collections.EMPTY_LIST;
	
	private ResourceLocator(){
		/**
		 * Default contructor
		 */
	}
	
	public void registerCurrentWorkingSet(IWorkingSet currentWorkingSet){
		this.currentWorkingSet = currentWorkingSet;
	}

	public void registerSelectedRuleSource(String selectedRuleSource) {
	    if ("".equals(selectedRuleSource)) selectedRuleSource = null;
	    this.selectedRuleSource = selectedRuleSource;
	  }

	public IWorkingSet getCurrentWorkingSet() {
    	return this.currentWorkingSet;
    }

//	public String getSelectedRuleSource() {
//    	return this.selectedRuleSource;
//    }
	
	/**
	 * This method must be called before the parsing of the rules
	 * and after the selection of the rule file
	 * @return
	 */
	public String getRuleFilePath()
	{
		String path = "";
		//Configuration config;
//        try {
	        //config = new PropertiesConfiguration(RULES_PROPERTIES);
			if ( null == selectedRuleSource || "".equals(selectedRuleSource)) {
			//if externalFilePath == null or is "", the user do not select any rule file,
			//so the default rule file must be load.
				URL relativeURL = //DrarchPlugin.getDefault().getBundle().getEntry("/");
					UtilPlugin.getDefault().getBundle().getEntry("/");
				URL localURL;
	            try {
	                localURL = FileLocator.toFileURL(relativeURL);
	                path = localURL.getPath() + "resources/rules/" + "DefaultRules.drarch";//config.getString("rules.default");
	            } catch (IOException e) {
//	            	logger.error("IOException while trying to get rules file: " + relativeURL + "in method " +
//	    					"getRuleFilePath in class ResourceAllocator.java", e);
	            	e.printStackTrace();
	            }
			} else {
			//the user select an especific rule file
				path = selectedRuleSource;
			}
//        } catch (ConfigurationException exception) {
//	        logger.error("ConfigurationException while trying to get configuration file " + RULES_PROPERTIES + 
//	        		"in method getRuleFilePath in class ResourceAllocator.java", exception);
//        }
    	return path;
	}

	/**
     * @param current_rule_list
     */
    public void registerCurrentRules(List current_rule_list) {
    	this.currentRuleList  = current_rule_list;
    }

	public List getCurrentRuleList() {
    	return this.currentRuleList;
    }

}
