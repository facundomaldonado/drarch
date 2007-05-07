package org.design.rules4Java.ui.view.editor;

import org.design.rules4Java.engine.ruleModel.DrarchFileModel;
import org.design.rules4Java.engine.ruleModel.RuleModelPackage;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.MultiPageEditorPart;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

public class DrarchMultiPageEditor extends MultiPageEditorPart implements
    Adapter {

  private static final Map SAVE_OPTIONS = Collections.singletonMap(
      XMLResource.OPTION_ENCODING, "ISO-8859-15");
  private boolean dirty = false;
  private DrarchMultipagePropertiesEditor properties;
  private DrarchFileModel fileModel;
  private ResourceSet resourceSet = new ResourceSetImpl();
  private Resource resource;

  public DrarchMultiPageEditor() {
    super();
  }

  public String getPartName() {
    return getEditorInput().getName();
  }

  public boolean isDirty() {
    return (dirty || super.isDirty());
  }

  /**
   * Returns the active nested editor if there is one. Subclasses should not
   * override this method.
   * 
   * @return the active nested editor, or {code null} if none
   */
  public IEditorPart getActiveEditor() {
    int index = getActivePage();
    if (index != -1) return getEditor(index);
    return null;
  }

  @Override
  public int getActivePage() {
    return super.getActivePage();
  }

  @Override
  public void setActivePage(int pageIndex) {
    super.setActivePage(pageIndex);
  }

  /**
   * Creates page 0 of the multi-page editor, which is a flabot multipage
   * properties editor.
   */
  void createPage0() {
    try {
      properties = new DrarchMultipagePropertiesEditor(getModel());
      int index = addPage(properties, getEditorInput());
      setPageText(index, "propertiesTabName");
    } catch (PartInitException e) {
      ErrorDialog.openError(getSite().getShell(), "error Creating Nested "
          + "Editor Dialog Name", null, e.getStatus());
    }
  }

  /**
   * Creates the pages of the multi-page editor.
   */
  @Override
  protected void createPages() {
    createPage0();
  }

  /**
   * Saves the multi-page editor's document.
   */
  public void doSave(IProgressMonitor monitor) {
    if (resource == null) {
      URI uri = getURI(getEditorInput());
      resource = resourceSet.createResource(uri);
      resource.getContents().add(fileModel);
    }
    try {
      resource.save(SAVE_OPTIONS);
      this.unsetDirty();
    } catch (IOException e) {
    }
  }

  @Override
  public void doSaveAs() {
  }

  @Override
  public boolean isSaveAsAllowed() {
    return true;
  }

  public void notifyChanged(Notification arg0) {
  }

  public void init(IEditorSite site, IEditorInput editorInput)
      throws PartInitException {
    if (!(editorInput instanceof IFileEditorInput || editorInput instanceof IPathEditorInput))
      throw new PartInitException("invalidInput");
    super.init(site, editorInput);
    fileModel.eAdapters().add(this);
  }

  protected void setInput(IEditorInput input) {
    super.setInput(input);
    URI uri = getURI(input);

    /*
     * Initialize packages so their factories are registered and the editorModel
     * is loaded correctly.
     */
    RuleModelPackage mpp = RuleModelPackage.eINSTANCE;

    /*
     * Modify the load options so that files are loaded even without the
     * required EMF packages.
     */
    resourceSet.getLoadOptions().put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE,
        Boolean.TRUE);
    if (resource == null) {
      resource = resourceSet.getResource(uri, true);
    }
    if (resource != null) {
      fileModel = (DrarchFileModel) resource.getContents().get(0);
    }
  }

  /**
   * Extract the path from the given input.
   * 
   * @param input
   * @return Returns the {@code URI} path.
   */
  private URI getURI(IEditorInput input) {
    if (input instanceof IFileEditorInput) {
      IFileEditorInput fileInput = (IFileEditorInput) input;
      IPath path = fileInput.getFile().getFullPath();
      return URI.createPlatformResourceURI(path.toString());
    }
    if (input instanceof IPathEditorInput) {
      IPathEditorInput pathInput = (IPathEditorInput) input;
      IPath path = pathInput.getPath();
      return URI.createFileURI(path.toString());
    }
    throw new RuntimeException("invalidInput");
  }

  public DrarchFileModel getModel() {
    return fileModel;
  }

  public void closeEditor(boolean save) {
    getSite().getPage().closeEditor(DrarchMultiPageEditor.this, save);
  }

  public Notifier getTarget() {
    return null;
  }

  public void setTarget(Notifier arg0) {
  }

  public boolean isAdapterForType(Object arg0) {
    return false;
  }

  public void unsetDirty() {
    // Unset dirty state for this.
    this.dirty = false;
    firePropertyChange(IEditorPart.PROP_DIRTY);
  }

  protected void superSetInput(IEditorInput input) {
    super.setInput(input);
  }
}
