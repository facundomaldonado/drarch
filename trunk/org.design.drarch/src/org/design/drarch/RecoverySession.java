/**
 * 
 */
package org.design.drarch;

import org.eclipse.ui.IWorkingSet;

/**
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 *
 */
public class RecoverySession {

	private String selectedRuleSource = "";
	private String defaultRulesSource = "";
	private String projectName = "";
	private String projectLocation = "";
	private String knowledgeBaseSource = "";
	private IWorkingSet workingSet = null;	
	
	public boolean isSetupComplete() {
		if (!"".equals(projectName) && !"".equals(knowledgeBaseSource) && null!= workingSet)
			return true; //TODO reever
		return false;
	}
	
	/**
     * @return
     */
    public String getSelectedRuleSource() {
	    return selectedRuleSource;
    }

	/**
     * @return
     */
    public String getProjectName() {
	    return projectName;
    }

	/**
     * @param projectName
     */
    public void setProjectName(String projectName) {
    	this.projectName = projectName;
    }

	/**
     * @param workingSet
     */
    public void setWorkingSet(IWorkingSet workingSet) {
    	this.workingSet = workingSet;
    }

	public IWorkingSet getWorkingSet() {
    	return this.workingSet;
    }

	/**
     * @param path2DefaultRules
     */
    public void setPathToDefaultRules(String path2DefaultRules) {
    	this.defaultRulesSource = path2DefaultRules;
    }

	/**
     * @param path2KnowledgeBase
     */
    public void setPathToKnowledgeBase(String path2KnowledgeBase) {
    	this.knowledgeBaseSource = path2KnowledgeBase;
    }
    
    public String getPathToKnowledgeBase() {
    	return knowledgeBaseSource;
    }

	/**
     * @param string
     */
    public void setPathtoSelectedRulesSource(String path2RulesFile) {
    	this.selectedRuleSource = path2RulesFile;
    }

	public String getDefaultRulesSource() {
    	return this.defaultRulesSource;
    }

	public String getProjectLocation() {
    	return this.projectLocation;
    }

	public void setProjectLocation(String projectLocation) {
    	this.projectLocation = projectLocation;
    }

}
