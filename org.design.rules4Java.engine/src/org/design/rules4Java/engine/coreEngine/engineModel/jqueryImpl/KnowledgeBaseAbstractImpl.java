package org.design.rules4Java.engine.coreEngine.engineModel.jqueryImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.imageio.stream.FileImageOutputStream;

import org.apache.log4j.Logger;
import org.design.rules4Java.engine.EnginePlugin;
import org.design.rules4Java.engine.coreEngine.engineModel.KnowledgeBase;
import org.design.rules4Java.engine.exceptions.DrarchEngineModelException;

import ca.ubc.jquery.gui.results.WorkingSetNode;

/**
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public abstract class KnowledgeBaseAbstractImpl implements KnowledgeBase {

	private static Logger logger = Logger.getLogger(EnginePlugin.class.getName());
	
	protected File file;
	protected List<File> knoledgeBaseFiles;
	private WorkingSetNode workingSetNode;

	public KnowledgeBaseAbstractImpl(WorkingSetNode workingSetNode) {
		this.workingSetNode = workingSetNode;
	}

	public void addFact(String predicate) throws DrarchEngineModelException {
        try {
	        if (!exist(predicate)) {
	        	FileImageOutputStream fios = new FileImageOutputStream(file);
	        	fios.seek(file.length());
	        	predicate = predicate + "\n";
	        	byte[] utf8Bytes = predicate.getBytes("UTF-8");
	        	fios.write(utf8Bytes);
	        	fios.close();
	        }
        } catch (DrarchEngineModelException e) {
        	throw e;
        } catch (FileNotFoundException e) {
        	logger.info("Wrap FileNotFoundException in addFact method in QueryEngineImpl class. Throw DrarchEngineModelException");
			throw new DrarchEngineModelException("FileNotFoundException in method addFact in class " +
        			"KnowledgeBaseAbstractImpl while trying to open database file", e);
        } catch (UnsupportedEncodingException e) {
        	logger.info("Wrap UnsupportedEncodingException in addFact method in QueryEngineImpl class. Throw DrarchEngineModelException");
        	throw new DrarchEngineModelException("UnsupportedEncodingException in method addFact in class " +
					"KnowledgeBaseAbstractImpl while trying to encoding in UTF8 the new fact", e);
        } catch (IOException e) {
        	logger.info("Wrap IOException in addFact method in QueryEngineImpl class. Throw DrarchEngineModelException");
			throw new DrarchEngineModelException("IOException in method addFact in class KnowledgeBaseAbstractImpl", e);
        }
	}

	public boolean exist(String predicate) throws DrarchEngineModelException {
		
		FileImageOutputStream fios = null;
		try {
			fios = new FileImageOutputStream(file);
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
			logger.info("Wrap FileNotFoundException in exist method in QueryEngineImpl class. Throw DrarchEngineModelException");
			throw new DrarchEngineModelException("FileNotFoundException in method exist in class KnowledgeBaseAbstractImpl", e);
		} catch (IOException e) {
			logger.info("Wrap IOException in addFact method in QueryEngineImpl class. Throw DrarchEngineModelException");
			throw new DrarchEngineModelException("IOException in method exist in class KnowledgeBaseAbstractImpl", e);
		}
	}

	public void removeFact(String predicate) throws DrarchEngineModelException {
		try {
			FileImageOutputStream fios = new FileImageOutputStream(file);
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
			throw new DrarchEngineModelException("FileNotFoundException in method removeFact in class KnowledgeBaseAbstractImpl", e);
		} catch (IOException e) {
			throw new DrarchEngineModelException("IOException in method removeFact in class KnowledgeBaseAbstractImpl", e);
		}
	}

	public void setInclude() throws DrarchEngineModelException {
		File userIncludeFile = new File(workingSetNode.getUserIncludeFile());
		PrintWriter writer;
		try {
			writer = new PrintWriter(new FileWriter(userIncludeFile));
			for(File currentFile : knoledgeBaseFiles) {
				String currentFilePath = currentFile.getAbsolutePath();
				
				logger.debug(currentFile.getAbsolutePath());
				
				String include = currentFilePath.replace("\\", "\\\\");
				writer.println("#include \"file:///" + include + "\"");
			}
			writer.println();
			writer.close();
		} catch (IOException e) {
			throw new DrarchEngineModelException("IOException in method setInclude in class KnowledgeBaseAbstractImpl",e);
		}
		workingSetNode.reloadRules();
	}

	public void setWorkingSetNode(WorkingSetNode workingSetNode) {
    	this.workingSetNode = workingSetNode;
    }

}
