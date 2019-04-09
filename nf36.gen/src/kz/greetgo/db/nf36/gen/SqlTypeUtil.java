package kz.greetgo.db.nf36.gen;

import kz.greetgo.nf36.core.ID;
import kz.greetgo.nf36.core.Length;
import kz.greetgo.nf36.core.LongLength;
import kz.greetgo.nf36.core.NotNullInDb;
import kz.greetgo.nf36.core.ReferencesTo;
import kz.greetgo.nf36.core.ValueScale;
import kz.greetgo.nf36.core.ShortLength;
import kz.greetgo.nf36.core.BigTextInDb;
import kz.greetgo.nf36.errors.ConflictError;
import kz.greetgo.nf36.model.DbType;
import kz.greetgo.nf36.model.SqlType;

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

  public static DbType extractDbType(Field field, ModelCollector collector) {
    if (String.class.equals(field.getType())) {
      boolean idOrReference = field.getAnnotation(ID.class) != null
          || field.getAnnotation(ReferencesTo.class) != null;

      boolean nullable = field.getAnnotation(ID.class) == null && field.getAnnotation(NotNullInDb.class) == null;

      int len = idOrReference ? collector.idLength : collector.defaultLength;

      ShortLength aShort = field.getAnnotation(ShortLength.class);
      if (aShort != null) {
        len = collector.shortLength;
      }
      LongLength aLong = field.getAnnotation(LongLength.class);
      if (aLong != null) {
        len = collector.longLength;
      }

      if (aShort != null && aLong != null) { throw new ConflictError(aShort, aLong); }

      Object prev = aShort != null ? aShort : aLong;

      Length aLength = field.getAnnotation(Length.class);
      if (aLength != null) {
        len = aLength.value();
      }

      if (prev != null && aLength != null) { throw new ConflictError(prev, aLength); }

      prev = prev != null ? prev : aLength;

      BigTextInDb aText = field.getAnnotation(BigTextInDb.class);
      if (prev != null && aText != null) { throw new ConflictError(prev, aText); }

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
        if (collector.enumLength < 10) {
          throw new RuntimeException("enumLength must be >= 10");
        }
        len = collector.enumLength;
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
}
