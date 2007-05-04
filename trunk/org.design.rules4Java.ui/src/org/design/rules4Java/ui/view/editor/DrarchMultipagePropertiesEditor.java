package org.design.rules4Java.ui.view.editor;

import org.design.rules4Java.engine.ruleModel.DrarchFileModel;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.part.MultiPageSelectionProvider;

public class DrarchMultipagePropertiesEditor extends FormEditor {
  private DrarchFileModel model;
  private OverviewPage overviewPage;
  private TextEditor textEditor;

  public DrarchMultipagePropertiesEditor(final DrarchFileModel model){
    this.model = model;
  }

  public DrarchFileModel getModel(){
    return model;
  }

  @Override
  protected void addPages() {
    addOverviewPage();
    addRawTextPage();
  }

  public IEditorPart getActiveEditor() {
    IEditorPart editor = super.getActiveEditor();
    if (editor == null)
      editor = getTextEditor();
    return editor;
  }

  /**
   * add the raw xml text editor page
   */
  private void addRawTextPage() {
    try {
      textEditor = new TextEditor();
      final int index = addPage(getTextEditor(), getEditorInput());			
      setPageText(index, "Raw XML");
    } catch (final PartInitException e) {
      ErrorDialog.openError(getSite().getShell(), "Error creating nested " +
          "text editor page",null,e.getStatus());
    }
  }

  /**
   * add the overview page
   */
  private void addOverviewPage() {
    try {
      overviewPage = new OverviewPage(this, getModel());
      addPage(overviewPage);
    } catch (final PartInitException e) {
      ErrorDialog.openError(getSite().getShell(), "Error creating nested " +
          "overview page", null, e.getStatus());
    }
  }

  public void init(final IEditorSite site, final IEditorInput input) {
    setSite(site);
    setInput(input);
    site.setSelectionProvider(new FormEditorSelectionProvider(this));
  }

  @Override
  public void doSave(final IProgressMonitor monitor) {
  }

  @Override
  public void doSaveAs() {}

  @Override
  public boolean isSaveAsAllowed() {
    return false;
  }

  /**
   * @return Returns the overviewPage.
   */
  public OverviewPage getOverviewPage() {
    return overviewPage;
  }

  /**
   * @return Returns the textEditor.
   */
  public TextEditor getTextEditor() {
    return textEditor;
  }

  private static class FormEditorSelectionProvider extends 
  MultiPageSelectionProvider {
    private ISelection globalSelection;

    /**
     * @param formEditor
     */
    public FormEditorSelectionProvider(final FormEditor formEditor) {
      super(formEditor);
    }

    public ISelection getSelection() {
      final IEditorPart activeEditor = ((FormEditor) getMultiPageEditor())
      .getActiveEditor();
      if (activeEditor != null) {
        final ISelectionProvider selectionProvider = activeEditor.getSite()
        .getSelectionProvider();
        if (selectionProvider != null) {
          return selectionProvider.getSelection();
        }
      }
      return globalSelection;
    }

    public void setSelection(final ISelection selection) {
      final IEditorPart activeEditor = ((FormEditor) getMultiPageEditor())
      .getActiveEditor();
      if (activeEditor != null) {
        final ISelectionProvider selectionProvider = activeEditor.getSite()
        .getSelectionProvider();
        if (selectionProvider != null) {
          selectionProvider.setSelection(selection);
        }
      } else {
        this.globalSelection = selection;
        fireSelectionChanged(new SelectionChangedEvent(this, globalSelection));
      }
    }
  }
}