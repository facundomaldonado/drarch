package org.design.rules4Java.engine.coreEngine;

import org.design.rules4Java.engine.coreEngine.engineModel.QueryResult;
import org.design.rules4Java.engine.coreEngine.engineModel.ResultSet;
import org.design.rules4Java.engine.coreEngine.engineModel.Suggest;
import org.design.rules4Java.engine.coreEngine.engineModel.defaultImpl.SuggestImpl;
import org.design.rules4Java.engine.ruleModel.Var;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public class SuggestMaker {

  public SuggestMaker() {
  }

  /**
   * @param queryResult
   * @param template
   * @param vars
   * @return una lista de Sugerencias-- no de Strings
   */
  public List<Suggest> getSuggests(ResultSet queryResult, 
		  						   String template,
		  						   List<Var> vars) {
	  
    List<Suggest> suggests = new LinkedList<Suggest>();
    QueryResult result;
    Suggest s;
    while (queryResult.hasMoreElements()) {
      result = queryResult.next();
      List<String> values = new LinkedList<String>();
      for (Iterator<Var> ivar = vars.iterator(); ivar.hasNext();) {
        String value = result.getValueOfVar(ivar.next().getVarText());
        if (!value.equals("")) {
          values.add(value);
        }
      }
      String aux = parseSuggest(vars, values, template);
      s = new SuggestImpl();
      s.setResult(result);
      s.setSuggest(aux);

      /**
       * TODO: PARCHAZO
       */

      boolean flag = false;
      for (Iterator sug = suggests.iterator(); sug.hasNext();) {
        Suggest aux2 = (Suggest) sug.next();
        String aux3 = aux2.getSuggest();
        if (aux3.equals(aux)) {
          flag = true;
          break;
        }
      }
      if (!flag) {
        suggests.add(s);
      }
    }
    return suggests;
  }

  private String replaceVarByValue(String var, String value,
      String currentSuggest) {

    /*
     * Var es del tipo "?Var" y value es el string que reemplazara la ocurrencia
     * de la variable en el texto de la sugerencia
     */
    String aux = currentSuggest;
    currentSuggest = aux.replace(var, value);
    return currentSuggest;
  }

  public String parseSuggest(List<Var> vars, List<String> values,
      String suggestTemplate) {

    Iterator<String> ivalues = values.iterator();
    String currentSuggest = suggestTemplate;
    for (Iterator<Var> ivars = vars.iterator(); ivars.hasNext();) {
      Var var = ivars.next();
      String value = ivalues.next();
      currentSuggest = replaceVarByValue(var.getVarText(), value, currentSuggest);
    }
    return currentSuggest;
  }
}
