package org.design.drarch.views;

import org.apache.log4j.Logger;
import org.design.drarch.DrarchPlugin;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

/**
 * 
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public class SuggestsView extends ViewPart {

	public static final String ID_VIEW = "org.design.drarch.views.SuggestsView";

	private SuggestsViewer viewer;
	private static Logger logger = Logger.getLogger(DrarchPlugin.class.getName());

	public void createPartControl(Composite parent) {
		logger.debug("SuggestsView: CreatePartControl()");
		viewer = new SuggestsViewer(parent, this);
		logger.debug("SuggestsView: SuggestViewer creation");
	}

	public void setFocus() {
		viewer.getControl().setFocus();
	}

	/**
	 * Cleans up all resources created by this ViewPart.
	 */
	public void dispose() {
		super.dispose();
		logger.info("SuggestView dispose");
	}
}
