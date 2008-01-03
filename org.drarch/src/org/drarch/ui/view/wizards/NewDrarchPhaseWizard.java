package org.drarch.ui.view.wizards;

import java.io.File;
import java.io.IOException;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.PlatformUI;

public class NewDrarchPhaseWizard extends Wizard implements INewWizard {

	Logger	                         logger	= Logger.getLogger(NewDrarchPhaseWizard.class.getName());
	private NewDrarchPhaseWizardPage	page;
	private ISelection	             selection;
	private String phaseName;
    private PropertiesConfiguration config;
	
	/**
	 * Constructor for NewDrarchPhaseWizard.
	 */
	public NewDrarchPhaseWizard() {
		super();
		setNeedsProgressMonitor(true);
	}

	/**
	 * Adding the page to the wizard.
	 */
	public void addPages() {
		page = new NewDrarchPhaseWizardPage(selection);
		addPage(page);
	}

	/**
	 * This method is called when 'Finish' button is pressed in
	 * the wizard. We will create an operation and run it
	 * using wizard as execution context.
	 */
	public boolean performFinish() {
		phaseName = page.getPhaseName();
		IStructuredSelection strSelection = (IStructuredSelection) selection;
		IFolder phase;
		IProject selectedProject;
		if (strSelection.getFirstElement() instanceof IProject)
			selectedProject = (IProject)strSelection.getFirstElement();
		else {
			IFile configFile = (IFile)strSelection.getFirstElement();
			selectedProject = (IProject)configFile.getProject();
		}
		phase = selectedProject.getFolder(phaseName);
		createConfigFileForPhase(phase);
		createDirectoryStructure(phase);
		return true;
	}

	/**
     * @param phase
     */
    private void createConfigFileForPhase(IFolder phase) {
    	try {
	    	phase.create(true, true, null);
	    	
	    	String flabotFileName = page.getFlabotFileName();
	    	String workingSetName = page.getWorkingSetName();
	    	boolean isInteractive = page.isInteractive();
	    	
	    	String path = phase.getLocation().toString() + "/" + phaseName + ".drarchPhase";
	    	File newFile = new File(path);
	    	FileUtils.touch(newFile);
	    	phase.refreshLocal(IResource.DEPTH_ONE, null);
	    	
	        config = new PropertiesConfiguration(newFile);

	        config.setProperty("newPhase.projectName", phase.getProject().getName());
		    config.setProperty("newPhase.name", phaseName);
		    config.setProperty("newPhase.flabotFileName", flabotFileName);
		    config.setProperty("newPhase.workingSetName", workingSetName);
		    config.setProperty("newPhase.isInteractive", String.valueOf(isInteractive));
		    config.save();
		    
        } catch (ConfigurationException e) {
			logger.error("ConfigurationException while trying to create resources in createPropertiesConfiguration method " + 
					"in NewDrarchPhaseWizard.java", e);
		} catch (IOException e) {
			logger.error("IOException while trying to create resources in createPropertiesConfiguration method " + 
					"in NewDrarchPhaseWizard.java", e);
        } catch (CoreException e) {
        	logger.error("CoreException while trying to create resources in createPropertiesConfiguration method " + 
					"in NewDrarchPhaseWizard.java", e);
        	MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), getWindowTitle(),e.getMessage());
        }	    
    }

	/**
     * @param phase
     */
