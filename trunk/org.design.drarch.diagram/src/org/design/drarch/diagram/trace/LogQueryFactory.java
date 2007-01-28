package org.design.drarch.diagram.trace;

public class LogQueryFactory {
	
	private static LogQueryFactory instance=null;
	
	
	public static String DEFAULT_RESPONSIBILITY_QUERY="logResponsibility(?R)";
	public static String DEFAULT_EXECUTION_QUERY="responsibilityExecution(?R,?I)";
	public static String DEFAULT_METHOD_QUERY="executionMethod(?I,?M)";
	public static String DEFAULT_CLASS_QUERY="executionDeclaredClass(?I,?C)";
	public static String DEFAULT_COMPONENT_CLASS_ASSOCIATION_QUERY="association(?C,?P,?CU),component(?C)";
	public static String DEFAULT_COMPONENT_RESPONSIBILITY_ASSOCIATION_QUERY="hasResponsibility(?C,?R)";
	
	public LogQueryFactory(){}

	public static LogQueryFactory getInstance(){
		if(instance==null){
			instance=new LogQueryFactory();
		}
		return instance;
	}
	
	public String createResponsibilityPredicate(String responsibilityName){
		return ("logResponsibility("+responsibilityName+").");
	}
	
	public String createExecutionPredicate(String responsibilityName, String exectutionId){
		return ("responsibilityExecution("+responsibilityName+","+exectutionId+").");
	}
	
	public String createExecutionMethodPredicate(String executionId, String methodName){
		return ("executionMethod("+executionId+","+methodName+").");
	}
	
	public String createExecutionDeclaredClass(String executionId, String className){
		return ("executionDeclaredClass("+executionId+","+className+").");
	}
	
	public String createComponentClassAssociationQuery(String className, String packageName){
		return "association(?C,"+packageName+","+className+"),component(?C)";
	}
	
	public  String createComponentResponsibilityAssociationQuery(String responsibility){
		return "hasResponsibility(?C,"+responsibility+")";
	}
}
