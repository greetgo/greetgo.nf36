package kz.greetgo.nf36.adapters;

class JdbcSequenceNextAdapterPostgres extends JdbcSequenceNextAbstractAdapter {
  @Override
  protected String selectSequence(String sequenceName) {
    return "select nextVal('" + sequenceName + "')";
  }
}
