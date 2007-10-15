package org.design.drarch.ui.popup.action;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;
import org.design.drarch.DrarchApplication;
import org.design.drarch.DrarchConstants;
import org.design.drarch.DrarchPlugin;
import org.design.drarch.RecoverySession;
import org.design.rules4Java.util.Util;
import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkingSet;

public class RestoreRecoverySession implements IObjectActionDelegate {

	Logger	logger	= Logger.getLogger(RestoreRecoverySession.class.getName());

	public RestoreRecoverySession() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		doRestoreSession();
	}

	/**
	 * 
	 */
	private void doRestoreSession() {
		try {
			//select .properties file
			StructuredSelection selection = (StructuredSelection)cachedSelection;
			IFile file = (IFile)selection.getFirstElement();
			PropertiesConfiguration config = new PropertiesConfiguration(file.getLocation().toFile());
			
			//get properties from config file
			String workingSetName = (String) config
			        .getProperty(DrarchConstants.PROPERTY_WORKINGSET_NAME);
			String projectName = (String) config
			        .getProperty(DrarchConstants.PROPERTIES_PROJECT_NAME);
			String projectLocation = (String)config.getProperty(DrarchConstants.NEW_PROJECT_LOCATION);
			String initialRulesFile = (String)config.getProperty(DrarchConstants.PROPERTIES_INITIAL_RULE_FILENAME);
			
			//getting constant for the initialization of the new project
			String path2DefaultRules = DrarchConstants.NEW_PROJECT_PATH_TO_RULES_FILES;
			String path2KnowledgeBase = DrarchConstants.NEW_PROJECT_PATH_TO_KNOWLEDGEBASE_FILES;

			//getting selected workingset from workbench
			IWorkingSet workingSet = DrarchPlugin.getDefault().getWorkbench()
			        .getWorkingSetManager().getWorkingSet(workingSetName);
			//creating recovering sessions
			RecoverySession session = DrarchApplication.INSTANCE.createSession();

			session.setProjectName(projectName);
			session.setWorkingSet(workingSet);
			//TODO CAMBIAR!!!!!!!!!
			Util.getInstance().setCurrentWorkingSet(workingSet);
			//*********************
			session.setProjectLocation(projectLocation);
			session.setPathToDefaultRules(projectLocation + path2DefaultRules);
			session.setPathToKnowledgeBase(projectLocation + path2KnowledgeBase);
			session.setPathToInitialsRules(initialRulesFile);

			//registering session in the engine
			DrarchApplication.INSTANCE.getDrarchEngine();
			DrarchApplication.INSTANCE.registerRecoverySession(session);
			
		} catch (ConfigurationException e) {
			logger.error("ConfigurationException while trying to get properties file", e);
			Shell shell = new Shell();
			MessageDialog.openError(shell, "Drarch Plug-in",
			                "ConfigurationException while trying to get properties file");
		}catch (RuntimeException e) {
			logger.error(e.getMessage(),e);
		}
	}

	/**
	 * ISelection is cached in method selectionChanged for later use it in run()
	 */
	private ISelection cachedSelection;
	
	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		logger.debug(selection);
		cachedSelection = selection;
	}

}
