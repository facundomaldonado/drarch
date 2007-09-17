package org.design.rules4Java.ui.view.editor;

import org.design.rules4Java.engine.ruleModel.DrarchFileModel;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;

public abstract class DrarchFormPage extends FormPage {
  private DrarchFileModel model;

  public DrarchFormPage(FormEditor editor, String id, String title,
      DrarchFileModel model) {
    super(editor, id, title);
    this.model = model;
  }

  /**
   * Provides access to the drarch file editorModel.
   * 
   * @return Returns the {@code DrarchFileModel}.
   */
  public DrarchFileModel getModel() {
    return model;
  }
}
