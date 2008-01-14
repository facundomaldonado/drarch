/**
 * 
 */
package org.drarch.ui.view.actions;

import java.util.Iterator;
import java.util.Set;

import org.drarch.Activator;
import org.drarch.engine.DrarchEngine;
import org.drarch.engine.ruleEngine.Suggest;
import org.drarch.engine.stepEngine.IPhase;
import org.drarch.engine.stepEngine.Step;
import org.drarch.engine.stepEngine.drarch.DrarchInteractivePhase;
import org.drarch.ui.modelProviders.model.TreeObject;
import org.drarch.ui.modelProviders.model.TreeParent;
import org.drarch.ui.view.DrarchSuggestViewer;
import org.eclipse.jface.action.Action;

public class ExecuteStepAction extends Action {

	DrarchSuggestViewer viewer;

	/**
	 * 
	 */
	public ExecuteStepAction(String name, DrarchSuggestViewer viewer) {
		super(name);
		this.viewer = viewer;
		setImageDescriptor(Activator.getImageDescriptor("ff.gif"));
		setToolTipText("Execute Step");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.Action#run()
	 */
	@Override
	public void run() {

		IPhase current = DrarchEngine.INSTANCE.executePhase();
		showSuggests(current);

	}

	/**
	 * @param phase
	 */
	private void showSuggests(IPhase phase) {
		if (phase instanceof DrarchInteractivePhase) {
			DrarchInteractivePhase currentPhase = (DrarchInteractivePhase) phase;

			Set<Suggest> suggests = currentPhase.getLastStepSuggests();
			if (null != suggests) {
				TreeParent viewCurrentNode = viewer.getCurrentParent();

				buildViewTree(viewCurrentNode, currentPhase);
				viewCurrentNode = viewer.getCurrentParent();
				Iterator i = suggests.iterator();
				while (i.hasNext()) {
					Suggest sug = (Suggest) i.next();
					TreeObject child = new TreeObject(sug.getSuggest());
					child.setValue(sug);
					child.setSelected(sug.isApply());

					viewer.addChild(child, viewCurrentNode);
				}
			}
		}
	}

	/**
	 * @param viewCurrentNode
	 * @param currentPhase
	 */
	private void buildViewTree(TreeParent viewCurrentNode,
			DrarchInteractivePhase currentPhase) {

		Object nodeValue = viewCurrentNode.getValue();
		if (nodeValue instanceof Step) {
			viewer.setCurrentParentNode(viewCurrentNode.getParent());
			// cambia la raiz, por lo que debo recalcular el valor del nodo.
			viewCurrentNode = viewer.getCurrentParent();
			nodeValue = viewCurrentNode.getValue();
		}
		if (null == nodeValue) {
			// currentNode is root of the tree and this stepAction is
			// the first step of the phase
			TreeParent phaseTreeNode = new TreeParent(currentPhase.getName());
			phaseTreeNode.setValue(currentPhase);

			viewer.addChild(phaseTreeNode, viewCurrentNode);
			viewCurrentNode = viewer.getCurrentParent();
			// now viewCurrentNode.getValue must be currentPhase
			assert (viewCurrentNode.getValue().equals(phaseTreeNode));
			nodeValue = viewCurrentNode.getValue();
		}
		if (nodeValue instanceof DrarchInteractivePhase) {
			Step currentStep = ((DrarchInteractivePhase) nodeValue)
					.getCurrentStep();
			TreeParent stepTreeNode = new TreeParent(currentStep.getName());
			stepTreeNode.setValue(currentStep);

			viewer.addChild(stepTreeNode, viewCurrentNode);
			viewCurrentNode = viewer.getCurrentParent();
			// now viewCurrentNode must be stepTreeNode
			assert (viewCurrentNode.getValue().equals(stepTreeNode));
			nodeValue = viewCurrentNode.getValue();
		}
	}

}
