package org.design.drarch.ui.popup.action;

import java.io.IOException;
import java.util.Collections;

import org.apache.log4j.Logger;
import org.design.drarch.DrarchApplication;
import org.design.drarch.diagram.trace.uiAction.AnaliceLogTraceAction;
import org.design.rules4Java.engine.coreEngine.engineModel.KnowledgeBase;
import org.design.rules4Java.engine.coreEngine.engineModel.QueryEngine;
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

        KnowledgeBase knowledgeBase = DrarchApplication.INSTANCE.getDrarchEngine().getKnowledgeBase();
		QueryEngine queryEngine = DrarchApplication.INSTANCE.getDrarchEngine().getQueryEngine();

		AnaliceLogTraceAction analiceLog = new AnaliceLogTraceAction(queryEngine, knowledgeBase, loadedTracelog);
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
