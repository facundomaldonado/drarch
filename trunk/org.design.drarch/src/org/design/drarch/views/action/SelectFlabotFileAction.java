package org.design.drarch.views.action;

import org.design.drarch.DrarchApplication;
import org.design.drarch.DrarchConstants;
import org.design.drarch.diagram.flabot.DiagramManager;
import org.eclipse.jface.action.Action;

/**
 * 
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public class SelectFlabotFileAction extends Action {

  public void run() {
      //Si todo esta bien, elijo el archivo flabot destino
	  DrarchApplication.INSTANCE.getDrarchEngine().startEngine();
	  
      FileSelectionAction act = new FileSelectionAction();
      act.run();
      
      String fileName = act.getfileName();
      String path2project = DrarchApplication.INSTANCE.getCurrentSession().getProjectLocation();
      String path2file = path2project + DrarchConstants.NEW_PROJECT_PATH_TO_FLABOT_FILES + fileName;
      
      DiagramManager.getInstance().setFileName(path2file);
      
  }
  
}
