package org.design.drarch;

import java.util.List;

import org.design.rules4Java.engine.coreEngine.RuleManager;
import org.design.rules4Java.engine.coreEngine.StepsManager;
import org.design.rules4Java.engine.coreEngine.engineModel.KnowledgeBase;
import org.design.rules4Java.engine.coreEngine.engineModel.QueryEngine;
import org.design.rules4Java.engine.coreEngine.engineModel.exceptions.DrarchEngineModelException;
import org.design.rules4Java.util.Util;
import org.eclipse.ui.IWorkingSet;
import org.eclipse.ui.IWorkingSetManager;

/**
 * 
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public class Application {
 
//  private static final String DEFAULT_XML_RULES = "DefaultRules.drarch";
 
//  private QueryEngine queryEngine;
//  private SuggestMaker suggester = new SuggestMaker();

//  private List<Rule> ruleList;
//  private String externalFilePath;
  
  
  DrarchPlugin plugin;
//  RuleManager ruleManager;
//  KnowledgeBase knowledgeBase;  
  DrarchEngine drarchEngine = DrarchEngine.INSTANCE;
//  StepsManager stepManager;
//  IWorkingSet currentWorkingSet;
  IWorkingSetManager workingSetManager;
//  QueryEngine queryEngine;
//  List ruleList;
  
  private static Application INSTANCE;
  private static final String DEFAULT_PATH = "";
  
  public static Application getInstance() {
	    if (INSTANCE == null) INSTANCE = new Application();
	    return INSTANCE;
	  }
  
  private Application() {
    plugin = DrarchPlugin.getDefault();
    workingSetManager = plugin.getWorkbench().getWorkingSetManager();
  }

  @SuppressWarnings("unchecked")
public void initEngine() throws DrarchEngineModelException {
	  drarchEngine = DrarchEngine.INSTANCE;
	  drarchEngine.initialize();
//	  queryEngine = drarchEngine.initializeQueryEngine(workingSetManager);
//	  ruleManager = drarchEngine.initializeRuleManager(queryEngine);
//	  ruleList    = drarchEngine.getRulesFromRulesFile(DEFAULT_PATH);
//	  stepManager = drarchEngine.initializeStepsManager();
//	  try{
//		  knowledgeBase = drarchEngine.initializeKnowledgeBase(queryEngine);
//	  }catch(RuntimeException e){
//		  e.printStackTrace();
//	  }
	  
//    queryEngine = EngineModelFactory.INSTANCE.createQueryEngine();//new QueryEngineImpl();
//    ((QueryEngineImpl)queryEngine).setWorkingSet(currentWorkingSet);
//    Util.getInstance().setCurrentWorkingSet(currentWorkingSet);
//    ((QueryEngineImpl)queryEngine).setWorkingSetManager(workingSetManager);
//    ((QueryEngineImpl) queryEngine).init();
    //Util.getInstance().setQueryEngine(queryEngine);
//    ruleManager = RuleManager.getInstance();
//    ruleManager.setQueryEngine(queryEngine);
//    ruleManager.setSuggestMaker(suggester);
//    parseRulesFile();
//    stepManager = StepsManagerImpl.getInstance();
//    knowledgeBase = new KnowledgeBaseImpl();
//    knowledgeBase.generateFile();
//    try {
//	    ((KnowledgeBaseImpl) knowledgeBase).setInclude(((QueryEngineImpl) queryEngine).getWorkingSetNode());
//    } catch (DrarchEngineModelException e) {
//	    // TODO Auto-generated catch block
//	    e.printStackTrace();
//    }
    //Util.getInstance().setBase(knowledgeBase);
  }

  public IWorkingSetManager getWorkingSetManager(){
	  return workingSetManager;
  }
  
  /**
   * TODO: reset all if the user select another source or change the rule file
 * @throws DrarchEngineModelException 
   *
   */
  /*private void resetEnvironment() throws DrarchEngineModelException{
	  INSTANCE = new Application();
	  DrarchEngine.INSTANCE.resetDrarchEngine();
	  DrarchEngine.INSTANCE.resetStepsManager();
	  init();
  }*/
//  private void parseRulesFile() {
//    try {
//      String path;
//      externalFilePath = Util.getInstance().getExternalFilePath();
//      if (externalFilePath == null) {
//        URL relativeURL = DrarchPlugin.getDefault().getBundle().getEntry("/");
//        URL localURL = FileLocator.toFileURL(relativeURL);
//        path = localURL.getPath() + "res/rules/" + DEFAULT_XML_RULES;
//      } else {
//        path = externalFilePath;
//      }
//      ParseRuleFile parser = new ParseRuleFile(path);
//      parser.parseFile();
//      defaultsRules = parser.getFileModel().getRules();
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//  }

//  public List<Rule> getDefaultList() {
//    return defaultsRules;
//  }

//  public QueryEngine getQueryEngine() {
//    return queryEngine;
//  }
//
//  public IWorkingSet getCurrentWorkingSet() {
//    return currentWorkingSet;
//  }
//
//  public void setCurrentWorkingSet(IWorkingSet currentWorkingSet) {
//    this.currentWorkingSet = currentWorkingSet;
//    /*try {
//	    resetEnvironment();
//    } catch (DrarchEngineModelException e) {
//	    // TODO Auto-generated catch block
//	    e.printStackTrace();
//    }*/
//  }
////
//
//  public void setWorkingSetManager(IWorkingSetManager workingSetManager) {
//    this.workingSetManager = workingSetManager;
//  }
//
//  public SuggestMaker getSuggester() {
//    return suggester;
//  }
//
//  public KnowledgeBase getKnowledegeBase() {
//    return knowledgeBase;
//  }
//  

}