package nf36_example_with_depinject.generated.impl.postgres.save;

import java.lang.Integer;
import java.util.function.Predicate;
import kz.greetgo.nf36.core.Nf36Saver;
import nf36_example_with_depinject.generated.faces.save.ManyIdsSave;

public class ManyIdsSaveImpl implements ManyIdsSave {
  private final Nf36Saver saver13;

  public ManyIdsSaveImpl(Nf36Saver saver) {
    this.saver13 = saver;
    saver.setNf3TableName("many_ids");
    saver.setTimeFieldName("ts");
    saver.setAuthorFieldNames("created_by", "modified_by", "inserted_by");
    saver.addIdName("int_id");
    saver.addIdName("boxed_int_id");
    saver.addIdName("long_id");
    saver.addIdName("boxed_long_id");
    saver.addIdName("str_id");
    saver.addFieldName("memory_never_be_superfluous.many_ids_a_field", "a_field");
  }

  private final aField aField = new aField() {
    @Override
    public ManyIdsSave set(int value) {
      saver13.presetValue("a_field", value);
      return ManyIdsSaveImpl.this;
    }

    @Override
    public ManyIdsSave skipIf(Predicate<Integer>  predicate) {
      saver13.addSkipIf("a_field", predicate);
      return ManyIdsSaveImpl.this;
    }

    @Override
    public ManyIdsSave alias(String alias) {
      saver13.addAlias("a_field", alias);
      return ManyIdsSaveImpl.this;
    }

  };

  @Override
  public aField aField() {
    return aField;
  }

  @Override
  public void save(Object objectWithData) {
    saver13.putUpdateToNow("mod_at");
    saver13.save(objectWithData);
  }
}
