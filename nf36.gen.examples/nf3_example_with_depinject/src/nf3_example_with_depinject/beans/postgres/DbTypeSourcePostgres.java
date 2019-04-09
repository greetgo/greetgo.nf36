package nf3_example_with_depinject.beans.postgres;

import kz.greetgo.db.DbType;
import kz.greetgo.depinject.core.Bean;
import nf3_example_with_depinject.util.DbTypeSource;
import nf3_example_with_depinject.util.NoRef;

@Bean
@NoRef
public class DbTypeSourcePostgres implements DbTypeSource {
  @Override
  public DbType currentDbType() {
    return DbType.Postgres;
  }
}
