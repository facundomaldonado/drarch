package org.drarch.diagram.flabot.mapping;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.isistan.flabot.coremodel.ComponentModel;
import org.isistan.flabot.coremodel.ComponentRole;
import org.isistan.flabot.coremodel.CoreModel;
import org.isistan.flabot.coremodel.InterfaceLink;
import org.isistan.flabot.coremodel.InterfaceModel;
import org.isistan.flabot.coremodel.Path;
import org.isistan.flabot.coremodel.PortModel;
import org.isistan.flabot.coremodel.Relationship;
import org.isistan.flabot.coremodel.Responsibility;
import org.isistan.flabot.coremodel.ResponsibilityNode;
import org.isistan.flabot.coremodel.UseCaseMap;
import org.isistan.flabot.mapping.MappingPlugin;
import org.isistan.flabot.mapping.mappingmodel.PatternMapping;


/**
 * Mapping the flabot model to facts.
 * 
 * This mapping allows to represent the flabot model into a logic programing language.
 * 
 * Note: The intent of this class is map a selected components of the model. It not cover all
 * flabot model.
 * 
 * @author nfrontini
 *
 */
public class MappingModel {

	public static List<String> getFactSet(CoreModel coreModel) {
		List<String> factSet = new ArrayList<String>();
		
		factSet.addAll(getComponentFacts(coreModel.getComponents()));
		factSet.addAll(getResponsibilityFacts(coreModel.getResponsibilities()));
		factSet.addAll(getHasResponsibilityFacts(coreModel.getResponsibilities()));
		factSet.addAll(getInterfaceLinkFacts(coreModel.getInterfaceLinks()));
		factSet.addAll(getInterfaceModelFacts(coreModel.getInterfaceLinks()));
		factSet.addAll(getPortFacts(coreModel.getComponents()));
		factSet.addAll(getHasPortFacts(coreModel.getComponents()));
		factSet.addAll(getHasProvidedInterfaceFacts(coreModel.getComponents()));
		factSet.addAll(getHasRequiredInterfaceFacts(coreModel.getComponents()));
		factSet.addAll(getMappingFacts(coreModel.getResponsibilities()));
		factSet.addAll(getRelationshipFacts(coreModel.getRelationships()));
		factSet.addAll(getAssociationFacts(coreModel.getComponents()));
		factSet.addAll(getResponsibilityExecutionFacts(coreModel.getUseCaseMaps()));
		factSet.addAll(getComponentRolesFacts(coreModel.getUseCaseMaps()));
		
		return factSet;
		
	}

	/**
	 * Fact: component(componentName).
	 * 
	 * @param components
	 * @return
	 */
	private static List<String> getComponentFacts(EList components) {
		List<String> factList = new ArrayList<String>();
		for (Object element : components) {
			ComponentModel componentModel = (ComponentModel) element;
			factList.add("component(" + componentModel.getName() + ").");
		}
		return factList;
	}
	
	/**
	 * Fact: responsibility(responsibilityName).
	 * 
	 * @param components
	 * @return
	 */
	private static List<String> getResponsibilityFacts(EList responsibilities) {
		List<String> factList = new ArrayList<String>();
		for (Object element : responsibilities) {
			Responsibility responsibility = (Responsibility) element;
			factList.add("responsibility(" + responsibility.getName() + ").");
		}
		return factList;
	}
	
	/**
	 * Fact: hasResponsibility(componentName, responsibilityName).
	 * 
	 * @param components
	 * @return
	 */
	private static List<String> getHasResponsibilityFacts(EList responsibilities) {
		List<String> factList = new ArrayList<String>();
		for (Object element : responsibilities) {
			Responsibility responsibility = (Responsibility) element;
			for (Object subElement : responsibility.getComponents()) {
				ComponentModel componentModel = (ComponentModel) subElement;
				factList.add("hasResponsibility(" + componentModel.getName() +
						", " + responsibility.getName() + ").");
			}
		}
		return factList;
	}

