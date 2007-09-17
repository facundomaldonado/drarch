package org.design.drarch.diagram.trace.action;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.design.drarch.diagram.trace.LogQueryFactory;
import org.design.drarch.diagram.trace.LogSearcher;
import org.design.drarch.diagram.trace.logModel.InnerTag;
import org.design.drarch.diagram.trace.logModel.PropertyLogNode;
import org.design.drarch.diagram.trace.logModel.Responsibility;
import org.design.drarch.diagram.trace.logModel.TagLogNode;
import org.design.rules4Java.engine.engine.engineModel.KnowledgeBase;
import org.design.rules4Java.engine.engine.engineModel.QueryEngine;
import org.design.rules4Java.engine.jqueryImpl.engineModel.QueryEngineImpl;
import org.design.rules4Java.util.Util;
import org.eclipse.jface.action.Action;
import org.isistan.flabot.engine.executionstate.TraceLogManager;
import org.isistan.flabot.trace.log.TraceLog;

/**
 * 
 * 
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public class LoadLogFactsAction extends Action {
  private List responsibilities;
  private LogSearcher searcher;
  private List factsList;
  private LogQueryFactory predicateFactory = LogQueryFactory.getInstance();
  private TraceLog log;
  private KnowledgeBase base;
  private QueryEngine queryEngine;

  public LoadLogFactsAction() {
    log = TraceLogManager.getDefault().loadLog();
    searcher = new LogSearcher(log);
    factsList = new LinkedList();

    base = Util.getInstance().getBase();
    queryEngine = Util.getInstance().getQueryEngine();
  }

  @SuppressWarnings("unchecked")
  public void run() {
    responsibilities = searcher.getResponsibilities();
    for (Iterator ir = responsibilities.iterator(); ir.hasNext();) {
      Responsibility res = (Responsibility) ir.next();

      String s = predicateFactory.createResponsibilityPredicate(res.getName());
      factsList.add(s);
      List materializations = (res).getExecutions();
      for (Iterator im = materializations.iterator(); im.hasNext();) {

        InnerTag mat = (InnerTag) im.next();
        String execId = ((PropertyLogNode) mat.getTags().get(
            LogSearcher.EXEC_ID_PROPERTY)).getValue();
        s = predicateFactory.createExecutionPredicate(res.getName(), execId);
        factsList.add(s);
        {
          TagLogNode behTag = (TagLogNode) mat.getTags().get(
              LogSearcher.BEHAVIOR_TAG);
          InnerTag behavior = searcher.getTagLogNodeInfo(behTag);
          String behavior_name = ((PropertyLogNode) behavior.getTags().get(
              LogSearcher.ALL_NAME_PROPERTY)).getValue();
          s = predicateFactory.createExecutionMethodPredicate(execId,
              behavior_name);
          factsList.add(s);
          {
            TagLogNode decClassTag = (TagLogNode) behavior.getTags().get(
                LogSearcher.BEHAVIOR_DECLARING_CLASS_TAG);
            InnerTag declaringClass = searcher.getTagLogNodeInfo(decClassTag);
            {
              String className = ((PropertyLogNode) declaringClass.getTags()
                  .get(LogSearcher.ALL_NAME_PROPERTY)).getValue();
              s = predicateFactory.createExecutionDeclaredClass(execId,
                  className);
              factsList.add(s);
            }
          }
        }
      }
    }
    for (Iterator i = factsList.iterator(); i.hasNext();) {
      String fact = (String) i.next();
      System.out.println(fact);
      base.addFact(fact);
    }
    ((QueryEngineImpl) queryEngine).getWorkingSetNode().reloadRules();
  }
}
