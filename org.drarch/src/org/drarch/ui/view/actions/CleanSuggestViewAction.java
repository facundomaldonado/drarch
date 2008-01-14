/**
 * 
 */
package org.drarch.ui.view.actions;

import org.drarch.Activator;
import org.drarch.engine.DrarchEngine;
import org.drarch.ui.view.DrarchSuggestViewer;
import org.drarch.ui.view.DrarchSuggestsView;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

/**
 */
public class CleanSuggestViewAction extends Action {

	DrarchSuggestViewer viewer;

	/**
	 * 
	 */
	public CleanSuggestViewAction(String name, DrarchSuggestViewer viewer) {
		super(name);
		this.viewer = viewer;
		setImageDescriptor(Activator.getImageDescriptor("testfail.gif"));
		setToolTipText("Clear Suggest View");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.Action#run()
	 */
	@Override
	public void run() {
		// TODO: Limpiar el arbol de Suggest View
		DrarchEngine.INSTANCE.resetEngine();
		try {
			PlatformUI.getWorkbench().getActiveWorkbenchWindow()
					.getActivePage().showView(DrarchSuggestsView.ID_VIEW);
			DrarchSuggestsView drarchView = (DrarchSuggestsView) PlatformUI
					.getWorkbench().getActiveWorkbenchWindow().getActivePage()
					.findView(DrarchSuggestsView.ID_VIEW);
			drarchView.setActivePhase(null);
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
