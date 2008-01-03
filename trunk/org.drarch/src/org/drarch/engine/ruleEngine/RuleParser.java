/**
 * 
 */
package org.drarch.engine.ruleEngine;

import java.io.File;
import java.util.Collections;
import java.util.List;

import org.drarch.engine.ruleModel.DrarchFileModel;
import org.drarch.engine.ruleModel.Rule;
import org.drarch.engine.ruleModel.RuleModelPackage;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

/**
 * @author @author maldonadofacundo@gmail.com (Facundo Maldonado)
 *
 */
public class RuleParser {

	@SuppressWarnings("unchecked")
    public List<Rule> getRules(String rulesFilePath){
		@SuppressWarnings("unused")
        RuleModelPackage rmp = RuleModelPackage.eINSTANCE;
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getLoadOptions().put(
		        XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);

		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
		        .put(Resource.Factory.Registry.DEFAULT_EXTENSION,
		                new XMIResourceFactoryImpl());

		URI uri = URI.createFileURI(new File(rulesFilePath).getAbsolutePath());

		Resource resource = resourceSet.getResource(uri, true);
		if (resource.getContents().isEmpty()) 
			return Collections.EMPTY_LIST;
		DrarchFileModel fileModel = (DrarchFileModel) resource.getContents().get(0);
		return fileModel.getRules();
	}
	
}
