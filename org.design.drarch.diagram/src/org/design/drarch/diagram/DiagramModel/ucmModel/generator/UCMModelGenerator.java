package org.design.drarch.diagram.DiagramModel.ucmModel.generator;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.design.drarch.diagram.DiagramPlugin;
import org.design.drarch.diagram.DiagramModel.componentModel.Component;
import org.design.drarch.diagram.DiagramModel.ucmModel.ComponentRole;
import org.design.drarch.diagram.DiagramModel.ucmModel.Path;
import org.design.drarch.diagram.DiagramModel.ucmModel.PathNode;
import org.design.drarch.diagram.DiagramModel.ucmModel.UCMModel;
import org.design.drarch.diagram.DiagramModel.ucmModel.UcmModelFactory;
import org.design.drarch.diagram.flabot.DiagramManager;
import org.design.drarch.diagram.trace.LogQueryFactory;
import org.design.rules4Java.engine.coreEngine.engineModel.EngineModelFactory;
import org.design.rules4Java.engine.coreEngine.engineModel.QueryEngine;
import org.design.rules4Java.engine.coreEngine.engineModel.QueryResult;
import org.design.rules4Java.engine.coreEngine.engineModel.ResultSet;
import org.design.rules4Java.engine.exceptions.DrarchEngineModelException;
import org.design.rules4Java.engine.ruleModel.Query;
import org.design.rules4Java.engine.ruleModel.RuleModelFactory;
import org.design.rules4Java.engine.ruleModel.Var;

/**
 * Generate the drarch model by quering the Knowledge base.
 * 
 * @author nfrontini
 */
public class UCMModelGenerator {
	
	private static Logger logger = Logger.getLogger(DiagramPlugin.class.getName());
	
	private UCMModel ucmModel;
	
	private QueryEngine queryEngine;

	public UCMModelGenerator(QueryEngine theQueryEngine) {
		ucmModel = UcmModelFactory.eINSTANCE.createUCMModel();
		queryEngine = theQueryEngine;
	}
	
	/**
	 * Build the model from the knowledge base.
	 * 
	 * @return Returns the <code>UCMModel</code> from the knowledge base.
	 */
	public UCMModel getModel() {
		ucmModel = UcmModelFactory.eINSTANCE.createUCMModel();
		ucmModel.getComponentRoles().addAll(getComponentsRole());
		ucmModel.getPaths().addAll(getExecutionPaths());
		ucmModel.setName("ucm");
		return ucmModel;
	}
	
	/**
	 * Get a list of <code>ComponentRole</code>.
	 * 
	 * @return Returns a list of <code>ComponentRole</code>.
	 */
	private List<ComponentRole> getComponentsRole() {
		List<ComponentRole> componentsRole = new ArrayList<ComponentRole>();
		List<String> responsibilities = getExecutedResponsibilities();
		for (String responsibilityName : responsibilities) {
			Component component = getAsociatedComponent(responsibilityName);
			if (component != null) {
				ComponentRole componentRole = UcmModelFactory.eINSTANCE.createComponentRole();
				componentRole.setComponent(component);
				componentRole.setName(component.getName());
				componentsRole.add(componentRole);
			}
		}
		return componentsRole;
	}
	
	/**
	 * Get the executed responsibilities.
	 * 
	 * @return Returns the executed responsibilities.
	 */
	private List<String> getExecutedResponsibilities() {
		Query query = RuleModelFactory.eINSTANCE.createQuery();
		query.setQueryString(LogQueryFactory.DEFAULT_EXECUTION_QUERY);
		Var responsibilityVar = RuleModelFactory.eINSTANCE.createVar();
		responsibilityVar.setVarText("?Responsibility");
		query.getChosenVars().add(responsibilityVar);
		ResultSet responsibilitiesResultSet = evaluateQuery(query);
		List<String> responsibilities = new ArrayList<String>();
 		while (responsibilitiesResultSet.hasMoreElements()) {
			QueryResult responsibility = responsibilitiesResultSet.next();
			responsibilities.add(responsibility.getValueOfVar("?Responsibility"));
		}
		return responsibilities;
	}
	
	/**
	 * Get a component asociated with a responsibility.
	 * 
	 * @param responsibilityName The name of the responsibility.
	 * @return Returns a component asociated with the specified responsibility.
	 */
	private Component getAsociatedComponent(String responsibilityName) {
		Query query = RuleModelFactory.eINSTANCE.createQuery();
		query.setQueryString(LogQueryFactory.getInstance()
		        .createComponentResponsibilityAssociationQuery(responsibilityName));
		Var componentVar = RuleModelFactory.eINSTANCE.createVar();
		componentVar.setVarText("?Component");
		query.getChosenVars().add(componentVar);
		ResultSet componentsResultSet = evaluateQuery(query);
		String componentName = "";
		while (componentsResultSet.hasMoreElements()) {
			QueryResult components = componentsResultSet.next();
			componentName = components.getValueOfVar("?Component");
		}
		return DiagramManager.getInstance().getCurrentComponentModel().getComponent(componentName);
	}
	
	/**
	 * Create the initial path with the start and end nodes.
	 * 
	 * @return Return a path with the start and end nodes.
	 */
	public Path createInitialPath() {
		Path path = UcmModelFactory.eINSTANCE.createPath();
		PathNode startNode = UcmModelFactory.eINSTANCE.createPathNode();
		startNode.setResponsibilityName("Start");
		path.getStartNodes().add(startNode);
		PathNode endNode = UcmModelFactory.eINSTANCE.createPathNode();
		endNode.setResponsibilityName("End");
		path.getEndNodes().add(endNode);
		return path;
	}
	
	/**
	 * Get the path by id.
	 * 
	 * @param pathId The path id.
	 * @return Return a <code>Path</code>.
	 */
	public Path getPath(int pathId) {
		Path path = createInitialPath();
		PathNode lastNode = (PathNode) path.getStartNodes().get(0);
		List<String> responsibilities = getExecutedResponsibilities();
		for (String responsibility : responsibilities) {
			PathNode actualNode = UcmModelFactory.eINSTANCE.createPathNode();
			actualNode.setAsociatedComponent(getAsociatedComponent(responsibility));
			actualNode.setResponsibilityName(responsibility);
			path.getNodes().add(actualNode);
			lastNode.addNext(actualNode);
			lastNode = actualNode;
		}
		lastNode.addNext((PathNode) path.getEndNodes().get(0));
		return path;
	}
	
	/**
	 * Get a list of executed nodes.
	 * 
	 * @return Returns the list of executed nodes.
	 */
	public List<Path> getExecutionPaths() {
		List<Path> paths = new ArrayList<Path>();
		
		//TODO(nfrontini) iterate over all differents paths.
		int pathId = 0;
		paths.add(getPath(pathId));
		return paths;
	}
	
	/**
	 * Evaluate the specified query.
	 * 
	 * @param query The query to be evaluated.
	 * @return Returns a <code>ResultSet</code> after evaluate the query.
	 */
	private ResultSet evaluateQuery(Query query) {
		try {
			return queryEngine.evaluateQuery(query);
		} catch (DrarchEngineModelException e) {
			logger.error("DrarchEngineModelException catched when trying to execute " +
					"queryEngine.evaluateQuery in method getResultQuery of LoadModelAction class.", e);
			return EngineModelFactory.INSTANCE.createEmptyResultSet();
		}
	}
}
