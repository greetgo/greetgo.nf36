package kz.greetgo.ng36.gen;

import java.io.File;

public interface GeneratorDDL {
  void generateNf3Tables(File outFile);

  void generateSequences(File outFile);

  void generateNf3References(File outFile);

  void generateComments(File outFile);

}
