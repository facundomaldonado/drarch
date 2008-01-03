package org.drarch.diagram.DiagramModel.componentModel.generator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.drarch.diagram.DiagramModel.componentModel.ComponentModel;
import org.drarch.diagram.DiagramModel.componentModel.Interface;
import org.drarch.diagram.DiagramModel.componentModel.Port;
import org.drarch.diagram.flabot.component.DBehavior;
import org.drarch.diagram.flabot.component.DObject;
import org.drarch.engine.ruleEngine.QueryEngine;
import org.drarch.engine.ruleEngine.QueryResult;
import org.drarch.engine.ruleEngine.ResultSet;
import org.drarch.engine.ruleModel.Query;
import org.drarch.engine.ruleModel.RuleModelFactory;

public class ComponentModelGenerator {

	private static Logger logger = Logger
			.getLogger(ComponentModelGenerator.class.getName());

	private QueryEngine queryEngine;

	public ComponentModelGenerator(QueryEngine theQueryEngine) {
		queryEngine = theQueryEngine;
		queryEngine.reloadRules();
	}

	public ResultSet getResultQuery(String consult, List varList) {
		Query query = RuleModelFactory.eINSTANCE.createQuery();
		query.setQueryString(consult);
		return queryEngine.evaluateQuery(query);
	}

	@SuppressWarnings("unchecked")
	public ResultSet getComponents() {
		List varList = new LinkedList();
		varList.add("?C");
		return getResultQuery("component(?C)", varList);
	}

	@SuppressWarnings("unchecked")
	public ResultSet getPorts(String component) {
		List varList = new LinkedList();
		varList.add("?P");
		return getResultQuery("port(?P), hasPort(" + component + ",?P). ",
				varList);
	}

	@SuppressWarnings("unchecked")
	private ResultSet getProvidedInterface(String port) {
		List varList = new LinkedList();
		varList.add("?I");
		return getResultQuery("hasProvidedInterface(" + port + ", ?I), "
				+ "interfaceModel(?I).", varList);
	}

	@SuppressWarnings("unchecked")
	private ResultSet getRequiredInterface(String port) {
		List varList = new LinkedList();
		varList.add("?I");
		return getResultQuery("hasRequiredInterface(" + port + ", ?I), "
				+ "interfaceModel(?I).", varList);
	}

