package org.design.rules4Java.ui.view.editor.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

public class RuleDescriptionDialog extends Dialog {

  private String description = "";
  private Text descriptionText;
  private Composite composite1;

  public RuleDescriptionDialog() {
    super(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
  }

  protected void configureShell(Shell shell) {
    super.configureShell(shell);
    shell.setText("Rule Description");
  }

  protected void buttonPressed(int buttonId) {
    if (buttonId == IDialogConstants.OK_ID) {
      description = descriptionText.getText();
    }
    super.buttonPressed(buttonId);
  }

  protected void createButtonsForButtonBar(Composite parent) {
    createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, 
        true);
    createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants
        .CANCEL_LABEL, false);
  }

  protected Control createDialogArea(Composite parent) {
    composite1 = new Composite(parent, SWT.NONE);
    GridLayout composite1Layout = new GridLayout();
    composite1Layout.makeColumnsEqualWidth = true;
    composite1Layout.numColumns = 4;
    composite1.setLayout(composite1Layout);
    Label textLabel = new Label(composite1, SWT.CENTER);
    textLabel.setText(" Description : ");
    GridData textLData = new GridData();
    textLData.grabExcessHorizontalSpace = true;
    textLData.horizontalAlignment = GridData.FILL;
    textLData.verticalAlignment = GridData.FILL;
    textLData.horizontalSpan = 3;
    textLData.verticalSpan= 2;
    descriptionText = new Text(composite1, SWT.NONE | SWT.BORDER);
    descriptionText.setText(description);
    descriptionText.setLayoutData(textLData);
    return parent;
  }

  public String getdescription(){
    return description;
  }
}