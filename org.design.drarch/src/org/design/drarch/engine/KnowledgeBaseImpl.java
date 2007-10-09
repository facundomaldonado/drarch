package org.design.drarch.engine;

import java.io.File;
import java.util.LinkedList;

import org.apache.log4j.Logger;
import org.design.drarch.DrarchApplication;
import org.design.drarch.DrarchConstants;
import org.design.drarch.DrarchPlugin;
import org.design.rules4Java.engine.coreEngine.engineModel.jqueryImpl.KnowledgeBaseAbstractImpl;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;

import ca.ubc.jquery.gui.results.WorkingSetNode;

/**
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public class KnowledgeBaseImpl extends KnowledgeBaseAbstractImpl {

	private static Logger	    logger	  = Logger.getLogger(DrarchPlugin.class
	                                              .getName());

	public KnowledgeBaseImpl(WorkingSetNode workingSetNode) {
		super(workingSetNode);
	}

	public void loadFiles() {
		String path2KnowledgeBase = DrarchApplication.INSTANCE
		        .getCurrentSession().getPathToKnowledgeBase();
		
		String projectName = DrarchApplication.INSTANCE.getCurrentSession().getProjectName();
		
		IWorkspaceRoot myWorkspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		IProject project = myWorkspaceRoot.getProject(projectName);
		IFolder knowledgeBaseFolder = project.getFolder(DrarchConstants.NEW_PROJECT_FACTS_FOLDER);
		knoledgeBaseFiles = new LinkedList<File>();
		IResource[] resources;
        try {
	        resources = knowledgeBaseFolder.members();
	        int i = 0;
	        while (resources.length > i) {
	        	IResource res = resources[i++];
	        	if(res instanceof IFile) {
		        	IFile file = (IFile)res;
		        	String path = file.getLocation().toString();
		        	File f = new File(path);
		    		knoledgeBaseFiles.add(f);
                }
			}
        } catch (CoreException e) {
        	logger.error("CoreException while trying to access to knoledgeBase respurces", e);
        } 
        //Al final agrego el path al archivo de predicados que ire actualizando
        //en la superclase se define el atributo file, que lo inicializo en este momento
		file = new File(path2KnowledgeBase);
	}

}
