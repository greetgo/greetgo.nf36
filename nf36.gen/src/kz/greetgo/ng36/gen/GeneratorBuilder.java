package kz.greetgo.ng36.gen;

import kz.greetgo.ng36.gen.ddl.GeneratorDDL;
import kz.greetgo.ng36.gen.ddl.GeneratorDDLImpl;
import kz.greetgo.ng36.gen.dialect.SqlDialect;
import kz.greetgo.ng36.gen.java.GeneratorJava;
import kz.greetgo.ng36.gen.java.GeneratorJavaImpl;
import kz.greetgo.ng36.gen.structure.StructureImpl;

import java.util.List;

public class GeneratorBuilder {

  private SqlDialect sqlDialect;
  private final EntityCollector entityCollector = new EntityCollector();

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
      structure = new StructureImpl(config);
      structure.collect();
    }
    return structure;
  }

  public GeneratorDDL createGeneratorDDL() {
    return new GeneratorDDLImpl(config, structure());
  }

  public GeneratorJava createGeneratorJava() {
    return new GeneratorJavaImpl(config, structure());
  }

  public GeneratorBuilder scanEntitiesOver(Class<?> positioningClass) {
    entityCollector.scanEntitiesOver(positioningClass);
    return this;
  }

  private int idLength = 31;
  private int defaultLength = 301;
  private int shortLength = 51;
  private int longLength = 2001;
  private int enumLength = 51;

  private final GeneratorConfig config = new GeneratorConfig() {
    @Override
    public SqlDialect getSqlDialect() {
      return sqlDialect;
    }

    @Override
    public List<Class<?>> getAllEntityClasses() {
      return entityCollector.scan();
    }

    @Override
    public int getIdLength() {
      return idLength;
    }

    @Override
    public int getDefaultLength() {
      return defaultLength;
    }

    @Override
    public int getShortLength() {
      return shortLength;
    }

    @Override
    public int getLongLength() {
      return longLength;
    }

    @Override
    public int getEnumLength() {
      return enumLength;
    }
  };

  @SuppressWarnings("unused")
  public GeneratorBuilder setIdLength(int idLength) {
    this.idLength = idLength;
    return this;
  }

  @SuppressWarnings("unused")
  public GeneratorBuilder setDefaultLength(int defaultLength) {
    this.defaultLength = defaultLength;
    return this;
  }

  @SuppressWarnings("unused")
  public GeneratorBuilder setShortLength(int shortLength) {
    this.shortLength = shortLength;
    return this;
  }

  @SuppressWarnings("unused")
  public GeneratorBuilder setLongLength(int longLength) {
    this.longLength = longLength;
    return this;
  }

  @SuppressWarnings("unused")
  public GeneratorBuilder setEnumLength(int enumLength) {
    this.enumLength = enumLength;
    return this;
  }

}
