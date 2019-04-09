package kz.greetgo.db.nf36.gen;

import kz.greetgo.nf36.utils.UtilsNf36;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

public class JavaFilePrinter {

  public String packageName;
  public String classHeader;
  private boolean printDebugMarkers = false;

  private final StringBuilder content = new StringBuilder();

  private final Map<String, String> imports = new HashMap<>();

  public JavaFilePrinter(boolean printDebugMarkers) {
    this.printDebugMarkers = printDebugMarkers;
  }

  public JavaFilePrinter pr(String str) {
    content.append(str);
    return this;
  }

  public JavaFilePrinter pr(String str, String debugMarker) {
    content.append(str);
    if (printDebugMarkers && debugMarker != null) {
      content.append(' ');
      content.append(debugMarker);
    }
    return this;
  }

  public JavaFilePrinter prn(String str) {
    content.append(str).append("\n");
    return this;
  }

  public JavaFilePrinter prn(String str, String debugMarker) {
    content.append(str);
    if (printDebugMarkers && debugMarker != null) {
      content.append(debugMarker);
    }
    content.append("\n");
    return this;
  }

  public JavaFilePrinter prn() {
    content.append("\n");
    return this;
  }

  public JavaFilePrinter ofs(int tabCount) {
    content.append("  ".repeat(Math.max(0, tabCount)));
    return this;
  }

  public String i(String fullName) {
    if (fullName == null) {
      throw new IllegalArgumentException("fullName == null");
    }
    String simpleName = UtilsNf36.extractSimpleName(fullName);
    if (Objects.equals(simpleName, fullName)) {
      return fullName;
    }

    String fullNameStored = imports.get(simpleName);
    if (fullNameStored == null) {
      imports.put(simpleName, fullName);
      return simpleName;
    }

    if (Objects.equals(fullName, fullNameStored)) {
      return simpleName;
    }

    return fullName;
  }

  public String i(Class<?> aClass) {
    return i(aClass.getName());
  }

  public StringBuilder result() {
    StringBuilder result = new StringBuilder();
    if (packageName != null) {
      result.append("package ").append(packageName).append(";\n");
    }

    result.append("\n");

    result.append(imports.values().stream().sorted().map(fn -> "import " + fn + ";").collect(Collectors.joining("\n")));

    result.append("\n\n");

    result.append(classHeader).append(" {\n");

    result.append(content);

    if (!content.toString().endsWith("\n")) {
      result.append("\n");
    }

    result.append("}\n");

    return result;
  }

  public void printTo(PrintStream pr) {
    pr.print(result());
  }

  public void printToFile(File file) {
    file.getParentFile().mkdirs();
    try (PrintStream pr = new PrintStream(file, UTF_8)) {
      printTo(pr);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
