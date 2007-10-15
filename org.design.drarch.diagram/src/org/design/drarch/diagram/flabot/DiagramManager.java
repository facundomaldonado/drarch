package org.design.drarch.diagram.flabot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.design.drarch.diagram.DiagramPlugin;
import org.design.drarch.diagram.IDiagramManager;
import org.design.drarch.diagram.DiagramModel.componentModel.Component;
import org.design.drarch.diagram.DiagramModel.componentModel.ComponentModel;
import org.design.drarch.diagram.DiagramModel.componentModel.InterfaceLink;
import org.design.drarch.diagram.DiagramModel.componentModel.Port;
import org.design.drarch.diagram.DiagramModel.componentModel.Relationship;
import org.design.drarch.diagram.DiagramModel.componentModel.Responsibility;
import org.design.drarch.diagram.DiagramModel.ucmModel.ComponentRole;
import org.design.drarch.diagram.DiagramModel.ucmModel.Path;
import org.design.drarch.diagram.DiagramModel.ucmModel.UCMModel;
import org.design.drarch.diagram.flabot.component.ComponentsDiagram;
import org.design.drarch.diagram.flabot.ucm.UCMDiagrams;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.isistan.flabot.coremodel.CoreModel;
import org.isistan.flabot.coremodel.CoremodelFactory;
import org.isistan.flabot.edit.editormodel.Diagram;
import org.isistan.flabot.edit.editormodel.EditormodelFactory;
import org.isistan.flabot.edit.editormodel.FlabotFileModel;
import org.isistan.flabot.edit.multipage.FlabotMultiPageEditor;
import org.isistan.flabot.util.emf.WorkaroundURIConverter;

