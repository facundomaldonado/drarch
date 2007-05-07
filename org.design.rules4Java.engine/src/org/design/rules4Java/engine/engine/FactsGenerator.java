package org.design.rules4Java.engine.engine;

import org.design.rules4Java.engine.engine.engineModel.QueryResult;
import org.design.rules4Java.engine.engine.engineModel.Suggest;
import org.design.rules4Java.engine.ruleModel.Fact;
import org.design.rules4Java.engine.ruleModel.FactSet;
import org.design.rules4Java.engine.ruleModel.RuleModelFactory;
import org.design.rules4Java.engine.ruleModel.Var;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * 
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
  public List<FactSet> getFacts(List<Suggest> suggestList,
      FactSet factSetTemplate, List<Var> varList) {
    List finalFacts = new LinkedList();

    /*
     * Recorro la lista de sugerencias tomando las que estan aceptadas.
     */
    for (Iterator<Suggest> iSug = suggestList.iterator(); iSug.hasNext();) {
      Suggest s = iSug.next();
      if (s.getApply()) {
        QueryResult result = s.getResult();

        /*
         * Recorro el template de facts tomando sentencia por sentencia y
         * reemplazando las variables por el valor correspondiente.
         */
        FactSet finalFactSet = RuleModelFactory.eINSTANCE.createFactSet();
        for (Iterator<Fact> itemplate = factSetTemplate.getFactTemplates()
            .iterator(); itemplate.hasNext();) {
          Fact factTemplate = itemplate.next();
          String factText = factTemplate.getFactText();

          /**
           * Recorro la lista de var, obteniendo el valor de cada una del
           * resultado del query y reemplazandola en el template del fact.
           */
          for (Iterator<Var> ivar = varList.iterator(); ivar.hasNext();) {
            Var var = ivar.next();
            String value = result.getValueOfVar(var.getVarText());
            if (!value.equals("")) {
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
