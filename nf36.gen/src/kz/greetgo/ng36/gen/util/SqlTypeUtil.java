package kz.greetgo.ng36.gen.util;

import kz.greetgo.ng36.ann.BigTextInDb;
import kz.greetgo.ng36.ann.ID;
import kz.greetgo.ng36.ann.Length;
import kz.greetgo.ng36.ann.LongLength;
import kz.greetgo.ng36.ann.NotNullInDb;
import kz.greetgo.ng36.ann.ReferencesTo;
import kz.greetgo.ng36.ann.ShortLength;
import kz.greetgo.ng36.ann.ValueScale;
import kz.greetgo.ng36.errors.ConflictError;
import kz.greetgo.ng36.gen.GeneratorConfig;
import kz.greetgo.ng36.gen.structure.DbType;
import kz.greetgo.ng36.gen.structure.SqlType;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;

public class SqlTypeUtil {

  private static class DbTypeImpl implements DbType {

    private final SqlType sqlType;
    private final int len;
    private final boolean nullable;
    private final boolean sequential;
    public int scale;

    public DbTypeImpl(SqlType sqlType, int len, boolean nullable, boolean sequential) {
      this.sqlType = sqlType;
      this.len = len;
      this.nullable = nullable;
      this.sequential = sequential;
    }

    @Override
    public SqlType sqlType() {
      return sqlType;
    }

    @Override
    public int len() {
      return len;
    }

    @Override
    public int scale() {
      return scale;
    }

    @Override
    public boolean nullable() {
      return nullable;
    }

    @Override
    public boolean sequential() {
      return sequential;
    }
  }

  public static DbType extractDbType(Field field, GeneratorConfig config) {
    if (String.class.equals(field.getType())) {
      boolean idOrReference = field.getAnnotation(ID.class) != null
        || field.getAnnotation(ReferencesTo.class) != null;

      boolean nullable = field.getAnnotation(ID.class) == null && field.getAnnotation(NotNullInDb.class) == null;

      int len = idOrReference ? config.getIdLength() : config.getDefaultLength();

      ShortLength aShort = field.getAnnotation(ShortLength.class);
      if (aShort != null) {
        len = config.getShortLength();
      }
      LongLength aLong = field.getAnnotation(LongLength.class);
      if (aLong != null) {
        len = config.getLongLength();
      }

      if (aShort != null && aLong != null) {
        throw new ConflictError(aShort, aLong);
      }

      Object prev = aShort != null ? aShort : aLong;

      Length aLength = field.getAnnotation(Length.class);
      if (aLength != null) {
        len = aLength.value();
      }

      if (prev != null && aLength != null) {
        throw new ConflictError(prev, aLength);
      }

      prev = prev != null ? prev : aLength;

      BigTextInDb aText = field.getAnnotation(BigTextInDb.class);
      if (prev != null && aText != null) {
        throw new ConflictError(prev, aText);
      }

      if (aText != null) {
        return new DbTypeImpl(SqlType.CLOB, 0, nullable, false);
      }

      return new DbTypeImpl(SqlType.VARCHAR, len, nullable, false);
    }

    if (Integer.class.equals(field.getType())) {
      return new DbTypeImpl(SqlType.INT, intLen(field), true, true);
    }

    if (int.class.equals(field.getType())) {
      return new DbTypeImpl(SqlType.INT, intLen(field), false, true);
    }

    if (Long.class.equals(field.getType())) {
      return new DbTypeImpl(SqlType.BIGINT, 0, true, true);
    }

    if (long.class.equals(field.getType())) {
      return new DbTypeImpl(SqlType.BIGINT, 0, false, true);
    }

    if (Boolean.class.equals(field.getType())) {
      return new DbTypeImpl(SqlType.BOOL, 0, true, false);
    }

    if (boolean.class.equals(field.getType())) {
      return new DbTypeImpl(SqlType.BOOL, 0, false, false);
    }

    if (Date.class.equals(field.getType())) {
      boolean nullable = field.getAnnotation(NotNullInDb.class) == null;
      return new DbTypeImpl(SqlType.TIMESTAMP, 0, nullable, false);
    }

    if (Enum.class.isAssignableFrom(field.getType())) {
      int len = 0;
      Length aLength = field.getAnnotation(Length.class);

      if (aLength != null) {
        len = aLength.value();
      }

      if (len == 0) {
        if (config.getEnumLength() < 10) {
          throw new RuntimeException("enumLength must be >= 10");
        }
        len = config.getEnumLength();
      }

      boolean nullable = field.getAnnotation(NotNullInDb.class) == null;
      return new DbTypeImpl(SqlType.VARCHAR, len, nullable, false);
    }

    if (BigDecimal.class.equals(field.getType())) {
      int len = 19;
      Length aLength = field.getAnnotation(Length.class);
      if (aLength != null) {
        len = aLength.value();
      }
      int scale = 4;
      ValueScale aScale = field.getAnnotation(ValueScale.class);
      if (aLength != null) {
        scale = aScale.value();
      }

      boolean nullable = field.getAnnotation(NotNullInDb.class) == null;

      DbTypeImpl ret = new DbTypeImpl(SqlType.DECIMAL, len, nullable, false);
      ret.scale = scale;
      return ret;
    }

    throw new RuntimeException("Cannot extract DbType from " + field);
  }

  private static int intLen(Field field) {
    return field.getAnnotation(ShortLength.class) == null ? 4 : 8;
  }

  public static String dbTypeToStr(DbType t, GeneratorConfig config) {

    switch (t.sqlType()) {

      case TIMESTAMP:
        return "TIMESTAMP";

      case VARCHAR:
        return config.getSqlDialect().strType() + "(" + t.len() + ")";

      case INT:
        return config.getSqlDialect().intType(t.len());

      case BOOL:
        return config.getSqlDialect().smallintType();

      case BIGINT:
        return config.getSqlDialect().bigintType();

      case CLOB:
        return config.getSqlDialect().clobType();

      case DECIMAL:
        return config.getSqlDialect().decimalType() + "(" + t.len() + ", " + t.scale() + ")";

      default:
        throw new IllegalArgumentException("Cannot create type for " + t.sqlType());

    }

  }

}
