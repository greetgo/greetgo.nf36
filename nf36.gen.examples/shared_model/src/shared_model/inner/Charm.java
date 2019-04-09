package shared_model.inner;

import kz.greetgo.nf36.core.Nf3Description;
import kz.greetgo.nf36.core.Nf3Entity;
import kz.greetgo.nf36.core.Nf3ID;

@Nf3Entity
@Nf3Description("Характер")
@SuppressWarnings("unused")
public class Charm {
  @Nf3Description("hi")
  @Nf3ID
  public String id;

  @Nf3Description("hi")
  public String name;
}
