package kz.greetgo.ng36.gen.java;

import kz.greetgo.ng36.gen.GeneratorConfig;
import kz.greetgo.ng36.gen.structure.Structure;

public class GeneratorJavaImpl implements GeneratorJava {

  private final GeneratorConfig config;
  private final Structure structure;

  public GeneratorJavaImpl(GeneratorConfig config, Structure structure) {
    this.config = config;
    this.structure = structure;
  }

  @Override
  public void generate() {
    System.out.println("config = " + config + ", structure = " + structure);
  }

}
