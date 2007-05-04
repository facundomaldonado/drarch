package org.design.rules4Java.ui.view.modelProviders.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TreeParent extends TreeObject {
  private List<TreeObject> children;

  public TreeParent(String name) {
    super(name);
    children = new ArrayList<TreeObject>();
  }

  public void addChild(TreeObject child) {
    children.add(child);
    child.setParent(this);
  }
  public void removeChild(TreeObject child) {
    children.remove(child);
    child.setParent(null);
  }
  /**
   * @return  Returns the children.
   * @uml.property  name="children"
   */
  @SuppressWarnings("unchecked")
  public TreeObject [] getChildren() {
    return children.toArray(new TreeObject[children.size()]);
  }
  public boolean hasChildren() {
    return children.size()>0;
  }

  public void setSelected(boolean selected) {
    super.setSelected(selected);
    for (Iterator<TreeObject> i = children.iterator(); i.hasNext(); ){
      i.next().setSelected(selected);
    }
  }

  public List<TreeObject> getChildrens(){
    return children;
  }
}