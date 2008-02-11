package org.drarch.ui.view.action;

import java.io.IOException;
import java.util.Collections;

import org.apache.log4j.Logger;
import org.drarch.diagram.trace.uiAction.AnaliceLogTraceAction;
import org.drarch.engine.DrarchEngine;
import org.drarch.engine.stepEngine.drarch.DrarchInteractivePhase;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.isistan.flabot.trace.log.TraceLog;
import org.isistan.flabot.util.resource.PathUtil;

public class GenerateLogFacts implements IObjectActionDelegate {

	Logger logger = Logger.getLogger(GenerateLogFacts.class.getName());

	public GenerateLogFacts() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		
		// Select .tracelog file
		StructuredSelection selection = (StructuredSelection) cachedSelection;
		IFile file = (IFile) selection.getFirstElement();
		IPath path = PathUtil.makeSystemAbsolute(file.getFullPath());
		String fileName = PathUtil.toString(path);
       	Resource resource = new XMIResourceImpl(URI.createFileURI(fileName));
       	try {
			resource.load(Collections.emptyMap());
		} catch (IOException e1) {
			logger.error(e1);
		}
        TraceLog loadedTracelog = (TraceLog) resource.getContents().get(0);

        
		DrarchInteractivePhase phase = (DrarchInteractivePhase) 
			DrarchEngine.INSTANCE.getCurrentPhase();

        // TODO: No hace falta pasarle nada! que lo agarre directo del contructor.
		AnaliceLogTraceAction analiceLog = new AnaliceLogTraceAction(
				phase.getPhaseHelper().getQueryEngine(), 
				phase.getPhaseHelper().getKnowledgebase(), 
				loadedTracelog, phase);
		analiceLog.run();
	}

	/**
	 * ISelection is cached in method selectionChanged for later use it in run()
	 */
	private ISelection cachedSelection;

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		logger.debug(selection);
		cachedSelection = selection;
	}
}
