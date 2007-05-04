package org.design.drarch;

import org.design.drarch.engine.KnowledgeBaseImpl;
import org.design.drarch.manager.StepsManagerImpl;
import org.design.rules4Java.engine.engine.RuleManager;
import org.design.rules4Java.engine.engine.StepsManager;
import org.design.rules4Java.engine.engine.SuggestMaker;
import org.design.rules4Java.engine.engine.engineModel.KnowledgeBase;
import org.design.rules4Java.engine.engine.engineModel.QueryEngine;
import org.design.rules4Java.engine.jqueryImpl.engineModel.QueryEngineImpl;
import org.design.rules4Java.engine.parser.ParseRuleFile;
import org.design.rules4Java.engine.ruleModel.Rule;
import org.design.rules4Java.util.Util;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.ui.IWorkingSet;
import org.eclipse.ui.IWorkingSetManager;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class Application {

  private static final String DEFAULT_XML_RULES = "DefaultRules.drarch";
  private static Application instance;
  private QueryEngine queryEngine;
  private SuggestMaker suggester=new SuggestMaker();
  private IWorkingSet currentWorkingSet;
  private IWorkingSetManager workingSetManager;
  private DrarchPlugin plugin;
  private List<Rule> defaultsRules;
  private String externalFilePath;
  private RuleManager ruleManager;
  private KnowledgeBase knowledgeBase;

  @SuppressWarnings("unused")
  private StepsManager stepManager;

  private Application() {
    plugin= DrarchPlugin.getDefault();
    workingSetManager = plugin.getWorkbench().getWorkingSetManager();
    externalFilePath = null;
  }

  public void init(){
    queryEngine = new QueryEngineImpl();
    queryEngine.setWorkingSet(currentWorkingSet);
    Util.getInstance().setCurrentWorkingSet(currentWorkingSet);
    queryEngine.setWorkingSetManager(workingSetManager);
    (( QueryEngineImpl)queryEngine).init();
    Util.getInstance().setQueryEngine(queryEngine);
    ruleManager= RuleManager.getInstance();
    ruleManager.setQueryEngine(queryEngine);
    ruleManager.setSuggestMaker(suggester);
    parseRulesFile();
    stepManager = StepsManagerImpl.getInstance();
    knowledgeBase = new KnowledgeBaseImpl();
    knowledgeBase.generateFile();
    ((KnowledgeBaseImpl)knowledgeBase).setInclude(((QueryEngineImpl)queryEngine).getWorkingSetNode());
    Util.getInstance().setBase(knowledgeBase);
  }

  public static synchronized Application getInstance() {
    if (instance == null)
      instance =  new Application();
    return instance;	
  }

  @SuppressWarnings("unchecked")
  private void parseRulesFile(){
    try {
      String path;
      externalFilePath= Util.getInstance().getExternalFilePath();
      if (externalFilePath == null){
        URL relativeURL = DrarchPlugin.getDefault().getBundle().getEntry("/");
        URL localURL = FileLocator.toFileURL(relativeURL);
        path = localURL.getPath() + "res/rules/" + DEFAULT_XML_RULES;
      }
      else {
        path = externalFilePath;
      }
      ParseRuleFile parser = new ParseRuleFile(path);
      parser.parseFile();
      defaultsRules=parser.getFileModel().getRules();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public List getDefaultList(){
    return defaultsRules;
  }
  public QueryEngine getQueryEngine() {
    return queryEngine;
  }
  public IWorkingSet getCurrentWorkingSet() {
    return currentWorkingSet;
  }

  public void setCurrentWorkingSet(IWorkingSet currentWorkingSet) {
    this.currentWorkingSet = currentWorkingSet;
  }

  public IWorkingSetManager getWorkingSetManager() {
    return workingSetManager;
  }

  public void setWorkingSetManager(IWorkingSetManager workingSetManager) {
    this.workingSetManager = workingSetManager;
  }

  public SuggestMaker getSuggester() {
    return suggester;
  }

  public KnowledgeBase getKnowledegeBase() {
    return knowledgeBase;
  }
}