/**
 * 
 * 
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public class DiagramManager implements IDiagramManager {
	
  private List<ComponentsDiagram> components;
  private List<UCMDiagrams> ucms;
  private FlabotFileModel flabotFileModel;
  private String fileName = "";
  private static DiagramManager instance;
  private CoreModel coreModel;

  private ComponentModel currentModel;

  public DiagramManager() {
    components = new ArrayList<ComponentsDiagram>();
    ucms = new ArrayList<UCMDiagrams>();
    coreModel = CoremodelFactory.eINSTANCE.createCoreModel();
    flabotFileModel = EditormodelFactory.eINSTANCE.createFlabotFileModel();

  }

  public static DiagramManager getInstance() {
    if (instance == null) {
      instance = new DiagramManager();
    }
    return instance;
  }

  @SuppressWarnings("unchecked")
  public void createComponentDiagram(String name) {
    ComponentsDiagram componentsDiagra = new ComponentsDiagram(name);
    components.add(componentsDiagra);
  }

  @SuppressWarnings("unchecked")
  public void createUCMDiagram(UCMModel model) {
    UCMDiagrams ucmDiagrams = new UCMDiagrams(model.getName());
    for (int i = 0; i < model.getComponentRoles().size(); i++) {
      ComponentRole componentRole = (ComponentRole) model.getComponentRoles().get(i);
      ucmDiagrams.createComponentRole(componentRole.getName());
    }
    for (int i = 0; i < model.getPaths().size(); i++) {
      Path path = (Path) model.getPaths().get(i);
    	ucmDiagrams.createPath(path);
    }
    ucmDiagrams.organizeLayout();
    ucms.add(ucmDiagrams);
    update(false);
  }

  @SuppressWarnings("unchecked")
  public void createComponentDiagram(ComponentModel model) {
    currentModel = model;
    coreModel = getCoreModel();

    int numComponent = components.size() + 1;
    model.setName("# " + numComponent);
    ComponentsDiagram componentsDiagram = new ComponentsDiagram(model.getName());

    // Crea todos los componentes, los puertos y las interfaces
    List<Component> componentsModel = model.getAllComponents();
    for (int i = 0; i < componentsModel.size(); i++) {
      Component c = (Component) componentsModel.get(i);
      componentsDiagram.createComponent(c.getName());

      List<Port> ports = c.getAllPorts();
      for (int j = 0; j < ports.size(); j++) {
        Port port = (Port) ports.get(j);
        componentsDiagram.createPort(c.getName(), port.getName());
        if (port.hasProvidedInterface())
          componentsDiagram.createProvidedInterface(port.getProvided()
              .getName(), port.getName(), port.getComponent().getName());
        if (port.hasRequiredInterface())
          componentsDiagram.createRequiredsInterface(port.getRequired()
              .getName(), port.getName(), port.getComponent().getName());
      }
      componentsDiagram.setScope(c.getName(), model
          .getAssociations(c.getName()));
    }
    // Crea links entre las interfaces
    List<InterfaceLink> interfaceLinksModel = model.getInterfaceLinks();
    for (int i = 0; i < interfaceLinksModel.size(); i++) {
      InterfaceLink interfaceLink = (InterfaceLink) interfaceLinksModel.get(i);
      String componentSource = interfaceLink.getSource().getPort()
          .getComponent().getName();
      String componentTarget = interfaceLink.getTarget().getPort()
          .getComponent().getName();
      componentsDiagram.createInterfaceLink(componentSource, componentTarget,
          interfaceLink.getSource().getPort().getName(), interfaceLink
              .getTarget().getPort().getName(), interfaceLink.getSource()
              .getName(), interfaceLink.getTarget().getName());
    }
    // Crea relaciones entre componentes
    List<Relationship> relationships = model.getRelationships();
    for (int i = 0; i < relationships.size(); i++) {
      Relationship relation = (Relationship) relationships.get(i);
      componentsDiagram.createRelationship(relation.getSource().getName(),
          relation.getTarget().getName(), relation.getStereotype());
    }

    // Crea responsabilidades
    List<Responsibility> responsibilities = model.getResponsibilities();
    for (int i = 0; i < responsibilities.size(); i++) {
      Responsibility responsibility = (Responsibility) responsibilities.get(i);
      componentsDiagram.createResponsibility(responsibility.getName());
      if (responsibility.hasComponent()) {
        componentsDiagram.registryResponsibility(responsibility.getComponent()
            .getName(), responsibility.getName());

        componentsDiagram.mappingResponsibility(responsibility.getName(), model
            .getMapping(responsibility.getName()));
      }
    }
    componentsDiagram.organizeLayout();
    components.add(componentsDiagram);
  }

  public ComponentsDiagram getComponentsDiagram(String name) {

    for (int i = 0; i < components.size(); i++) {
      ComponentsDiagram componentsDiagra = (ComponentsDiagram) components
          .get(i);
      String componentName = componentsDiagra.getName();
      if (componentName.equals(name)) {
        return componentsDiagra;
      }
    }
    return null;
  }

  @SuppressWarnings("unchecked")
  public void update(boolean componentDiagram) {

    FlabotMultiPageEditor editorPart = getFlabotEditorPart();

    if (editorPart != null) {

      flabotFileModel = editorPart.getModel();
      int currentComponent = components.size();
      int currentUCM = ucms.size();
      if (componentDiagram) {
        ComponentsDiagram c = (ComponentsDiagram) components
            .get(currentComponent - 1);
        flabotFileModel.getDiagrams().add(c.getDiagram());
        flabotFileModel.getOpenDiagrams().add(c.getDiagram());
        editorPart.openDiagram((Diagram) flabotFileModel.getDiagrams().get(
            currentComponent - 1));
      } else {
        UCMDiagrams ucm = (UCMDiagrams) ucms.get(currentUCM - 1);
        flabotFileModel.getDiagrams().add(ucm.getDiagram());
        flabotFileModel.getOpenDiagrams().add(ucm.getDiagram());
        editorPart.openDiagram((Diagram) flabotFileModel.getDiagrams().get(
            currentComponent + currentUCM - 1));
      }

    } else {
      flabotFileModel = EditormodelFactory.eINSTANCE.createFlabotFileModel();
      for (int i = 0; i < components.size(); i++) {
        ComponentsDiagram c = (ComponentsDiagram) components.get(i);
        flabotFileModel.getDiagrams().add(c.getDiagram());
        flabotFileModel.getOpenDiagrams().add(c.getDiagram());
      }

      for (int i = 0; i < ucms.size(); i++) {
        UCMDiagrams ucm = (UCMDiagrams) ucms.get(i);
        flabotFileModel.getDiagrams().add(ucm.getDiagram());
        flabotFileModel.getOpenDiagrams().add(ucm.getDiagram());
      }
      flabotFileModel.setCoreModel(coreModel);
      save();
    }
  }

  private CoreModel getCoreModel() {
    FlabotMultiPageEditor editorPart = getFlabotEditorPart();
    if (editorPart != null) {
      flabotFileModel = editorPart.getModel();
      return flabotFileModel.getCoreModel();
    }
    return coreModel;
  }

  public CoreModel getCore() {
    return getCoreModel();
  }

  @SuppressWarnings("unchecked")
  private void save() {
    ResourceSet resourceSet = new ResourceSetImpl();
    resourceSet.getLoadOptions().put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE,
        Boolean.TRUE);
    resourceSet.setURIConverter(new WorkaroundURIConverter());

    //URI uri = URI.createFileURI(Util.getInstance().getPath() + "/Drarch/" + fileName);
    URI flabotFileURI = URI.createFileURI(fileName);
    
    Resource resource = resourceSet.createResource(flabotFileURI);
    resource.getContents().add(flabotFileModel);
    try {
      resource.save(Collections.singletonMap(XMLResource.OPTION_ENCODING,
          "ISO-8859-15"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @SuppressWarnings("deprecation")
  private FlabotMultiPageEditor getFlabotEditorPart() {
    IWorkbenchPage[] workbenchPageList = DiagramPlugin.getDefault()
        .getWorkbench().getActiveWorkbenchWindow().getPages();
    for (int i = 0; i < workbenchPageList.length; i++) {
      IWorkbenchPage workbenchPage = workbenchPageList[i];
      IEditorPart[] editorPartList = workbenchPage.getEditors();
      for (int j = 0; j < editorPartList.length; j++) {
        IEditorPart editorPart = editorPartList[j];
        if (editorPart instanceof FlabotMultiPageEditor) {
          // if (((FlabotMultiPageEditor)
          // editorPart).getPartName().equals(fileName)){//bug
          if (((FlabotMultiPageEditor) editorPart).getPartName().endsWith(
              ".flabot")) {
            return (FlabotMultiPageEditor) editorPart;
          }
        }
      }
    }
    return null;
  }


  @SuppressWarnings("unchecked")
  public void createUCMDiagram(String name) {
    UCMDiagrams ucmDiagrams = new UCMDiagrams(name);
    ucms.add(ucmDiagrams);
  }

  public Object getUCMDiagram(String name) {
    return null;
  }


  public void restart() {
    instance = null;

  }

  public void addNote(String description) {
    if (0 < components.size()) {
      ComponentsDiagram componentsDiagram = (ComponentsDiagram) components
          .get(components.size() - 1);
      componentsDiagram.createNote(description);
    }
  }

  public void setFileName(String name) {
    fileName = name;
    FlabotMultiPageEditor f = getFlabotEditorPart();
    if (f != null) 
    	f.closeEditor(true);
    flabotFileModel = EditormodelFactory.eINSTANCE.createFlabotFileModel();
  }

  public ComponentModel getCurrentModel() {
    return currentModel;
  }
}
