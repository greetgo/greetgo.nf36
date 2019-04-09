package nf3_example_with_depinject.beans.oracle;

import kz.greetgo.db.DbType;
import kz.greetgo.depinject.core.Bean;
import nf3_example_with_depinject.util.DbTypeSource;
import nf3_example_with_depinject.util.NoRef;

@Bean
@NoRef
public class DbTypeSourceOracle implements DbTypeSource {
  @Override
  public DbType currentDbType() {
    return DbType.Oracle;
  }
}
