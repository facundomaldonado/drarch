package org.drarch.engine.stepEngine.drarch;

import org.drarch.engine.stepEngine.AlgorithmicPhase;

public class DrarchAlgorithmicPhase extends AlgorithmicPhase {

	private DrarchAlgorithmicPhaseHelper helper;
	
	public DrarchAlgorithmicPhase(DrarchAlgorithmicPhaseHelper aHelper) {
		super(aHelper.getAlgorithm());
	}

}
