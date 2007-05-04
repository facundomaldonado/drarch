package org.design.drarch.diagram.DiagramModel.componentModel;

public class Interface {
  private String name;
  private Port port;

  public Interface(){
    this.name = "noname";
  }

  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public Port getPort() {
    return port;
  }
  
  public void setPort(Port port) {
    this.port = port;
  }
}