	/**
	 * Fact: interfaceLink(sourceName, targetName).
	 * 
	 * @param components
	 * @return
	 */
	private static List<String> getInterfaceLinkFacts(EList interfaceLinks) {
		List<String> factList = new ArrayList<String>();
		for (Object element : interfaceLinks) {
			InterfaceLink interfaceLink = (InterfaceLink) element;
			factList.add("interfaceLink(" + interfaceLink.getSource().getName() +
					", " + interfaceLink.getTarget().getName() + ").");
		}
		return factList;
	}
	
	/**
	 * Fact: interfaceModel(interfaceName).
	 * 
	 * @param components
	 * @return
	 */
	private static List<String> getInterfaceModelFacts(EList components) {
		List<String> factList = new ArrayList<String>();
		for (Object element : components) {
			ComponentModel componentModel = (ComponentModel) element;
			for (Object subElement : componentModel.getOwnedPorts()) {
				PortModel portModel = (PortModel) subElement;
				for (Object subSubElement : portModel.getProvideds()) {
					InterfaceModel interfaceModel = (InterfaceModel) subSubElement;
					factList.add("interfaceModel(" + interfaceModel.getName() + ").");	
				}
				for (Object subSubElement : portModel.getRequireds()) {
					InterfaceModel interfaceModel = (InterfaceModel) subSubElement;
					factList.add("interfaceModel(" + interfaceModel.getName() + ").");	
				}
			}
		}
		return factList;
	}

	/**
	 * Fact: port(portName).
	 * 
	 * @param components
	 * @return
	 */
	private static List<String> getPortFacts(EList components) {
		List<String> factList = new ArrayList<String>();
		for (Object element : components) {
			ComponentModel componentModel = (ComponentModel) element;
			for (Object subElement : componentModel.getOwnedPorts()) {
				PortModel portModel = (PortModel) subElement;
				factList.add("port(" + portModel.getName() + ").");
			}
		}
		return factList;
	}
	
	/**
	 * Fact: hasPort(componentName, portName).
	 * 
	 * @param components
	 * @return
	 */
	private static List<String> getHasPortFacts(EList components) {
		List<String> factList = new ArrayList<String>();
		for (Object element : components) {
			ComponentModel componentModel = (ComponentModel) element;
			for (Object subElement : componentModel.getOwnedPorts()) {
				PortModel portModel = (PortModel) subElement;
				factList.add("hasPort(" + componentModel.getName() +
						", " + portModel.getName() + ").");
			}
		}
		return factList;
	}

	/**
	 * Fact: hasProvidedInterface(portName, interfaceName).
	 * 
	 * @param components
	 * @return
	 */
	private static List<String> getHasProvidedInterfaceFacts(EList components) {
		List<String> factList = new ArrayList<String>();
		for (Object element : components) {
			ComponentModel componentModel = (ComponentModel) element;
			for (Object subElement : componentModel.getOwnedPorts()) {
				PortModel portModel = (PortModel) subElement;
				for (Object subSubElement : portModel.getProvideds()) {
					InterfaceModel interfaceModel = (InterfaceModel) subSubElement;
					factList.add("hasProvidedInterface(" + portModel.getName() +
							", " + interfaceModel.getName() + ").");	
				}
			}
		}
		return factList;
	}

	/**
	 * Fact: hasRequiredInterface(portName, interfaceName).
	 * 
	 * @param components
	 * @return
	 */
	private static List<String> getHasRequiredInterfaceFacts(EList components) {
		List<String> factList = new ArrayList<String>();
		for (Object element : components) {
			ComponentModel componentModel = (ComponentModel) element;
			for (Object subElement : componentModel.getOwnedPorts()) {
				PortModel portModel = (PortModel) subElement;
				for (Object subSubElement : portModel.getRequireds()) {
					InterfaceModel interfaceModel = (InterfaceModel) subSubElement;
					factList.add("hasRequiredInterface(" + portModel.getName() +
							", " + interfaceModel.getName() + ").");	
				}
			}
		}
		return factList;
	}

