package org.design.rules4Java.engine.jqueryImpl.engineModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.stream.FileImageOutputStream;

import org.design.rules4Java.engine.engine.engineModel.KnowledgeBase;

import ca.ubc.jquery.gui.results.WorkingSetNode;


public abstract class KnowledgeBaseAbstractImpl implements KnowledgeBase{

	protected File file;
	
	public KnowledgeBaseAbstractImpl(){
	}
	
	public void addFact(String predicate) {
		try {
			if (!exist(predicate)){
				FileImageOutputStream fios = new FileImageOutputStream(file);
				fios.seek(file.length());
				predicate = predicate + "\n";
				byte [] utf8Bytes = predicate.getBytes ("UTF-8"); 
				fios.write(utf8Bytes);
				fios.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean exist(String predicate) throws Exception {
		FileImageOutputStream fios = new FileImageOutputStream(file);
		String index = "";
		while (index != null){
			if (index.equals(predicate)){
				return true;
			}
			index = fios.readLine();
		}
		fios.close();
		return false;
	}

	public void removeFact(String predicate){
		try {
			FileImageOutputStream fios = new FileImageOutputStream(file);
			String index = "";
			while (index != null){
				if (index.equals(predicate)){
					String erase = new String();
					fios.seek(fios.getStreamPosition() - predicate.length() - 1);
					for (int i = 0; i < predicate.length(); i++) 
						erase = erase + " ";
					byte [] utf8Bytes = erase.getBytes ("UTF-8"); 
					fios.write(utf8Bytes);
				}
				index = fios.readLine();
			}
			fios.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setInclude(WorkingSetNode workingSetNode){
		File userIncludeFile = new File(workingSetNode.getUserIncludeFile());
		PrintWriter writer;
		try {
			writer = new PrintWriter(new FileWriter(userIncludeFile));
			String aux = file.getAbsolutePath();
			String include = aux.replace("\\", "\\\\");
			writer.println("#include \"file:///" + include + "\"");
			writer.println();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		workingSetNode.reloadRules();
	}
}
