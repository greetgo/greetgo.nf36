package shared_model.transaction;

import kz.greetgo.nf36.core.Description;
import kz.greetgo.nf36.core.Entity;
import kz.greetgo.nf36.core.ID;

@Entity
@Description("Using only ids")
public class OnlyIds {
  @ID
  @Description("id №1")
  public long id1;

  @ID(order = 2)
  @Description("id №2")
  public String id2;
}
