package nf3_example_with_depinject.beans.postgres;

import kz.greetgo.db.Jdbc;
import kz.greetgo.depinject.core.Bean;
import kz.greetgo.depinject.core.BeanGetter;
import kz.greetgo.nf36.core.Saver;
import kz.greetgo.nf36.core.Upserter;
import kz.greetgo.nf36.core.SequenceNext;
import kz.greetgo.nf36.core.SqlLogAcceptor;
import nf3_example_with_depinject.beans.all.SaverCreator;
import nf3_example_with_depinject.beans.all.UpserterCreator;
import nf3_example_with_depinject.generated.impl.postgres.AbstractExampleUpserterPostgres;
import nf3_example_with_depinject.util.AuthorGetter;
import nf3_example_with_depinject.util.DbTypeSource;
import nf3_example_with_depinject.util.NoRef;

import static kz.greetgo.nf36.Nf36Builder.newNf36Builder;

@Bean
public class ExampleUpserterPostgresConnector extends AbstractExampleUpserterPostgres {

  public BeanGetter<Jdbc> jdbc;
  public BeanGetter<DbTypeSource> dbTypeSource;
  public BeanGetter<SqlLogAcceptor> logAcceptor;

  public BeanGetter<AuthorGetter> authorGetter;

  @Override
  protected Upserter createUpserter() {
    return newNf36Builder()
      .upserter()
      .database(dbTypeSource.get().currentDbType())
      .setJdbc(jdbc.get())
      .setLogAcceptor(logAcceptor.get())
      .setAuthor(authorGetter.get().getAuthor())
      .build()
      ;
  }

  @Bean
  @NoRef
  public UpserterCreator createUpserterCreator() {
    return this::createUpserter;
  }

  @Override
  protected SequenceNext getSequenceNext() {
    return newNf36Builder()
      .sequenceNext()
      .setLogAcceptor(logAcceptor.get())
      .setJdbc(jdbc.get())
      .database(dbTypeSource.get().currentDbType())
      .build();
  }

  @Bean
  @NoRef
  public SaverCreator createSaverCreator() {
    return this::createSaver;
  }

  @Override
  protected Saver createSaver() {
    return newNf36Builder()
      .saver()
      .setUpserter(createUpserter())
      .build();
  }

}
