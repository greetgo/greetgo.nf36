package shared_model.transaction;

import kz.greetgo.ng36.ann.Description;
import kz.greetgo.ng36.ann.Entity;
import kz.greetgo.ng36.ann.ID;

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
