package nf3_example_with_depinject.bean_containers;

import kz.greetgo.depinject.Depinject;
import kz.greetgo.depinject.NoImplementor;
import kz.greetgo.depinject.core.BeanContainer;
import kz.greetgo.depinject.core.Include;
import kz.greetgo.depinject.gen.DepinjectUtil;
import kz.greetgo.nf36.db.worker.oracle.BeanConfigDbWorkerOracle;
import kz.greetgo.nf36.db.worker.oracle.DbWorkerOracle;
import kz.greetgo.nf36.db.worker.util.Places;
import nf3_example_with_depinject.conf.oracle.BeanConfigOracleConf;
import nf3_example_with_depinject.generators.BeanConfigGenerators;
import nf3_example_with_depinject.generators.ExampleGenerators;

@Include({
  BeanConfigGenerators.class,
  BeanConfigOracleConf.class,
  BeanConfigDbWorkerOracle.class,
})
public interface BeanContainerOracle extends BeanContainer {

  static BeanContainerOracle create() {
    try {
      return Depinject.newInstance(BeanContainerOracle.class);
    } catch (NoImplementor ignore) {
      DepinjectUtil.implementAndUseBeanContainers(
        "nf36_example_with_depinject.bean_containers",
        Places.nf36ExampleWithDepinject().resolve("build").resolve("bean_containers_impl").toString());
      return Depinject.newInstance(BeanContainerOracle.class);
    }
  }

  DbWorkerOracle dbWorker();

  ExampleGenerators generators();

}
