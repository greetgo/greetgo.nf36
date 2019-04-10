package kz.greetgo.ng36.ann;

import kz.greetgo.ng36.model.SqlLog;

public interface SqlLogAcceptor {
  boolean isTraceEnabled();

  boolean isErrorEnabled();

  void accept(SqlLog sqlLog);
}
