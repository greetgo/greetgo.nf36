package nf3_example_with_depinject.beans.postgres;

import kz.greetgo.db.Jdbc;
import kz.greetgo.depinject.core.Bean;
import kz.greetgo.depinject.core.BeanGetter;
import kz.greetgo.nf36.core.Updater;
import kz.greetgo.nf36.core.SqlLogAcceptor;
import nf3_example_with_depinject.generated.impl.postgres.AbstractExampleUpdaterPostgres;
import nf3_example_with_depinject.util.AuthorGetter;
import nf3_example_with_depinject.util.DbTypeSource;
import nf3_example_with_depinject.util.NoRef;

import static kz.greetgo.nf36.Nf36Builder.newNf36Builder;

@Bean
@NoRef
public class ExampleUpdaterPostgresConnector extends AbstractExampleUpdaterPostgres {

  public BeanGetter<Jdbc> jdbc;
  public BeanGetter<DbTypeSource> dbTypeSource;
  public BeanGetter<SqlLogAcceptor> logAcceptor;

  public BeanGetter<AuthorGetter> authorGetter;

  @Override
  protected Updater createUpdater() {
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
