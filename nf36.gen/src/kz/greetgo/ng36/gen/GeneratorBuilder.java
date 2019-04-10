package kz.greetgo.ng36.gen;

import kz.greetgo.ng36.gen.ddl.GeneratorDDL;
import kz.greetgo.ng36.gen.ddl.GeneratorDDLImpl;
import kz.greetgo.ng36.gen.dialect.SqlDialect;
import kz.greetgo.ng36.gen.java.GeneratorJava;
import kz.greetgo.ng36.gen.java.GeneratorJavaImpl;
import kz.greetgo.ng36.gen.structure.StructureImpl;

public class GeneratorBuilder implements GeneratorConfig {

  private SqlDialect sqlDialect;
  private String nf3TablePrefix;

  private GeneratorBuilder() {}

  public static GeneratorBuilder newInstance() {
    return new GeneratorBuilder();
  }

  public GeneratorBuilder setSqlDialect(SqlDialect sqlDialect) {
    this.sqlDialect = sqlDialect;
    return this;
  }

  private StructureImpl structure;

  private StructureImpl structure() {
    if (structure == null) {
      structure = new StructureImpl(this);
      structure.collect();
    }
    return structure;
  }

  public GeneratorDDL createGeneratorDDL() {
    return new GeneratorDDLImpl(this, structure());
  }

  public GeneratorJava createGeneratorJava() {
    return new GeneratorJavaImpl(this, structure());
  }

  @Override
  public SqlDialect getSqlDialect() {
    return sqlDialect;
  }

  @Override
  public String getNf3TablePrefix() {
    return nf3TablePrefix;
  }

  @SuppressWarnings("unused")
  public GeneratorBuilder setNf3TablePrefix(String nf3TablePrefix) {
    this.nf3TablePrefix = nf3TablePrefix;
    return this;
  }
}
