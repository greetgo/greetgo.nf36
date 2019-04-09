package nf3_example_with_depinject.bean_containers.for_tests;

import kz.greetgo.depinject.core.BeanConfig;
import kz.greetgo.depinject.core.Include;
import kz.greetgo.nf36.db.worker.postgres.BeanConfigDbWorkerPostgres;
import nf3_example_with_depinject.beans.postgres.BeanConfigPostgres;

@BeanConfig
@Include({
  BeanConfigPostgres.class,
  BeanConfigDbWorkerPostgres.class,
})
public class BeanConfigForPostgresTests {}
