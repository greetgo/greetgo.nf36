package kz.greetgo.ng36.gen.ddl;

import kz.greetgo.ng36.gen.GeneratorConfig;
import kz.greetgo.ng36.gen.structure.Structure;

import java.io.PrintStream;

public abstract class PrinterDDL {

  protected final GeneratorConfig config;

  protected final Structure structure;

  public PrinterDDL(GeneratorConfig config, Structure structure) {
    this.config = config;
    this.structure = structure;
  }

  public abstract void print(PrintStream printStream);

}