	@SuppressWarnings("unchecked")
	public ComponentModel getModel() {
		ComponentModel cModel = new ComponentModel();
		ResultSet allComponents = getComponents();
		while (allComponents.hasMoreElements()) {
			QueryResult resultComponent = allComponents.next();
			String component = resultComponent.getValueOfVar("?C");
			cModel.createComponent(component);

			ResultSet ports = getPorts(component);
			while (ports.hasMoreElements()) {
				QueryResult resultPort = ports.next();
				Port port = cModel.createPort(resultPort.getValueOfVar("?P"),
						component);
				ResultSet providedInterfaceName = getProvidedInterface(port
						.getName());
				while (providedInterfaceName.hasMoreElements()) {
					Interface providedInterface = new Interface();
					QueryResult resultProvided = providedInterfaceName.next();
					providedInterface.setName(resultProvided
							.getValueOfVar("?I"));
					port.setProvided(providedInterface);
					providedInterface.setPort(port);
				}
				ResultSet requiredInterfaceName = getRequiredInterface(port
						.getName());
				while (requiredInterfaceName.hasMoreElements()) {
					Interface requiredInterface = new Interface();
					QueryResult resultRequired = requiredInterfaceName.next();
					requiredInterface.setName(resultRequired
							.getValueOfVar("?I"));
					port.setRequired(requiredInterface);
					requiredInterface.setPort(port);
				}
				cModel.getComponent(component).addPort(port);
			}
			List association = new ArrayList();
			ResultSet resultSetAssociation = getAssociation(resultComponent
					.getValueOfVar("?C"));
			while (resultSetAssociation.hasMoreElements()) {
				QueryResult ResultAssociation = resultSetAssociation.next();
				DObject dObject = new DObject();
				dObject.setBehavior(null);

				dObject.setClassName(ResultAssociation.getValueOfVar(
						"?ClazzName").toString());
				dObject.setPackageName(ResultAssociation.getValueOfVar(
						"?PackageName").toString());
				association.add(dObject);
			}
			cModel.setAssociations(resultComponent.getValueOfVar("?C"),
					association);
		}

		ResultSet links = getLinks();
		while (links.hasMoreElements()) {
			QueryResult link = links.next();
			cModel.createLinkInterface(link.getValueOfVar("?Cp"), link
					.getValueOfVar("?Cr"), link.getValueOfVar("?Pp"), link
					.getValueOfVar("?Pr"), link.getValueOfVar("?Ip"), link
					.getValueOfVar("?Ir"));
		}

		ResultSet relationships = getRelationships();
		while (relationships.hasMoreElements()) {
			QueryResult relation = relationships.next();
			cModel.createRelationship(relation.getValueOfVar("?Ccreator"),
					relation.getValueOfVar("?Ccreated"), relation
							.getValueOfVar("?Stereotype"));
		}

		ResultSet responsibilities = getResponsibiliies();
		while (responsibilities.hasMoreElements()) {
			QueryResult responsibility = responsibilities.next();
			cModel.createResponsibility(responsibility.getValueOfVar("?Res"));
		}

		ResultSet responsibilitiesReg = getResponsibiliiesRegistration();
		while (responsibilitiesReg.hasMoreElements()) {
			QueryResult responsibilityReg = responsibilitiesReg.next();

			cModel.RegisterResponsability(responsibilityReg
					.getValueOfVar("?Comp"), responsibilityReg
					.getValueOfVar("?Res"));

			// Se mapean las clases y metodos a cada responsabilidad.

			// TODO: esto es para mapear clases completas!!! Se deben mapear metodos.
			List<DObject> mapping = new ArrayList<DObject>();
			ResultSet resultSetMappingClass = getMappingClass(responsibilityReg
					.getValueOfVar("?Res"));
			while (resultSetMappingClass.hasMoreElements()) {
				QueryResult ResultMappingClass = resultSetMappingClass.next();
				DObject dObject = new DObject();
				dObject.setClassName(ResultMappingClass.getValueOfVar(
						"?ClazzName").toString());
				dObject.setPackageName(ResultMappingClass.getValueOfVar(
						"?PackageName").toString());

				ResultSet resultSetMappingMethod = getMappingMethod(
						responsibilityReg.getValueOfVar("?Res").toString(),
						ResultMappingClass.getValueOfVar("?PackageName")
								.toString(), ResultMappingClass.getValueOfVar(
								"?ClazzName").toString());
				Vector behaviors = new Vector();
				while (resultSetMappingMethod.hasMoreElements()) {
					QueryResult resultMappingMethod = resultSetMappingMethod
							.next();
					DBehavior behavior = new DBehavior();
					behavior.setMethod(resultMappingMethod
							.getValueOfVar("?MethodName"));
					behavior.setType((resultMappingMethod
							.getValueOfVar("?Type")).replace("%", ""));
					ResultSet resultSetMappingArguments = getMappingArguments(
							ResultMappingClass.getValueOfVar("?PackageName")
									.toString(), ResultMappingClass
									.getValueOfVar("?ClazzName").toString(),
							resultMappingMethod.getValueOfVar("?MethodName")
									.toString());
					while (resultSetMappingArguments.hasMoreElements()) {
						QueryResult resultMappingArguments = resultSetMappingArguments
								.next();
						behavior.addArgumentType(resultMappingArguments
								.getValueOfVar("?ParamsList").toString());
					}
					behaviors.add(behavior);
				}
				dObject.setBehavior(behaviors);
				mapping.add(dObject);
			}
			cModel.addMappingResponsibility(responsibilityReg
					.getValueOfVar("?Res"), mapping);
		}
		return cModel;
	}

	@SuppressWarnings("unchecked")
	private ResultSet getMappingArguments(String packageName, String className,
			String methodName) {
		LinkedList varList = new LinkedList();
		varList.add("?ParamsList");
		return getResultQuery("package(?P), name(?P," + packageName + "), "
				+ "child(?P,?File), javaFile(?File), child(?File, ?Class), "
				+ "type(?Class), name(?Class, " + className
				+ "),	method(?Class," + " ?Method),	name(?Method, "
				+ methodName + "), params(?Method, " + "?ParamsList) ", varList);
	}

	@SuppressWarnings("unchecked")
	private ResultSet getMappingMethod(String packageName, String className) {
		LinkedList varList = new LinkedList();
		varList.add("?MethodName");
		varList.add("?Type");
		return getResultQuery(
				"package(?P), name(?P, "
						+ packageName
						+ "), "
						+ "child(?P,?CU), name(?class, "
						+ className
						+ "), child(?CU, "
						+ "?class),Type(?class), javaFile(?CU), method(?class,?Method), "
						+ "returns(?Method, ?Type), name(?Method, ?MethodName)" // , "
						// +
				, varList);
	}

