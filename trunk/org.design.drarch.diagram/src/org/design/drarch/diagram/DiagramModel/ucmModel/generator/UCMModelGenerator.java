package org.design.drarch.diagram.DiagramModel.ucmModel.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.design.drarch.diagram.DiagramModel.componentModel.Component;
import org.design.drarch.diagram.DiagramModel.ucmModel.ComponentRole;
import org.design.drarch.diagram.DiagramModel.ucmModel.Path;
import org.design.drarch.diagram.DiagramModel.ucmModel.PathNode;
import org.design.drarch.diagram.DiagramModel.ucmModel.UCMModel;
import org.design.drarch.diagram.DiagramModel.ucmModel.UcmModelFactory;
import org.design.drarch.diagram.flabot.DiagramManager;
import org.design.drarch.diagram.trace.LogQueryFactory;
import org.design.drarch.diagram.trace.LogSearcher;
import org.design.rules4Java.engine.engine.engineModel.QueryEngine;
import org.design.rules4Java.engine.engine.engineModel.QueryResult;
import org.design.rules4Java.engine.engine.engineModel.ResultSet;
import org.design.rules4Java.engine.ruleModel.Query;
import org.design.rules4Java.engine.ruleModel.RuleModelFactory;
import org.design.rules4Java.engine.ruleModel.Var;
import org.design.rules4Java.util.Util;

@SuppressWarnings("unchecked")
public class UCMModelGenerator {

  private UCMModel model;
  private UCMModel alternativeModel;
  private QueryEngine queryEngine;
  private List<NodeInfo> sortedNodes = new ArrayList();
  private Path path;
  private Path alternativePath;
  private List<ComponentRole> componentRoles = new ArrayList();
  
  // componentName - list<className>.
  private Map<String,List<String>> comp_class = new HashMap(); 
  
  // className - list<methodName>
  private Map<String,List<String>> class_meth = new HashMap(); 

  // responsabilityName, componentName.
  private Map<String,String> resp_comp = new HashMap();

  @SuppressWarnings("unchecked")
  public UCMModelGenerator(){
    model= UcmModelFactory.eINSTANCE.createUCMModel();
    alternativeModel= UcmModelFactory.eINSTANCE.createUCMModel();
    queryEngine=Util.getInstance().getQueryEngine();
  }

  @SuppressWarnings("unchecked")
  private List<NodeInfo> getNodesFromBase() {
    
    // Recupero las responsabilidades.
    ResultSet responsibilityQueryResult=getResponsibilities();
    while (responsibilityQueryResult.hasMoreElements()){
      QueryResult r_result = responsibilityQueryResult.next();
      String responsibilityName=r_result.getValueOfVar("?R");
      ResultSet executionQueryResult = getExecutions(responsibilityName);
      while (executionQueryResult.hasMoreElements()){
        QueryResult e_result=executionQueryResult.next();
        String execId=e_result.getValueOfVar("?I");

        // Para ese id recupero toda la info.
        String methodName = getExecutedMethod(execId);

        // Clase que lo declara.
        String className = getExecutedClass(execId);
        NodeInfo node = new NodeInfo(responsibilityName, execId, className, 
            methodName);
        sortedNodes.add(node);
      }
    }
    return sortedNodes;
  }
  
  private void getComponentRoles() {
    List<String> responsibilities=getResponsibilites();
    for(Iterator<String> i = responsibilities.iterator(); i.hasNext(); ) {
      String resp = i.next();
      Component component= getComponent(resp);
      if (component!=null){
        ComponentRole compRole=UcmModelFactory.eINSTANCE.createComponentRole();
        compRole.setComponent(component);
        compRole.setName(component.getName());
        componentRoles.add(compRole);
        resp_comp.put(resp,compRole.getName());
      }
    }
  }
  
