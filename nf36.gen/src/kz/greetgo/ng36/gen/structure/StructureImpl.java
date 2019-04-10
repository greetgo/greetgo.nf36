package kz.greetgo.ng36.gen.structure;

import kz.greetgo.ng36.gen.GeneratorConfig;
import kz.greetgo.ng36.gen.model.DbTable;

import java.util.List;
import java.util.stream.Collectors;

public class StructureImpl implements Structure {

  private final GeneratorConfig config;

  public StructureImpl(GeneratorConfig config) {
    this.config = config;
  }

  private List<DbTable> tables;

  @Override
  public List<DbTable> getTables() {
    return tables;
  }

  public void collect() {

    tables = config
      .getAllEntityClasses()
      .stream()
      .map(source -> new DbTable(source, config, this))
      .collect(Collectors.toList());

  }

  @Override
  public DbTable findTable(Class<?> tableClass) {
    for (DbTable table : tables) {
      if (table.source == tableClass) {
        return table;
      }
    }
    return null;
  }

}
