package shared_model.inner;

import kz.greetgo.ng36.ann.Description;
import kz.greetgo.ng36.ann.Entity;
import kz.greetgo.ng36.ann.ID;

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
