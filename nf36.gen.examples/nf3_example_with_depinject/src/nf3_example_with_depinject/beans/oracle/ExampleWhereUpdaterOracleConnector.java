package nf3_example_with_depinject.beans.oracle;

import kz.greetgo.db.Jdbc;
import kz.greetgo.depinject.core.Bean;
import kz.greetgo.depinject.core.BeanGetter;
import kz.greetgo.nf36.core.Nf36Updater;
import kz.greetgo.nf36.core.SqlLogAcceptor;
import nf3_example_with_depinject.generated.impl.oracle.AbstractExampleUpdaterOracle;
import nf3_example_with_depinject.util.AuthorGetter;
import nf3_example_with_depinject.util.DbTypeSource;
import nf3_example_with_depinject.util.NoRef;

import static kz.greetgo.nf36.Nf36Builder.newNf36Builder;

@Bean
@NoRef
public class ExampleWhereUpdaterOracleConnector extends AbstractExampleUpdaterOracle {

  public BeanGetter<Jdbc> jdbc;
  public BeanGetter<DbTypeSource> dbTypeSource;
  public BeanGetter<SqlLogAcceptor> logAcceptor;

  public BeanGetter<AuthorGetter> authorGetter;

  @Override
  protected Nf36Updater createUpdater() {
    return newNf36Builder()
        .updater()
        .database(dbTypeSource.get().currentDbType())
        .setJdbc(jdbc.get())
        .setLogAcceptor(logAcceptor.get())
        .setAuthor(authorGetter.get().getAuthor())
        .build()
        ;
  }
}
