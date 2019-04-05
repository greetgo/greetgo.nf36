package kz.greetgo.nf36.adapters;

class JdbcNf36HistorySelectorAdapterPostgres extends JdbcNf36HistorySelectorAbstractAdapter {
  @Override
  protected String timestampParameter() {
    return "?::timestamp";
  }

  @Override
  protected void sqlAppendFromDual() {}
}
