package org.design.drarch.diagram.DiagramModel.componentModel;

import java.util.Vector;

public class Responsibility {

	private Component component;
	private String name;
	private String description;
	private Vector mapping;
	
	public Responsibility(){
		mapping = new Vector();
	}

	public Component getComponent() {
		return component;
	}

	public void setComponent(Component component) {
		this.component = component;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean hasComponent() {
		if (component != null)
			return true;
		return false;
	}
	
	
	@SuppressWarnings("unchecked")
	public void addMapping(String className){
		mapping.add(className);
	}

	public Vector getMapping() {
		return mapping;
	}
	
	public void setMapping(Vector m) {
		for (int i = 0; i < m.size(); i++) {
			this.mapping.add(m.get(i));
		}
	}
}
