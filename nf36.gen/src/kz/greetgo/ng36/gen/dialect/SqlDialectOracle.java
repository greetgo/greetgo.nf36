package kz.greetgo.ng36.gen.dialect;

import kz.greetgo.ng36.util.UtilsNf36;

public class SqlDialectOracle extends SqlDialectAbstract {

  @Override
  public String classToNf3TableName(Class<?> aClass) {
    return UtilsNf36.javaNameToDbName(aClass.getSimpleName()).toUpperCase();
  }

  @Override
  public String fieldNameToDbName(String javaFieldName) {
    return UtilsNf36.javaNameToDbName(javaFieldName).toUpperCase();
  }

  @Override
  public void checkObjectName(String objectName, DbObjectType dbObjectType) {
    if (objectName.length() > 30) {
      throw new RuntimeException("In Oracle maximum object name length is 30," +
        " but you want length = " + objectName.length() + ", object name is `" + objectName + "`," +
        " object type is " + dbObjectType);
    }
  }

  @Override
  public String strType() {
    return "VARCHAR2";
  }

  @Override
  public String smallintType() {
    return "INT";
  }

  @Override
  public String bigintType() {
    return "NUMBER";
  }

  @Override
  public String clobType() {
    return "CLOB";
  }

  @Override
  public String now() {
    return "systimestamp";
  }

}
