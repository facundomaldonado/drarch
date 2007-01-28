package org.design.drarch.diagram.DiagramModel.componentModel;

public class InterfaceLink {
	private String name;
	private Interface source;
	private Interface target;
	
	public InterfaceLink(){
		this.name = "noname";
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Interface getSource() {
		return source;
	}
	public void setSource(Interface source) {
		this.source = source;
	}
	public Interface getTarget() {
		return target;
	}
	public void setTarget(Interface target) {
		this.target = target;
	}
	
	
}
