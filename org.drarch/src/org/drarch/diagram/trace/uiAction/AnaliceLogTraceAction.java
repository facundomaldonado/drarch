package org.drarch.diagram.trace.uiAction;

import org.apache.log4j.Logger;
import org.drarch.engine.ruleEngine.KnowledgeBase;
import org.drarch.engine.ruleEngine.QueryEngine;
import org.drarch.engine.stepEngine.Phase;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PlatformUI;
import org.isistan.flabot.trace.log.TraceLog;

/**
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public class AnaliceLogTraceAction {

	Logger log = Logger.getLogger(AnaliceLogTraceAction.class.getName());

	private KnowledgeBase knowledgeBase;
	private QueryEngine queryEngine;
	private TraceLog traceLog;
	private Phase phase;
	
  public AnaliceLogTraceAction(QueryEngine theQueryEngine, KnowledgeBase theBase, Phase thePhase) {
	  super();
	  knowledgeBase = theBase;
	  queryEngine = theQueryEngine;
	  traceLog = null;
	  phase = thePhase;
  }
  
  public AnaliceLogTraceAction(QueryEngine theQueryEngine, KnowledgeBase theBase, TraceLog theTraceLog, Phase thePhase) {
	  super();
	  knowledgeBase = theBase;
	  queryEngine = theQueryEngine;
	  traceLog = theTraceLog;
	  phase = thePhase;
  }

  public void run() {
	  log.debug("Start analize trace log.");
	  LogFacts loadLogFacts;
	    if (traceLog == null) {
	    	loadLogFacts = new LogFacts(queryEngine, knowledgeBase, phase);
	    } else {
	    	loadLogFacts = new LogFacts(queryEngine, knowledgeBase, traceLog, phase);
	    }
	    try {
		    loadLogFacts.load();
			log.debug("End analize trace log.");
	    } catch (RuntimeException e1) {
	    	log.error(e1);
	    	MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), 
	    			"Drarch", "You have to be executed a phase.");
    }
  }
}
