package nf36_example_with_depinject.beans.all;

import kz.greetgo.nf36.core.SqlLogAcceptor;
import kz.greetgo.nf36.model.SqlLog;
import kz.greetgo.depinject.core.Bean;

@Bean
public class MySqlLogAcceptor implements SqlLogAcceptor {
  @Override
  public boolean isTraceEnabled() {
    return true;
  }

  @Override
  public boolean isErrorEnabled() {
    return true;
  }

  @Override
  public void accept(SqlLog sqlLog) {
    System.out.println(sqlLog.toStr(true, true, true));
  }
}
