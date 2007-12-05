package org.design.drarch.diagram.flabot.component;

import java.util.List;

import org.design.drarch.diagram.flabot.DiagramManager;
import org.eclipse.emf.common.util.EList;
import org.isistan.flabot.coremodel.ComponentModel;
import org.isistan.flabot.coremodel.CoreModel;
import org.isistan.flabot.coremodel.CoremodelFactory;
import org.isistan.flabot.coremodel.InterfaceLink;
import org.isistan.flabot.coremodel.InterfaceModel;
import org.isistan.flabot.coremodel.Note;
import org.isistan.flabot.coremodel.PortModel;
import org.isistan.flabot.coremodel.Relationship;
import org.isistan.flabot.coremodel.Responsibility;
import org.isistan.flabot.coremodel.Stereotype;
import org.isistan.flabot.edit.componentmodel.ComponentDiagram;
import org.isistan.flabot.edit.componentmodel.ComponentmodelFactory;
import org.isistan.flabot.edit.editormodel.ConnectionVisualModel;
import org.isistan.flabot.edit.editormodel.Diagram;
import org.isistan.flabot.edit.editormodel.Dimension;
import org.isistan.flabot.edit.editormodel.EditormodelFactory;
import org.isistan.flabot.edit.editormodel.NodeVisualModel;
import org.isistan.flabot.edit.editormodel.Point;
import org.isistan.flabot.edit.editormodel.VisualModel;
import org.isistan.flabot.engine.executionstate.ExecutionstateFactory;
import org.isistan.flabot.engine.executionstate.TraceBasedStateDeterminationStrategy;
//import org.isistan.flabot.engine.executionstate.javalogtrace.JavalogTraceInferenceStrategy;
//import org.isistan.flabot.engine.executionstate.javalogtrace.JavalogtraceFactory;
import org.isistan.flabot.launcher.LauncherPlugin;
import org.isistan.flabot.launcher.filter.filtermodel.FiltermodelFactory;
import org.isistan.flabot.launcher.filter.filtermodel.LogFilter;
import org.isistan.flabot.mapping.MappingPlugin;
import org.isistan.flabot.mapping.mappingmodel.MappingmodelFactory;
import org.isistan.flabot.mapping.mappingmodel.PatternMapping;

