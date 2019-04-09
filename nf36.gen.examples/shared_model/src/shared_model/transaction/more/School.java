package shared_model.transaction.more;

import kz.greetgo.nf36.core.Nf3Description;
import kz.greetgo.nf36.core.Nf3Entity;
import kz.greetgo.nf36.core.Nf3ID;

@Nf3Entity
@SuppressWarnings("unused")
@Nf3Description("Description")
public class School {

  @Nf3ID
  @Nf3Description("Description")
  public String id;

  @Nf3Description("Description")
  public String name;
}
