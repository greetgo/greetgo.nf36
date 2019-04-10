package kz.greetgo.ng36.gen.model;

import kz.greetgo.ng36.ann.ID;
import kz.greetgo.ng36.gen.GeneratorConfig;
import kz.greetgo.ng36.gen.structure.DbType;
import kz.greetgo.ng36.gen.structure.Structure;
import kz.greetgo.ng36.gen.util.SqlTypeUtil;

import java.lang.reflect.Field;

public class DbField {
  public final Field field;
  private final GeneratorConfig config;
  private final Structure structure;

  public DbField(Field field, GeneratorConfig config, Structure structure) {
    this.field = field;
    this.config = config;
    this.structure = structure;
  }

  public String dbName() {
    return config.getSqlDialect().fieldNameToDbName(field.getName());
  }

  private DbType dbType = null;

  public DbType getDbType() {
    if (dbType == null) {
      dbType = SqlTypeUtil.extractDbType(field, config);
    }
    return dbType;
  }

  public String dbTypeStr() {
    return SqlTypeUtil.dbTypeToStr(getDbType(), config);
  }

  public boolean isId() {
    return field.getAnnotation(ID.class) != null;
  }

  public int idOrder() {
    return field.getAnnotation(ID.class).order();
  }

  public String idNextPart() {
    ID id = field.getAnnotation(ID.class);
    return id.nextPart().length() == 0 ? null : id.nextPart();
  }

  public DbTable idReference() {
    ID id = field.getAnnotation(ID.class);
    Class<?> ref = id.ref();
    if (ref == Object.class) {
      return null;
    }

    {
      DbTable table = structure.findTable(ref);

      if (table == null) {
        throw new RuntimeException("No table for class " + ref + " in "
          + field.getDeclaringClass().getSimpleName() + "." + field.getName());
      }

      return table;
    }
  }

}
