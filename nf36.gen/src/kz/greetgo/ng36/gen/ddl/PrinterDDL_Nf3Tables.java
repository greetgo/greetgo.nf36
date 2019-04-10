package kz.greetgo.ng36.gen.ddl;

import kz.greetgo.ng36.gen.GeneratorConfig;
import kz.greetgo.ng36.gen.dialect.DbObjectType;
import kz.greetgo.ng36.gen.model.DbField;
import kz.greetgo.ng36.gen.model.DbTable;
import kz.greetgo.ng36.gen.structure.Structure;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PrinterDDL_Nf3Tables extends PrinterDDL {

  public PrinterDDL_Nf3Tables(GeneratorConfig config, Structure structure) {
    super(config, structure);
  }

  @Override
  public void print(PrintStream printStream) {

    structure.getTables().forEach(table -> printCreateTable(printStream, table));

  }

  private void printCreateTable(PrintStream printStream, DbTable table) {

    config.getSqlDialect().checkObjectName(table.getNf3TableName(), DbObjectType.TABLE_NAME);

    List<String> cmdParts = new ArrayList<>();

    for (DbField field : table.getFieldList()) {

      config.getSqlDialect().checkObjectName(field.dbName(), DbObjectType.TABLE_FIELD_NAME);

      boolean nullable = field.getDbType().nullable();

      cmdParts.add(field.dbName() + " " + field.dbTypeStr() + (nullable ? "" : " not null"));

    }

    List<String> idList = table.getFieldList()
      .stream()
      .filter(DbField::isId)
      .sorted(Comparator.comparing(DbField::idOrder))
      .map(DbField::dbName)
      .collect(Collectors.toList());

    if (!idList.isEmpty()) {
      cmdParts.add("primary key(" + String.join(", ", idList) + ")");
    }

    printStream.println("create table "
      + table.getNf3TableName() + " (" + String.join(", ", cmdParts) + ")"
      + config.getSqlDialect().sqlSeparator());

  }

}
