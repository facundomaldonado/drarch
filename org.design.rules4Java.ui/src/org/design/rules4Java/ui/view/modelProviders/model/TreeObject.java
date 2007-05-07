package org.design.rules4Java.ui.view.modelProviders.model;

import org.design.rules4Java.engine.engine.engineModel.Suggest;
import org.eclipse.core.runtime.IAdaptable;

public class TreeObject implements IAdaptable {
  private String name;
  private boolean selected = false;
  private TreeParent parent;

  private Object value;

  public TreeObject(String name) {
    this.name = name;
  }

  /**
   * @return Returns the name.
   * @uml.property name = "name"
   */
  public String getName() {
    return name;
  }

  /**
   * @param parent The parent to set.
   * @uml.property name = "parent"
   */
  public void setParent(TreeParent parent) {
    this.parent = parent;
  }

  /**
   * @return Returns the parent.
   * @uml.property name="parent"
   */
  public TreeParent getParent() {
    return parent;
  }

  public String toString() {
    return getName();
  }

  public Object getAdapter(Class key) {
    return null;
  }

  public boolean isSelected() {
    return selected;
  }

  public void setSelected(boolean selected) {
    this.selected = selected;
    if (value instanceof Suggest) {
      ((Suggest) value).setApply(selected);
    }
  }

  public Object getValue() {
    return value;
  }

  public void setValue(Object value) {
    this.value = value;
  }
}
