package org.design.rules4Java.engine.parser;

import java.io.File;
import java.util.List;

import org.design.rules4Java.engine.ruleModel.DrarchFileModel;
import org.design.rules4Java.engine.ruleModel.RuleModelPackage;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

/**
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public class RulesFileParser {

	private String	        path;
	private DrarchFileModel	fileModel;
	private ResourceSet	    resourceSet	= new ResourceSetImpl();
	private Resource	    resource	= null;

	public RulesFileParser(String path) {
		this.path = path;
		parseFile();
	}

	@SuppressWarnings("unchecked")
	private void parseFile() {
		@SuppressWarnings("unused")
		RuleModelPackage rmp = RuleModelPackage.eINSTANCE;

		resourceSet.getLoadOptions().put(
		        XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);

		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
		        .put(Resource.Factory.Registry.DEFAULT_EXTENSION,
		                new XMIResourceFactoryImpl());

		URI uri = URI.createFileURI(new File(path.toString()).getAbsolutePath());
		
		
		
		if (resource == null) {
			resource = resourceSet.getResource(uri, true);
		}
		fileModel = (DrarchFileModel) resource.getContents().get(0);
	}

	/**
	 * Returns the rules that were parsed from rulesFile
	 * @return List<Rule>
	 */
	public List getParsedRules() {
		return fileModel.getRules();
	}
}
