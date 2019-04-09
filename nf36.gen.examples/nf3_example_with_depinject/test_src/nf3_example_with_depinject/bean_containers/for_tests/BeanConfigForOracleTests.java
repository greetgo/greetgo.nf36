package nf3_example_with_depinject.bean_containers.for_tests;

import kz.greetgo.depinject.core.BeanConfig;
import kz.greetgo.depinject.core.Include;
import kz.greetgo.nf36.db.worker.oracle.BeanConfigDbWorkerOracle;
import nf3_example_with_depinject.beans.oracle.BeanConfigOracle;

@BeanConfig
@Include({
  BeanConfigOracle.class,
  BeanConfigDbWorkerOracle.class,
})
public class BeanConfigForOracleTests {}
