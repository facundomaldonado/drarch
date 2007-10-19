package org.design.drarch.engine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;

import javax.imageio.stream.FileImageOutputStream;

import org.apache.log4j.Logger;
import org.design.drarch.DrarchApplication;
import org.design.drarch.DrarchConstants;
import org.design.drarch.DrarchPlugin;
import org.design.rules4Java.engine.coreEngine.engineModel.jqueryImpl.KnowledgeBaseAbstractImpl;
import org.design.rules4Java.engine.exceptions.DrarchEngineModelException;
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

	private static Logger	logger	= Logger.getLogger(DrarchPlugin.class.getName());

	private File	      temporalFactBase;

	public KnowledgeBaseImpl(WorkingSetNode workingSetNode) {
		super(workingSetNode);
	}

	public void loadFiles() {
		String path2KnowledgeBase = DrarchApplication.INSTANCE.getCurrentSession().getPathToKnowledgeBase();
		String path2TemporalBase = DrarchApplication.INSTANCE.getCurrentSession().getPathToTemporalKnowledgeBase();
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
				if (res instanceof IFile) {
					IFile file = (IFile) res;
					String path = file.getLocation().toString();
					File f = new File(path);
					knoledgeBaseFiles.add(f);
				}
			}
		} catch (CoreException e) {
			logger.error("CoreException while trying to access to knoledgeBase resources", e);
		}
		//Al final agrego el path al archivo de predicados que ire actualizando
		//en la superclase se define el atributo file, que lo inicializo en este momento
		factBaseFile = new File(path2KnowledgeBase);
		temporalFactBase = new File(path2TemporalBase);
		
	}

	public void addTemporalFact(String predicate) throws DrarchEngineModelException {
		try {
			if (!existTemporalFact(predicate)) {
				FileImageOutputStream fios = new FileImageOutputStream(temporalFactBase);
				fios.seek(temporalFactBase.length());
				predicate = predicate + "\n";
				byte[] utf8Bytes = predicate.getBytes("UTF-8");
				fios.write(utf8Bytes);
				fios.close();
			}
		} catch (DrarchEngineModelException e) {
			throw e;
		} catch (FileNotFoundException e) {
			logger.info("Wrap FileNotFoundException in addFact method in KnowledgeBaseImpl class. Throw DrarchEngineModelException");
			throw new DrarchEngineModelException("FileNotFoundException in method addFact in class "
			        + "KnowledgeBaseImpl while trying to open database file", e);
		} catch (UnsupportedEncodingException e) {
			logger.info("Wrap UnsupportedEncodingException in addFact method in KnowledgeBaseImpl class. Throw DrarchEngineModelException");
			throw new DrarchEngineModelException("UnsupportedEncodingException in method addFact in class "
			        + "KnowledgeBaseImpl while trying to encoding in UTF8 the new fact", e);
		} catch (IOException e) {
			logger.info("Wrap IOException in addFact method in KnowledgeBaseImpl class. Throw DrarchEngineModelException");
			throw new DrarchEngineModelException("IOException in method addFact in class KnowledgeBaseImpl", e);
		}
	}

	public boolean existTemporalFact(String predicate) throws DrarchEngineModelException {
		FileImageOutputStream fios = null;
		try {
			fios = new FileImageOutputStream(temporalFactBase);
			String index = "";
			while (index != null) {
				if (index.equals(predicate)) {
					return true;
				}
				index = fios.readLine();
			}
			fios.close();
			return false;
		} catch (FileNotFoundException e) {
			logger.info("Wrap FileNotFoundException in exist method in KnowledgeBaseImpl class. Throw DrarchEngineModelException");
			throw new DrarchEngineModelException("FileNotFoundException in method exist in class KnowledgeBaseImpl", e);
		} catch (IOException e) {
			logger.info("Wrap IOException in addFact method in KnowledgeBaseImpl class. Throw DrarchEngineModelException");
			throw new DrarchEngineModelException("IOException in method exist in class KnowledgeBaseImpl", e);
		}
	}

	public void removeTemporalFact(String predicate) throws DrarchEngineModelException {
		try {
			FileImageOutputStream fios = new FileImageOutputStream(temporalFactBase);
			String index = "";
			while (index != null) {
				if (index.equals(predicate)) {
					String erase = new String();
					fios
					        .seek(fios.getStreamPosition() - predicate.length()
					                - 1);
					for (int i = 0; i < predicate.length(); i++)
						erase = erase + " ";
					byte[] utf8Bytes = erase.getBytes("UTF-8");
					fios.write(utf8Bytes);
				}
				index = fios.readLine();
			}
			fios.close();

		} catch (FileNotFoundException e) {
			throw new DrarchEngineModelException("FileNotFoundException in method removeFact in class KnowledgeBaseImpl", e);
		} catch (IOException e) {
			throw new DrarchEngineModelException("IOException in method removeFact in class KnowledgeBaseImpl", e);
		}
	}

	@Override
    public void addFact(String predicate) throws DrarchEngineModelException {
		if (DrarchApplication.INSTANCE.getCurrentSession().isTemporalBaseActive())
			addTemporalFact(predicate);
		else
			super.addFact(predicate);
    }

	@Override
    public boolean exist(String predicate) throws DrarchEngineModelException {
		if (DrarchApplication.INSTANCE.getCurrentSession().isTemporalBaseActive())
			return existTemporalFact(predicate);
		else
			return super.exist(predicate);
	    
    }

	@Override
    public void removeFact(String predicate) throws DrarchEngineModelException {
		if (DrarchApplication.INSTANCE.getCurrentSession().isTemporalBaseActive())
			removeTemporalFact(predicate);
		else
			super.removeFact(predicate);
	    
    }

}
