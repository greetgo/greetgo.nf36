package kz.greetgo.nf36.adapters;

class JdbcSequenceNextAdapterOracle extends JdbcSequenceNextAbstractAdapter {
  @Override
  protected String selectSequence(String sequenceName) {
    return "select " + sequenceName + ".nextVal from dual";
  }
}
