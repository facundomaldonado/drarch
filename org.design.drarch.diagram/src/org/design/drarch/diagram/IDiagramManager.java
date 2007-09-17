package org.design.drarch.diagram;

import org.design.drarch.diagram.DiagramModel.componentModel.ComponentModel;
import org.design.drarch.diagram.DiagramModel.ucmModel.UCMModel;

/**
 * 
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public interface IDiagramManager {

  public void createComponentDiagram(String name);

  public void createUCMDiagram(String name);

  public void createComponentDiagram(ComponentModel model);

  public void createUCMDiagram(UCMModel model);

  public Object getComponentsDiagram(String name);

  public Object getUCMDiagram(String name);

  public void update(boolean componentDiagram);
  
  
}
