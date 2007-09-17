package org.design.drarch.diagram.DiagramModel.componentModel;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.design.drarch.diagram.DiagramPlugin;
import org.design.drarch.diagram.DiagramModel.IModel;
import org.design.drarch.diagram.flabot.component.DObject;

/**
 * 
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public class ComponentModel implements IModel {

	private static Logger logger = Logger.getLogger(DiagramPlugin.class.getName());
	
	private String	             name;
	private List<Component>	     components;
	private List<Relationship>	 relationships;
	private List<Responsibility>	responsibilities;
	private List<InterfaceLink>	 interfaceLinks;

	public ComponentModel() {
		this.name = null;
		this.components = new ArrayList<Component>();
		this.interfaceLinks = new ArrayList<InterfaceLink>();
		this.relationships = new ArrayList<Relationship>();
		this.responsibilities = new ArrayList<Responsibility>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Component getComponent(String componentName) {
		for (int i = 0; i < components.size(); i++) {
			Component c = (Component) components.get(i);
			if (componentName.equals(c.getName())) {
				return c;
			}
		}
		return null;
	}

	public Responsibility getResponsibilitie(String name) {
		for (int i = 0; i < responsibilities.size(); i++) {
			Responsibility r = (Responsibility) responsibilities.get(i);
			if (name.equals(r.getName())) {
				return r;
			}
		}
		return null;
	}

	public boolean existComponent(String componentName) {
		for (int i = 0; i < components.size(); i++) {
			Component c = (Component) components.get(i);
			if (componentName.equals(c.getName())) {
				return true;
			}
		}
		return false;
	}

	public void createComponent(String name) {
		if (!existComponent(name)) {
			Component c = new Component();
			c.setName(name);
			components.add(c);
		} else {
			//TODO remove. ver si se lanza una excepcion o no.
			//decidir si se interrumpe el flujo o se sigue.
			System.out
			        .println("ComponentModel.crateComponent(): The componene allready exist");
		}
	}

	@SuppressWarnings("unchecked")
	public void createLinkInterface(String componentProvided,
	        String componentRequired, String portProvided, String portRequired,
	        String interfaceProvided, String interfaceRequired) {

		// if
		// (existComponent(componentProvided)&&(existComponent(componentRequired)))
		Interface providedInterface = getComponent(componentProvided).getPort(
		        portProvided).getProvided();
		Interface requiredInterface = getComponent(componentRequired).getPort(
		        portRequired).getRequired();
		InterfaceLink interfaceLink = new InterfaceLink();
		interfaceLink.setSource(providedInterface);
		interfaceLink.setTarget(requiredInterface);
		interfaceLinks.add(interfaceLink);
	}

	@SuppressWarnings("unchecked")
	public List<Component> getAllComponents() {
		return this.components;
	}

	public List<InterfaceLink> getInterfaceLinks() {
		return this.interfaceLinks;
	}

	public Port createPort(String port, String component) {
		Port p = new Port(getComponent(component));
		p.setName(port);
		return p;
	}

	@SuppressWarnings("unchecked")
	public void createRelationship(String componentSource,
	        String componentTarget, String stereotype) {
		Relationship relationship = new Relationship();
		relationship.setSource(getComponent(componentSource));
		relationship.setTarget(getComponent(componentTarget));
		relationship.setStereotype(stereotype);
		this.relationships.add(relationship);
	}

	public List<Relationship> getRelationships() {
		return relationships;
	}

	public List<Responsibility> getResponsibilities() {
		return responsibilities;
	}

	public void createResponsibility(String name) {
		Responsibility responsibility = new Responsibility();
		responsibility.setName(name);
		responsibilities.add(responsibility);
	}

	public void RegisterResponsability(String component, String res) {
		for (int i = 0; i < responsibilities.size(); i++) {
			Responsibility responsibility = (Responsibility) responsibilities
			        .get(i);
			if (responsibility.getName().equals(res)) {
				responsibility.setComponent(getComponent(component));
			}
		}
	}

	public List getAssociations(String componentName) {
		return getComponent(componentName).getAssociation();
	}

	public void setAssociations(String componentName, List associations) {
		getComponent(componentName).setAssociation(associations);
	}

	public void addMappingResponsibility(String responsibilityName,
	        List<DObject> mapping) {
		Responsibility r = getResponsibilitie(responsibilityName);
		r.setMapping(mapping);
	}

	public List getMapping(String responsibilityName) {
		return getResponsibilitie(responsibilityName).getMapping();
	}
}
