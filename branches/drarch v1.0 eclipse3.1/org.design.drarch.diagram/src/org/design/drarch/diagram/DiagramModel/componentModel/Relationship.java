package org.design.drarch.diagram.DiagramModel.componentModel;

/**
 * 
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public class Relationship {
  private String name;
  private Component source;
  private Component target;
  private String stereotype;

  public Relationship() {
    this.name = "noname";
    this.stereotype = "";
  }

  public Component getSource() {
    return source;
  }

  public void setSource(Component source) {
    this.source = source;
  }

  public Component getTarget() {
    return target;
  }

  public void setTarget(Component target) {
    this.target = target;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getStereotype() {
    return stereotype;
  }

  public void setStereotype(String stereotype) {
    this.stereotype = stereotype;
  }

  public boolean hasSteroitype() {
    if (!stereotype.equalsIgnoreCase("")) {
      return false;
    }
    return true;
  }
}
