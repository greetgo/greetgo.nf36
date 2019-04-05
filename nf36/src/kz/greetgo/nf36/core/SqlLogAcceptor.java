package kz.greetgo.nf36.core;

import kz.greetgo.nf36.model.SqlLog;

public interface SqlLogAcceptor {
  boolean isTraceEnabled();

  boolean isErrorEnabled();

  void accept(SqlLog sqlLog);
}