//    private void importFactFiles2newProject(IFolder phase) {
//    	IFolder factsFolder = phase.getFolder("facts");
//    	try {
//    		Bundle drarchPlugin = DrarchPlugin.getDefault().getBundle();
//	    	Enumeration<String> rulesFiles = drarchPlugin.getEntryPaths("/resources/facts/");
//	    	Map<String, URL> drarchRuleFiles = filterFilesByPattern(drarchPlugin, rulesFiles, ".rub");
//	    	
//	    	importFiles2Folder(drarchRuleFiles, factsFolder);
//	    	
//    	} catch (URISyntaxException e) {
//    		logger.error("URISyntaxException while importing facts files",e);
//        } catch (IOException e) {
//        	logger.error("IOException while importing facts files",e);
//        }
//    }
//
//	/**
//     * 
//     */
//    private void importRulesFiles2newProject(IFolder phase) {
//    	IFolder rulesFolder = phase.getFolder("rules");
//    	try {
//    		Bundle drarchPlugin = DrarchPlugin.getDefault().getBundle();
//	    	Enumeration<String> rulesFiles = drarchPlugin.getEntryPaths("/resources/rules/");
//	    	Map<String, URL> drarchRuleFiles = filterFilesByPattern(drarchPlugin, rulesFiles, ".drarch");
//	    	
//	    	importFiles2Folder(drarchRuleFiles, rulesFolder);
//	    	
//    	} catch (URISyntaxException e) {
//    		logger.error("URISyntaxException while importing rules files",e);
//        } catch (IOException e) {
//        	logger.error("IOException while importing rules files",e);
//        }
//    }
//
//	/**
//     * @param files
//     * @param destiniFolder
//     * @throws URISyntaxException
//     * @throws IOException
//     */
//    private void importFiles2Folder(Map<String, URL> files, IFolder destiniFolder) throws URISyntaxException, IOException {
//	    for (Iterator<String> iterator = files.keySet().iterator() ; iterator.hasNext();) {
//	    	String fileName = iterator.next();
//	    	URL url = (URL)files.get(fileName);
//	    	
//	    	URI sourceFileURI = FileLocator.toFileURL(url).toURI();
//
//	        File sourceFile = new File(sourceFileURI);
//	        
//	        URI destiniFolderURI = destiniFolder.getLocationURI();
//	        String destiniFileName = destiniFolderURI.getPath() + "/" + fileName.substring(fileName.lastIndexOf("/")) ;
//	        File destiniFile = new File(destiniFileName);
//	        FileUtils.copyFile(sourceFile, destiniFile);
//	    }
//    }
//
//	/**
//     * @param rulesFiles
//     * @return
//     */
//    private Map<String, URL> filterFilesByPattern(Bundle drarchPlugin, Enumeration<String> files, String pattern) {
//    	Map<String, URL> result = new HashMap<String, URL>();
//    	while (files.hasMoreElements()) {
//    		String file = files.nextElement();
//    		if (file.endsWith(pattern)) {
//    			URL fileUrl = drarchPlugin.getEntry(file);
//    			result.put(file, fileUrl);
//    		}
//    	}
//    	return result;
//    }

	/**
	 * 
	 */
	private void createDirectoryStructure(IFolder phase) {
		try {
			IFolder rulesFolder = phase.getFolder("rules");
			rulesFolder.create(true, false, null);

			IFolder factsFolder = phase.getFolder("facts");
			factsFolder.create(true, false, null);
			createPhaseKnowledgeBase(factsFolder);

			IFolder flabotFolder = phase.getFolder("flabot");
			flabotFolder.create(true, false, null);

		} catch (CoreException e) {
			logger.error("CoreException while trying to create resources in createDirectoryStructure method " + "in NewDrarchPhaseWizard.java", e);
		}
	}

	/**
     * @param factsFolder
     */
    private void createPhaseKnowledgeBase(IFolder factsFolder) {
    	String knowledgeBaseFileName = phaseName + ".rub";
	    String phaseKnowledgeBaseFilePath = factsFolder.getLocation().toString() + 
	    	String.valueOf(Path.SEPARATOR) + knowledgeBaseFileName;
	    File phaseKnowledgeBaseFile = new File(phaseKnowledgeBaseFilePath);
	    try {
	        FileUtils.touch(phaseKnowledgeBaseFile);
	        factsFolder.refreshLocal(IFolder.DEPTH_INFINITE, null);
	        
	        config.setProperty("newPhase.knowledgeBaseFile", knowledgeBaseFileName);
	        config.save();
	        
        } catch (IOException e) {
        	logger.error(e.getClass().getName(), e);
        } catch (CoreException e) {
        	logger.error(e.getClass().getName(), e);
        } catch (ConfigurationException e) {
        	logger.error(e.getClass().getName(), e);
        }
    }

	/**
	 * We will accept the selection in the workbench to see if
	 * we can initialize from it.
	 * @see IWorkbenchWizard#init(IWorkbench, IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.selection = selection;
	}
}