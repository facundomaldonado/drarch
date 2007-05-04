package org.design.rules4Java.ui.view.action;

import org.design.rules4Java.engine.parser.ParseRuleFile;
import org.design.rules4Java.engine.ruleModel.DrarchFileModel;
import org.design.rules4Java.engine.ruleModel.Rule;
import org.eclipse.jface.action.Action;

import java.util.List;

public class GetRulesFromFile extends Action {
  private DrarchFileModel fileModel;
  private ParseRuleFile parser;

  public GetRulesFromFile(String path){
    parser=new ParseRuleFile(path);
  }

 public void run(){
    parser.parseFile();
    fileModel = parser.getFileModel();
  }

  public List<Rule> getRuleList(){
    return fileModel.getRules();
  }
}