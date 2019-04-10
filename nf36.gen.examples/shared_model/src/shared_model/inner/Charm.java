package shared_model.inner;

import kz.greetgo.ng36.ann.Description;
import kz.greetgo.ng36.ann.Entity;
import kz.greetgo.ng36.ann.ID;

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
