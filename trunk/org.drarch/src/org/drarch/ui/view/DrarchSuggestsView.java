/**
 * 
 */
package org.drarch.ui.view;

import org.apache.log4j.Logger;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

/**
 * @author @author maldonadofacundo@gmail.com (Facundo Maldonado)
 *
 */
public class DrarchSuggestsView extends ViewPart  {

	public static final String ID_VIEW = "org.design.drarch.ui.views.DrarchSuggestsView";

	private DrarchSuggestViewer viewer;
	private static Logger logger = Logger.getLogger(DrarchSuggestsView.class.getName());

	public void createPartControl(Composite parent) {
		viewer = new DrarchSuggestViewer(parent, this);
	}

	public void setFocus() {
		viewer.getControl().setFocus();
	}

	/**
	 * Cleans up all resources created by this ViewPart.
	 */
	public void dispose() {
		super.dispose();
		logger.info("DrarchSuggestView dispose");
	}

}
