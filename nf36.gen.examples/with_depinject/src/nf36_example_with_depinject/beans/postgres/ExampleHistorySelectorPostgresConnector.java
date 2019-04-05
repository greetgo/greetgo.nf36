package nf36_example_with_depinject.beans.postgres;

import kz.greetgo.db.Jdbc;
import kz.greetgo.nf36.core.Nf36HistorySelector;
import kz.greetgo.nf36.core.SqlLogAcceptor;
import kz.greetgo.depinject.core.Bean;
import kz.greetgo.depinject.core.BeanGetter;
import nf36_example_with_depinject.generated.impl.postgres.AbstractExampleHistorySelectorPostgres;
import nf36_example_with_depinject.util.DbTypeSource;

import static kz.greetgo.nf36.Nf36Builder.newNf36Builder;

@Bean
public class ExampleHistorySelectorPostgresConnector extends AbstractExampleHistorySelectorPostgres {

  public BeanGetter<Jdbc> jdbc;
  public BeanGetter<DbTypeSource> dbTypeSource;
  public BeanGetter<SqlLogAcceptor> logAcceptor;

  @Override
  protected Nf36HistorySelector createHistorySelector() {
    return newNf36Builder()
        .historySelector()
        .setLogAcceptor(logAcceptor.get())
        .database(dbTypeSource.get().currentDbType())
        .setJdbc(jdbc.get())
        .build()
        ;
  }
}
