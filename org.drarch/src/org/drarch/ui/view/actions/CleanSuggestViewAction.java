/**
 * 
 */
package org.drarch.ui.view.actions;

import org.drarch.Activator;
import org.drarch.ui.view.DrarchSuggestViewer;
import org.eclipse.jface.action.Action;

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
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.action.Action#run()
	 */
	@Override
	public void run() {

		// TODO: Limpiar el arbol de Suggest View
		
	}
	
}
