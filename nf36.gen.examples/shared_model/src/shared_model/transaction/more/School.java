package shared_model.transaction.more;

import kz.greetgo.nf36.core.Description;
import kz.greetgo.nf36.core.Entity;
import kz.greetgo.nf36.core.ID;

@Entity
@SuppressWarnings("unused")
@Description("Description")
public class School {

  @ID
  @Description("Description")
  public String id;

  @Description("Description")
  public String name;
}
