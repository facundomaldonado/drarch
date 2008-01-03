package org.drarch.ui.editor.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

public class SelectFileDialog extends Dialog {
  private static final String FILE_NAME = "ComponentDiagram";
  private String fileName;
  private Text fileNameText;

  public SelectFileDialog() {
    super(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
    fileName = FILE_NAME;
  }

  @Override
  protected Control createDialogArea(Composite parent) {
    Composite comp = new Composite(parent, SWT.NONE);
    GridLayout layout = new GridLayout(2, false);
    comp.setLayout(layout);
    Label l = new Label(comp, SWT.LEFT);
    l.setText("File Name : ");
    fileNameText = new Text(comp, SWT.BORDER);
    GridData gd = new GridData();
    fileNameText.setLayoutData(gd);

    return comp;
  }

  @Override
  protected void okPressed() {
    fileName = fileNameText.getText();
  }

  public String getFileName() {
    return fileName + ".flabot";
  }
}