	// TODO: Sirve el mapping con tres parametros?
	/**
	 * Fact: mapping(responsibilityName, XXX, s).
	 * 		 mapping(responsibilityName, packageName.className.methodName).
	 * @param components
	 * @return
	 */
	private static List<String> getMappingFacts(EList responsibilities) {
		List<String> factList = new ArrayList<String>();
		for (Object element : responsibilities) {
			Responsibility responsibility = (Responsibility) element;
	        PatternMapping patternMapping = (PatternMapping) responsibility.
	        		getExtendedData(MappingPlugin.SYMBOLIC_NAME, "mapping");
	        String dirtyMethodName = patternMapping.getBehaviorPattern();
	        String[] methodsName = dirtyMethodName.replace("\\", "").replace("@", "").
	        		replace("#", ".").split("\\|");
	        for (String methodName : methodsName) {
	        	
	        	// TODO: No se estan agregando los parametros ni el tipo que retorna.
	        	factList.add("mapping(" + responsibility.getName() + ", " + 
	        			methodName.substring(0, methodName.indexOf("(")) + ").");
	        }
		}
		return factList;
	}

	/**
	 * Fact: relationship(componentName, componentName, stereotypeName).
	 * 
	 * @param components
	 * @return
	 */
	private static List<String> getRelationshipFacts(EList relationships) {
		List<String> factList = new ArrayList<String>();
		for (Object element : relationships) {	
			Relationship relationship = (Relationship) element;
			ComponentModel source = relationship.getSource();
			ComponentModel target = relationship.getTarget();
			factList.add("relationship(" + source.getName() + ", " +
					target.getName() + ", " + relationship.getStereotype().getName());
		}
		return factList;
	}

	/**
	 * Fact: association(componentName, packageName.className).
	 * 
	 * @param components
	 * @return
	 */
	private static List<String> getAssociationFacts(EList components) {
		List<String> factList = new ArrayList<String>();
		for (Object element : components) {
			ComponentModel componentModel = (ComponentModel) element;
			PatternMapping patternMapping = (PatternMapping) componentModel.
					getExtendedData(MappingPlugin.SYMBOLIC_NAME, "mapping");
			String dirtyClassNames = patternMapping.getClassPattern();
			String[] classNames = dirtyClassNames.replace("\\", "").replace("@", "").split("\\|");
			for (String className : classNames) {
				factList.add("association(" + componentModel.getName() + ", " +
						className + ").");
			}
		}
		return factList;
	}

	/**
	 * Fact: responsibilityExecution(responsibilityName, id, return).
	 *
	 * @param components
	 * @return
	 */
	private static List<String> getResponsibilityExecutionFacts(EList useCaseMaps) {
		List<String> factList = new ArrayList<String>();
		for (Object element : useCaseMaps) {
			UseCaseMap useCaseMap = (UseCaseMap) element;
			
			for (Object subElement : useCaseMap.getPaths()) {
				Path path = (Path) subElement;
				int id = 0;
				for (Object subSubElement : path.getNodes()) {
					if (subSubElement instanceof ResponsibilityNode) {
						ResponsibilityNode responsibilityNode = (ResponsibilityNode) subSubElement;
					    factList.add("responsibilityExecution(" + responsibilityNode.getName() +
					    		", " + id++ + ").");
					}
				}
			}
		}
		return factList;
	}
	
	/**
	 * Fact: componentRoles(componentRolesName).
	 *
	 * @param components
	 * @return
	 */
	private static List<String> getComponentRolesFacts(EList useCaseMaps) {
		List<String> factList = new ArrayList<String>();
		for (Object element : useCaseMaps) {
			UseCaseMap useCaseMap = (UseCaseMap) element;
			for (Object subElement : useCaseMap.getComponentRoles()) {
				ComponentRole componentRoles = (ComponentRole) subElement;
				factList.add("componentRoles(" + componentRoles.getComponent().getName() + ").");
			}
		}
		return factList;
	}
}
