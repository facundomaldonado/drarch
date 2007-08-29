package org.design.drarch.diagram.DiagramModel.componentModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public class Component {
  private String name;
  private List<Port> ports;
  private List association;

  public Component() {
    this.name = "noname";
    this.ports = new ArrayList<Port>();
    this.association = new ArrayList();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @SuppressWarnings("unchecked")
  public void addPort(Port portProvided) {
    ports.add(portProvided);
  }

  public List getAllPorts() {
    return ports;
  }

  public Port getPort(String portName) {
    for (int i = 0; i < ports.size(); i++) {
      Port port = (Port) ports.get(i);
      if (port.getName().equals(portName)) {
        return port;
      }
    }
    return null;
  }

  public List getAssociation() {
    return association;
  }

  public void setAssociation(List associations) {
    this.association = associations;
  }
}
