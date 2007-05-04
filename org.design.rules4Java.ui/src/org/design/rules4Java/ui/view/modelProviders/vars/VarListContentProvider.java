package org.design.rules4Java.ui.view.modelProviders.vars;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;

public class VarListContentProvider implements IStructuredContentProvider, IVarListViewer {

  private VarList varList;
  private TableViewer viewer;

  public VarListContentProvider(VarList varList, TableViewer viewer){
    super();
    this.varList = varList;
    this.viewer = viewer;
  }

  public Object[] getElements(Object inputElement) {
    return varList.getVars().toArray();
  }

  public void inputChanged(Viewer v, Object oldInput, Object newInput) {
    if (newInput != null) {
      ((VarList) newInput).addChangeListener(this);
    }
    if (oldInput != null) {
      ((VarList) oldInput).removeChangeListener(this);
    }
  }

  public void dispose() {
    varList.removeChangeListener(this);
  }

  public void addVar(Var v) {
    viewer.add(v);
  }

  public void removeVar(Var v) {
    viewer.remove(v);
  }
}