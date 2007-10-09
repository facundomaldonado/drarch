/**
 * 
 */
package org.design.rules4Java.util;

import java.io.IOException;
import java.net.URI;
import java.net.URL;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;

/**
 * @author @author maldonadofacundo@gmail.com (Facundo Maldonado)
 *
 */
public class ResourceLocator {

	public static ResourceLocator	INSTANCE	  = new ResourceLocator();
//	private IWorkingSet	          currentWorkingSet;
//	private String	              selectedRuleSource;
//	private List	              currentRuleList	= Collections.EMPTY_LIST;
//	protected String	currentProjectName;
	
	private ResourceLocator() {
		/**
		 * Default contructor
		 */
	}
	
//	public void setCurrentProjectName(String projectName) {
//		currentProjectName = projectName;
//	}
//
//	public void registerCurrentWorkingSet(IWorkingSet currentWorkingSet) {
//		this.currentWorkingSet = currentWorkingSet;
//	}
//
//	public void registerSelectedRuleSource(String selectedRuleSource) {
//		if ("".equals(selectedRuleSource))
//			selectedRuleSource = null;
//		this.selectedRuleSource = selectedRuleSource;
//	}
//
//	public IWorkingSet getCurrentWorkingSet() {
//		return this.currentWorkingSet;
//	}

	/**
	 * This method must be called before the parsing of the rules
	 * and after the selection of the rule file
	 * @return
	 */
	public String getRuleFilePath(String selectedRuleSource, String currentProjectName) {
		String path = "";
		if (null == selectedRuleSource || "".equals(selectedRuleSource)) {
//			URL relativeURL = UtilPlugin.getDefault().getBundle().getEntry("/");
			URL localURL;
			URI relativeURI = null;
			IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
			IProject recoverySession = workspaceRoot.getProject(currentProjectName);
			if(recoverySession.isOpen()) {
				relativeURI = recoverySession.getLocationURI();
			}
			else {
				//TODO: tirar una excepcion
			}
			try {
				localURL = relativeURI.toURL();
				path = localURL.getPath() + "/resources/rules/"
				        + "DefaultRules.drarch";
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			//the user select an especific rule file
			path = selectedRuleSource;
		}
		return path;
	}

//	/**
//	 * @param current_rule_list
//	 */
//	public void registerCurrentRules(List current_rule_list) {
//		this.currentRuleList = current_rule_list;
//	}
//
//	public List getCurrentRuleList() {
//		return this.currentRuleList;
//	}

}
