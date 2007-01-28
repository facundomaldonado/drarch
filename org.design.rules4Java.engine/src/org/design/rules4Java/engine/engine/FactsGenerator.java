package org.design.rules4Java.engine.engine;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.design.rules4Java.engine.engine.engineModel.QueryResult;
import org.design.rules4Java.engine.engine.engineModel.Suggest;
import org.design.rules4Java.engine.ruleModel.Fact;
import org.design.rules4Java.engine.ruleModel.FactSet;
import org.design.rules4Java.engine.ruleModel.RuleModelFactory;
import org.design.rules4Java.engine.ruleModel.Var;


/**
 * 
 * @author pelado
 */
public class FactsGenerator {

	/**
	 * @param suggestList
	 * @param factSetTemplate
	 * @param varList
	 * @return List of parsed Facts
	 */
	@SuppressWarnings("unchecked") 
	List getFacts(List suggestList, FactSet factSetTemplate, List varList){
		LinkedList finalFacts=new LinkedList();
		System.out.println("FactsGenerators");
		/**
		 * recorro la lista de sugerencias tomando las que estan aceptadas,
		 */
		Iterator iSug= suggestList.iterator();
		while(iSug.hasNext()){
			Suggest s= (Suggest)iSug.next();
			if (s.getApply()){
				QueryResult result=s.getResult();
				/**
				 * recorro el template de facts tomando sentencia por 
				 * sentencia y reemplazando las variables por el valor
				 * correspondiente
				 */
				FactSet finalFactSet=RuleModelFactory.eINSTANCE.createFactSet();
				Iterator itemplate=factSetTemplate.getFactTemplates().iterator();
				while (itemplate.hasNext()) {
					Fact finalFact;
					Fact factTemplate = (Fact) itemplate.next();
					String factText= factTemplate.getFactText();
					
					/**
					 * recorro la lista de var, obteniendo el valor de
					 * cada una del resultado del query y reemplazandola
					 * en el template del fact
					 */
					Iterator ivar= varList.iterator();
					while (ivar.hasNext()) {
						Var var = (Var) ivar.next();
						String value=result.getValueOfVar(var.getVarText());
						if (!value.equals("")){
							factText=factText.replace(var.getVarText(),value);
						}
					}
					finalFact=RuleModelFactory.eINSTANCE.createFact();
					finalFact.setFactText(factText);
					finalFactSet.getFactTemplates().add(finalFact);
				}
				finalFacts.add(finalFactSet);
			}
		}
		
		return finalFacts;
	}
}
