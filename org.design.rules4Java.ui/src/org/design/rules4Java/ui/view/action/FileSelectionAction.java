package org.design.rules4Java.ui.view.action;

import org.design.rules4Java.ui.view.dialogs.FileNameDialog;
import org.eclipse.jface.action.Action;

public class FileSelectionAction extends Action {

  private String filePath;

  public FileSelectionAction() {
  }

  public void run() {
    FileNameDialog dialog = new FileNameDialog();
    dialog.open();
    filePath = dialog.getFileName();
  }

  public String getfileName() {
    return filePath;
  }
}
