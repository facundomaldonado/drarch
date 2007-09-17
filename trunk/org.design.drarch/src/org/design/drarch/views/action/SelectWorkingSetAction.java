package org.design.drarch.views.action;

import org.design.drarch.Application;
import org.design.drarch.diagram.flabot.DiagramManager;
import org.design.drarch.manager.StepsManagerImpl;
import org.design.rules4Java.engine.coreEngine.engineModel.exceptions.DrarchEngineModelException;
import org.design.rules4Java.util.ResourceLocator;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkingSet;
import org.eclipse.ui.IWorkingSetManager;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.IWorkingSetSelectionDialog;

/**
 * 
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public class SelectWorkingSetAction extends Action {

  private IWorkingSet workingSet;
  private IWorkbenchWindow window;
  private IWorkingSetManager workingSetManager;

  public SelectWorkingSetAction(IWorkingSetManager manager) {
    workingSetManager = manager;
  }

  public IWorkingSet getWorkingSet() {
    return workingSet;
  }

  public void run() {
    window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
    workingSet = selectWorkingSet();
    if (workingSet != null) {
    	ResourceLocator.INSTANCE.registerCurrentWorkingSet(workingSet);
      try {
    	  //Inicializar ENGINE
    	  Application.getInstance().initEngine();
    	  StepsManagerImpl.getInstance().restart();
          DiagramManager.getInstance().restart();
      } catch (DrarchEngineModelException e) {
  	    // TODO Auto-generated catch block
  	    e.printStackTrace();
      }
      //Si todo esta bien, elijo el archivo flabot destino
      FileSelectionAction act = new FileSelectionAction();
      act.run();
      String fileName = act.getfileName();
      DiagramManager.getInstance().setFileName(fileName);
    }
    else {
    	String[] buttons = {"OK"};
		MessageDialog informationMessage = 
				new MessageDialog(
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), 
					"Information Message",
					null,
					"Warning! \n\nNo workingSet selected.", 0,
					buttons, 0);
		informationMessage.open();
    }
  }

  private IWorkingSet selectWorkingSet() {
    /**
     * Abre un dialogo para elegir un projecto ó un conjunto de clases.
     */
    IWorkingSetSelectionDialog iws = workingSetManager
        .createWorkingSetSelectionDialog(window.getShell(), false);
    if (iws.open() == org.eclipse.jface.window.Window.OK && 
    	iws.getSelection() != null) {
      IWorkingSet selectedSet = iws.getSelection()[0];
      return selectedSet;
    }
    return null;
  }
}
