package org.design.rules4Java.ui.view.modelProviders.vars;

public class Var {

	private String var;
	private boolean selected=false;
	
	public Var(String var){
		this.var=var;
	}

	public void setSelected(boolean select){
		this.selected=select;
	}
	
	public boolean isSelected(){
		return selected;
	}
	
	public String getVar(){
		return var;
	}
}