  private void getPathNodes(List<NodeInfo> nodes) {
    path = UcmModelFactory.eINSTANCE.createPath();
    alternativePath = UcmModelFactory.eINSTANCE.createPath();
    PathNode startNode = UcmModelFactory.eINSTANCE.createPathNode();
    startNode.setResponsibilityName("Start");
    path.getStartNodes().add(startNode);
    PathNode startAlternativeNode = UcmModelFactory.eINSTANCE.createPathNode();
    startAlternativeNode.setResponsibilityName("Start");
    alternativePath.getStartNodes().add(startAlternativeNode);
    PathNode endNode=UcmModelFactory.eINSTANCE.createPathNode();
    endNode.setResponsibilityName("End");
    path.getEndNodes().add(endNode);
    PathNode endAlternativeNode=UcmModelFactory.eINSTANCE.createPathNode();
    endAlternativeNode.setResponsibilityName("End");
    alternativePath.getEndNodes().add(endAlternativeNode);

    PathNode lastNode = startNode;
    PathNode lastAlternativeNode = startAlternativeNode;

    for (Iterator<NodeInfo> i = nodes.iterator(); i.hasNext(); ) {
      NodeInfo node = i.next();

      String componentName = resp_comp.get(node.responsibilityName);
      if (comp_class.containsKey(componentName)) {
        comp_class.get(componentName).add(node.className);
      } else {
        comp_class.put(componentName,new ArrayList());
        comp_class.get(componentName).add(node.className);
      }
      if (class_meth.containsKey(node.className)) {
        class_meth.get(node.className).add(node.methodName);
      } else {
        class_meth.put(node.className,new ArrayList());
        class_meth.get(node.className).add(node.methodName);
      }
      
      // Voy obteniendo las ejecuciones en orden.
      if (!(lastNode.getResponsibilityName().equals(componentName))) {
        PathNode actualNode = UcmModelFactory.eINSTANCE.createPathNode();
        actualNode.setResponsibilityName(componentName);
        path.getNodes().add(actualNode);
        lastNode.addNext(actualNode);
        lastNode = actualNode;
      }
      int index = (node.className).lastIndexOf(".");
      String className = node.className;
      lastNode.addComment(" - " + className.substring(index+1, className
          .length()) + "->" + node.methodName + "\n");
      lastAlternativeNode.addComment(" - " + className.substring(index + 1, 
          className.length()) + "->" + node.methodName + "\n");
      PathNode actualAlternativeNode = UcmModelFactory.eINSTANCE
          .createPathNode();
      actualAlternativeNode.setResponsibilityName(componentName);
      alternativePath.getNodes().add(actualAlternativeNode);
      lastAlternativeNode.addNext(actualAlternativeNode);
      lastAlternativeNode = actualAlternativeNode;
    }
    lastNode.addNext(endNode);
    lastAlternativeNode.addNext(endAlternativeNode);
  }

  private void processNodes(List<NodeInfo> nodes) {
    
    // Creo los Components roles.
    getComponentRoles();
    
    // Creo los pathNodes.
    getPathNodes(nodes);
  }
  
  public void make(){
    getNodesFromBase();
    List nodes = getNodes();
    
    // Ordenada por id de ejecucion.
    processNodes(nodes);
    model = UcmModelFactory.eINSTANCE.createUCMModel();
    model.getPaths().add(path);
    alternativeModel = UcmModelFactory.eINSTANCE.createUCMModel();
    alternativeModel.getPaths().add(alternativePath);
    for(Iterator i=componentRoles.iterator();i.hasNext();) {
      ComponentRole componentRole = (ComponentRole) i.next();
      model.getComponentRoles().add(componentRole);
      alternativeModel.getComponentRoles().add(componentRole);
    }
    getNodes();
  }

  public UCMModel getModel(){
    model.setName("UCM - 2");
    return model;
  }

  public UCMModel getAlternativeModel() {
    alternativeModel.setName("UCM - 1");
    return alternativeModel;
  }
  
  public List<String> getResponsibilites() {
    List<String> responsibilities = new ArrayList();
    ResultSet respResultSet = getResponsibilities();
    while(respResultSet.hasMoreElements()) {
      QueryResult q_r = respResultSet.next();
      responsibilities.add(q_r.getValueOfVar("?R"));
    }
    return responsibilities;	
  }

