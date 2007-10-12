package org.design.drarch.diagram.trace.uiAction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.design.drarch.diagram.trace.LogQueryFactory;
import org.design.drarch.diagram.trace.LogSearcher;
import org.design.drarch.diagram.trace.logModel.InnerTag;
import org.design.drarch.diagram.trace.logModel.PropertyLogNode;
import org.design.drarch.diagram.trace.logModel.Responsibility;
import org.design.drarch.diagram.trace.logModel.TagLogNode;
import org.design.rules4Java.engine.coreEngine.engineModel.KnowledgeBase;
import org.design.rules4Java.engine.coreEngine.engineModel.QueryEngine;
import org.design.rules4Java.engine.coreEngine.engineModel.jqueryImpl.QueryEngineImpl;
import org.design.rules4Java.engine.exceptions.DrarchEngineModelException;
import org.isistan.flabot.engine.executionstate.TraceLogManager;
import org.isistan.flabot.trace.log.TraceLog;

/**
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public class LoadLogFactsAction {

	private List<Responsibility> responsibilities;

	private LogSearcher searcher;

	private List<String> factsList;

	private LogQueryFactory predicateFactory = LogQueryFactory.getInstance();

	private TraceLog log;

	private KnowledgeBase knowledgeBase;

	private QueryEngine queryEngine;

	public LoadLogFactsAction(QueryEngine theQueryEngine, KnowledgeBase base) {
		log = TraceLogManager.getDefault().loadLog();
		searcher = new LogSearcher(log);
		factsList = new ArrayList<String>();
		knowledgeBase = base;
		queryEngine = theQueryEngine;
	}

	@SuppressWarnings("unchecked")
	// TODO rename -> loadLogFacts
	public void run() throws DrarchEngineModelException {
		responsibilities = searcher.getResponsibilities();
		for (Responsibility responsibility : responsibilities) {
//			String s = predicateFactory
//					.createResponsibilityPredicate(responsibility.getName());
//			factsList.add(s);
			
			/* Get all materializations (clases, methods) mepped 
			 * to the executed responsibility.
			 */
			List<InnerTag> materializations = responsibility.getExecutions();
			for (InnerTag materialization : materializations) {
				String execId = ((PropertyLogNode) materialization.getTags().get(
						LogSearcher.EXEC_ID_PROPERTY)).getValue();
				
				TagLogNode valueTag = (TagLogNode) materialization.getTags().get(
						LogSearcher.VALUE_TAG);
				InnerTag valueInnerTag = searcher.getTagLogNodeInfo(valueTag);
				String exitValue = ((PropertyLogNode) valueInnerTag.getTags()
						.get("string")).getValue();				
				if (exitValue != null) {
					exitValue = "emptyValue";
				}
				factsList.add(predicateFactory.createExecutionPredicate(
						responsibility.getName(), execId, exitValue));
				
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
				factsList.add(predicateFactory.createExecutedMethodValue(className + "." + 
						methodName, execId, exitValue));
			}
		}
		
		for (String fact : factsList) {
			knowledgeBase.addFact(fact);
		}
		((QueryEngineImpl) queryEngine).getWorkingSetNode().reloadRules();
	}
}
