package org.drarch.engine.ruleEngine;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.drarch.engine.ruleModel.Fact;
import org.drarch.engine.ruleModel.FactSet;
import org.drarch.engine.ruleModel.RuleModelFactory;
import org.drarch.engine.ruleModel.Var;

/**
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public class FactsGenerator {

	/**
	 * @param suggestList
	 * @param factSetTemplate
	 * @param varList
	 * @return List of parsed Facts
	 */
	@SuppressWarnings("unchecked")
    public Set<FactSet> getFacts(Set<Suggest> suggestList, 
								  FactSet factSetTemplate, 
								  List<Var> varList) {
		
		
		Set<FactSet> finalFacts = new HashSet<FactSet>();
		/*
		 * Recorro la lista de sugerencias tomando las que estan aceptadas.
		 */
		for (Suggest suggest : suggestList) {
			if (suggest.isApply()) {
				QueryResult result = suggest.getResult();
				/*
				 * Recorro el template de facts tomando sentencia por sentencia y
				 * reemplazando las variables por el valor correspondiente.
				 */
				FactSet finalFactSet = RuleModelFactory.eINSTANCE.createFactSet();
				List<Fact> factSetTemplateList = factSetTemplate.getFactTemplates();
				for (Fact factTemplate : factSetTemplateList) {
					String factText = factTemplate.getFactText();
					/**
					 * Recorro la lista de var, obteniendo el valor de cada una del
					 * resultado del query y reemplazandola en el template del fact.
					 */
					for (Var var: varList) {
						String value = result.getValueOfVar(var.getVarText());
						if (!"".equals(value)) {
							factText = factText.replace(var.getVarText(), value);
						}
					}
					Fact finalFact = RuleModelFactory.eINSTANCE.createFact();
					finalFact.setFactText(factText);
					finalFactSet.getFactTemplates().add(finalFact);

                }
				finalFacts.add(finalFactSet);
			}
        }
		return finalFacts;
	}
}
