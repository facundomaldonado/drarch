package org.design.drarch.ui.wizards;

import org.design.drarch.DrarchPlugin;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkingSet;
import org.eclipse.ui.IWorkingSetManager;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.IWorkingSetSelectionDialog;

/**
 * @author @author maldonadofacundo@gmail.com (Facundo Maldonado)
 *
 */
public class NewRecoverySessionWizardPage extends WizardPage {

	private Text	text;
	private String workingSetName = null;
	private String initialRuleFilePath;
	/**
	 * @param pageName
	 */
	protected NewRecoverySessionWizardPage(String pageName) {
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
		{
			label = new Label(container, SWT.NULL);
			label.setText("Workingset selection: ");
			final Text workingSetText = new Text(container, SWT.BORDER);
			Button button = new Button(container, SWT.NULL);
			button.setText("select");
			button.addSelectionListener(new SelectionListener() {
				public void widgetSelected(SelectionEvent e) {
					try {
		                doSelectWorkingSet();
		                workingSetText.setText(workingSetName);
	                } catch (Exception e1) {
	                	MessageDialog.openError(getShell(), getProjectName(), e1.getMessage());
	                }
				}
				public void widgetDefaultSelected(SelectionEvent e) {
				}
			});
			gd = new GridData(GridData.FILL_HORIZONTAL);
			workingSetText.setLayoutData(gd);
			gd = new GridData(GridData.CENTER);
			button.setLayoutData(gd);
		}
		{
			label = new Label(container, SWT.NULL);
			label.setText("Select Initial rules file");
			label.setToolTipText("The initials rules will be executed before the analisis, without user interaction");
			final Text initialRulesText = new Text(container, SWT.BORDER);
			initialRulesText.setText("");
			Button initialRulesButton = new Button(container, SWT.PUSH);
			initialRulesButton.setText("select");
			initialRulesButton.addSelectionListener(new SelectionListener() {
				public void widgetSelected(SelectionEvent e) {
					try {
		                doSelectInitialRulesFile();
		                initialRulesText.setText(initialRuleFilePath);
	                } catch (Exception e1) {
	                	MessageDialog.openError(getShell(), getProjectName(), e1.getMessage());
	                }
				}
				public void widgetDefaultSelected(SelectionEvent e) {
				}
			});
			gd = new GridData(GridData.FILL_HORIZONTAL);
			initialRulesText.setLayoutData(gd);
			gd = new GridData(GridData.CENTER);
			initialRulesButton.setLayoutData(gd);
		}
		setControl(container);
	}
	
	 /**
     * 
     */
    protected void doSelectInitialRulesFile() {
    	FileDialog fileDialog = new FileDialog(getShell());
    	String filePath = fileDialog.open();
    	if( null != filePath) 
    		initialRuleFilePath = filePath;
    }

	/**
	 * @throws Exception  
     */
    private void doSelectWorkingSet() throws Exception {
        IWorkingSetManager workingSetManager = DrarchPlugin.getDefault().
													getWorkbench().getWorkingSetManager();
		IWorkingSetSelectionDialog iws = workingSetManager.createWorkingSetSelectionDialog(
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), false);
		IWorkingSet workingSet;
	    if (iws.open() == org.eclipse.jface.window.Window.OK && iws.getSelection() != null)
	    	workingSet = iws.getSelection()[0];
	    else throw new Exception("No workingset was selected.");
	    //TODO reveer esta linea si se registra o no dependiendo de si crea la instancia del
	    //engine ahora o despues
//	    ResourceLocator.INSTANCE.registerCurrentWorkingSet(workingSet);
	    workingSetName = workingSet.getName();
	    updateStatus(null);
    }

    protected String getWorkingSetName() {
    	return workingSetName;
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
		if (null == workingSetName) {
			updateStatus("working set must be selected");
			return;
		}
		updateStatus(null);
	}

	private void updateStatus(String message) {
		setErrorMessage(message);
		setPageComplete(message == null);
	}

	protected String getInitialRuleFilePath() {
    	return this.initialRuleFilePath;
    }
}
