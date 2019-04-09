package kz.greetgo.nf36.db.worker.util;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Places {

  private static Path rootProjectDir() {

    for (String dir : ". .. ../.. ../../..".trim().split("\\s+")) {
      Path aDir = Paths.get(dir);
      if (Files.exists(aDir.resolve(".greetgo").resolve("project-name.txt"))) {
        return aDir;
      }
    }

    throw new RuntimeException("Cannot find root project from dir: "
      + new File(".").getAbsoluteFile().toPath().normalize());

  }

  public static Path nf36GenExamples() {
    return rootProjectDir().resolve("nf36.gen.examples");
  }

  public static Path nf36ExampleWithDepinject() {

    return nf36GenExamples().resolve("nf36_example_with_depinject");

  }

  public static Path nf3ExampleWithDepinject() {

    return nf36GenExamples().resolve("nf3_example_with_depinject");

  }

}