/**
 * 
 * 
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public class ComponentsDiagram {

  private CoreModel coreModel;
  private Diagram diagram;
  private String diagramName;

  protected static final String PROLOG_CODE_EDEFAULT = "executionState(\'Faulty\') :-\n\texecutionTag(T), isExitError(T).\n\n"
      + "executionState(\'Executed\') :-\n\texecutionTag(_).\n\n"
      + "executionState(\'NotExecuted\').";

  /**
   * Crea el modelo y el diagrama.
   * 
   * @param name
   */
  public ComponentsDiagram(String name) {
    this.diagramName = name;
    coreModel = DiagramManager.getInstance().getCoreModel();
    ComponentDiagram componentDiagram = ComponentmodelFactory.eINSTANCE
        .createComponentDiagram();
    componentDiagram.setName(diagramName);
    diagram = componentDiagram;

  }

  public String getName() {
    return this.diagramName;
  }

  public Diagram getDiagram() {
    diagram.setCoreModel(coreModel);
    return diagram;
  }

  /**
   * Crea un componente en el modelo y en el diagrama.
   * 
   * @param name
   */
  public void createComponent(String name) {
    if (!existComponent(name)) {
      ComponentModel component = CoremodelFactory.eINSTANCE
          .createComponentModel();
      component.setName(name);
      coreModel.getComponents().add(component);

      // Crea el Componente Visual
      NodeVisualModel nodeVisualModel = EditormodelFactory.eINSTANCE
          .createNodeVisualModel();
      nodeVisualModel.setSemanticModel(component);
      diagram.getChildren().add(nodeVisualModel);
    } else if (!existComponentVisual(name)) {
      // Crea el Componente Visual
      NodeVisualModel nodeVisualModel = EditormodelFactory.eINSTANCE
          .createNodeVisualModel();
      nodeVisualModel.setSemanticModel(getComponentModel(name));
      diagram.getChildren().add(nodeVisualModel);
    }
  }

  /**
   * Verifica la existencia de un componente visual.
   * 
   * @param name
   * @return
   */
  private boolean existComponentVisual(String name) {
    if (getComponentNode(name) != null) return true;
    return false;
  }

  /**
   * Verifica la existencia de un componente en el modelo.
   * 
   * @param name
   * @return
   */
  private boolean existComponent(String name) {
    for (int i = 0; i < coreModel.getComponents().size(); i++) {
      ComponentModel componentModel = (ComponentModel) coreModel
          .getComponents().get(i);
      if (name.equals(componentModel.getName())) {
        return true;
      }
    }
    return false;
  }

  /**
   * crea una relacion entre dos componentes. La relacion se crea en el modelo y
   * en el diagrama.
   * 
   * @param target
   * @param source
   */
  @SuppressWarnings("unchecked")
  public void createRelationship(String target, String source,
      String stereotypeName) {
    if (existComponent(target) && existComponent(source)
        && !source.equals(target)) {
      ComponentModel componentTarget = getComponentModel(target);
      ComponentModel componentSource = getComponentModel(source);

      Relationship relationship = CoremodelFactory.eINSTANCE
          .createRelationship();
      relationship.setTarget(componentTarget);
      relationship.setSource(componentSource);

      if (stereotypeName != null) {
        Stereotype stereotype = CoremodelFactory.eINSTANCE.createStereotype();
        stereotype.setName(stereotypeName);
        coreModel.getStereotypes().add(stereotype);
        relationship.setStereotype(stereotype);
      }
      coreModel.getRelationships().add(relationship);

      // Genera la conexion en el diagrama para que se visualice
      ConnectionVisualModel connectionVisualModel = EditormodelFactory.eINSTANCE
          .createConnectionVisualModel();
      NodeVisualModel nodeVisualTarget = getComponentNode(target);
      NodeVisualModel nodeVisualSource = getComponentNode(source);
      connectionVisualModel.setTarget(nodeVisualTarget);
      connectionVisualModel.setSource(nodeVisualSource);
      connectionVisualModel.setSemanticModel(relationship);
    }
  }

  /**
   * Busca en el diagrama visual el nodo que tenga como nombre el valor de name.
   * 
   * @param name
   * @return
   */
  private NodeVisualModel getComponentNode(String name) {
    for (int i = 0; i < diagram.getChildren().size(); i++) {
      Object object = diagram.getChildren().get(i);
      if (object instanceof NodeVisualModel) {
        NodeVisualModel nodeVisualModel = (NodeVisualModel) object;
        ComponentModel componentModel = (ComponentModel) nodeVisualModel
            .getSemanticModel();
        if (componentModel.getName().equals(name)) return nodeVisualModel;
      }
    }
    return null;
  }

  /**
   * Busca en el modelo un componente que tenga como nombre el valor de name
   * 
   * @param name
   * @return
   */
  public ComponentModel getComponentModel(String name) {
    ComponentModel componentModel;
    for (int i = 0; i < coreModel.getComponents().size(); i++) {
      componentModel = (ComponentModel) coreModel.getComponents().get(i);
      if (componentModel.getName().equals(name)) return componentModel;
    }
    return null;
  }


  /**
   * Crear un puerto en un componente
   */
  @SuppressWarnings("unchecked")
  public void createPort(String componentName, String portName) {
    ComponentModel componentModel = getComponentModel(componentName);
    PortModel port = CoremodelFactory.eINSTANCE.createPortModel();
    port.setName(portName);
    componentModel.getOwnedPorts().add(port);

    // Crea el puerto en el nodo para visualizarlo
    NodeVisualModel componentNode = getComponentNode(componentName);
    VisualModel portVisual = EditormodelFactory.eINSTANCE.createVisualModel();
    portVisual.setSemanticModel(port);
    componentNode.getChildren().add(portVisual);
  }

  /**
   * Crea una interface en el puerto especificado dentro del componente
   * especificado.
   */
  @SuppressWarnings("unchecked")
  public void createProvidedInterface(String interfaceName, String portName,
      String componentName) {
    @SuppressWarnings("unused")
    boolean hasRequireds = false;
    ComponentModel componentModel = getComponentModel(componentName);
    EList portList = componentModel.getOwnedPorts();
    InterfaceModel providedInterface = CoremodelFactory.eINSTANCE
        .createInterfaceModel();
    providedInterface.setName(interfaceName);

    for (int i = 0; i < portList.size(); i++) {
      PortModel port = (PortModel) portList.get(i);
      if (port.getName().equals(portName)) {
        providedInterface.setPort(port);
        port.getProvideds().add(providedInterface);
        if (port.getRequireds().size() != 0) hasRequireds = true;
      }
    }

    // Crea la interface en el puerto para visualizarla
    NodeVisualModel componentNode = getComponentNode(componentName);
    NodeVisualModel providedInterfaceVisual = EditormodelFactory.eINSTANCE
        .createNodeVisualModel();
    providedInterfaceVisual.setSemanticModel(providedInterface);

    // Se crea el punto y el tamaño del componente visual (la interface)
    Point point = EditormodelFactory.eINSTANCE.createPoint();
    point.setX(2);

    point.setY(2);
    Dimension dimension = EditormodelFactory.eINSTANCE.createDimension();
    dimension.setWidth(40);
    dimension.setHeight(15);
    providedInterfaceVisual.setSize(dimension);
    providedInterfaceVisual.setLocation(point);

    VisualModel portVisual = (VisualModel) componentNode.getChildren().get(0);
    portVisual.getChildren().add(providedInterfaceVisual);
  }

  /**
   * Retorna la interface proveedora del puerto especificado.
   */
  private InterfaceModel getInterfaceProvided(String source, String portName) {
    ComponentModel componentModel = getComponentModel(source);
    EList ports = componentModel.getOwnedPorts();
    for (int i = 0; i < ports.size(); i++) {
      PortModel port = (PortModel) ports.get(i);
      if (port.getName().equals(portName))
        if (port.getProvideds().size() != 0)
          return (InterfaceModel) port.getProvideds().get(0);

    }
    return null;
  }

  /**
   * Retorna la interface requerida del puerto especificado
   * 
   * @param target Es el nombre del componente
   * @param portName Es el nombre del puerto.
   * @return Retorna una interface.
   */
  private InterfaceModel getInterfaceRequired(String target, String portName) {
    ComponentModel componentModel = getComponentModel(target);
    EList ports = componentModel.getOwnedPorts();
    for (int i = 0; i < ports.size(); i++) {
      PortModel port = (PortModel) ports.get(i);
      if (port.getName().equals(portName))
    	  //TODO : revisar ->unchequed exception 
        return (InterfaceModel) port.getRequireds().get(0);
    }
    return null;
  }

  @SuppressWarnings("unchecked")
  public void createRequiredsInterface(String interfaceName, String portName,
      String componentName) {
    boolean hasProvideds = false;

    ComponentModel componentModel = getComponentModel(componentName);
    EList portList = componentModel.getOwnedPorts();
    InterfaceModel requiredInterface = CoremodelFactory.eINSTANCE
        .createInterfaceModel();
    requiredInterface.setName(interfaceName);

    for (int i = 0; i < portList.size(); i++) {
      PortModel port = (PortModel) portList.get(i);
      if (port.getName().equals(portName)) {
        requiredInterface.setPort(port);
        port.getRequireds().add(requiredInterface);
        if (port.getProvideds().size() > 0) {
          hasProvideds = true;
        }
      }
    }

    // Crea la interface en el puerto para visualizarla
    NodeVisualModel componentNode = getComponentNode(componentName);
    NodeVisualModel requiredInterfaceVisual = EditormodelFactory.eINSTANCE
        .createNodeVisualModel();
    requiredInterfaceVisual.setSemanticModel(requiredInterface);

    // Se crea el punto y el tamaño del componente visual (la interface)
    Point point = EditormodelFactory.eINSTANCE.createPoint();
    point.setX(2);
    if (hasProvideds)
      point.setY(25);
    else
      point.setY(2);
    Dimension dimension = EditormodelFactory.eINSTANCE.createDimension();
    dimension.setWidth(40);
    dimension.setHeight(15);
    requiredInterfaceVisual.setSize(dimension);
    requiredInterfaceVisual.setLocation(point);

    VisualModel portVisual = (VisualModel) componentNode.getChildren().get(0);
    portVisual.getChildren().add(requiredInterfaceVisual);
  }

  @SuppressWarnings("unchecked")
  public void createInterfaceLink(String sourceComponent,
      String targetComponent, String portProvided, String portRequired,
      String interfaceProvided, String interfaceRequired) {
    if (existComponent(sourceComponent) && existComponent(targetComponent)
        && !targetComponent.equals(sourceComponent)) {

      InterfaceModel sourceInterface = getInterfaceProvided(sourceComponent,
          portProvided);
      InterfaceModel targetInterface = getInterfaceRequired(targetComponent,
          portRequired);

      InterfaceLink interfaceLink = CoremodelFactory.eINSTANCE
          .createInterfaceLink();
      interfaceLink.setSource(sourceInterface);
      interfaceLink.setTarget(targetInterface);
      coreModel.getInterfaceLinks().add(interfaceLink);

      // Genera el link en el diagrama visual
      VisualModel portSourceVisual = getPort(sourceComponent, portProvided);
      VisualModel portTargetVisual = getPort(targetComponent, portRequired);

      NodeVisualModel providedsInterface = getInterface(portSourceVisual,
          interfaceProvided);
      NodeVisualModel requiredsInterface = getInterface(portTargetVisual,
          interfaceRequired);

      ConnectionVisualModel linkModel = EditormodelFactory.eINSTANCE
          .createConnectionVisualModel();

      linkModel.setSource(providedsInterface);
      linkModel.setTarget(requiredsInterface);

      linkModel.setSemanticModel(interfaceLink);
    }
  }

  private NodeVisualModel getInterface(VisualModel portVisual,
      String interfaceName) {
    EList interfaces = portVisual.getChildren();
    for (int i = 0; i < interfaces.size(); i++) {
      NodeVisualModel interfaceVisual = (NodeVisualModel) interfaces.get(i);
      InterfaceModel interfaceModel = (InterfaceModel) interfaceVisual
          .getSemanticModel();
      if (interfaceModel.getName().equals(interfaceName))
        return interfaceVisual;
    }
    return null;
  }

  public VisualModel getPort(String componentName, String portName) {
    NodeVisualModel component = getComponentNode(componentName);
    EList ports = component.getChildren();
    for (int i = 0; i < ports.size(); i++) {
      VisualModel portSourceVisual = (VisualModel) ports.get(i);
      PortModel port = (PortModel) portSourceVisual.getSemanticModel();
      if (port.getName().endsWith(portName)) return portSourceVisual;
    }
    return null;
  }

  @SuppressWarnings("unchecked")
  public void createNote(String description) {

    Note note = CoremodelFactory.eINSTANCE.createNote();
    note.setNote(description);

    // Crea una nota en el diagrama
    NodeVisualModel nodeVisualModel = EditormodelFactory.eINSTANCE
        .createNodeVisualModel();
    nodeVisualModel.setSemanticModel(note);
    diagram.getNotes().add(note);
    diagram.getChildren().add(nodeVisualModel);

  }

  @SuppressWarnings("unchecked")
  public void createStereotypeInComponent(String componentName,
      String stereotypeName) {
    if (existComponent(componentName)) {
      ComponentModel component = getComponentModel(componentName);
      Stereotype stereotype = CoremodelFactory.eINSTANCE.createStereotype();
      stereotype.setName(stereotypeName);
      coreModel.getStereotypes().add(stereotype);
      component.setStereotype(stereotype);
    }
  }

  public void setScope(String componentName, List association) {
    if (association != null) {
      if (association.size() > 0) {
        ComponentModel component = getComponentModel(componentName);
        PatternMapping pm = (PatternMapping) component.getExtendedData(
            MappingPlugin.SYMBOLIC_NAME, "mapping");
        String packageNames = "";
        String classNames = "";
        String javaFileNames = "";
        String separator = "|";
        if (pm == null) {
          pm = MappingmodelFactory.eINSTANCE.createPatternMapping();
        }
        // else{
        // packageNames = pm.getPackagePattern();
        // classNames = pm.getClassPattern();
        // javaFileNames = pm.getJavaFilePattern();
        // }



        for (int i = 0; i < association.size(); i++) {
          // if (!packageNames.equals("")) separator = "|";
          DObject dObject = (DObject) association.get(i);
          if (!packageNames.contains(dObject.getPackageName())) {
            if (packageNames.equals(""))
              packageNames = escape(dObject.getPackageName());
            else
              packageNames = packageNames + separator
                  + escape(dObject.getPackageName());
          }
          if (classNames.equals(""))
            classNames = "@" + escape(dObject.getClassName());
          else
            classNames = classNames + separator + "@"
                + escape(dObject.getClassName());

          if (javaFileNames.equals(""))
            javaFileNames = escape(dObject.getJavaFileDescriptor());
          else
            javaFileNames = javaFileNames + separator
                + escape(dObject.getJavaFileDescriptor());
        }
        pm.setPackagePattern(packageNames);
        pm.setBehaviorPattern("");
        pm.setClassPattern(classNames);
        pm.setJavaFilePattern(javaFileNames);

        component.putExtendedData(MappingPlugin.SYMBOLIC_NAME, "mapping", pm);
      }
    }

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
  public void createResponsibility(String name) {

    Responsibility responsibility = CoremodelFactory.eINSTANCE
        .createResponsibility();
    responsibility.setName(name);

    TraceBasedStateDeterminationStrategy traceStrategy = ExecutionstateFactory.eINSTANCE
        .createTraceBasedStateDeterminationStrategy();
//    JavalogTraceInferenceStrategy inferenceStrategy = JavalogtraceFactory.eINSTANCE
//        .createJavalogTraceInferenceStrategy();
//    inferenceStrategy
//        .setPrologCode("executionState('Faulty') :- texecutionTag(T), isExitError(T). "
//            + "executionState('Executed') :- texecutionTag(_)."
//            + "executionState('NotExecuted').");
//    inferenceStrategy.setStateDeterminationStrategy(traceStrategy);
//    traceStrategy.setTraceInferenceStrategy(inferenceStrategy);
//    traceStrategy.setResponsibility(responsibility);

    // traceStrategy.setResponsibility(responsibility);
    responsibility.putExtendedData("org.isistan.flabot.engine",
        "stateDeterminationStrategy", traceStrategy);
    coreModel.getResponsibilities().add(responsibility);
  }



  @SuppressWarnings("unchecked")
  public void registryResponsibility(String componentName,
      String responsibilityName) {
    Responsibility responsibility = getResponsibility(responsibilityName);

    getComponentModel(componentName).getFeatures().add(
        getResponsibility(responsibilityName));
    responsibility.getComponents().add(getComponentModel(componentName));

    LogFilter logFilter = FiltermodelFactory.eINSTANCE.createLogFilter();
    logFilter.setGaugeTypes(7);
    responsibility.putExtendedData(LauncherPlugin.SYMBOLIC_NAME, "logFilter",
        logFilter);
  }


  public void mappingResponsibility(String responsibilityName, List mapping) {

    if (mapping != null) {
      if (mapping.size() > 0) {
        Responsibility responsibility = getResponsibility(responsibilityName);
        PatternMapping pm = (PatternMapping) responsibility.getExtendedData(
            MappingPlugin.SYMBOLIC_NAME, "mapping");
        String packageNames = "";
        String classNames = "";
        String javaFileNames = "";
        String behavior = "";
        String separator = "|";
        if (pm == null) {
          pm = MappingmodelFactory.eINSTANCE.createPatternMapping();
        }
        // else{
        // packageNames = pm.getPackagePattern();
        // classNames = pm.getClassPattern();
        // javaFileNames = pm.getJavaFilePattern();
        // behavior = pm.getBehaviorPattern();
        // }

        for (int i = 0; i < mapping.size(); i++) {
          DObject dObject = (DObject) mapping.get(i);
          if (behavior.equals(""))
            behavior = escape(dObject.getBehaviorString());
          else
            behavior = behavior + separator
                + escape(dObject.getBehaviorString());
          /*
           * src.Player#&lt;init>() | src.Player#getItems():int |
           * src.Player#setItems(int):void
           */

          if (!packageNames.contains(dObject.getPackageName())) {
            if (packageNames.equals(""))
              packageNames = escape(dObject.getPackageName());
            else
              packageNames = packageNames + separator
                  + escape(dObject.getPackageName());
          }

          if (classNames.equals(""))
            classNames = escape(dObject.getClassName());
          else
            classNames = classNames + separator
                + escape(dObject.getClassName());

          if (javaFileNames.equals(""))
            javaFileNames = escape(dObject.getJavaFileDescriptor());
          else
            javaFileNames = javaFileNames + separator
                + escape(dObject.getJavaFileDescriptor());
        }
        pm.setPackagePattern(packageNames);
        pm.setBehaviorPattern(behavior);
        pm.setClassPattern(classNames);
        pm.setJavaFilePattern(javaFileNames);

        responsibility.putExtendedData(MappingPlugin.SYMBOLIC_NAME, "mapping",
            pm);
      }
    }
  }



  private String escape(String string) {
    return string.replace(".java", "").replace(".", "\\.") //$NON-NLS-1$ //$NON-NLS-2$
        .replace("(", "\\(") //$NON-NLS-1$ //$NON-NLS-2$
        .replace(")", "\\)") //$NON-NLS-1$ //$NON-NLS-2$
        .replace("[", "\\[") //$NON-NLS-1$ //$NON-NLS-2$
        .replace("]", "\\]") //$NON-NLS-1$ //$NON-NLS-2$
        .replace("$", "\\$"); //$NON-NLS-1$ //$NON-NLS-2$
  }

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
