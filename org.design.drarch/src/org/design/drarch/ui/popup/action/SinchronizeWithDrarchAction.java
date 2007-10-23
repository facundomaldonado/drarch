package org.design.drarch.ui.popup.action;

import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

public class SinchronizeWithDrarchAction extends SelectionAction {

	private static final String ID = "SINCHRONIZE_ACTION"; 
	
	public SinchronizeWithDrarchAction(IWorkbenchPart part) {
		super(part);
		setId(ID);
		setText("Sinchronize with Drarch");
	}

	public SinchronizeWithDrarchAction(IWorkbenchPart part, int style) {
		super(part, style);
		setId(ID);
		setText("Sinchronize action");
	}

	@Override
	protected boolean calculateEnabled() {
		return true;
	}

	public synchronized void run() {	
		MessageDialog.openInformation(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
				"Test_Editor", "Editor_Action_executed");
	
	}	
}
