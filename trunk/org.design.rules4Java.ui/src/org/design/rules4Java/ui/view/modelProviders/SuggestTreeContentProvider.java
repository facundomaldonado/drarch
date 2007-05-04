package org.design.rules4Java.ui.view.modelProviders;

import org.design.rules4Java.ui.view.modelProviders.model.TreeObject;
import org.design.rules4Java.ui.view.modelProviders.model.TreeParent;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class SuggestTreeContentProvider implements IStructuredContentProvider, 
    ITreeContentProvider {
  private TreeParent invisibleRoot;
  
  public SuggestTreeContentProvider() {}
  
  public void inputChanged(Viewer v, Object oldInput, Object newInput) {}
  
  public void dispose() {}
  
  public Object[] getElements(Object parent) {
    return getChildren(parent);
  }
  
  public Object getParent(Object child) {
    if (child instanceof TreeObject) {
      return ((TreeObject)child).getParent();
    }
    return null;
  }
  
  public Object [] getChildren(Object parent) {
    if (parent instanceof TreeParent) {
      return ((TreeParent)parent).getChildren();
    }
    return new Object[0];
  }
  
  public boolean hasChildren(Object parent) {
    if (parent instanceof TreeParent)
      return ((TreeParent)parent).hasChildren();
    return false;
  }

  public TreeParent initialize() {
    invisibleRoot = new TreeParent("");
    return invisibleRoot;
  }
}