package shared_model.transaction;

import kz.greetgo.nf36.core.Description;
import kz.greetgo.nf36.core.Entity;
import kz.greetgo.nf36.core.ID;

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
