package org.design.drarch.ui.popup.action;

import java.util.List;

import org.design.drarch.diagram.DiagramPlugin;
import org.design.drarch.diagram.flabot.mapping.MappingModel;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.isistan.flabot.edit.multipage.FlabotMultiPageEditor;

public class SinchronizeWithDrarchAction extends SelectionAction {

	private static final String ID = "SINCHRONIZE_ACTION"; 
	
	public SinchronizeWithDrarchAction(IWorkbenchPart part) {
		super(part);
		setId(ID);
		setText("Sinchronize with Drarch");
	}

	public SinchronizeWithDrarchAction(IWorkbenchPart part, int style) {
		super(part, style);
		setId(ID);
		setText("Sinchronize With Drarch Action");
	}

	@Override
	protected boolean calculateEnabled() {
		return true;
	}

	public synchronized void run() {
		FlabotMultiPageEditor flabotMultipageEditor = getFlabotEditorPart();
		
		List<String> facts = MappingModel.getFactSet(flabotMultipageEditor.getModel().getCoreModel());
		
		
		for (String s : facts) {
			System.out.println(s);
		}
		
		// TODO: add facts to knowledge base.
		//KnowledgeBase knowledgeBase = DrarchApplication.INSTANCE.getDrarchEngine().getKnowledgeBase();
		
		MessageDialog.openInformation(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
				"Sinchronized message", "The model was sinchronized with Drarch.");
	}	
	
	private FlabotMultiPageEditor getFlabotEditorPart() {
	    IWorkbenchPage[] workbenchPageList = DiagramPlugin.getDefault()
	        .getWorkbench().getActiveWorkbenchWindow().getPages();
	    for (int i = 0; i < workbenchPageList.length; i++) {
	      IWorkbenchPage workbenchPage = workbenchPageList[i];
	      IEditorPart[] editorPartList = workbenchPage.getEditors();
	      for (int j = 0; j < editorPartList.length; j++) {
	        IEditorPart editorPart = editorPartList[j];
	        if (editorPart instanceof FlabotMultiPageEditor) {
	          // if (((FlabotMultiPageEditor)
	          // editorPart).getPartName().equals(fileName)){//bug
	          if (((FlabotMultiPageEditor) editorPart).getPartName().endsWith(
	              ".flabot")) {
	            return (FlabotMultiPageEditor) editorPart;
	          }
	        }
	      }
	    }
	    return null;
	  }
}
