package org.design.drarch.diagram.flabot.ucm;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.design.drarch.diagram.DiagramModel.ucmModel.PathNode;
import org.design.drarch.diagram.flabot.DiagramManager;
import org.isistan.flabot.coremodel.ComponentModel;
import org.isistan.flabot.coremodel.ComponentRole;
import org.isistan.flabot.coremodel.CoreModel;
import org.isistan.flabot.coremodel.CoremodelFactory;
import org.isistan.flabot.coremodel.Note;
import org.isistan.flabot.coremodel.Path;
import org.isistan.flabot.coremodel.Responsibility;
import org.isistan.flabot.coremodel.ResponsibilityNode;
import org.isistan.flabot.coremodel.SimplePathNode;
import org.isistan.flabot.coremodel.UseCaseMap;
import org.isistan.flabot.edit.editormodel.ConnectionVisualModel;
import org.isistan.flabot.edit.editormodel.Dimension;
import org.isistan.flabot.edit.editormodel.EditormodelFactory;
import org.isistan.flabot.edit.editormodel.NodeVisualModel;
import org.isistan.flabot.edit.editormodel.Point;
import org.isistan.flabot.edit.ucmmodel.UCMDiagram;
import org.isistan.flabot.edit.ucmmodel.UcmmodelFactory;

