package org.design.rules4Java.ui.view.modelProviders.model;

import java.util.ArrayList;
import java.util.Iterator;

public class TreeParent extends TreeObject {
	
	private ArrayList children;
	
	public TreeParent(String name) {
		super(name);
		children = new ArrayList();
	}
	@SuppressWarnings("unchecked")
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
		return (TreeObject [])children.toArray(new TreeObject[children.size()]);
	}
	public boolean hasChildren() {
		return children.size()>0;
	}
	
	public void setSelected(boolean selected) {
		super.setSelected(selected);
		Iterator i=children.iterator();
		while (i.hasNext()){
			TreeObject child=(TreeObject) i.next();
			child.setSelected(selected);
		}
	}
	
	public ArrayList getChildrens(){
		return children;
	}
}