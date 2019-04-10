package kz.greetgo.nf36.adapters;

import kz.greetgo.db.DbType;
import kz.greetgo.db.Jdbc;
import kz.greetgo.nf36.core.Upserter;
import kz.greetgo.nf36.core.SqlLogAcceptor;

public class UpserterAdapterBuilder {
  private SqlLogAcceptor logAcceptor = null;
  private Jdbc jdbc = null;

  public UpserterAdapterBuilder setJdbc(Jdbc jdbc) {
    this.jdbc = jdbc;
    return this;
  }

  public UpserterAdapterBuilder setLogAcceptor(SqlLogAcceptor logAcceptor) {
    this.logAcceptor = logAcceptor;
    return this;
  }

  private JdbcUpserterAbstractAdapter adapter = null;

  public UpserterAdapterBuilder database(DbType dbType) {
    switch (dbType) {
      case Postgres:
        adapter = new JdbcUpserterAdapterPostgres();
        return this;

      case Oracle:
        adapter = new JdbcUpserterAdapterOracle();
        return this;

      default:
        throw new IllegalArgumentException("Database " + dbType + " is not supported");
    }
  }

  private boolean hasAuthor = false;
  private Object author;

  public UpserterAdapterBuilder setAuthor(Object author) {
    hasAuthor = true;
    this.author = author;
    return this;
  }

  public Upserter build() {
    if (adapter == null) {
      throw new RuntimeException("Please define database. Call method 'database'");
    }
    if (jdbc == null) {
      throw new RuntimeException("Please set jdbc");
    }
    adapter.logAcceptor = logAcceptor;
    adapter.jdbc = jdbc;
    if (hasAuthor) {
      adapter.setAuthor(author);
    }
    return adapter;
  }
}