/**
 * 
 * 
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public class UCMDiagrams {
  private CoreModel coreModel;
  private UCMDiagram diagram;
  private String diagramName;
  private Hashtable<String, String> notes;

  @SuppressWarnings("unchecked")
  public UCMDiagrams(String name) {

    this.diagramName = name;
    coreModel = DiagramManager.getInstance().getCoreModel();

    diagram = UcmmodelFactory.eINSTANCE.createUCMDiagram();
    diagram.setName(name);

    UseCaseMap map = CoremodelFactory.eINSTANCE.createUseCaseMap();
    map.setName(diagram.getName());
    diagram.setMap(map);

    diagram.setCoreModel(coreModel);

    coreModel.getUseCaseMaps().add(map);

    notes = new Hashtable<String, String>();

  }

  public String getName() {
    return this.diagramName;
  }

  public UCMDiagram getDiagram() {
    diagram.setCoreModel(coreModel);
    return diagram;
  }

  public ComponentModel getComponentModel(String name) {
    ComponentModel componentModel;
    for (int i = 0; i < coreModel.getComponents().size(); i++) {
      componentModel = (ComponentModel) coreModel.getComponents().get(i);
      if (componentModel.getName().equals(name)) return componentModel;
    }
    return null;
  }

  private ComponentRole getRole(String roleName) {
    ComponentRole componentRole;

    for (int i = 0; i < diagram.getMap().getComponentRoles().size(); i++) {
      componentRole = (ComponentRole) diagram.getMap().getComponentRoles().get(
          i);
      if (componentRole.getName().equals(roleName)) return componentRole;
    }
    return null;
  }

  @SuppressWarnings("unchecked")
  public void createComponentRole(String componentName) {

    ComponentRole componentRole = CoremodelFactory.eINSTANCE
        .createComponentRole();
    ComponentModel component = getComponentModel(componentName);
    if (component != null) {
      componentRole.setComponent(component);
      componentRole.setName(componentName);
      diagram.getMap().getComponentRoles().add(componentRole);

      NodeVisualModel nodeVisualModel = EditormodelFactory.eINSTANCE
          .createNodeVisualModel();
      nodeVisualModel.setSemanticModel(componentRole);
      diagram.getChildren().add(nodeVisualModel);
    }
  }

//  @SuppressWarnings("unchecked")
//  public void createPath(List<List<String>> pathResponsibility) {
//    // Modelo
//    Path path = CoremodelFactory.eINSTANCE.createPath();
//    SimplePathNode startNode = CoremodelFactory.eINSTANCE
//        .createSimplePathNode();
//    startNode.setName("StartNode");
//
//    SimplePathNode endNode = CoremodelFactory.eINSTANCE.createSimplePathNode();
//    endNode.setName("EndNode");
//
//    path.getStartNodes().add(startNode);
//    path.getNodes().add(startNode);
//
//    path.getEndNodes().add(endNode);
//    path.getNodes().add(endNode);
//
//    SimplePathNode previusNode = startNode;
//    for (int i = 0; i < pathResponsibility.size(); i++) {
//      List<String> peer = (List<String>) pathResponsibility.get(i);
//
//      //TODO para que le paso un peer si tomo siempre el primer par!!!
//      previusNode = addResponsibilityNode(path, peer.get(0),
//          peer.get(1), previusNode);
//
//      if (i == (pathResponsibility.size() - 1))
//        endNode.addPrevious(previusNode);
//    }
//
//    diagram.getMap().getPaths().add(path);
//    drawNodes(path);
//    drawConnections(path);
////    createNote(path);
//  }
  
  public void createPath(org.design.drarch.diagram.DiagramModel.ucmModel.Path modelPath) {
    // Modelo
    Path path = CoremodelFactory.eINSTANCE.createPath();
    SimplePathNode startNode = CoremodelFactory.eINSTANCE
        .createSimplePathNode();
    startNode.setName("StartNode");

    SimplePathNode endNode = CoremodelFactory.eINSTANCE.createSimplePathNode();
    endNode.setName("EndNode");

    path.getStartNodes().add(startNode);
    path.getNodes().add(startNode);

    path.getEndNodes().add(endNode);
    path.getNodes().add(endNode);

    SimplePathNode previusNode = startNode;
    for (int i = 0; i < modelPath.getNodes().size(); i++) {
    	PathNode pathNode = (PathNode) modelPath.getNodes().get(i);
      PathNode pathNodePrevius = ((PathNode) pathNode.getPrevious().get(0));
      previusNode = addResponsibilityNode(path, (String) pathNode.getResponsibilityName(),
          pathNode.getAsociatedComponent().getName(), previusNode);

      if (i == (modelPath.getNodes().size() - 1))
        endNode.addPrevious(previusNode);
    }

    diagram.getMap().getPaths().add(path);
    drawNodes(path);
    drawConnections(path);
  }

  @SuppressWarnings("unchecked")
  private SimplePathNode addResponsibilityNode(Path path,
      String responsibilityName, String componentRoleName, SimplePathNode previusNode) {
    ResponsibilityNode middleNode = CoremodelFactory.eINSTANCE.createResponsibilityNode();
    middleNode.setName(responsibilityName);
    middleNode.addPrevious(previusNode);
    middleNode.setMap(diagram.getMap());
    middleNode.setResponsibility(getResponsibility(responsibilityName));
    middleNode.setRole(getRole(componentRoleName));
    path.getNodes().add(middleNode);
    return middleNode;
  }

  @SuppressWarnings("unused")
  private String getCompRoleName(String respNodeName) {
    int index = respNodeName.indexOf("_");
    if (index == -1) return respNodeName;
    return respNodeName.substring(0, index);
  }

  private NodeVisualModel getResponsibilityNode(String responsibilityID) {
    for (int i = 0; i < diagram.getChildren().size(); i++) {
      if (diagram.getChildren().get(i) instanceof NodeVisualModel) {
        NodeVisualModel nodeVisualModel = (NodeVisualModel) diagram
            .getChildren().get(i);
        for (int j = 0; j < nodeVisualModel.getChildren().size(); j++) {
          if (nodeVisualModel.getChildren().get(j) instanceof NodeVisualModel) {
            NodeVisualModel responsibilityNodeVisualModel = (NodeVisualModel) nodeVisualModel
                .getChildren().get(j);
            if (responsibilityNodeVisualModel.getSemanticModel() instanceof SimplePathNode) {
              SimplePathNode simplePathNode = (SimplePathNode) responsibilityNodeVisualModel
                  .getSemanticModel();
              if (simplePathNode.getID().equals(responsibilityID))
                return responsibilityNodeVisualModel;
            }
          }
        }
      }
    }
    return null;
  }

  @SuppressWarnings("unused")
  private SimplePathNode getNode(Path path, String nodeName) {
    for (int i = 0; i < path.getNodes().size(); i++) {
      ResponsibilityNode responsibilityNode = null;
      if (path.getNodes().get(i) instanceof ResponsibilityNode) {
        responsibilityNode = (ResponsibilityNode) path.getNodes().get(i);
        if ((responsibilityNode).getName().equals(nodeName))
          return responsibilityNode;
      }
      SimplePathNode simplePathNode = null;
      if (path.getNodes().get(i) instanceof SimplePathNode) {
        simplePathNode = (SimplePathNode) path.getNodes().get(i);
        if (simplePathNode.getName().equals(nodeName)) return simplePathNode;
      }
    }
    return null;
  }

  public Responsibility getResponsibility(String name) {
    for (int i = 0; i < coreModel.getResponsibilities().size(); i++) {
      Responsibility res = (Responsibility) coreModel.getResponsibilities()
          .get(i);
      if (res.getName().equals(name)) return res;
    }
    return null;
  }


  @SuppressWarnings("unchecked")
  private void drawNodes(Path path) {

    for (int i = 0; i < (path.getNodes().size()); i++) {
      NodeVisualModel nodeVisual = EditormodelFactory.eINSTANCE
          .createNodeVisualModel();
      SimplePathNode nodeModel = (SimplePathNode) path.getNodes().get(i);
      nodeVisual.setSemanticModel(nodeModel);

      Dimension dimension = EditormodelFactory.eINSTANCE.createDimension();
      dimension.setHeight(15);
      dimension.setWidth(15);
      nodeVisual.setSize(dimension);

      diagram.getChildren().add(nodeVisual);
    }
  }

  @SuppressWarnings("unchecked")
  private void drawConnections(Path path) {

    for (int i = 0; i < (path.getNodes().size()); i++) {
      SimplePathNode nodeModel;
      if (path.getNodes().get(i) instanceof ResponsibilityNode) {
        nodeModel = (ResponsibilityNode) path.getNodes().get(i);
      } else
        nodeModel = (SimplePathNode) path.getNodes().get(i);

      NodeVisualModel nodeVisual = getNodeVisual(nodeModel.getID());

      if (nodeModel instanceof ResponsibilityNode) {
        ComponentRole componentRoleModel = (ComponentRole) ((ResponsibilityNode) nodeModel)
            .getRole();
        NodeVisualModel componentRoleVisual = null;
        if (componentRoleModel != null)
          componentRoleVisual = getNodeVisual(componentRoleModel.getID());
        if (componentRoleVisual != null)
          componentRoleVisual.getChildren().add(nodeVisual);
      }

      for (int j = 0; j < nodeModel.uGetNext().size(); j++) {
        SimplePathNode nodeModelNext = null;
        if (nodeModel.uGetNext().get(j) instanceof ResponsibilityNode)
          nodeModelNext = (ResponsibilityNode) nodeModel.uGetNext().get(j);
        else {
          if (nodeModel.uGetNext().get(j) instanceof SimplePathNode)
            nodeModelNext = (SimplePathNode) nodeModel.uGetNext().get(j);

        }

        NodeVisualModel nodeVisualNext = getNodeVisual(nodeModelNext.getID());

        ConnectionVisualModel connectionVisualModel = EditormodelFactory.eINSTANCE
            .createConnectionVisualModel();
        connectionVisualModel.setSource(nodeVisual);
        connectionVisualModel.setTarget(nodeVisualNext);
        connectionVisualModel.setSemanticModel(path);
      }
    }
  }

  private NodeVisualModel getNodeVisual(String id) {
    for (int i = 0; i < diagram.getChildren().size(); i++) {
      if (diagram.getChildren().get(i) instanceof NodeVisualModel) {
        NodeVisualModel nodeVisual = (NodeVisualModel) diagram.getChildren()
            .get(i);
        if (nodeVisual.getSemanticModel() instanceof SimplePathNode) {
          if (((SimplePathNode) nodeVisual.getSemanticModel()).getID().equals(
              id)) return nodeVisual;
        } else {
          if (nodeVisual.getSemanticModel() instanceof ComponentRole) {
            if (((ComponentRole) nodeVisual.getSemanticModel()).getID().equals(
                id)) return nodeVisual;
          }
        }
      }
    }
    return null;
  }

  @SuppressWarnings("unchecked")
  public void createNote(Path path) {

    for (int i = 0; i < path.getNodes().size(); i++) {
      SimplePathNode simplePathNode = (SimplePathNode) path.getNodes().get(i);
      if (!(simplePathNode.getName().equals("StartNode") || (simplePathNode
          .getName().equals("EndNode")))) {
        Note note = CoremodelFactory.eINSTANCE.createNote();
        note.setNote(notes.get(simplePathNode.getName()));
        note.getTargets().add(simplePathNode);


        // Crea una nota en el diagrama
        diagram.getNotes().add(note);

        NodeVisualModel nodeVisualModel = EditormodelFactory.eINSTANCE
            .createNodeVisualModel();
        Dimension dimension = EditormodelFactory.eINSTANCE.createDimension();
        dimension.setHeight(50);
        dimension.setWidth(200);
        nodeVisualModel.setSize(dimension);
        nodeVisualModel.setSemanticModel(note);

        NodeVisualModel responsibilityVisualModel = getResponsibilityNode(simplePathNode
            .getID());

        ConnectionVisualModel connection = EditormodelFactory.eINSTANCE
            .createConnectionVisualModel();
        connection.setSemanticModel(note);
        connection.setTarget(responsibilityVisualModel);

        nodeVisualModel.getSourceConnections().add(connection);

        diagram.getChildren().add(nodeVisualModel);
      }
    }
  }

//  public void addNote(String responsibilityName, String comment) {
//    notes.put(responsibilityName, comment);
//  }

  /**
   * Algoritmo simple para organizar los componentes en el editor
   * 
   */
  public void organizeLayout() {
    double nodes = diagram.getChildren().size();
    int matrix = (int) (Math.round(Math.sqrt(nodes)));

    int x = 0;
    int y = 0;
    for (int i = 0; i < diagram.getChildren().size(); i++) {
      NodeVisualModel nodeVisualModel = (NodeVisualModel) diagram.getChildren()
          .get(i);
      Point point = nodeVisualModel.getAbsoluteLocation();
      point.setX(x * 250);
      point.setY(y * 250);
      if (x < matrix) {
        x = x + 1;
      } else {
        x = 0;
        y = y + 1;
      }
    }
  }
}
