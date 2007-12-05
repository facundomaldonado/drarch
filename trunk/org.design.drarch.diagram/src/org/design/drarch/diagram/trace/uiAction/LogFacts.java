
package org.design.drarch.diagram.trace.uiAction;

import java.util.ArrayList;
import java.util.List;

import org.design.drarch.diagram.trace.LogQueryFactory;
import org.design.drarch.diagram.trace.LogSearcher;
import org.design.drarch.diagram.trace.logModel.InnerTag;
import org.design.drarch.diagram.trace.logModel.LogNode;
import org.design.drarch.diagram.trace.logModel.PropertyLogNode;
import org.design.drarch.diagram.trace.logModel.Responsibility;
import org.design.drarch.diagram.trace.logModel.TagLogNode;
import org.design.rules4Java.engine.coreEngine.engineModel.KnowledgeBase;
import org.design.rules4Java.engine.coreEngine.engineModel.QueryEngine;
import org.design.rules4Java.engine.coreEngine.engineModel.jqueryImpl.QueryEngineImpl;
import org.design.rules4Java.engine.exceptions.DrarchEngineModelException;
import org.isistan.flabot.engine.executionstate.TraceLogManager;
import org.isistan.flabot.trace.log.TraceLog;

/** Crea los hechos correspondientes del log en la knwoledgebase.
 * 
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public class LogFacts {

	/** Define la profundidad del analisis de la informaciotn del log.
	 */
	private static final int MAX_DEPTH_LOG = 5;

	private List<Responsibility> responsibilities;

	private LogSearcher searcher;

	private List<String> factsList;

	private LogQueryFactory predicateFactory = LogQueryFactory.getInstance();

	private TraceLog log;

	private KnowledgeBase knowledgeBase;

	private QueryEngine queryEngine;

	public LogFacts(QueryEngine theQueryEngine, KnowledgeBase theKnowledgeBase) {
		log = TraceLogManager.getDefault().loadLog();
		searcher = new LogSearcher(log);
		factsList = new ArrayList<String>();
		knowledgeBase = theKnowledgeBase;
		queryEngine = theQueryEngine;
	}
	
	public LogFacts(QueryEngine theQueryEngine, KnowledgeBase theKnowledgeBase, TraceLog theLog) {
		log = theLog;
		searcher = new LogSearcher(log);
		factsList = new ArrayList<String>();
		knowledgeBase = theKnowledgeBase;
		queryEngine = theQueryEngine;
	}

	public void load() throws DrarchEngineModelException {
		responsibilities = searcher.getResponsibilities();
		for (Responsibility responsibility : responsibilities) {

			/* Get all materializations (clases, methods) mepped 
			 * to the executed responsibility.
			 */
			List<InnerTag> materializations = responsibility.getExecutions();
			for (InnerTag materialization : materializations) {
				String execId = ((PropertyLogNode) materialization.getTags().get(
						LogSearcher.EXEC_ID_PROPERTY)).getValue();
				
				TagLogNode valueTag = (TagLogNode) materialization.getTags().get(
						LogSearcher.VALUE_TAG);
				
				// Get the snapshot object.
				snapshotFacts(valueTag, execId, 0);
				
				InnerTag valueInnerTag = searcher.getTagLogNodeInfo(valueTag);
				String exitValue = "void";
				PropertyLogNode propertyLogNode = (PropertyLogNode) valueInnerTag.getTags().get("string");
				if (propertyLogNode != null) {
					exitValue = propertyLogNode.getValue();	
				}
//				factsList.add(predicateFactory.createExecutionPredicate(
//						responsibility.getName(), execId, exitValue));
				
				TagLogNode behaviorTag = (TagLogNode) materialization.getTags().get(
						LogSearcher.BEHAVIOR_TAG);
				InnerTag behavior = searcher.getTagLogNodeInfo(behaviorTag);
				String methodName = ((PropertyLogNode) behavior.getTags()
						.get(LogSearcher.ALL_NAME_PROPERTY)).getValue();
				TagLogNode declaringClassTag = (TagLogNode) behavior.getTags().get(
						LogSearcher.BEHAVIOR_DECLARING_CLASS_TAG);
				InnerTag declaringClass = searcher.getTagLogNodeInfo(declaringClassTag);
				String className = ((PropertyLogNode) declaringClass.getTags()
						.get(LogSearcher.ALL_NAME_PROPERTY)).getValue();
				
				TagLogNode argumentsTag = (TagLogNode) materialization.getTags().get(
						LogSearcher.ARGUMENTS_TAG);
				String argumentName = "null";
				if (!argumentsTag.getTag().getTags().isEmpty()) {
					
					// TODO: Recuperar el resto de los argumentos!!!
					TagLogNode argumentTag = (TagLogNode) argumentsTag.getChildrens()[1];
					InnerTag argument = searcher.getTagLogNodeInfo(argumentTag);
					PropertyLogNode argumentPropertyLogNode = (PropertyLogNode) argument.getTags().get("string");
					if (argumentPropertyLogNode != null) {
					  argumentName = argumentPropertyLogNode.getValue();
					}  
				}
				factsList.add(predicateFactory.createExecutedMethodValue(className + "." + 
						methodName, execId, exitValue, argumentName));
			}
		}
		
		for (String fact : factsList) {
			knowledgeBase.addFact(fact);
		}
		((QueryEngineImpl) queryEngine).getWorkingSetNode().reloadRules();
	}

	private void snapshotFacts(TagLogNode valueTag, String id, int depth) {
		if (valueTag != null) {
			for (LogNode logNode : valueTag.getChildrens()) {
				if (logNode instanceof PropertyLogNode) {
					PropertyLogNode propertyLogNode = (PropertyLogNode) logNode;
					String dirtyField = propertyLogNode.getName();
					if (dirtyField.contains("#")) {
						String field = dirtyField.substring(0, dirtyField.indexOf(":")).replace("#", ".");
						String fact = "snapshot(" + id + ", '" + field + "', '" +  propertyLogNode.getValue() + "').";
						factsList.add(fact);
					}
				}
  				if (logNode instanceof TagLogNode) {
					TagLogNode tagLogNode = (TagLogNode) logNode;
					if (depth < MAX_DEPTH_LOG) {
						snapshotFacts(tagLogNode, id, depth + 1);
					}
				}
			}
		}
	}
}
