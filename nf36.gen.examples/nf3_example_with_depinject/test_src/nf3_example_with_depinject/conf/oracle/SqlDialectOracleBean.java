package nf3_example_with_depinject.conf.oracle;

import kz.greetgo.depinject.core.Bean;
import kz.greetgo.ng36.gen.SqlDialectOracle;
import nf3_example_with_depinject.util.NoRef;

@Bean
@NoRef
public class SqlDialectOracleBean extends SqlDialectOracle {}
