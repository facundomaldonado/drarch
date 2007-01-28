package org.design.drarch.diagram.DiagramModel.componentModel;

import java.util.Vector;

import org.design.drarch.diagram.DiagramModel.IModel;



public class ComponentModel implements IModel{
	private String name;
	private Vector components;
	private Vector relationships;
	private Vector responsibilities;
	private Vector interfaceLinks;
	@SuppressWarnings("unused")
	private Vector notes;
	
	public ComponentModel(){
		this.name = null;
		this.components = new Vector();
		this.interfaceLinks = new Vector();
		this.relationships = new Vector();
		this.responsibilities = new Vector();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Component getComponent(String componentName){
		for (int i = 0; i < components.size(); i++) {
			Component c = (Component)components.get(i);
			if (componentName.equals(c.getName())){
				return c;
			}
		}
		return null;
	}
	
	public Responsibility getResponsibilitie(String name){
		for (int i = 0; i < responsibilities.size(); i++) {
			Responsibility r = (Responsibility)responsibilities.get(i);
			if (name.equals(r.getName())){
				return r;
			}
		}
		return null;
	}
	
	public boolean existComponent(String componentName){
		for (int i = 0; i < components.size(); i++) {
			Component c = (Component)components.get(i);
			if (componentName.equals(c.getName())){
				return true;
			}
		}
		return false;
	}
	
	
	@SuppressWarnings("unchecked")
	public void createComponent(String name){
		if (!existComponent(name)){
			Component c = new Component();
			c.setName(name);
			components.add(c);
		}
		else {
			System.out.println("ComponentModel.crateComponent(): The componene allready exist" );
		}
	}
	
	@SuppressWarnings("unchecked")
	public void createLinkInterface(String componentProvided, 
									String componentRequired,
									String portProvided, 
									String portRequired, 
									String interfaceProvided, 
									String interfaceRequired){
		
		//if (existComponent(componentProvided)&&(existComponent(componentRequired)))
		Interface providedInterface = getComponent(componentProvided).getPort(portProvided).getProvided();
		Interface requiredInterface = getComponent(componentRequired).getPort(portRequired).getRequired();
		InterfaceLink interfaceLink = new InterfaceLink();
		interfaceLink.setSource(providedInterface);
		interfaceLink.setTarget(requiredInterface);
		interfaceLinks.add(interfaceLink);
		
	}
	
	@SuppressWarnings("unchecked")
	public Vector<String> getAllComponents(){
		return this.components;
	}

	public Vector getInterfaceLinks() {
		return this.interfaceLinks;
	}

	public Port createPort(String port, String component) {
		Port p = new Port(getComponent(component));
		p.setName(port);
		return p;
	}

	@SuppressWarnings("unchecked")
	public void createRelationship(String componentSource, String componentTarget, String stereotype) {
		Relationship relationship = new Relationship();
		relationship.setSource(getComponent(componentSource));
		relationship.setTarget(getComponent(componentTarget));
		relationship.setStereotype(stereotype);
		this.relationships.add(relationship);
	}

	public Vector getRelationships() {
		return relationships;
	}
	
	public Vector getResponsibilities(){
		return responsibilities;
	}

	@SuppressWarnings("unchecked")
	public void createResponsibility(String name) {
		Responsibility responsibility = new Responsibility();
		responsibility.setName(name);
		responsibilities.add(responsibility);
	}

	public void RegisterResponsability(String component, String res) {
		for (int i = 0; i < responsibilities.size(); i++) {
			Responsibility responsibility = (Responsibility) responsibilities.get(i);
			if (responsibility.getName().equals(res)){
				responsibility.setComponent(getComponent(component));
			}
		}
	}

	public Vector getAssociations(String componentName) {
		return getComponent(componentName).getAssociation();
	}

	public void setAssociations(String componentName, Vector associations) {
		getComponent(componentName).setAssociation(associations);
	}

	public void addMappingResponsibility(String responsibilityName, Vector mapping) {
		Responsibility r = getResponsibilitie(responsibilityName);
		r.setMapping(mapping);
	}

	public Vector getMapping(String responsibilityName) {
		return getResponsibilitie(responsibilityName).getMapping();
	}
}