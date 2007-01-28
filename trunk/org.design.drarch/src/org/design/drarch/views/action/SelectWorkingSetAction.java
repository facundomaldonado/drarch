package org.design.drarch.views.action;


import org.design.drarch.Application;
import org.design.drarch.diagram.flabot.DiagramManager;
import org.design.drarch.manager.StepsManagerImpl;
import org.design.rules4Java.ui.view.action.FileSelectionAction;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkingSet;
import org.eclipse.ui.IWorkingSetManager;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.IWorkingSetSelectionDialog;



public class SelectWorkingSetAction extends Action {
	
	private IWorkingSet workingSet;
	private IWorkbenchWindow window;
	private IWorkingSetManager workingSetManager;
	
	public SelectWorkingSetAction(IWorkingSetManager manager){
		workingSetManager=manager;
	}
	
	public IWorkingSet getWorkingSet(){
		return workingSet;
	}

	public void run() {
		window= PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		workingSet= getWorkingSetFromSource();
		if (workingSet!=null){
			Application.getInstance().setCurrentWorkingSet(getWorkingSet());
			
			Application.getInstance().init();
			StepsManagerImpl.getInstance().restart();
			DiagramManager.getInstance().restart();
		
			FileSelectionAction act=new FileSelectionAction();
			act.run();
			String fileName=act.getfileName();
			DiagramManager.getInstance().setFileName(fileName);
		}
	}
	
	private IWorkingSet getWorkingSetFromSource(){	
		/**
		 * Abre un dialogo para elegir un projecto
		 * ó un conjunto de clases.
		 */
		IWorkingSetSelectionDialog iws = workingSetManager.createWorkingSetSelectionDialog(window.getShell(), false);
		if (iws.open() == org.eclipse.jface.window.Window.OK && iws.getSelection() != null) {
			IWorkingSet selectedSet = iws.getSelection()[0];
			return selectedSet;
		}
		return null;
	}
	
	
}
