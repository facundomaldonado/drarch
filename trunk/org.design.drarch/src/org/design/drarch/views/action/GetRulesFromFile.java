//package org.design.drarch.views.action;
//
//import org.design.drarch.DrarchEngine;
//import org.design.rules4Java.engine.parser.RulesFileParser;
//import org.design.rules4Java.engine.ruleModel.DrarchFileModel;
//import org.design.rules4Java.engine.ruleModel.Rule;
//import org.eclipse.jface.action.Action;
//
//import java.util.List;
//
//public class GetRulesFromFile extends Action {
//  private DrarchFileModel fileModel;
//  private RulesFileParser parser;
//
//  public GetRulesFromFile(String path) {
//    parser = DrarchEngine.INSTANCE.getRulesParser(path);//new RulesFileParser(path);
//  }
//
//  public void run() {
//    parser.parseFile();
//    fileModel = parser.getFileModel();
//  }
//
////  public List<Rule> getRuleList() {
////    return fileModel.getRules();
////  }
//}
