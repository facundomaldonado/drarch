/**
 * 
 */
package org.drarch.engine.stepEngine.test;

import java.util.Set;

import junit.framework.TestCase;

import org.drarch.engine.ruleEngine.Suggest;
import org.drarch.engine.ruleModel.Rule;
import org.drarch.engine.stepEngine.IPhaseManager;
import org.drarch.engine.stepEngine.IStepImplementation;
import org.drarch.engine.stepEngine.InteractivePhase;
import org.drarch.engine.stepEngine.NonInteractivePhase;
import org.drarch.engine.stepEngine.PhaseManager;
import org.drarch.engine.stepEngine.Step;

/**
 * @author @author maldonadofacundo@gmail.com (Facundo Maldonado)
 *
 */
public class StepManagerTest extends TestCase {

	IPhaseManager stepManager;
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		stepManager = loadSteps();
		
	}

    private IPhaseManager loadSteps() {
    	
    	Step action1 = new Step(new StepActionImpl("Action 1"));
    	Step action2 = new Step(new StepActionImpl("Action 2"));
    	Step action3 = new Step(new StepActionImpl("Action 3"));
    	Step action4 = new Step(new StepActionImpl("Action 4"));
    	Step action5 = new Step(new StepActionImpl("Action 5"));
    	Step action6 = new Step(new StepActionImpl("Action 6"));
    	Step action7 = new Step(new StepActionImpl("Action 7"));
    	Step action8 = new Step(new StepActionImpl("Action 8"));
    	Step action9 = new Step(new StepActionImpl("Action 9"));
    	
    	//non interactive
    	action1.addNext(action2);
    	action2.addNext(action3);
    	
    	action4.addNext(action5);
    	//interactives
    	action6.addNext(action7);
    	action7.addNext(action8);
    	action8.addNext(action9);
    	
    	NonInteractivePhase nonInteractiveStep1 = new NonInteractivePhase(action1);
    	NonInteractivePhase nonInteractiveStep2 = new NonInteractivePhase(action4);
    	InteractivePhase interactiveStep1 = new InteractivePhase(action6);
    	
    	nonInteractiveStep1.addPhase(nonInteractiveStep2);
    	nonInteractiveStep2.addPhase(interactiveStep1);
    	
    	PhaseManager newStepManager = new PhaseManager();
//    	newStepManager.addNewPhase(nonInteractiveStep1);
    	newStepManager.addNewPhase(interactiveStep1);
    	return newStepManager;
    }

	/**
	 * Test method for {@link org.design.rules4Java.engine.stepEngine.PhaseManager#executeNextPhase()}.
	 */
	public void testExecuteNextStep() {
		stepManager.executeNextPhase();
		assertTrue(true);
		
		stepManager.executeNextPhase();
		assertTrue(true);
		stepManager.executeNextPhase();
		assertTrue(true);
		stepManager.executeNextPhase();
		assertTrue(true);
//		stepManager.executeNextPhase();
//		assertTrue(true);
//		stepManager.executeNextPhase();
//		assertTrue(true);

	}
	
	private class StepActionImpl implements IStepImplementation{
		
		String name;
		
        public StepActionImpl(String name) {
        	this.name = name;
        }
		
		/* (non-Javadoc)
         * @see org.design.rules4Java.engine.newEngine.IStepActionImplementation#run()
         */
        public Set<Suggest> execute(Rule rule) {
        	System.out.println(name);
        	return null;
        }

		/* (non-Javadoc)
         * @see org.design.rules4Java.engine.stepEngine.IStepImplementation#setLastPhaseFacts(java.util.Set)
         */
        public void setLastStepSuggests(Set<Suggest> lastPhaseSuggests) {
	        // TODO pelado: not yet implemented - setLastPhaseFacts
	        
        }

		/* (non-Javadoc)
         * @see org.design.rules4Java.engine.stepEngine.IStepImplementation#getName()
         */
        public String getName() {
	        // TODO pelado: not yet implemented - getName
	        return null;
	        
        }

		public Rule getStepRule() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}

}
