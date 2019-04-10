package kz.greetgo.ng36.gen;

import kz.greetgo.ng36.gen.dialect.SqlDialect;

import java.util.List;

public interface GeneratorConfig {

  SqlDialect getSqlDialect();

  List<Class<?>> getAllEntityClasses();

  int getIdLength();

  int getDefaultLength();

  int getShortLength();

  int getLongLength();

  int getEnumLength();
}
