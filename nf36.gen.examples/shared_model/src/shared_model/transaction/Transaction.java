package shared_model.transaction;

import kz.greetgo.ng36.ann.Description;
import kz.greetgo.ng36.ann.Entity;
import kz.greetgo.ng36.ann.ID;

@Entity
@SuppressWarnings("unused")
@Description("Description")
public class Transaction {
  @ID
  @Description("Description")
  public long id;

  @Description("Description")
  public String description;
}
