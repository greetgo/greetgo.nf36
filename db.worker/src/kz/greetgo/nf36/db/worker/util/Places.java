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

  public static Path withDepinjectDir() {

    return rootProjectDir().resolve("nf36.gen.examples").resolve("with_depinject");

  }
}
