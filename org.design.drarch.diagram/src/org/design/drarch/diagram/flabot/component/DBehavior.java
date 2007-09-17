package org.design.drarch.diagram.flabot.component;

import java.util.Vector;

/**
 * 
 * 
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public class DBehavior {

  String method;
  String type;
  Vector argumentType;

  public DBehavior() {
    this.argumentType = new Vector();
  }

  public String getArgumentType() {
    String result = "";
    for (int i = 0; i < argumentType.size(); i++) {
      if (i == 0)
        result = result + argumentType.get(i);
      else
        result = result + "," + argumentType.get(i);
    }
    return result;
  }

  public void setArgumentType(Vector argumentType) {
    this.argumentType = argumentType;
  }

  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    this.method = method;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @SuppressWarnings("unchecked")
  public void addArgumentType(String string) {
    // ["java.lang%.String"::RefType,"java.lang%.String"::RefType,"java.util%.Vector"::RefType]
    //System.out.println(string);
    string = string.replaceAll("%", "");
    string = string.replaceAll("\"", "");
    string = string.substring(1);
    string = string.replaceAll("]", "");
    string = string.replaceAll("::RefType", "");
    string = string.replaceAll("::Primitive", "");

    this.argumentType.add(string);

  }
}
