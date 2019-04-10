package kz.greetgo.ng36.gen.structure;

import kz.greetgo.ng36.gen.model.DbTable;

import java.util.List;

public interface Structure {

  List<DbTable> getTables();

  DbTable findTable(Class<?> tableClass);
}
