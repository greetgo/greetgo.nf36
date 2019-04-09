package shared_model.inner;

import kz.greetgo.nf36.core.Description;
import kz.greetgo.nf36.core.Entity;
import kz.greetgo.nf36.core.ID;

@Entity
@Description("Характер")
@SuppressWarnings("unused")
public class Charm {
  @Description("hi")
  @ID
  public String id;

  @Description("hi")
  public String name;
}
