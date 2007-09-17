package org.design.rules4Java.ui.view.modelProviders.vars;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class VarList {
  private List<Var> vars = new ArrayList<Var>();
  private Set<IVarListViewer> changeListeners = new HashSet<IVarListViewer>();

  public VarList() {
  }

  public VarList(List<Var> newVars) {
    vars = newVars;
  }

  public List<Var> getVars() {
    return vars;
  }

  public void addVar(Var v) {
    vars.add(v);
    Iterator iterator = changeListeners.iterator();
    while (iterator.hasNext())
      ((IVarListViewer) iterator.next()).addVar(v);
  }

  public void removeVar(Var v) {
    vars.remove(v);
    for (Iterator<IVarListViewer> iterator = changeListeners.iterator(); iterator
        .hasNext();) {
      iterator.next().removeVar(v);
    }
  }

  public void removeChangeListener(IVarListViewer viewer) {
    changeListeners.remove(viewer);
  }

  public void addChangeListener(IVarListViewer viewer) {
    changeListeners.add(viewer);
  }

  public int size() {
    return vars.size();
  }
}
