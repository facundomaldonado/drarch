package org.design.rules4Java.ui.view.modelProviders.vars;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class VarList {
	
	private List vars = new ArrayList(); 
	private Set changeListeners = new HashSet();
	
	public VarList(){
	}
	
	public VarList(List newVars){
		vars=newVars;
	}
	
	public List getVars() {
		return vars;
	}
		
	@SuppressWarnings("unchecked")
	public void addVar( Var v) {
		vars.add(v);
		Iterator iterator = changeListeners.iterator();
		while (iterator.hasNext())
			((IVarListViewer) iterator.next()).addVar(v);
	}

	public void removeVar(Var v) {
		vars.remove(v);
		Iterator iterator = changeListeners.iterator();
		while (iterator.hasNext())
			((IVarListViewer) iterator.next()).removeVar(v);
	}

	public void removeChangeListener(IVarListViewer viewer) {
		changeListeners.remove(viewer);
	}

	@SuppressWarnings("unchecked")
	public void addChangeListener(IVarListViewer viewer) {
		changeListeners.add(viewer);
	}
	
	public int size(){
		return vars.size();
	}
}