package org.design.rules4Java.ui.view.editor.dialogs;

import org.design.rules4Java.ui.view.modelProviders.vars.Var;
import org.design.rules4Java.ui.view.modelProviders.vars.VarList;
import org.design.rules4Java.ui.view.modelProviders.vars.VarListContentProvider;
import org.design.rules4Java.ui.view.modelProviders.vars.VarListLabelProvider;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.PlatformUI;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class QueryVarsDialog extends Dialog {
  private String title;
  private Composite composite1;
  private Button getVarsButton;
  private String query;
  private TableViewer viewer;
  private List<String> choosenVars = new ArrayList<String>();
  private VarList varList = new VarList();

  public QueryVarsDialog(String query) {
    super(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
    this.title = "Select vars";
    this.query = query;
  }

  protected void buttonPressed(int buttonId) {
    if (buttonId == IDialogConstants.OK_ID) {
      doFinish();
    }
    super.buttonPressed(buttonId);
  }

  protected void configureShell(Shell shell) {
    super.configureShell(shell);
    if (title != null) shell.setText(title);
  }

  protected void createButtonsForButtonBar(Composite parent) {
    createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
        true);
    createButton(parent, IDialogConstants.CANCEL_ID,
        IDialogConstants.CANCEL_LABEL, false);
  }

  protected Control createDialogArea(Composite parent) {
    composite1 = new Composite(parent, SWT.NONE);
    GridLayout composite1Layout = new GridLayout();
    composite1Layout.makeColumnsEqualWidth = true;
    composite1Layout.numColumns = 4;
    composite1.setLayout(composite1Layout);
    Label textLabel = new Label(composite1, SWT.NONE);
    textLabel.setText("Set Vars");
    GridData textLData = new GridData();
    textLData.grabExcessHorizontalSpace = true;
    textLData.horizontalAlignment = GridData.FILL;
    textLData.verticalAlignment = GridData.FILL;
    textLData.horizontalSpan = 2;
    textLData.verticalSpan = 10;
    Table table = new Table(composite1, SWT.BORDER);
    table.setLayoutData(textLData);
    viewer = new TableViewer(table);
    // Create the cell editors
    CellEditor[] editors = new CellEditor[1];
    // Column 1 : Completed (Checkbox)
    editors[0] = new CheckboxCellEditor(table);
    viewer.setCellEditors(editors);
    viewer.setContentProvider(new VarListContentProvider(varList, viewer));
    viewer.setLabelProvider(new VarListLabelProvider());
    viewer.addSelectionChangedListener(new ISelectionChangedListener() {
      public void selectionChanged(SelectionChangedEvent event) {
        IStructuredSelection selection = (IStructuredSelection) event
            .getSelection();
        Var var = (Var) selection.getFirstElement();
        if (!(var == null)) {
          System.out.println(viewer.isCellEditorActive());
          var.setSelected(!var.isSelected());
          viewer.update(var, null);
          viewer.refresh();
        }
      }
    });


    getVarsButton = new Button(composite1, SWT.PUSH | SWT.CENTER);
    GridData getVarsButtonLData = new GridData();
    getVarsButtonLData.verticalSpan = 10;
    getVarsButtonLData.horizontalAlignment = GridData.CENTER;
    getVarsButton.setLayoutData(getVarsButtonLData);
    getVarsButton.setText("get Vars");
    getVarsButton.addSelectionListener(new SelectionListener() {
      public void widgetSelected(SelectionEvent e) {
        getVarsFromQuery();
        viewer.setInput(varList);
        viewer.refresh();
      }

      public void widgetDefaultSelected(SelectionEvent e) {
      }
    });
    return parent;
  }

  protected void getVarsFromQuery() {
    String queryAux = query;
    char[] querya = queryAux.toCharArray();
    List vars = new ArrayList();
    int index = 0;
    int ini = 0;
    int end = query.length();
    try {
      while (end > index) {
        ini = queryAux.indexOf("?");
        index = ini;
        while ((!(query.charAt(index) == ',') && !(query.charAt(index) == ')') && !(query
            .charAt(index) == ' '))
            && (index <= end)) {
          index++;
        }
        Var var = new Var(query.substring(ini, index));
        if (!vars.contains(var.getVar())) {
          vars.add(var.getVar());
          varList.addVar(var);
        }
        querya[ini] = '.';
        index++;
        queryAux = new String(querya);
      }
    } catch (Exception e) {
      String[] labels = {"ok"};
      MessageDialog errorDialog = new MessageDialog(PlatformUI.getWorkbench()
          .getActiveWorkbenchWindow().getShell(), "Query Declaration Error",
          null, "Review query declaration", MessageDialog.ERROR, labels, 0);
      errorDialog.open();
    }
  }

  private void doFinish() {
    for (Iterator<Var> i = varList.getVars().iterator(); i.hasNext();) {
      Var var = i.next();
      if (var.isSelected()) choosenVars.add(var.getVar());
    }
  }

  public List<String> getVars() {
    return choosenVars;
  }
}
