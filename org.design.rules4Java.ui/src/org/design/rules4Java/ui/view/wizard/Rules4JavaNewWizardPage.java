package org.design.rules4Java.ui.view.wizard;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;

public class Rules4JavaNewWizardPage extends WizardPage {
  private Text containerText;
  private Text fileText;
  private ISelection selection;

  public Rules4JavaNewWizardPage(ISelection selection) {
    super("New Rules File"); 
    setTitle("New Rules File"); 
    setDescription("description"); 
    this.selection = selection;
  }

  public void createControl(Composite parent) {
    Composite container = new Composite(parent, SWT.NULL);
    GridLayout layout = new GridLayout();
    container.setLayout(layout);
    layout.numColumns = 3;
    layout.verticalSpacing = 9;
    Label label = new Label(container, SWT.NULL);
    label.setText("container"); 
    containerText = new Text(container, SWT.BORDER | SWT.SINGLE);
    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
    containerText.setLayoutData(gd);
    containerText.addModifyListener(new ModifyListener() {
      public void modifyText(ModifyEvent e) {
        dialogChanged();
      }
    });
    Button button = new Button(container, SWT.PUSH);
    button.setText("browse"); 
    button.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent e) {
        handleBrowse();
      }
    });
    label = new Label(container, SWT.NULL);
    label.setText("fileName"); 
    fileText = new Text(container, SWT.BORDER | SWT.SINGLE);
    gd = new GridData(GridData.FILL_HORIZONTAL);
    fileText.setLayoutData(gd);
    fileText.addModifyListener(new ModifyListener() {
      public void modifyText(ModifyEvent e) {
        dialogChanged();
      }
    });
    initialize();
    dialogChanged();
    setControl(container);
  }
  
  /**
   * Tests if the current workbench selection is a suitable container to use.
   */
  private void initialize() {
    if (selection != null && selection.isEmpty() == false
        && selection instanceof IStructuredSelection) {
      IStructuredSelection ssel = (IStructuredSelection) selection;
      if (ssel.size() > 1)
        return;
      Object obj = ssel.getFirstElement();
      IContainer container;
      if (obj instanceof IJavaProject) {
        container = ((IJavaProject) obj).getProject();
        containerText.setText(container.getFullPath().toString());
      } else if (obj instanceof IResource) {

        if (obj instanceof IContainer)
          container = (IContainer) obj;
        else
          container = ((IResource) obj).getParent();
        containerText.setText(container.getFullPath().toString());
      }
    }
    fileText.setText("defaultFileName.drarch");
  }

  /**
   * Uses the standard container selection dialog to choose the new value for
   * the container field.
   */
  private void handleBrowse() {
    ContainerSelectionDialog dialog = new ContainerSelectionDialog(
        getShell(), ResourcesPlugin.getWorkspace().getRoot(), false,
        "select File Container"); 
    if (dialog.open() == ContainerSelectionDialog.OK) {
      Object[] result = dialog.getResult();
      if (result.length == 1) {
        containerText.setText(((Path) result[0]).toString());
      }
    }
  }

  /**
   * Ensures that both text fields are set.
   */
  private void dialogChanged() {
    IResource container = ResourcesPlugin.getWorkspace().getRoot()
    .findMember(new Path(getContainerName()));
    String fileName = getFileName();

    if (getContainerName().length() == 0) {
      updateStatus("container name can't be empty");
      return;
    }
    if (container == null
        || (container.getType() & (IResource.PROJECT | IResource.FOLDER)) == 0) {
      updateStatus("select a correct container"); 
      return;
    }
    if (!container.isAccessible()) {
      updateStatus("container is not accesible");
      return;
    }
    if (fileName.length() == 0) {
      updateStatus("file name can't be zero lenght");
      return;
    }
    if (fileName.replace('\\', '/').indexOf('/', 1) > 0) {
      updateStatus("file name can't start with /");
      return;
    }
    int dotLoc = fileName.lastIndexOf('.');
    if (dotLoc != -1) {
      String ext = fileName.substring(dotLoc + 1);
      if (ext.equalsIgnoreCase("drarch") == false) {
        updateStatus("file extension must be .drarch");
        return;
      }
    }
    if (container.getProject().findMember(new Path(fileName)) != null) {
      updateStatus("container already have a file with the sama name");
      return;
    }
    updateStatus(null);
  }

  private void updateStatus(String message) {
    setErrorMessage(message);
    setPageComplete(message == null);
  }

  public String getContainerName() {
    return containerText.getText();
  }

  public String getFileName() {
    return fileText.getText();
  }
}