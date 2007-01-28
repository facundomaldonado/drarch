package org.design.rules4Java.engine.engine;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import org.design.rules4Java.engine.ruleModel.Rule;


/**
 * 
 * @author pelado
 */
public abstract class StepsManager {
	
	private Vector<StepAction> steps;
	private int currentIndexStep;
	private StepAction currentStep;
	
	@SuppressWarnings("unused")
	protected static StepsManager instance;
	
	protected StepsManager(){
		currentIndexStep = 0;
		steps = new Vector<StepAction>();
		loadStepsfromFile();
	}

	public void nextStep(){
		if (steps.size() > currentIndexStep)
			currentIndexStep++;
	}
	
	public boolean hasNext(){
		return (steps.size() >= currentIndexStep);
	}
	
	public void startStep(){
	
		if (steps.size() > currentIndexStep){
			currentStep = (StepAction) steps.get(currentIndexStep);
		}
		else{
			currentStep = (StepAction) steps.get(currentIndexStep - 1);
		}
		if (hasBackStep()){
			StepAction backStep = (StepAction) steps.get(currentIndexStep - 1);
			currentStep.loadFacts(backStep.getPublishedFacts());		
		}
		else{
			currentStep.loadFacts(new LinkedList());
		}
		currentStep.run();
	}

	private boolean hasBackStep() {
		return (currentIndexStep > 0);
	}
	
	public List getStepSuggests(){
		return ((StepAction)steps.get(currentIndexStep)).getSuggests();
	}
		
	public void loadStepsfromFile(){
		List rules = getDefaultList();
		
		Iterator iter = rules.iterator();
		while (iter.hasNext()){
			Rule rule = (Rule)iter.next();
			StepAction step = createStepAction(rule);//new StepAction(rule);
			steps.add(step);
		}
	}

	public int getNumberStep() {
		return this.currentIndexStep;
	}

	public void restart() {
		instance = null;
	}
	
	protected abstract List getDefaultList();
	protected abstract StepAction createStepAction(Rule rule);
}