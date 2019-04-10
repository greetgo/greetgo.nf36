package kz.greetgo.nf36.adapters;

class JdbcHistorySelectorAdapterPostgres extends JdbcHistorySelectorAbstractAdapter {
  @Override
  protected String timestampParameter() {
    return "?::timestamp";
  }

  @Override
  protected void sqlAppendFromDual() {}
}
