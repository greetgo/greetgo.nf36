package kz.greetgo.ng36.gen.dialect;

import kz.greetgo.ng36.util.UtilsNf36;

public abstract class SqlDialectAbstract implements SqlDialect {

  @Override
  public String classToNf3TableName(Class<?> aClass) {
    return UtilsNf36.javaNameToDbName(aClass.getSimpleName());
  }

  @Override
  public String sqlSeparator() {
    return ";;";
  }

  @Override
  public String fieldNameToDbName(String javaFieldName) {
    return UtilsNf36.javaNameToDbName(javaFieldName);
  }

  @Override
  public void checkObjectName(String objectName, DbObjectType dbObjectType) {}

  @Override
  public String strType() {
    return "VARCHAR";
  }

  @Override
  public String intType(int length) {
    switch (length) {
      case 4:
        return smallintType();
      case 8:
        return bigintType();
      default:
        throw new IllegalArgumentException("No integer with length = " + length);
    }
  }

  @Override
  public String decimalType() {
    return "DECIMAL";
  }
}
