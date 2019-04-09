package kz.greetgo.nf36.adapters;

import kz.greetgo.db.DbType;
import kz.greetgo.db.Jdbc;
import kz.greetgo.nf36.core.Updater;
import kz.greetgo.nf36.core.SqlLogAcceptor;

public class UpdaterBuilder {
  private SqlLogAcceptor logAcceptor = null;
  private Jdbc jdbc = null;

  public UpdaterBuilder setJdbc(Jdbc jdbc) {
    this.jdbc = jdbc;
    return this;
  }

  public UpdaterBuilder setLogAcceptor(SqlLogAcceptor logAcceptor) {
    this.logAcceptor = logAcceptor;
    return this;
  }

  private boolean hasAuthor = false;
  private Object author;

  public UpdaterBuilder setAuthor(Object author) {
    hasAuthor = true;
    this.author = author;
    return this;
  }

  private JdbcUpdaterAbstractAdapter adapter = null;

  public UpdaterBuilder database(DbType dbType) {
    switch (dbType) {
      case Postgres:
        adapter = new JdbcUpdaterAdapterPostgres();
        return this;

      case Oracle:
        adapter = new JdbcUpdaterAdapterOracle();
        return this;

      default:
        throw new IllegalArgumentException("Database " + dbType + " is not supported");
    }
  }

  public Updater build() {
    if (adapter == null) {
      throw new RuntimeException("Please define database. Call method 'database'");
    }
    if (jdbc == null) {
      throw new RuntimeException("Please set jdbc");
    }
    adapter.jdbc = jdbc;
    adapter.logAcceptor = logAcceptor;
    if (hasAuthor) {
      adapter.setAuthor(author);
    }
    return adapter;
  }
}
