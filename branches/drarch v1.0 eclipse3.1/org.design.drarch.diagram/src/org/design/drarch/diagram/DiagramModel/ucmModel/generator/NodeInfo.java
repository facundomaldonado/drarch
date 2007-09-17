package org.design.drarch.diagram.DiagramModel.ucmModel.generator;

/**
 * 
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public class NodeInfo {
  public String responsibilityName;
  public String execId;
  public String className;
  public String methodName;
  public String objectId;

  public String toString() {
    return (execId + " " + responsibilityName + " " + methodName + " "
        + className + " " + objectId);
  }

  public NodeInfo(String r_n, String e_id, String c_n, String m_n) {
    responsibilityName = r_n;
    execId = e_id;
    className = c_n;
    methodName = m_n;
  }
}
