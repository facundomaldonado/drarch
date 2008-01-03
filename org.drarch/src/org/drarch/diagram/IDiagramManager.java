package org.drarch.diagram;

import org.drarch.diagram.DiagramModel.componentModel.ComponentModel;
import org.drarch.diagram.DiagramModel.ucmModel.UCMModel;

/**
 * 
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public interface IDiagramManager {

  public void createComponentDiagram(ComponentModel model);

  public void createUCMDiagram(UCMModel model);

  public Object getComponentsDiagram(String name);

  public Object getUCMDiagram(String name);

  public void update(boolean componentDiagram);
  
  
}
