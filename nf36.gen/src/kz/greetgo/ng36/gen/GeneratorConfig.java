package kz.greetgo.ng36.gen;

import kz.greetgo.ng36.gen.dialect.SqlDialect;

public interface GeneratorConfig {

  SqlDialect getSqlDialect();

  String getNf3TablePrefix();

}
