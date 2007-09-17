package org.design.drarch.engine;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.design.rules4Java.engine.jqueryImpl.engineModel.KnowledgeBaseAbstractImpl;
import org.design.rules4Java.util.Util;

/**
 * 
 * @author nicolasfrontini@gmail.com (Nicolas Frontini)
 * @author maldonadofacundo@gmail.com (Facundo Maldonado)
 */
public class KnowledgeBaseImpl extends KnowledgeBaseAbstractImpl {

  private static final String FILE_NAME = "KnowledgeBase.rub";

  public KnowledgeBaseImpl() {
    super();
  }

  public void generateFile() {
    // genera el archivo con los hechos
    new File(Util.getInstance().getPath() + "/Drarch/rules").mkdirs();
    file = new File(Util.getInstance().getPath() + "/Drarch/rules/" + FILE_NAME);
    try {
      file.createNewFile();
      // Declaretion of predicates
      PrintWriter writer = new PrintWriter(new FileWriter(file));
      writer.println("component :: Object");
      writer.println("MODES");
      writer.println("(F) IS NONDET");
      writer.println("END");
      writer.println("");
      writer.println("interfaceLink :: String, String");
      writer.println("MODES");
      writer.println("(F, F) IS NONDET");
      writer.println("END");
      writer.println("");
      writer.println("interfaceModel :: String");
      writer.println("MODES");
      writer.println("(F) IS NONDET");
      writer.println("END");
      writer.println("");
      writer.println("port :: String");
      writer.println("MODES");
      writer.println("(F) IS NONDET");
      writer.println("END");
      writer.println("");
      writer.println("hasPort :: String, String");
      writer.println("MODES");
      writer.println("(F,F) IS NONDET");
      writer.println("END");
      writer.println("");
      writer.println("hasProvidedInterface :: String, String");
      writer.println("MODES");
      writer.println("(F, F) IS NONDET");
      writer.println("END");
      writer.println("");
      writer.println("hasRequiredInterface :: String, String");
      writer.println("MODES");
      writer.println("(F, F) IS NONDET");
      writer.println("END");
      writer.println("");
      // Asocia una responsabilidad, un paquete y un .java.
      writer.println("mapping :: Object, Object, Object");
      writer.println("MODES");
      writer.println("(F, F, F) IS NONDET");
      writer.println("END");
      writer.println("");
      writer.println("relationship :: String, String, String");
      writer.println("MODES");
      writer.println("(F, F, F) IS NONDET");
      writer.println("END");
      writer.println("");
      writer.println("responsibility :: Object");
      writer.println("MODES");
      writer.println("(F) IS NONDET");
      writer.println("END");
      writer.println("");
      writer.println("hasResponsibility :: Object, Object");
      writer.println("MODES");
      writer.println("(F, F) IS NONDET");
      writer.println("END");
      writer.println("");
      writer.println("association :: Object, Object, Object"); // asocia un
                                                                // componente,
                                                                // un paquete y
                                                                // un .java
      writer.println("MODES");
      writer.println("(F, F, F) IS NONDET");
      writer.println("END");
      writer.println("");
      writer.println("logResponsibility:: Object");
      writer.println("MODES");
      writer.println("(F) IS NONDET");
      writer.println("END");
      writer.println("");
      writer.println("responsibilityExecution :: Object, Object"); // id de
                                                                    // ejec,
                                                                    // resp name
      writer.println("MODES");
      writer.println("(F, F) IS NONDET");
      writer.println("END");
      writer.println("");
      // id de ejec, method name
      writer.println("executionMethod :: Object, Object");
      writer.println("MODES");
      writer.println("(F, F) IS NONDET");
      writer.println("END");
      writer.println("");
      // id de ejec, class name
      writer.println("executionDeclaredClass:: Object, Object");
      writer.println("MODES");
      writer.println("(F, F) IS NONDET");
      writer.println("END");
      writer.println("");
      writer.println();
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
