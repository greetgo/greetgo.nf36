package nf3_example_with_depinject.conf.postgres;

import kz.greetgo.depinject.core.Bean;
import kz.greetgo.nf36.db.worker.db.DbConfig;
import nf3_example_with_depinject.env.DbParamsPostgres;
import nf3_example_with_depinject.util.NoRef;

@Bean
@NoRef
public class DbConfigPostgres implements DbConfig {

  @Override
  public String url() {
    return DbParamsPostgres.url;
  }

  @Override
  public String username() {
    return DbParamsPostgres.username;
  }

  @Override
  public String password() {
    return DbParamsPostgres.password;
  }

  @Override
  public String dbName() {
    return DbParamsPostgres.dbName;
  }
}
