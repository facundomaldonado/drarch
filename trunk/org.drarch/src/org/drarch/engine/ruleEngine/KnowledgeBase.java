/**
 * 
 */
package org.drarch.engine.ruleEngine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.imageio.stream.FileImageOutputStream;

import org.apache.log4j.Logger;

import ca.ubc.jquery.gui.results.WorkingSetNode;

/**
 * @author @author maldonadofacundo@gmail.com (Facundo Maldonado)
 *
 */
public class KnowledgeBase {

	private static final Logger logger = Logger.getLogger(KnowledgeBase.class.getName());
	
	private WorkingSetNode workingSetNode;
	private Map<String , File> factFiles;
	
    public KnowledgeBase(Map<String , File> factFiles, WorkingSetNode workingSetNode) {
    	this.factFiles = factFiles;
    	this.workingSetNode = workingSetNode;
    	includeFilesInFactBase();
    }
	
	/* (non-Javadoc)
	 * @see org.design.rules4Java.engine.ruleEngine.IKnowledgeBase#addFact(java.lang.String)
	 */
	public void addFact(String predicate, String factBase) {
		File factBaseFile = factFiles.get(factBase + ".rub");
		if (null != factBaseFile && !exist(predicate, factBaseFile)) {
        	FileImageOutputStream fios;
            try {
                fios = new FileImageOutputStream(factBaseFile);
	        	fios.seek(factBaseFile.length());
	        	predicate = predicate + "\n";
	        	byte[] utf8Bytes = predicate.getBytes("UTF-8");
	        	fios.write(utf8Bytes);
	        	fios.close();
            } catch (FileNotFoundException e) {
    			logger.info(" FileNotFoundException in exist method in KnowledgeBase class");
    			throw new RuntimeException();
    		} catch (IOException e) {
    			logger.info(" IOException in addFact method in KnowledgeBase class");
    			throw new RuntimeException();
    		}
        }
	}

	/* (non-Javadoc)
	 * @see org.design.rules4Java.engine.ruleEngine.IKnowledgeBase#exist(java.lang.String)
	 */
	private boolean exist(String predicate, File factFile) {
		FileImageOutputStream fios = null;
		try {
			fios = new FileImageOutputStream(factFile);
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
			logger.info(" FileNotFoundException in exist method in KnowledgeBase class");
			throw new RuntimeException();
		} catch (IOException e) {
			logger.info(" IOException in addFact method in KnowledgeBase class");
			throw new RuntimeException();
		}
	}

	/* (non-Javadoc)
	 * @see org.design.rules4Java.engine.ruleEngine.IKnowledgeBase#removeFact(java.lang.String)
	 */
	public void removeFact(String predicate, String factBase) {
		File factBaseFile = factFiles.get(factBase);
		try {
			FileImageOutputStream fios = new FileImageOutputStream(factBaseFile);
			String index = "";
			while (index != null) {
				if (index.equals(predicate)) {
					String erase = new String();
					fios.seek(fios.getStreamPosition() - predicate.length()- 1);
					for (int i = 0; i < predicate.length(); i++)
						erase = erase + " ";
					byte[] utf8Bytes = erase.getBytes("UTF-8");
					fios.write(utf8Bytes);
				}
				index = fios.readLine();
			}
			fios.close();
		} catch (FileNotFoundException e) {
			logger.info(" FileNotFoundException in exist method in KnowledgeBase class");
			throw new RuntimeException();
		} catch (IOException e) {
			logger.info(" IOException in addFact method in KnowledgeBase class");
			throw new RuntimeException();
		}
	}
	
	private void includeFilesInFactBase(){
		String userIncludeFileName = workingSetNode.getUserIncludeFile();
		File userIncludeFile = new File(userIncludeFileName);
		PrintWriter writer;
		try {
			writer = new PrintWriter(new FileWriter(userIncludeFile));
			for(File currentFile : factFiles.values()) {
				String currentFilePath = currentFile.getAbsolutePath();
				logger.debug(currentFile.getAbsolutePath());
				
				String include = currentFilePath.replace("\\", "\\\\");
				writer.println("#include \"file:///" + include + "\"");
			}
			writer.println();
			writer.close();
		} catch (IOException e) {
			throw new RuntimeException("IOException in method includeFilesInFactBase in class KnowledgeBase", e);
		}
		workingSetNode.reloadRules();
	}
}
