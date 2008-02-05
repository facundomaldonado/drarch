package org.drarch.engine.stepEngine.drarch;

import java.io.File;
import java.util.Map;

import org.drarch.engine.ruleEngine.EngineFactory;
import org.drarch.engine.ruleEngine.KnowledgeBase;
import org.eclipse.ui.IWorkingSet;

import ca.ubc.jquery.gui.results.WorkingSetNode;

public class PhaseHelper {

	private String flabotFileName;

	private Map<String, File> factBaseFiles;

	private WorkingSetNode phaseWorkingSetNode;

	protected KnowledgeBase knowledgebase;

	protected String phaseName;

	public PhaseHelper(String currentPhaseName, String flabotFileName,
			Map<String, File> factFiles, IWorkingSet workingSet) {

		this.phaseName = currentPhaseName;
		this.flabotFileName = flabotFileName;
		this.factBaseFiles = factFiles;

		phaseWorkingSetNode = new WorkingSetNode(workingSet);
		knowledgebase = EngineFactory.createKnowledgeBase(phaseWorkingSetNode,
				factBaseFiles);
	}

	public String getFlabotFileName() {
		return flabotFileName;
	}

	public KnowledgeBase getKnowledgebase() {
		return knowledgebase;
	}

	public String getPhaseName() {
		return phaseName;
	}

	public WorkingSetNode getPhaseWorkingSetNode() {
		return phaseWorkingSetNode;
	}

}
