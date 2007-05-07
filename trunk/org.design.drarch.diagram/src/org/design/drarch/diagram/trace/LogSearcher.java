package org.design.drarch.diagram.trace;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.design.drarch.diagram.DiagramModel.ucmModel.generator.NodeInfo;
import org.design.drarch.diagram.flabot.DiagramManager;
import org.design.drarch.diagram.trace.logModel.InnerTag;
import org.design.drarch.diagram.trace.logModel.LogModelFactory;
import org.design.drarch.diagram.trace.logModel.LogNode;
import org.design.drarch.diagram.trace.logModel.Responsibility;
import org.design.drarch.diagram.trace.logModel.TagLogNode;
import org.isistan.flabot.coremodel.CoreModel;
import org.isistan.flabot.launcher.trace.TagUtil;
import org.isistan.flabot.trace.log.LogContext;
import org.isistan.flabot.trace.log.Tag;
import org.isistan.flabot.trace.log.TraceLog;

/**
 * 
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public class LogSearcher {

  public static String BEHAVIOR_TAG = "BEHAVIOR";
  public static String INSTANCE_TAG = "INSTANCE";
  public static String ARGUMENTS_TAG = "ARGUMENTS";
  public static String VALUE_TAG = "VALUE";
  public static String EVENT_TYPE_PROPERTY = "eventType";
  public static String EXIT_EVENT_TYPE_PROPERTY = "exitEventType";
  public static String EXEC_ID_PROPERTY = "executionId";
  public static String TIME_PROPERTY = "timeStamp";
  public static String ALL_NAME_PROPERTY = "name";
  public static String BEHAVIOR_DECLARING_CLASS_TAG = "declaringClass";
  public static String OBJECT_TAG = "object";
  public static String OBJECT_ID_PROPERTY = "id";
  private TraceLog log;
  private List responsibilities = new LinkedList();
  private CoreModel coreModel;

  public LogSearcher(TraceLog log) {
    this.log = log;
    coreModel = DiagramManager.getInstance().getCore();
  }

  public TraceLog getLog() {
    return log;
  }

  public TagLogNode getRootNode() {
    Tag rootTag = getRoot(log);
    TagLogNode rootNode = LogModelFactory.eINSTANCE.createTagLogNode();
    rootNode.setName("ROOT");
    rootNode.setTag(rootTag);
    return rootNode;
  }

  public Tag getRoot(TraceLog traceLog) {
    Tag root = TagUtil.createTag();
    Tag log = TagUtil.createChildTag(root, "log", true);
    Tag contexts = TagUtil.createChildTag(root, "contexts", true);
    int index = 0;
    for (Iterator iter = traceLog.getTags().iterator(); iter.hasNext();) {
      Tag tag = (Tag) iter.next();
      TagUtil.addChildTag(log, tag, Integer.toString(index++), false);
    }
    for (Iterator iter = traceLog.getContexts().iterator(); iter.hasNext();) {
      LogContext context = (LogContext) iter.next();
      Tag contextTag = TagUtil
          .createChildTag(contexts, context.getName(), true);
      int contextIndex = 0;
      for (Iterator tagIter = context.getTags().iterator(); tagIter.hasNext();) {
        Tag tag = (Tag) tagIter.next();
        TagUtil.addChildTag(contextTag, tag, Integer.toString(contextIndex++),
            false);
      }
    }
    // TagTreeModel.show("Trace log","trace log", root);
    return root;
  }

  @SuppressWarnings("unchecked")
  public void make() {
    TagLogNode node = getRootNode();
    LogNode[] nodesChild = node.getChildrens();

    LogNode contexts = nodesChild[0];

    LogNode[] responsabilidades = ((TagLogNode) contexts).getChildrens();
    for (int i = 0; i < responsabilidades.length; i++) {
      Responsibility r = LogModelFactory.eINSTANCE.createResponsibility();
      String respName = ((TagLogNode) responsabilidades[i]).getName();
      System.out.println(respName);
      String rress = ((org.isistan.flabot.coremodel.Responsibility) coreModel
          .getResponsibilities().get(i)).getName();
      System.out.println(rress);
      r.setName(rress);

      LogNode[] executions = ((TagLogNode) responsabilidades[i]).getChildrens();
      for (int k = 0; k < executions.length; k++) {
        LogNode[] mater_info = ((TagLogNode) executions[k]).getChildrens();
        InnerTag m = LogModelFactory.eINSTANCE.createInnerTag();
        for (int j = 0; j < mater_info.length; j++) {
          LogNode info = (LogNode) mater_info[j];
          m.getTags().put(info.getName(), info);
        }
        r.getExecutions().add(m);
      }
      responsibilities.add(r);
    }
  }

  public List getResponsibilities() {
    make();
    return responsibilities;
  }

  public InnerTag getTagLogNodeInfo(TagLogNode logNode) {
    InnerTag infoInnerTag = LogModelFactory.eINSTANCE.createInnerTag();
    LogNode[] childs = logNode.getChildrens();
    for (int j = 0; j < childs.length; j++) {
      LogNode info = (LogNode) childs[j];
      infoInnerTag.getTags().put(info.getName(), info);
    }
    return infoInnerTag;
  }

  public static class NumberKeyComparator implements Comparator {

    @SuppressWarnings("unchecked")
    public int compare(Object o1, Object o2) {
      if (o1 instanceof NodeInfo && o2 instanceof NodeInfo) {
        try {
          int value1 = Integer.parseInt(((NodeInfo) o1).execId);
          int value2 = Integer.parseInt(((NodeInfo) o2).execId);
          return value1 - value2;
        } catch (NumberFormatException nfe) {
        }
      }
      if (o1 instanceof Comparable && o2 instanceof Comparable) {
        return ((Comparable) o1).compareTo((Comparable) o2);
      }
      return 0;
    }
  }
}
