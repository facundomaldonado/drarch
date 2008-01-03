package org.drarch.ui.view.action;

import org.apache.log4j.Logger;
import org.drarch.diagram.flabot.DiagramManager;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.isistan.flabot.edit.editormodel.EditormodelPackage;
import org.isistan.flabot.edit.editormodel.FlabotFileModel;

public class LoadCoreModel implements IObjectActionDelegate{

	private static final Logger logger = Logger.getLogger(LoadCoreModel.class.getName());
	private ResourceSet resourceSet	= new ResourceSetImpl();
	private Resource resource = null;
	private FlabotFileModel fileModel;

	public LoadCoreModel() {
		super();
	}

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
	}

	public void run(IAction action) {
		logger.debug("Start load core model.");
		
		StructuredSelection selection = (StructuredSelection) cachedSelection;
		IFile file = (IFile) selection.getFirstElement();
		EditormodelPackage rmp = EditormodelPackage.eINSTANCE;

		resourceSet.getLoadOptions().put(
		        XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);

		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
		        .put(Resource.Factory.Registry.DEFAULT_EXTENSION,
		                new XMIResourceFactoryImpl());

		URI uri = URI.createFileURI(file.getLocation().toOSString());
		
		if (resource == null) {
			resource = resourceSet.getResource(uri, true);
		}
		fileModel = (FlabotFileModel) resource.getContents().get(0);
		
		DiagramManager.getInstance().setFlabotFileModel(fileModel);
		logger.debug("Finish load core model.");
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
