package nf3_example_with_depinject.beans.all;

import kz.greetgo.depinject.core.Bean;
import kz.greetgo.nf36.core.SqlLogAcceptor;
import kz.greetgo.nf36.model.SqlLog;
import nf3_example_with_depinject.util.NoRef;

@Bean
@NoRef
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
