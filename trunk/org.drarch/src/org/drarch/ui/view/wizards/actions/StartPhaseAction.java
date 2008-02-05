package org.drarch.ui.view.wizards.actions;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.drarch.Activator;
import org.drarch.diagram.flabot.DiagramManager;
import org.drarch.engine.DrarchEngine;
import org.drarch.engine.parser.RulesFileParser;
import org.drarch.engine.ruleModel.Rule;
import org.drarch.engine.stepEngine.AlgorithmicPhase;
import org.drarch.engine.stepEngine.IPhase;
import org.drarch.engine.stepEngine.Phase;
import org.drarch.engine.stepEngine.drarch.DrarchAlgorithmicPhase;
import org.drarch.engine.stepEngine.drarch.DrarchAlgorithmicPhaseHelper;
import org.drarch.engine.stepEngine.drarch.DrarchInteractivePhase;
import org.drarch.engine.stepEngine.drarch.DrarchPhaseHelper;
import org.drarch.engine.stepEngine.drarch.JdtSearchAlgorithm;
import org.drarch.ui.view.DrarchSuggestsView;
import org.drarch.ui.view.wizards.NewDrarchPhaseWizard;
import org.drarch.ui.view.wizards.NewDrarchPhaseWizardPage;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkingSet;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

public class StartPhaseAction implements IObjectActionDelegate {

	private static Log logger = LogFactory.getLog(StartPhaseAction.class
			.getName());

	private ISelection cachedSelection;

	private PropertiesConfiguration config;

	public StartPhaseAction() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction,
	 *      org.eclipse.ui.IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		// TODO pelado: not yet implemented - setActivePart

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
	 */
	public void run(IAction action) {
		try {
			// select .properties file
			StructuredSelection selection = (StructuredSelection) cachedSelection;
			IFile file = (IFile) selection.getFirstElement();
			config = new PropertiesConfiguration(file.getLocation().toFile());
			String phaseName = (String) config.getProperty("newPhase.name");

			logger.info("Starting phase named: " + phaseName);

			List<Rule> listOfRules = getPhaseRules(phaseName);
			Map<String, File> factFiles = getPhaseFactFiles(phaseName);
			String flabotFileName = (String) config
					.getProperty("newPhase.flabotFileName");
			String workingSetName = (String) config
					.getProperty("newPhase.workingSetName");

			// getting selected workingset from workbench
			IWorkingSet workingSet = Activator.getDefault().getWorkbench()
					.getWorkingSetManager().getWorkingSet(workingSetName);

			String phaseType = (String) config
					.getProperty("newPhase.phaseType");
			IPhase phase = null;
			if (phaseType.equals(NewDrarchPhaseWizardPage.INTERACTIVE)) {
				DrarchPhaseHelper phaseHelper = new DrarchPhaseHelper(
						phaseName, flabotFileName, listOfRules, factFiles,
						workingSet);
				phase = new DrarchInteractivePhase(phaseHelper);
			} else if (phaseType
					.equals(NewDrarchPhaseWizardPage.NON_INTERACTIVE)) {
				// phase = new NonInteractivePhase();
			} else {// es algoritmica
				DrarchAlgorithmicPhaseHelper phaseHelper = new DrarchAlgorithmicPhaseHelper(
						phaseName, flabotFileName, new JdtSearchAlgorithm(
								workingSet), factFiles, workingSet);
				String phaseAlgorithmicType = (String) config
						.getProperty("newPhase.phaseAlgorithmicType");
				if ("JDT".equals(phaseAlgorithmicType)) {
					phase = new DrarchAlgorithmicPhase(phaseHelper);
				}
			}
			DrarchEngine.INSTANCE.addNewPhase(phase);
			DiagramManager.getInstance().setFlabotFileName(flabotFileName);
			openOrUpdateView(phase);
		} catch (ConfigurationException e) {
			logger
					.error(
							"ConfigurationException while trying to get properties file",
							e);
			Shell shell = new Shell();
			MessageDialog
					.openError(shell, "Drarch Plug-in",
							"ConfigurationException while trying to get properties file");
		} catch (RuntimeException e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	/**
	 * @param phase
	 */
	private void openOrUpdateView(IPhase phase) {
		assert (null != phase);
		try {
			getWindow().getActivePage().showView(DrarchSuggestsView.ID_VIEW);
			DrarchSuggestsView drarchView = (DrarchSuggestsView) getWindow()
					.getActivePage().findView(DrarchSuggestsView.ID_VIEW);
			drarchView.setActivePhase(phase);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}

	private IWorkbenchWindow getWindow() {
		return PlatformUI.getWorkbench().getActiveWorkbenchWindow();
	}

	/**
	 * @param phaseName
	 * @return
	 */
	private Map<String, File> getPhaseFactFiles(String phaseName) {
		String projectName = (String) config
				.getProperty("newPhase.projectName");
		IWorkspaceRoot workSpaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IProject project = workSpaceRoot.getProject(projectName);
		IFolder phaseFolder = project.getFolder(phaseName);

		IFolder factsFolder = phaseFolder.getFolder("facts");

		assert (factsFolder.exists());
		assert (factsFolder.isAccessible());

		Map<String, File> factsFiles = new HashMap<String, File>();

		try {
			IResource[] resources = factsFolder.members();
			for (int i = 0; i < resources.length; i++) {
				IFile file = (IFile) resources[i];
				File factFile = new File(file.getLocation().toString());
				factsFiles.put(file.getName(), factFile);
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return factsFiles;
	}

	/**
	 * @param config
	 * @param phaseName
	 */
	private List<Rule> getPhaseRules(String phaseName) {
		String projectName = (String) config
				.getProperty("newPhase.projectName");
		IWorkspaceRoot workSpaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IProject project = workSpaceRoot.getProject(projectName);

		IFolder phaseFolder = project.getFolder(phaseName);
		return getRuleList(phaseFolder);
	}

	/**
	 * @param phaseFolder
	 */
	@SuppressWarnings("unchecked")
	private List<Rule> getRuleList(IFolder phaseFolder) {
		IFolder rulesFolder = phaseFolder.getFolder("rules");

		assert (rulesFolder.exists());
		assert (rulesFolder.isAccessible());

		List<Rule> listOfRules = new LinkedList<Rule>();
		RulesFileParser ruleParser;
		try {
			if (rulesFolder.isAccessible()) {
				IResource[] rulesResources = rulesFolder.members();
				logger.debug(rulesResources.length);
				for (int i = 0; i < rulesResources.length; i++) {
					IFile ruleFile = (IFile) rulesResources[i];
					ruleParser = new RulesFileParser(ruleFile.getLocation()
							.toString());
					listOfRules.addAll(ruleParser.getParsedRules());
				}
			}
		} catch (CoreException e) {
			logger.error("CoreException in GetRulesCommand", e);
		}
		return listOfRules;
	}

	/**
	 * ISelection is cached in method selectionChanged for later use it in run()
	 * 
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		logger.debug(selection);
		cachedSelection = selection;
	}

}
