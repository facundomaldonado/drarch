package org.drarch.engine.ruleEngine;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.drarch.engine.ruleModel.Var;

/**
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
	public Set<Suggest> getSuggests(ResultSet queryResultSet, String template, List<Var> vars) {

		Set<Suggest> suggests = new HashSet<Suggest>();
		QueryResult queryResult;
		Suggest newSuggest;
		while (queryResultSet.hasMoreElements()) {
			queryResult = queryResultSet.next();
			List<String> values = new LinkedList<String>();
			for (Var var : vars) {
				String value = queryResult.getValueOfVar(var.getVarText());
				if (!value.equals("")) {
					values.add(value);
				}
			}
			String suggest = parseSuggest(vars, values, template);
			newSuggest = EngineFactory.createSuggest();
			newSuggest.setResult(queryResult);
			newSuggest.setSuggest(suggest);

			suggests.add(newSuggest);
		}
		return suggests;
	}

	private String replaceVarByValue(String var, String value, String currentSuggest) {

		/*
		 * Var es del tipo "?Var" y value es el string que reemplazara la
		 * ocurrencia de la variable en el texto de la sugerencia
		 */
		String aux = currentSuggest;
		currentSuggest = aux.replace(var, value);
		return currentSuggest;
	}

	private String parseSuggest(List<Var> vars, List<String> values, String suggestTemplate) {

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
