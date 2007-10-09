/**
 * 
 */
package org.design.drarch.ui.wizards;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.design.drarch.DrarchConstants;
import org.design.drarch.DrarchPlugin;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

/**
 * @author
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public class NewRecoverySessionWizard extends Wizard implements INewWizard {
	
	Logger logger = Logger.getLogger(NewRecoverySessionWizard.class.getName());
	NewRecoverySessionWizardPage page;
	
	public NewRecoverySessionWizard() {
		super();
		setNeedsProgressMonitor(true);
		setWindowTitle("New Recovery Session Wizard");
	}
	
	public void addPages() {
		page = new NewRecoverySessionWizardPage("Recovery Session");
		addPage(page);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		String newProjectName = page.getProjectName();
		IWorkspaceRoot myWorkspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IProject myRecoverySessionProject = myWorkspaceRoot.getProject(newProjectName);
		try {
            myRecoverySessionProject.create(null);
            myRecoverySessionProject.open(null);
            
            logger.debug("project name " + newProjectName + " seted in ResourceLocator");
            
            copyDefaultRulesFileToNewProject(myRecoverySessionProject);
            copyKnowledgeBaseFileToNewProject(myRecoverySessionProject);
            createFlabotFolder(myRecoverySessionProject);
           	
            createProjectPropertiesFile(myRecoverySessionProject);
            myRecoverySessionProject.refreshLocal(IResource.DEPTH_INFINITE, null);
            
		} catch (ConfigurationException e) {
			logger.error("ConfigurationException while trying to create resources in performFinish method " + 
					"in NewRecoverySessionWizard.java", e);
		} catch (IOException e) {
			logger.error("IOException while trying to create resources in performFinish method " + 
					"in NewRecoverySessionWizard.java", e);
        } catch (CoreException e) {
        	logger.error("CoreException while trying to create resources in performFinish method " + 
					"in NewRecoverySessionWizard.java", e);
        	MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), getWindowTitle(),e.getMessage());
        } catch (URISyntaxException e) {
        	logger.error("URISyntaxException while trying to create resources in performFinish method " + 
					"in NewRecoverySessionWizard.java", e);
        } 
		return true;
	}

	/**
     * @param myRecoverySessionProject
	 * @throws CoreException 
     */
    private void createFlabotFolder(IProject myRecoverySessionProject) throws CoreException {
    	IFolder newFolder = myRecoverySessionProject.getFolder(DrarchConstants.NEW_PROJECT_FLABOT_FOLDER);
 	    newFolder.create(true,true,null);	    
    }

	/**
     * @throws ConfigurationException
	 * @throws CoreException 
	 * @throws IOException 
     */
    private void createProjectPropertiesFile(IProject newProject) throws ConfigurationException, CoreException, IOException {
    	logger.info("Creating new .properties file to store selected resources");
    	
    	String path = newProject.getLocation().toString() + "/" + DrarchConstants.PROPERTIES_FILENAME;
    	File newFile = new File(path);
    	FileUtils.touch(newFile);
    	newProject.refreshLocal(IResource.DEPTH_ONE, null);
    	
	    PropertiesConfiguration config = new PropertiesConfiguration(newFile);
	    config.setProperty(DrarchConstants.PROPERTIES_PROJECT_NAME,
	    				   newProject.getName());
	    config.setProperty(DrarchConstants.PROPERTY_DEFAULT_RULES,
	    				   DrarchConstants.VALUE_DEFAULT_RULES);
	    config.setProperty(DrarchConstants.PROPERTY_KNOWLEDGEBASE, 
	    				   DrarchConstants.VALUE_KNOWLEDGEBASE);
	    config.setProperty(DrarchConstants.PROPERTY_WORKINGSET_NAME, page.getWorkingSetName());
	    String projectLocation = newProject.getLocation().toString();
	    config.setProperty(DrarchConstants.NEW_PROJECT_LOCATION, projectLocation);
	    config.save();
    }

	/**
     * @param myRecoverySession
     * @throws CoreException
     * @throws URISyntaxException
     * @throws IOException
     */
    private void copyKnowledgeBaseFileToNewProject(IProject myRecoverySession)  throws CoreException, URISyntaxException, IOException {
    	logger.info("Copying KnowledgeBase file from plugin to new project");
    	//Copio el archivo(o los en su defecto) que me aportan predicados a mi analisis
    	IFolder newFolder = myRecoverySession.getFolder(DrarchConstants.NEW_PROJECT_FACTS_FOLDER);
 	    newFolder.create(true,true,null);
 	    
 	    URL sourceURL = DrarchPlugin.getDefault().getBundle().getEntry(DrarchConstants.PATH_TO_KNOWLEDGEBASE_FILE);
 	    URI sourceFileURI = FileLocator.toFileURL(sourceURL).toURI();
 	    File sourceFile = new File(sourceFileURI);
 	    
 	    URI destiniFolderURI = newFolder.getLocationURI();
 	    String destiniFileName = destiniFolderURI.getPath() + "/" + DrarchConstants.VALUE_KNOWLEDGEBASE;
 	    File destiniFile = new File(destiniFileName);
 	    FileUtils.copyFile(sourceFile, destiniFile);
 	    
 	    // Creo un archivo vacio que me va a servir para agregar los predicados a
 	    // medida que voy avanzando en el analisis.
 	    String myKnowledgeBaseFileName = destiniFolderURI.getPath() + "/" + DrarchConstants.NEW_PROJECT_MyKNOWLEDGEBASE_FILE;
 	    File newMyPredicates = new File(myKnowledgeBaseFileName);
 	    FileUtils.touch(newMyPredicates);
    }

	/**
     * @param myRecoverySession
     * @throws CoreException
     * @throws URISyntaxException
     * @throws IOException
     */
    private void copyDefaultRulesFileToNewProject(IProject myRecoverySession) throws CoreException, URISyntaxException, IOException {
    	logger.info("Copying DefaultRules file from plugin to the new project ");
    	
	    IFolder newFolder = myRecoverySession.getFolder(DrarchConstants.NEW_PROJECT_RULES_FOLDER);
	    newFolder.create(true,true,null);
	    
	    URL sourceURL = DrarchPlugin.getDefault().getBundle().getEntry(DrarchConstants.PATH_TO_DEFAULT_RULES_FILE);
	    URI sourceFileURI = FileLocator.toFileURL(sourceURL).toURI();
	    File sourceFile = new File(sourceFileURI);
	    
	    URI destiniFolderURI = newFolder.getLocationURI();
	    String destiniFileName = destiniFolderURI.getPath() + "/" + DrarchConstants.VALUE_DEFAULT_RULES ;
	    File destiniFile = new File(destiniFileName);
	    FileUtils.copyFile(sourceFile, destiniFile);
    }

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench,
	 *      org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		// TODO pelado: not yet implemented - init
	}

}