  public ResultSet getResponsibilities() {
    Query q = RuleModelFactory.eINSTANCE.createQuery();
    q.setQueryString( LogQueryFactory.DEFAULT_RESPONSIBILITY_QUERY );
    Var v1 = RuleModelFactory.eINSTANCE.createVar();
    v1.setVarText("?R");
    q.getChosenVars().add(v1);
    
    // Recupero las responsabilidades.
    ResultSet responsibilityQueryResult = queryEngine.evaluateQuery(q);
    return responsibilityQueryResult;
  }

  public ResultSet getExecutions(String responsibilityName) {
    Query q = RuleModelFactory.eINSTANCE.createQuery();
    String queryString = LogQueryFactory.DEFAULT_EXECUTION_QUERY ;
    queryString = queryString.replace("?R",responsibilityName);
    q.setQueryString( queryString );
    Var v = RuleModelFactory.eINSTANCE.createVar();
    v.setVarText("?I");
    q.getChosenVars().add(v);
    ResultSet executionQueryResult = queryEngine.evaluateQuery(q);
    return executionQueryResult;
  }

  public String getExecutedMethod(String execId){
    Query q = RuleModelFactory.eINSTANCE.createQuery();
    String queryString = LogQueryFactory.DEFAULT_METHOD_QUERY;
    queryString = queryString.replace("?I",execId);
    q.setQueryString( queryString );
    Var v = RuleModelFactory.eINSTANCE.createVar();
    v.setVarText("?M");
    q.getChosenVars().add(v);
    ResultSet methodQueryResult = queryEngine.evaluateQuery(q);
    QueryResult m_result = methodQueryResult.next();
    String methodName = m_result.getValueOfVar("?M");
    return methodName;
  }

  public String getExecutedClass(String execId){
    Query q = RuleModelFactory.eINSTANCE.createQuery();
    String queryString=LogQueryFactory.DEFAULT_CLASS_QUERY;
    queryString = queryString.replace("?I",execId);
    q.setQueryString( queryString );
    Var v = RuleModelFactory.eINSTANCE.createVar();
    v.setVarText("?C");
    q.getChosenVars().add(v);
    ResultSet classQueryResult = queryEngine.evaluateQuery(q);
    QueryResult c_result = classQueryResult.next();
    String className = c_result.getValueOfVar("?C");
    return className;
  }

  public Component getComponent(String responsibility) {
    String componentName = "";
    // Recupero el nombre del componente asociado de la base de datos.
    Query q = RuleModelFactory.eINSTANCE.createQuery();
    q.setQueryString(LogQueryFactory.getInstance()
        .createComponentResponsibilityAssociationQuery(responsibility));
    Var v = RuleModelFactory.eINSTANCE.createVar();
    v.setVarText("?C");
    q.getChosenVars().add(v);
    ResultSet result = queryEngine.evaluateQuery(q);
    while (result.hasMoreElements()) {
      QueryResult qr = result.next();
      componentName = qr.getValueOfVar("?C");
    }
    if(!componentName.equals("") || componentName != null) {
      Component component = DiagramManager.getInstance().getCurrentModel()
          .getComponent(componentName);
      if (component != null) {
        return component;
      }
    }
    return null;
  }

  @SuppressWarnings("unused")
  private String getClassName(String className) {
    int lastIndex=className.lastIndexOf(".");
    return className.substring(lastIndex + 1, className.length());
  }

  @SuppressWarnings("unused")
  private String getPackageName(String className) {
    int lastIndex = className.lastIndexOf(".");
    int index=className.substring(0, lastIndex).lastIndexOf(".");
    if (index > 0) {
      String comp=className.substring(index + 1, lastIndex);
      if (comp.toLowerCase().equals(comp)){
        // Es paquete.
        return className.substring(0,index);
      } else { 
        // Es clase interna.
        return getPackageName(className.substring(0, index));
      }
    }
    return className.substring(0, lastIndex);
  }
  
  /**
   * retorna la lista ordenada de ejecuciones, por id de ejecucion
   * @return lista de NodeInfo
   */
  public List<NodeInfo> getNodes() {
    Collections.sort(sortedNodes, new LogSearcher.NumberKeyComparator());
    return sortedNodes;
  }
}