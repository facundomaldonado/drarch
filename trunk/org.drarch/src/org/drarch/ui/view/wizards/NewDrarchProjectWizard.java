package org.drarch.ui.view.wizards;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.drarch.Activator;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.osgi.framework.Bundle;

public class NewDrarchProjectWizard extends Wizard implements INewWizard {
	
	Logger logger = Logger.getLogger(NewDrarchProjectWizard.class.getName());
	private NewDrarchProjectWizardPage page;

	/**
	 * Constructor for NewDrarchProjectWizard.
	 */
	public NewDrarchProjectWizard() {
		super();
		logger.debug("New drarch project wizard constructor");
		setNeedsProgressMonitor(true);
		setWindowTitle("New Drarch Project Wizard");
	}
	
	/**
	 * Adding the page to the wizard.
	 */

	public void addPages() {
		page = new NewDrarchProjectWizardPage("New Drarch Project Wizard page");
		addPage(page);
	}

	/**
	 * This method is called when 'Finish' button is pressed in
	 * the wizard. We will create an operation and run it
	 * using wizard as execution context.
	 */
	public boolean performFinish() {
		try {
	        doFinish(null);
        } catch (CoreException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
		return true;
	}

	private void createDirectoriesStructure(IProject myRecoveryProject, IProgressMonitor monitor) {
		try {
	        myRecoveryProject.create(monitor);
	        myRecoveryProject.open(monitor);

		    IFolder depotRulesFolder = myRecoveryProject.getFolder("Rules Depot");
		    depotRulesFolder.create(true,true,monitor);

		    IFolder depotFactsFolder = myRecoveryProject.getFolder("Facts Depot");
		    depotFactsFolder.create(true,true,monitor);
		    
        } catch (CoreException e) {
	        e.printStackTrace();
        }
	}
	
	/**
	 * The worker method. It will find the container, create the
	 * file if missing or just replace its contents, and open
	 * the editor on the newly created file.
	 */
	private void doFinish(IProgressMonitor monitor)	throws CoreException {
		String newProjectName = page.getProjectName();
		
		IWorkspaceRoot myWorkspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IProject myRecoveryProject = myWorkspaceRoot.getProject(newProjectName);
        
		createDirectoriesStructure(myRecoveryProject, monitor);
		createPropertiesConfiguration(myRecoveryProject);
		importFactFiles2newProject(myRecoveryProject);
		importRulesFiles2newProject(myRecoveryProject);
		
		myRecoveryProject.refreshLocal(IProject.DEPTH_INFINITE, monitor);
	}

    @SuppressWarnings("unchecked")
    private void importFactFiles2newProject(IProject project) {
    	IFolder factsFolder = project.getFolder("Facts Depot");
    	try {
    		Bundle drarchPlugin = Activator.getDefault().getBundle();
	    	Enumeration<String> rulesFiles = drarchPlugin.getEntryPaths("/resources/facts/");
	    	Map<String, URL> drarchRuleFiles = filterFilesByPattern(drarchPlugin, rulesFiles, ".rub");
	    	
	    	importFiles2Folder(drarchRuleFiles, factsFolder);
	    	
    	} catch (URISyntaxException e) {
    		logger.error("URISyntaxException while importing facts files",e);
        } catch (IOException e) {
        	logger.error("IOException while importing facts files",e);
        }
    }

	/**
     * 
     */
    @SuppressWarnings("unchecked")
    private void importRulesFiles2newProject(IProject project) {
    	IFolder rulesFolder = project.getFolder("Rules Depot");
    	try {
    		Bundle drarchPlugin = Activator.getDefault().getBundle();
	    	Enumeration<String> rulesFiles = drarchPlugin.getEntryPaths("/resources/rules/");
	    	Map<String, URL> drarchRuleFiles = filterFilesByPattern(drarchPlugin, rulesFiles, ".drarch");
	    	
	    	importFiles2Folder(drarchRuleFiles, rulesFolder);
	    	
    	} catch (URISyntaxException e) {
    		logger.error("URISyntaxException while importing rules files",e);
        } catch (IOException e) {
        	logger.error("IOException while importing rules files",e);
        }
    }

	/**
     * @param files
     * @param destiniFolder
     * @throws URISyntaxException
     * @throws IOException
     */
    private void importFiles2Folder(Map<String, URL> files, IFolder destiniFolder) throws URISyntaxException, IOException {
	    for (Iterator<String> iterator = files.keySet().iterator() ; iterator.hasNext();) {
	    	String fileName = iterator.next();
	    	URL url = (URL)files.get(fileName);
	    	
	    	URI sourceFileURI = FileLocator.toFileURL(url).toURI();

	        File sourceFile = new File(sourceFileURI);
	        
	        URI destiniFolderURI = destiniFolder.getLocationURI();
	        String destiniFileName = destiniFolderURI.getPath() + "/" + fileName.substring(fileName.lastIndexOf("/")) ;
	        File destiniFile = new File(destiniFileName);
	        FileUtils.copyFile(sourceFile, destiniFile);
	    }
    }

	/**
     * @param rulesFiles
     * @return
     */
    private Map<String, URL> filterFilesByPattern(Bundle drarchPlugin, Enumeration<String> files, String pattern) {
    	Map<String, URL> result = new HashMap<String, URL>();
    	while (files.hasMoreElements()) {
    		String file = files.nextElement();
    		if (file.endsWith(pattern)) {
    			URL fileUrl = drarchPlugin.getEntry(file);
    			result.put(file, fileUrl);
    		}
    	}
    	return result;
    }
	/**
     * 
     */
    private void createPropertiesConfiguration(IProject newProject) {
    	String path = newProject.getLocation().toString() + "/" + newProject.getName()+ ".drarchProject";
    	File newFile = new File(path);
        try {
        	
	    	FileUtils.touch(newFile);
	    	newProject.refreshLocal(IResource.DEPTH_ONE, null);
	    	
		    PropertiesConfiguration config;
	        config = new PropertiesConfiguration(newFile);
       
		    config.setProperty("newProject.name", newProject.getName());
		    config.setProperty("newProject.path2RulesDepot", newProject.getLocation().toString() + "/Rules Depot");
		    config.setProperty("newProject.path2FactsDepot", newProject.getLocation().toString() + "/Facts Depot");
		    
		    String projectLocation = newProject.getLocation().toString();
		    config.setProperty("newProject.location", projectLocation);
		    config.save();
		    
        } catch (ConfigurationException e) {
			logger.error("ConfigurationException while trying to create resources in createPropertiesConfiguration method " + 
					"in NewDrarchProjectWizard.java", e);
		} catch (IOException e) {
			logger.error("IOException while trying to create resources in createPropertiesConfiguration method " + 
					"in NewDrarchProjectWizard.java", e);
        } catch (CoreException e) {
        	logger.error("CoreException while trying to create resources in createPropertiesConfiguration method " + 
					"in NewDrarchProjectWizard.java", e);
        	MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), getWindowTitle(),e.getMessage());
        }
    }

	/* (non-Javadoc)
     * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench, org.eclipse.jface.viewers.IStructuredSelection)
     */
    public void init(IWorkbench workbench, IStructuredSelection selection) {
	    // TODO pelado: not yet implemented - init
	    
    }
	
}