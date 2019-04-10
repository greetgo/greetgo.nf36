package kz.greetgo.ng36.gen.dialect;

public interface SqlDialect {

  String classToNf3TableName(Class<?> aClass);

  String sqlSeparator();

  String fieldNameToDbName(String javaFieldName);

  void checkObjectName(String objectName, DbObjectType dbObjectType);

  String strType();

  String intType(int length);

  String smallintType();

  String bigintType();

  String clobType();

  String decimalType();

  String now();
}
