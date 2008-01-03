package org.drarch.ui.view.wizards;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * The "New" wizard page allows setting the container for the new file as well
 * as the file name. The page will only accept file name without the extension
 * OR with the extension that matches the expected one (recovery).
 */

public class NewDrarchProjectWizardPage extends WizardPage {
	
	private Text	text;

	/**
	 * @param pageName
	 */
	protected NewDrarchProjectWizardPage(String pageName) {
		super(pageName);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		container.setLayout(layout);
		layout.numColumns = 3;
		layout.verticalSpacing = 9;
		Label label;
		{
			label = new Label(container, SWT.NULL);
			label.setText("New recovery project name:");
			text = new Text(container, SWT.BORDER);
			text.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent e) {
					dialogChanged();
				}
			});
			gd.horizontalSpan = 2;
			text.setLayoutData(gd);
		}
	
		setControl(container);
	}
	
	
	protected String getProjectName() {
		return text.getText();
	}
	
	private void dialogChanged() {
		IResource project = ResourcesPlugin.getWorkspace().getRoot()
		        .getProject(getProjectName());

		if (getProjectName().length() == 0) {
			updateStatus("project name can't be empty");
			return;
		}
		if (project.exists()) {
			updateStatus("the project already exist");
			return;
		}
		updateStatus(null);
	}

	private void updateStatus(String message) {
		setErrorMessage(message);
		setPageComplete(message == null);
	}

}