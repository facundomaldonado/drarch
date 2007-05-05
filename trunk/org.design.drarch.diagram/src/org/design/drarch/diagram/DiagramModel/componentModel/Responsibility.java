package org.design.drarch.diagram.DiagramModel.componentModel;

/**
 * 
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Responsibility {
  private Component component;
  private String name;
  private String description;
  private List<String> mapping;

  public Responsibility(){
    mapping = new ArrayList<String>();
  }

  public Component getComponent() {
    return component;
  }

  public void setComponent(Component component) {
    this.component = component;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean hasComponent() {
    if (component != null)
      return true;
    return false;
  }

  @SuppressWarnings("unchecked")
  public void addMapping(String className){
    mapping.add(className);
  }

  public List getMapping() {
    return mapping;
  }

  public void setMapping(List<String> m) {
    for (Iterator<String> iterator = m.iterator(); iterator.hasNext();) {
      this.mapping.add(iterator.next());
    }
  }
}