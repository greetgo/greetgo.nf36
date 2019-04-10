package kz.greetgo.ng36.gen.dialect;

public class SqlDialectPostgres extends SqlDialectAbstract {

  @Override
  public String smallintType() {
    return "SMALLINT";
  }

  @Override
  public String bigintType() {
    return "BIGINT";
  }

  @Override
  public String clobType() {
    return "TEXT";
  }

  @Override
  public String intType(int length) {
    return "INT" + length;
  }

  @Override
  public String now() {
    return "clock_timestamp()";
  }

}
