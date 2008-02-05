package org.drarch.engine.stepEngine.drarch;

import java.io.File;
import java.util.Map;

import org.drarch.engine.stepEngine.IAlgorithm;
import org.eclipse.ui.IWorkingSet;

public class DrarchAlgorithmicPhaseHelper extends PhaseHelper {

    private IAlgorithm algorithm;
	
	public DrarchAlgorithmicPhaseHelper(String currentPhaseName, String flabotFileName, IAlgorithm algorithm,
    		Map<String, File> factFiles, IWorkingSet workingSet) {
    	super(currentPhaseName, flabotFileName, factFiles, workingSet);
	    this.algorithm = algorithm;
    }
	
	public IAlgorithm getAlgorithm() {
		return algorithm;
	}
	
}
