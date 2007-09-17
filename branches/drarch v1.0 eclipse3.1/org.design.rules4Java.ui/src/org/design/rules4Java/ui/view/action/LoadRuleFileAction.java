package org.design.rules4Java.ui.view.action;

import org.design.rules4Java.util.Util;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.PlatformUI;

public class LoadRuleFileAction extends Action {
  private String fileName = "";

  public LoadRuleFileAction() {
  }

  public void run() {
    FileDialog dialog = new FileDialog(PlatformUI.getWorkbench()
        .getActiveWorkbenchWindow().getShell());
    dialog.open();
    fileName = dialog.getFileName();
    String path = dialog.getFilterPath() + "/";
    Util.getInstance().setExternalFilePath(path + fileName);
  }

  public String getFileName() {
    return fileName;
  }
}
