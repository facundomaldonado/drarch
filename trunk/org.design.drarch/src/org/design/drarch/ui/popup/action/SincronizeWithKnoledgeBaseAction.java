package org.design.drarch.ui.popup.action;

import org.apache.log4j.Logger;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;

public class SincronizeWithKnoledgeBaseAction implements IViewActionDelegate {

	Logger logger = Logger.getLogger(SincronizeWithKnoledgeBaseAction.class.getName());
	
	public SincronizeWithKnoledgeBaseAction() {
		logger.debug("Creating SincronizeWithKnoledgeBaseAction");
	}

	public void init(IViewPart view) {
	}

	public void run(IAction action) {
		MessageDialog.openInformation(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
				"Test_Editor", "Editor_Action_executed");
	}

	public void selectionChanged(IAction action, ISelection selection) {
	}

}
