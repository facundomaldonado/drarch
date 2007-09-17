package org.design.drarch.diagram.flabot.component;

import java.util.List;
import java.util.Vector;

/**
 * 
 * 
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public class DObject {

  /*
   * src.Player#setItems(int):void
   */
  List<DBehavior> behaviors;

  String className;
  String javaFileDescriptor;
  String packageName;

  public List<DBehavior> getBehavior() {
    return behaviors;
  }

  public void setBehavior(List<DBehavior> behavior) {
    this.behaviors = behavior;
  }

  public String getClassName() {
    return packageName + "." + className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public String getJavaFileDescriptor() {
    return packageName + "." + javaFileDescriptor;
  }

  public void setJavaFileDescriptor(String javaFileDescriptor) {
    this.javaFileDescriptor = javaFileDescriptor;
  }

  public String getPackageName() {
    return packageName;
  }

  public void setPackageName(String packageName) {
    this.packageName = packageName;
  }

  public String getBehaviorString() {
    String result = "";
    String separator = "";
    for (int i = 0; i < behaviors.size(); i++) {
      DBehavior dBehavior = (DBehavior) behaviors.get(i);
      if (i > 0) separator = "|";
      result = result + separator + packageName + "." + className + "#"
          + dBehavior.getMethod() + "(" + dBehavior.getArgumentType() + "):" +

          dBehavior.getType();
    }
    return result;
  }
}
