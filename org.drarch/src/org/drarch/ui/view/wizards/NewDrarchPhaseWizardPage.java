package org.drarch.ui.view.wizards;

import org.drarch.Activator;
import org.eclipse.jface.dialogs.IDialogPage;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkingSet;
import org.eclipse.ui.IWorkingSetManager;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.IWorkingSetSelectionDialog;

/**
 * The "New" wizard page allows setting the container for the new file as well
 * as the file name. The page will only accept file name without the extension
 * OR with the extension that matches the expected one (mpe).
 */

public class NewDrarchPhaseWizardPage extends WizardPage {

	public static final String JDT = "JDT";

	private Text phaseName;

	private Text flabotFileName;

	protected String workingSetName = null;

	// protected boolean interactive = true;

	String type;

	String algorithmicType;

	public static String INTERACTIVE = "Interactive";

	public static String NON_INTERACTIVE = "Non interactive";

	public static String ALGORITHMIC = "Algorithmic";

	/**
	 * Constructor for SampleNewWizardPage.
	 * 
	 * @param pageName
	 */
	public NewDrarchPhaseWizardPage(ISelection selection) {
		super("wizardPage");
		setTitle("New Drarch Phase Wizard");
		setDescription("This wizard creates a new drarch phase in the selected project");
	}

	/**
	 * @see IDialogPage#createControl(Composite)
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 2;
		layout.verticalSpacing = 9;
		Label label = new Label(container, SWT.NULL);

		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		{
			label.setText("&Phase name:");

			phaseName = new Text(container, SWT.BORDER | SWT.SINGLE);
			gd = new GridData(GridData.FILL_HORIZONTAL);
			phaseName.setLayoutData(gd);
			phaseName.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent e) {
					dialogChanged();
				}
			});
		}
		{
			label = new Label(container, SWT.NULL);
			label.setText("&Flabot file name:");

			flabotFileName = new Text(container, SWT.BORDER | SWT.SINGLE);
			gd = new GridData(GridData.FILL_HORIZONTAL);
			flabotFileName.setLayoutData(gd);
			flabotFileName.addModifyListener(new ModifyListener() {
				public void modifyText(ModifyEvent e) {
					dialogChanged();
				}
			});
		}
		{
			label = new Label(container, SWT.NULL);
			label.setText("Workingset selection: ");
			Composite select = new Composite(container, SWT.NULL);
			GridLayout layout1 = new GridLayout();
			select.setLayout(layout1);
			layout1.numColumns = 2;
			layout1.verticalSpacing = 9;

			final Text workingSetText = new Text(select, SWT.BORDER);
			Button button = new Button(select, SWT.NULL);
			button.setText("select");
			button.addSelectionListener(new SelectionListener() {
				public void widgetSelected(SelectionEvent e) {
					try {
						doSelectWorkingSet();
						workingSetText.setText(workingSetName);
					} catch (Exception e1) {
						MessageDialog.openError(getShell(),
								"Drarch New Phase Wizard", e1.getMessage());
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
			label.setText("Phase Type: ");
			final Combo combo = new Combo(container, SWT.NULL);
			combo.add("Interactive");
			combo.add("Non Interactive");
			combo.add("Algorithmic");
			combo.select(2);
			final Composite algorithmicPanel = new Composite(container,
					SWT.NULL);
			combo.addSelectionListener(new SelectionListener() {
				public void widgetSelected(SelectionEvent e) {
					int i = combo.getSelectionIndex();
					switch (i) {
					case 0:
						type = INTERACTIVE;
						break;
					case 1:
						type = NON_INTERACTIVE;
						break;
					case 2:
						type = ALGORITHMIC;
						 algorithmicPanel.setVisible(true);
						break;
					default:
						type = INTERACTIVE;
						break;
					}
				}

				public void widgetDefaultSelected(SelectionEvent e) {
				}
			});
			algorithmicPanel.setVisible(false);
			GridLayout layout2 = new GridLayout();
			algorithmicPanel.setLayout(layout2);
			layout2.numColumns = 2;
			label = new Label(algorithmicPanel, SWT.NULL);
			label.setText("Algorithm: ");
			final Combo algorithmicCombo = new Combo(algorithmicPanel, SWT.NULL);
			algorithmicCombo.add(JDT);
			algorithmicCombo.select(0);
			algorithmicCombo.addSelectionListener(new SelectionListener() {

				public void widgetSelected(SelectionEvent e) {
					int i = algorithmicCombo.getSelectionIndex();
					switch (i) {
					case 0:
						algorithmicType = JDT;
						break;
					default:
						algorithmicType = JDT;
						break;
					}
				}

				public void widgetDefaultSelected(SelectionEvent e) {
					algorithmicType = JDT;
				}
			});
		}
		dialogChanged();
		setControl(container);
	}

	private void doSelectWorkingSet() throws Exception {
		IWorkingSetManager workingSetManager = Activator.getDefault()
				.getWorkbench().getWorkingSetManager();
		IWorkingSetSelectionDialog iws = workingSetManager
				.createWorkingSetSelectionDialog(PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow().getShell(), false);
		IWorkingSet workingSet;
		if (iws.open() == org.eclipse.jface.window.Window.OK
				&& iws.getSelection() != null)
			workingSet = iws.getSelection()[0];
		else
			throw new Exception("No workingset was selected.");
		// TODO reveer esta linea si se registra o no dependiendo de si crea la
		// instancia del
		// engine ahora o despues
		workingSetName = workingSet.getName();
		updateStatus(null);
	}

	/**
	 * Ensures that both text fields are set.
	 */

	private void dialogChanged() {
		String phaseName = getPhaseName();
		String flabotFileName = getFlabotFileName();
		if (phaseName.length() == 0) {
			updateStatus("Phase name must be specified");
			return;
		}
		if (phaseName.replace('\\', '/').indexOf('/', 1) > 0) {
			updateStatus("Phase name must be valid");
			return;
		}
		if (flabotFileName.length() == 0) {
			updateStatus("Flabot file name must be specified");
			return;
		}
		if (flabotFileName.replace('\\', '/').indexOf('/', 1) > 0) {
			updateStatus("Flabot file name must be valid");
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

	public String getPhaseName() {
		return phaseName.getText();
	}

	public String getFlabotFileName() {
		return flabotFileName.getText();
	}

	public String getWorkingSetName() {
		return workingSetName;
	}

}