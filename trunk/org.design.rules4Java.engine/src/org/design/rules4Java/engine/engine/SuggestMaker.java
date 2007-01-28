package org.design.rules4Java.engine.engine;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.design.rules4Java.engine.engine.engineModel.QueryResult;
import org.design.rules4Java.engine.engine.engineModel.ResultSet;
import org.design.rules4Java.engine.engine.engineModel.Suggest;
import org.design.rules4Java.engine.engine.engineModel.impl.SuggestImpl;
import org.design.rules4Java.engine.ruleModel.Var;


/**
 * @author pelado
 */
public class SuggestMaker {
	
	public SuggestMaker(){}
	/**
	 * @param queryResult
	 * @param template
	 * @param vars
	 * @return una lista de Sugerencias-- no de Strings
	 */
	@SuppressWarnings("unchecked")
	public List<Suggest> getSuggests(ResultSet queryResult,String template, List vars){
		
		List suggests=new LinkedList();
		QueryResult result;
		Suggest s;
	while (queryResult.hasMoreElements())
	{
		result= (QueryResult) queryResult.next();
		Iterator ivar= vars.iterator();
		List values= new LinkedList();
		while (ivar.hasNext())
		{
			Var var=(Var)ivar.next();
			String value=result.getValueOfVar(var.getVarText());
			if(!value.equals(""))
				values.add(value);
		}
		String aux=parseSuggest(vars,values,template);
		s= new SuggestImpl();
		s.setResult(result);
		s.setSuggest(aux);
		/**
		 * PARCHAZO
		 */
		
		Iterator sug=suggests.iterator();
		boolean flag = false;
		while(sug.hasNext()){
			Suggest aux2=(Suggest)sug.next();
			String aux3 = aux2.getSuggest();
			if (aux3.equals(aux)){
				flag = true;
				break;
			}
		}
		if (!flag){
			suggests.add(s);
		}
	}
	return suggests;
 }

 private String replaceVarByValue(String var, String value, String currentSuggest){
	//var es del tipo "?Var" y value es el string que reemplazara la
	//ocurrencia de la variable en el texto de la sugerencia
	String aux=currentSuggest;
	currentSuggest=aux.replace(var,value);
	return currentSuggest;
 }

 public String parseSuggest(List vars, List values, String suggestTemplate){
	
	Iterator ivars=vars.iterator();
	Iterator ivalues=values.iterator();
	
	String currentSuggest=suggestTemplate;
	
	while (ivars.hasNext()){
		Var var=(Var)ivars.next();
		String value=(String)ivalues.next();
		currentSuggest= replaceVarByValue(var.getVarText(),value,currentSuggest);
	}
	return currentSuggest;
  }
}
