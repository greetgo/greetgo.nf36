package kz.greetgo.ng36.gen.ddl;

import kz.greetgo.ng36.gen.GeneratorConfig;
import kz.greetgo.ng36.gen.structure.Structure;

import java.io.PrintStream;

public class PrinterDDL_Nf3Tables extends PrinterDDL {
  public PrinterDDL_Nf3Tables(GeneratorConfig config, Structure structure) {
    super(config, structure);
  }

  @Override
  public void print(PrintStream printStream) {
    printStream.println("PrinterDDL_Nf3Tables ...");
  }
}
