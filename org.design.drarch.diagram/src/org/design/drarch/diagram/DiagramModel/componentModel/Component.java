package org.design.drarch.diagram.DiagramModel.componentModel;

import java.util.Vector;


public class Component {
	private String name;
	private Vector ports;
	private Vector association;
	
	public Component(){
		this.name = "noname";
		this.ports = new Vector();
		this.association = new Vector();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@SuppressWarnings("unchecked")
	public void addPort(Port portProvided) {
		ports.add(portProvided);
	}
	
	public Vector getAllPorts(){
		return ports;
	}
	
	public Port getPort(String portName){
		for (int i = 0; i < ports.size(); i++) {
			Port port = (Port) ports.get(i);
			if (port.getName().equals(portName)){
				return port;
			}
		}
		return null;
	}

	public Vector getAssociation() {
		return association;
	}

	public void setAssociation(Vector associations) {
		this.association = associations;
		
	}
}
