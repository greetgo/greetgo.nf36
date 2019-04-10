package kz.greetgo.ng36.gen.ddl;

import kz.greetgo.ng36.gen.GeneratorConfig;
import kz.greetgo.ng36.gen.structure.Structure;

import java.io.PrintStream;

public class PrinterDDL_Comments extends PrinterDDL {
  public PrinterDDL_Comments(GeneratorConfig config, Structure structure) {
    super(config, structure);
  }

  @Override
  public void print(PrintStream printStream) {
    printStream.println("PrinterDDL_Comments ...");
  }
}
