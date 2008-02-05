package org.drarch.engine.stepEngine;

public class AlgorithmicPhase implements IPhase {

	private IPhase NEXT_PHASE = null;

	private IPhase BEFORE_PHASE = null;
	
	private IAlgorithm algorithm;
	
	public AlgorithmicPhase(IAlgorithm anAlgorithm) {
		algorithm = anAlgorithm;
		
	}

	public void executePhase() {
		executeBeforeRun();
		algorithm.run();
		executeAfterRun();
	}
	
	protected void executeBeforeRun() {
		
	}
	
	protected void executeAfterRun(){
		
	}

	public String getName() {
		return algorithm.getName();
	}

	public void addPhase(IPhase nextPhase) {
		this.NEXT_PHASE = nextPhase;
	}

	public IPhase nextPhase() {
		return NEXT_PHASE;
	}

	public IPhase beforePhase() {
		return BEFORE_PHASE;
	}

}
