package kz.greetgo.ng36.gen.ddl;

import kz.greetgo.ng36.gen.GeneratorConfig;
import kz.greetgo.ng36.gen.structure.Structure;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

public class GeneratorDDLImpl implements GeneratorDDL {

  private final GeneratorConfig config;

  private final Structure structure;

  public GeneratorDDLImpl(GeneratorConfig config, Structure structure) {
    this.config = config;
    this.structure = structure;
  }

  private static void printToFile(File file, Consumer<PrintStream> consumer) {

    file.getParentFile().mkdirs();

    try (PrintStream pr = new PrintStream(file, StandardCharsets.UTF_8)) {

      consumer.accept(pr);

    } catch (IOException e) {

      throw new RuntimeException(e);

    }

  }

  @Override
  public void generateNf3Tables(File outFile) {

    PrinterDDL printerDDL = new PrinterDDL_Nf3Tables(config, structure);

    printToFile(outFile, printerDDL::print);

  }

  @Override
  public void generateSequences(File outFile) {

    PrinterDDL printerDDL = new PrinterDDL_Sequences(config, structure);

    printToFile(outFile, printerDDL::print);

  }

  @Override
  public void generateNf3References(File outFile) {

    PrinterDDL printerDDL = new PrinterDDL_Nf3References(config, structure);

    printToFile(outFile, printerDDL::print);

  }

  @Override
  public void generateComments(File outFile) {

    PrinterDDL printerDDL = new PrinterDDL_Comments(config, structure);

    printToFile(outFile, printerDDL::print);

  }

}
