package kz.greetgo.ng36.gen;

import kz.greetgo.class_scanner.ClassScanner;
import kz.greetgo.class_scanner.ClassScannerDef;
import kz.greetgo.ng36.ann.Entity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EntityCollector {

  private final List<Class<?>> positioningClassList = new ArrayList<>();
  private final ClassScanner classScanner = new ClassScannerDef();

  public List<Class<?>> scan() {

    return positioningClassList
      .stream()
      .flatMap(this::scanClassesOver)
      .filter(this::isEntity)
      .distinct()
      .sorted(Comparator.comparing(Class::getName))
      .collect(Collectors.toList());

  }

  private boolean isEntity(Class<?> aClass) {
    return aClass.getAnnotation(Entity.class) != null;
  }

  private Stream<Class<?>> scanClassesOver(Class<?> cl) {
    return classScanner.scanPackage(cl.getPackageName()).stream();
  }

  public void scanEntitiesOver(Class<?> positioningClass) {
    positioningClassList.add(positioningClass);
  }

}
