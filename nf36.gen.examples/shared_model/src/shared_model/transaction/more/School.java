package shared_model.transaction.more;

import kz.greetgo.ng36.ann.Description;
import kz.greetgo.ng36.ann.Entity;
import kz.greetgo.ng36.ann.ID;

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
