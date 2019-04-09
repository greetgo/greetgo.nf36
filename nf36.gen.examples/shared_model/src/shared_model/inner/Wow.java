package shared_model.inner;

import kz.greetgo.nf36.core.Description;
import kz.greetgo.nf36.core.Entity;
import kz.greetgo.nf36.core.ID;

@Entity
@Description("wow")
@SuppressWarnings("unused")
public class Wow {
  @Description("hi")
  @ID
  public String wowId;

  @Description("hi")
  @ID(order = 2)
  public String wowId2;

  @Description("hi")
  public String hello;
}
