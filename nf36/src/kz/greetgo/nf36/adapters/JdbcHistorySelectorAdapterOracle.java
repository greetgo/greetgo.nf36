package kz.greetgo.nf36.adapters;

class JdbcHistorySelectorAdapterOracle extends JdbcHistorySelectorAbstractAdapter {
  @Override
  protected String timestampParameter() {
    return "?";
  }

  @Override
  protected void sqlAppendFromDual() {
    sqlAppend("from dual");
  }
}
