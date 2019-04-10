package nf3_example_with_depinject.conf.postgres;

import kz.greetgo.depinject.core.Bean;
import kz.greetgo.ng36.gen.dialect.SqlDialectPostgres;
import nf3_example_with_depinject.util.NoRef;

@Bean
@NoRef
public class SqlDialectPostgresBean extends SqlDialectPostgres {}
