/**
 * 
 */
package org.drarch.ui.view.wizards.actions;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.drarch.ui.view.wizards.NewDrarchPhaseWizard;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

/**
 * @author @author maldonadofacundo@gmail.com (Facundo Maldonado)
 *
 */
public class AddANewPhaseAction implements IObjectActionDelegate{

	private static Log logger = LogFactory.getLog(AddANewPhaseAction.class.getName());
	private ISelection cachedSelection;
	
	/* (non-Javadoc)
     * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction, org.eclipse.ui.IWorkbenchPart)
     */
    public void setActivePart(IAction action, IWorkbenchPart targetPart) {
	    // TODO pelado: not yet implemented - setActivePart
	    
    }

	/* (non-Javadoc)
     * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    public void run(IAction action) {
	    NewDrarchPhaseWizard wizard = new NewDrarchPhaseWizard();
	    wizard.init(PlatformUI.getWorkbench(), (IStructuredSelection) cachedSelection);
	    WizardDialog wizardDialog = 
	    	new WizardDialog(
	    						PlatformUI.getWorkbench().
	    							getActiveWorkbenchWindow().getShell(),
	    						wizard);
	    wizardDialog.open();
    }

	/* (non-Javadoc)
     * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
     */
    public void selectionChanged(IAction action, ISelection selection) {
    	logger.debug(selection);
		cachedSelection = selection;	    
    }
	
	

}
