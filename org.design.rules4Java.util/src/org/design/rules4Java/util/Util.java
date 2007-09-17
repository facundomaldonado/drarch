package org.design.rules4Java.util;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.ui.IWorkingSet;

public class Util {
	
  private static Util INSTANCE;
  private String externalFilePath;
  private IWorkingSet currentWorkingSet;

  private Util() {
  }

  public static Util getInstance() {
    if (INSTANCE == null) {
    	INSTANCE = new Util();
    }
    return INSTANCE;
  }

  public String getPath() {
    IAdaptable[] elements = getCurrentWorkingSet().getElements();
    IAdaptable adaptable = elements[0];
    IResource resources;
    if (adaptable instanceof IResource) {
      resources = (IResource) adaptable;
    } else {
      resources = (IResource) adaptable.getAdapter(IResource.class);
    }
    String path = resources.getProject().getLocation().toString();
    return path;
  }

  private IWorkingSet getCurrentWorkingSet() {
    return ResourceLocator.INSTANCE.getCurrentWorkingSet();
  }

}
