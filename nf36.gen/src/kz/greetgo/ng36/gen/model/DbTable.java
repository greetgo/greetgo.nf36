package kz.greetgo.ng36.gen.model;

import kz.greetgo.ng36.ann.Entity;
import kz.greetgo.ng36.gen.GeneratorConfig;
import kz.greetgo.ng36.gen.structure.Structure;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DbTable {

  public final Class<?> source;
  private final GeneratorConfig config;
  private final Structure structure;

  public DbTable(Class<?> source, GeneratorConfig config, Structure structure) {
    this.source = source;
    this.config = config;
    this.structure = structure;
  }

  public String getNf3TableName() {

    Entity entity = source.getAnnotation(Entity.class);

    String tableName = entity.tableName();

    if (tableName.isEmpty()) {
      tableName = config.getSqlDialect().classToNf3TableName(source);
    }

    return tableName;

  }

  private List<DbField> fieldList = null;

  public List<DbField> getFieldList() {
    if (fieldList == null) {

      fieldList = Arrays.stream(source.getFields())
        .map(field -> new DbField(field, config, structure))
        .sorted(Comparator.comparing(f -> f.field.getName()))
        .collect(Collectors.toList())
      ;

    }

    return fieldList;

  }

}
