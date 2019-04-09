package nf3_example_with_depinject.util;

import kz.greetgo.db.DbType;

public interface DbTypeSource {
  DbType currentDbType();
}
