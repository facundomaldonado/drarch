package org.design.drarch.views.action;

import org.design.drarch.DrarchApplication;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.PlatformUI;

public class LoadRuleFileAction extends Action {
  private String fileName = "";

  public LoadRuleFileAction() {
  }

  public void run() {
    FileDialog dialog = new FileDialog(
    		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
    dialog.open();
    fileName = dialog.getFileName();
    String path = dialog.getFilterPath() + "/";
    DrarchApplication.INSTANCE.getCurrentSession().setPathtoSelectedRulesSource(path + fileName);
  }

  public String getFileName() {
    return fileName;
  }
}
