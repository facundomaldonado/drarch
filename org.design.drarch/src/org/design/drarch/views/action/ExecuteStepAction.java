package org.design.drarch.views.action;

import java.util.Iterator;
import java.util.List;

import org.design.rules4Java.engine.engine.engineModel.Suggest;
import org.design.rules4Java.ui.view.modelProviders.model.TreeObject;
import org.design.rules4Java.ui.view.modelProviders.model.TreeParent;
import org.eclipse.jface.action.Action;

/**
 * 
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public class ExecuteStepAction extends Action {

  private TreeParent root;
  private List suggests;
  private int step;

  public ExecuteStepAction(List suggests, int step) {
    this.suggests = suggests;
    this.step = step;
  }

  public TreeParent getInPut() {
    return root;
  }

  public void run() {
    int showStep = step + 1;
    TreeParent result = new TreeParent("Stage " + showStep);
    Iterator i = suggests.iterator();
    while (i.hasNext()) {
      Suggest sug = (Suggest) i.next();
      TreeObject child = new TreeObject(sug.getSuggest());
      child.setValue(sug);
      child.setSelected(sug.getApply());
      result.addChild(child);
    }
    root = result;
  }
}
