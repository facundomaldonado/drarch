package org.drarch.ui.view.wizards;

import java.io.File;
import java.io.IOException;
import java.net.URI;

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

	Logger logger = Logger.getLogger(NewDrarchPhaseWizard.class.getName());

	private NewDrarchPhaseWizardPage page;

	private ISelection selection;

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
	 * This method is called when 'Finish' button is pressed in the wizard. We
	 * will create an operation and run it using wizard as execution context.
	 */
	public boolean performFinish() {
		phaseName = page.getPhaseName();
		IStructuredSelection strSelection = (IStructuredSelection) selection;
		IFolder phase;
		IProject selectedProject;
		if (strSelection.getFirstElement() instanceof IProject)
			selectedProject = (IProject) strSelection.getFirstElement();
		else {
			IFile configFile = (IFile) strSelection.getFirstElement();
			selectedProject = (IProject) configFile.getProject();
		}
		phase = selectedProject.getFolder(phaseName);
		try {
			phase.create(true, true, null);
			String path = phase.getLocation().toString() + "/" + phaseName
					+ ".drarchPhase";
			File newFile = new File(path);
			FileUtils.touch(newFile);
			phase.refreshLocal(IResource.DEPTH_ONE, null);

			config = new PropertiesConfiguration(newFile);
		} catch (CoreException e) {
			logger
					.error(
							"CoreException while trying to create resources in createPropertiesConfiguration method "
									+ "in NewDrarchPhaseWizard.java", e);
			MessageDialog.openError(PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getShell(), getWindowTitle(), e
					.getMessage());
		} catch (IOException e) {
			logger
					.error(
							"IOException while trying to create resources in createPropertiesConfiguration method "
									+ "in NewDrarchPhaseWizard.java", e);
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		createDirectoryStructure(phase);
		createConfigFileForPhase(phase);
		return true;
	}

	/**
	 * @param phase
	 */
	private void createConfigFileForPhase(IFolder phase) {
		try {

			String flabotFilePath = phase.getLocation().toString();
			String flabotFileName = flabotFilePath + "/flabot/"
					+ page.getFlabotFileName();
			String workingSetName = page.getWorkingSetName();
			boolean isInteractive = page.isInteractive();

			config.setProperty("newPhase.projectName", phase.getProject()
					.getName());
			config.setProperty("newPhase.name", phaseName);
			config.setProperty("newPhase.flabotFileName", flabotFileName);
			config.setProperty("newPhase.workingSetName", workingSetName);
			config.setProperty("newPhase.isInteractive", String
					.valueOf(isInteractive));
			config.save();

		} catch (ConfigurationException e) {
			logger
					.error(
							"ConfigurationException while trying to create resources in createPropertiesConfiguration method "
									+ "in NewDrarchPhaseWizard.java", e);
		}
	}

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
			logger
					.error(
							"CoreException while trying to create resources in createDirectoryStructure method "
									+ "in NewDrarchPhaseWizard.java", e);
		}
	}

	/**
	 * @param factsFolder
	 */
	private void createPhaseKnowledgeBase(IFolder factsFolder) {
		try {
			IProject project = factsFolder.getProject();
			IFolder factsdepot = project.getFolder("Facts Depot");
			IFile knowledgeBase = factsdepot.getFile("KnowledgeBase.rub");
			System.out.println(knowledgeBase.isAccessible());

			File sourceFile = new File(knowledgeBase.getLocationURI());

			URI destiniFolderURI = factsFolder.getLocationURI();
			File destiniFile = new File(destiniFolderURI.getPath()
					+ "/KnowledgeBase.rub");

			FileUtils.copyFile(sourceFile, destiniFile);

			String knowledgeBaseFileName = phaseName + ".rub";
			String phaseKnowledgeBaseFilePath = factsFolder.getLocation()
					.toString()
					+ String.valueOf(Path.SEPARATOR) + knowledgeBaseFileName;
			File phaseKnowledgeBaseFile = new File(phaseKnowledgeBaseFilePath);

			FileUtils.touch(phaseKnowledgeBaseFile);
			factsFolder.refreshLocal(IFolder.DEPTH_INFINITE, null);

			config.setProperty("newPhase.knowledgeBaseFile",
					knowledgeBaseFileName);
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
	 * We will accept the selection in the workbench to see if we can initialize
	 * from it.
	 * 
	 * @see IWorkbenchWizard#init(IWorkbench, IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.selection = selection;
	}
}