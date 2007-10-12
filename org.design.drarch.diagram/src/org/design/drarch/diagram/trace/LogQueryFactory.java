package org.design.drarch.diagram.trace;

/**
 * 
 * 
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public class LogQueryFactory {

  private static LogQueryFactory instance = null;
  public static final String DEFAULT_RESPONSIBILITY_QUERY = "logResponsibility(?R)";
  public static final String DEFAULT_EXECUTION_QUERY = "responsibilityExecution(?Responsibility, ?Id, ?Value)";
  public static final String DEFAULT_METHOD_QUERY = "executionMethod(?I,?M)";
  public static final String DEFAULT_CLASS_QUERY = "executionDeclaredClass(?I,?C)";
  public static final String DEFAULT_COMPONENT_CLASS_ASSOCIATION_QUERY = "association(?C,?QualifiedName),component(?C)";
  public static final String DEFAULT_COMPONENT_RESPONSIBILITY_ASSOCIATION_QUERY = "hasResponsibility(?C,?R)";

  public LogQueryFactory() {
  }

  public static LogQueryFactory getInstance() {
    if (instance == null) {
      instance = new LogQueryFactory();
    }
    return instance;
  }

  public String createResponsibilityPredicate(String responsibilityName) {
    return ("logResponsibility(" + responsibilityName + ").");
  }

  public String createExecutionPredicate(String responsibilityName,
      String exectutionId, String value) {
    return "responsibilityExecution(" + responsibilityName + " ,"
        + exectutionId + ", '" + value + "').";
  }

  public String createExecutionMethodPredicate(String executionId,
      String methodName) {
    return ("executionMethod(" + executionId + "," + methodName + ").");
  }

  public String createExecutionDeclaredClass(String executionId,
      String className) {
    return ("executionDeclaredClass(" + executionId + "," + className + ").");
  }

  public String createComponentClassAssociationQuery(String className,
      String packageName) {
    return "association(?C," + packageName + "." + className
        + "),component(?C)";
  }

  public String createComponentResponsibilityAssociationQuery(String responsibility) {
    return "hasResponsibility(?Component," + responsibility + ")";
  }

public String createExecutedMethodValue(String qualifiedMethodName, String execId, String exitValue) {
	// TODO Auto-generated method stub
	return null;
}
}
