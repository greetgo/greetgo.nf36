package nf3_example_with_depinject.bean_containers;

import kz.greetgo.depinject.Depinject;
import kz.greetgo.depinject.NoImplementor;
import kz.greetgo.depinject.core.BeanContainer;
import kz.greetgo.depinject.core.Include;
import kz.greetgo.depinject.gen.DepinjectUtil;
import kz.greetgo.nf36.db.worker.postgres.BeanConfigDbWorkerPostgres;
import kz.greetgo.nf36.db.worker.postgres.DbWorkerPostgres;
import kz.greetgo.nf36.db.worker.util.Places;
import nf3_example_with_depinject.conf.postgres.BeanConfigPostgresConf;
import nf3_example_with_depinject.generators.BeanConfigGenerators;
import nf3_example_with_depinject.generators.ExampleGenerators;

@Include({
  BeanConfigGenerators.class,
  BeanConfigPostgresConf.class,
  BeanConfigDbWorkerPostgres.class,
})
public interface BeanContainerPostgres extends BeanContainer {

  static BeanContainerPostgres create() {
    try {
      return Depinject.newInstance(BeanContainerPostgres.class);
    } catch (NoImplementor ignore) {
      DepinjectUtil.implementAndUseBeanContainers(
        "nf36_example_with_depinject.bean_containers",
        Places.withDepinjectDir() + "/build/bean_containers_impl");
      return Depinject.newInstance(BeanContainerPostgres.class);
    }
  }

  DbWorkerPostgres dbWorker();

  ExampleGenerators generators();
}