	@SuppressWarnings("unchecked")
	private ResultSet getMappingMethod(String responsName, String packageName,
			String className) {
		LinkedList varList = new LinkedList();
		varList.add("?MethodName");
		varList.add("?Type");
		return getResultQuery(
				"mapping("
						+ responsName
						+ ", ?QualifiedMethodName), "
						+ //"." + packageName + "." + className + ".?MethodName), " +
						"string_append(?QualifiedName, ?DotMethodName, ?QualifiedMethodName), "
						+ "string_append('.', ?MethodName, ?DotMethodName), "
						+ "string_append(?PackageName, ?DotClazzName, ?QualifiedName), "
						+ "string_append('.', ?ClazzName, ?DotClazzName), "
						+ "package(?Package), name(?Package, " + packageName
						+ "), package(?Clazz, ?Package), "
						+ "class(?Clazz), name(?Clazz, " + className
						+ "), method(?Clazz, ?Method),"
						+ "returns(?Method, ?Type), name(?Method, ?MethodName)",
				varList);
	}

	@SuppressWarnings("unchecked")
	private ResultSet getMappingClass(String responsibility) {
		LinkedList varList = new LinkedList();
		varList.add("?ClazzName");
		varList.add("?PackageName");

		// mapping(TaskImpl2.body, experimental.modeladmin.component2.task.TaskImpl2.body).

		return getResultQuery(
				"mapping("
						+ responsibility
						+ ", ?QualifiedMethodName), "
						+ "name(?Clazz, ?ClazzName), package(?Clazz, ?Package), name(?Package, ?PackageName),"
						+ "string_append(?PackageName, ?DotClazzName, ?QualifiedName), "
						+ "string_append('.', ?ClazzName, ?DotClazzName), "
						+ "method(?Clazz, ?Method), name(?Method, ?MethodName), "
						+ "string_append(?QualifiedName, ?DotMethodName, ?QualifiedMethodName), "
						+ "string_append('.', ?MethodName, ?DotMethodName)",
				varList);
	}

	@SuppressWarnings("unchecked")
	private ResultSet getAssociation(String component) {
		LinkedList varList = new LinkedList();
		varList.add("?PackageName");
		varList.add("?ClazzName");
		return getResultQuery(
				"association("
						+ component
						+ ", ?QualifiedName), "
						+ "name(?Clazz, ?ClazzName), package(?Clazz, ?Package), name(?Package, ?PackageName),"
						+ "string_append(?PackageName, ?DotClazzName, ?QualifiedName), "
						+ "string_append('.', ?ClazzName, ?DotClazzName)",
				varList);
	}

	@SuppressWarnings("unchecked")
	private ResultSet getResponsibiliiesRegistration() {
		LinkedList varList = new LinkedList();
		varList.add("?Comp");
		varList.add("?Res");
		return getResultQuery("hasResponsibility(?Comp, ?Res)", varList);
	}

	@SuppressWarnings("unchecked")
	private ResultSet getResponsibiliies() {
		LinkedList varList = new LinkedList();
		varList.add("?Res");
		return getResultQuery("responsibility(?Res)", varList);
	}

	@SuppressWarnings("unchecked")
	private ResultSet getRelationships() {
		LinkedList varList = new LinkedList();
		varList.add("?Ccreator");
		varList.add("?Ccreated");
		varList.add("?Stereotype");
		return getResultQuery(
				"relationship(?Ccreator, ?Ccreated, ?Stereotype)", varList);
	}

	@SuppressWarnings("unchecked")
	private ResultSet getLinks() {
		List varList = new LinkedList();
		varList.add("?Ip");
		varList.add("?Ir");
		varList.add("?Pp");
		varList.add("?Pr");
		varList.add("?Cp");
		varList.add("?Cr");
		return getResultQuery(
				"interfaceLink(?Ip, ?Ir),"
						+ "interfaceModel(?Ip), interfaceModel(?Ir), NOT(equals(?Ip, ?Ir)),"
						+ "hasProvidedInterface(?Pp, ?Ip), hasRequiredInterface(?Pr, ?Ir), "
						+ "hasPort(?Cr, ?Pr), hasPort(?Cp, ?Pp).", varList);
	}
}
