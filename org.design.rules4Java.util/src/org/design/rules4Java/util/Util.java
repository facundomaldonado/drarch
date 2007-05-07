package org.design.rules4Java.util;

import org.design.rules4Java.engine.engine.engineModel.KnowledgeBase;
import org.design.rules4Java.engine.engine.engineModel.QueryEngine;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.ui.IWorkingSet;

public class Util {
  private static Util instance;
  private String externalFilePath;
  private QueryEngine queryEngine;
  private IWorkingSet currentWorkingSet;
  private KnowledgeBase base;

  public String getExternalFilePath() {
    return externalFilePath;
  }

  private Util() {
  }

  public static Util getInstance() {
    if (instance == null) {
      instance = new Util();
    }
    return instance;
  }

  public void setExternalFilePath(String externalFilePath) {
    if (externalFilePath.equals("")) externalFilePath = null;

    this.externalFilePath = externalFilePath;
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
    return currentWorkingSet;
  }

  public void setCurrentWorkingSet(IWorkingSet current) {
    this.currentWorkingSet = current;
  }

  public QueryEngine getQueryEngine() {
    return queryEngine;
  }

  public void setQueryEngine(QueryEngine queryEngine) {
    this.queryEngine = queryEngine;
  }

  public KnowledgeBase getBase() {
    return base;
  }

  public void setBase(KnowledgeBase base) {
    this.base = base;
  }
}
