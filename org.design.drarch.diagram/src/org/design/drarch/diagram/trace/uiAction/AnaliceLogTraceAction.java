package org.design.drarch.diagram.trace.uiAction;

import org.design.drarch.diagram.flabot.DiagramManager;
import org.design.rules4Java.engine.coreEngine.engineModel.KnowledgeBase;
import org.design.rules4Java.engine.coreEngine.engineModel.QueryEngine;
import org.design.rules4Java.engine.exceptions.DrarchEngineModelException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PlatformUI;
import org.isistan.flabot.trace.log.TraceLog;

/**
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public class AnaliceLogTraceAction {

	private KnowledgeBase knowledgeBase;
	private QueryEngine queryEngine;
	private TraceLog traceLog;
	
  public AnaliceLogTraceAction(QueryEngine theQueryEngine, KnowledgeBase theBase) {
	  super();
	  knowledgeBase = theBase;
	  queryEngine = theQueryEngine;
	  traceLog = null;
  }
  
  public AnaliceLogTraceAction(QueryEngine theQueryEngine, KnowledgeBase theBase, TraceLog theTraceLog) {
	  super();
	  knowledgeBase = theBase;
	  queryEngine = theQueryEngine;
	  traceLog = theTraceLog;
  }

  public void run() {
	  LogFacts loadLogFacts;
    if (traceLog == null) {
    	loadLogFacts = new LogFacts(queryEngine, knowledgeBase);
    } else {
    	loadLogFacts = new LogFacts(queryEngine, knowledgeBase, traceLog);
    }
    try {
    	DiagramManager.getInstance().getCore() ;
	    loadLogFacts.load();
    } catch (DrarchEngineModelException e) {
    	//TODO add exception handler
	    e.printStackTrace();
    } catch (RuntimeException e1) {
    	MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), 
    			"Drarch", "You have to be executed a phase.");
    }
  }
}
