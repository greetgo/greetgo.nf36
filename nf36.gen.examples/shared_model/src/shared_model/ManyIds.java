package shared_model;

import kz.greetgo.nf36.core.Description;
import kz.greetgo.nf36.core.Entity;
import kz.greetgo.nf36.core.ID;

@Entity
@SuppressWarnings("unused")
@Description("Это энтити с большим количеством ИД-шников")
public class ManyIds {
  @SuppressWarnings("DefaultAnnotationParam")
  @Description("an id field")
  @ID(order = 1)
  public int intId;

  @Description("an id field")
  @ID(order = 2)
  public Integer boxedIntId;

  @Description("an id field")
  @ID(order = 3)
  public long longId;

  @Description("an id field")
  @ID(order = 4)
  public Long boxedLongId;

  @Description("an id field")
  @ID(order = 5)
  public String strId;

  @Description("an field")
  public int aField;
}
