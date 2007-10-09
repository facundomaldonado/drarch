package org.design.rules4Java.engine.coreEngine;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.design.rules4Java.engine.coreEngine.engineModel.KnowledgeBase;
import org.design.rules4Java.engine.coreEngine.engineModel.QueryEngine;
import org.design.rules4Java.engine.coreEngine.engineModel.Suggest;
import org.design.rules4Java.engine.exceptions.DrarchEngineModelException;
import org.design.rules4Java.engine.ruleModel.FactSet;
import org.design.rules4Java.engine.ruleModel.Rule;

/**
 * 
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public abstract class StepsManager {

	private List<StepAction>	  	steps;
	private int	                  	currentIndexStep;
	private StepAction	          	currentStep;
	protected static StepsManager	INSTANCE;
	private KnowledgeBase 			knowledgeBase;
	private QueryEngine 			queryEngine;
	
	private List<Rule> currentRules;

	/**
	 * antes de la creacion del StepsManager ya se deben tener las reglas parseadas
	 * @param rules
	 */
	public StepsManager(List<Rule> rules) {
		currentIndexStep = 0;
		currentRules = rules;
	}

	public void nextStep() {
		if (steps.size() > currentIndexStep) {
			currentIndexStep++;
		}
	}

	public boolean hasNext() {
		return (steps.size() > currentIndexStep);
	}

	public void startStep() throws DrarchEngineModelException {
		//TODO: REVISAR 
		if (steps.size() > currentIndexStep) {
			currentStep = steps.get(currentIndexStep);
		} else {
			currentStep = steps.get(currentIndexStep - 1);
		}
		if (hasBackStep()) {
			StepAction backStep = steps.get(currentIndexStep - 1);
			currentStep.loadFacts(backStep.getPublishedFacts());
		} else {
			currentStep.loadFacts(new LinkedList<FactSet>());
		}
		currentStep.run();
	}

	private boolean hasBackStep() {
		return (currentIndexStep > 0);
	}

	public List<Suggest> getStepSuggests() {
		return (steps.get(currentIndexStep)).getSuggests();
	}

	protected abstract StepAction createStepAction();
	
	/**
	 * agarra la lista de reglas y crea un step por cada una de ellas
	 *
	 */
	public void createStepsFromRules() {
		steps = new ArrayList<StepAction>();
		if (null != currentRules) {
			for (Rule rule: currentRules) {
				StepAction step = createStepAction();
				step.setKnowledgeBase(getKnowledgeBase());
				step.setQueryEngine(queryEngine);
				step.setRule(rule);
				steps.add(step);
			}
		}
	}

	public int getNumberStep() {
		return this.currentIndexStep;
	}

	public void restart() {
		INSTANCE = null;
	}

	protected KnowledgeBase getKnowledgeBase() {
    	return this.knowledgeBase;
    }

	public void setKnowledgeBase(KnowledgeBase knowledgeBase) {
    	this.knowledgeBase = knowledgeBase;
    }

	public QueryEngine getQueryEngine() {
    	return this.queryEngine;
    }

	public void setQueryEngine(QueryEngine queryEngine) {
    	this.queryEngine = queryEngine;
    }
}
