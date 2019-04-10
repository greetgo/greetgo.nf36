package kz.greetgo.ng36.gen.structure;

public interface DbType {

  SqlType sqlType();

  int len();

  int scale();

  boolean nullable();

  boolean sequential();

}
