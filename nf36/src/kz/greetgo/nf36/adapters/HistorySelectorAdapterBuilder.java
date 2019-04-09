package kz.greetgo.nf36.adapters;

import kz.greetgo.db.DbType;
import kz.greetgo.db.Jdbc;
import kz.greetgo.nf36.core.HistorySelector;
import kz.greetgo.nf36.core.SqlLogAcceptor;

public class HistorySelectorAdapterBuilder {
  private JdbcHistorySelectorAbstractAdapter adapter = null;
  private SqlLogAcceptor logAcceptor = null;
  private Jdbc jdbc = null;

  public HistorySelectorAdapterBuilder setJdbc(Jdbc jdbc) {
    this.jdbc = jdbc;
    return this;
  }

  public HistorySelectorAdapterBuilder setLogAcceptor(SqlLogAcceptor logAcceptor) {
    this.logAcceptor = logAcceptor;
    return this;
  }

  public HistorySelectorAdapterBuilder database(DbType dbType) {
    switch (dbType) {
      case Postgres:
        adapter = new JdbcHistorySelectorAdapterPostgres();
        return this;

      case Oracle:
        adapter = new JdbcHistorySelectorAdapterOracle();
        return this;

      default:
        throw new IllegalArgumentException("Database " + dbType + " is not supported");
    }
  }

  private boolean selectAuthor = false;

  public HistorySelectorAdapterBuilder setSelectAuthor(boolean selectAuthor) {
    this.selectAuthor = selectAuthor;
    return this;
  }

  public HistorySelector build() {
    if (adapter == null) {
      throw new RuntimeException("Please define database. Call method 'database'");
    }
    if (jdbc == null) {
      throw new RuntimeException("Please set jdbc");
    }
    adapter.logAcceptor = logAcceptor;
    adapter.jdbc = jdbc;
    adapter.selectAuthor=selectAuthor;
    return adapter;
  }
}
