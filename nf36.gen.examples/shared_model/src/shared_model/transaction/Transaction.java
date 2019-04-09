package shared_model.transaction;

import kz.greetgo.nf36.core.Nf3Description;
import kz.greetgo.nf36.core.Nf3Entity;
import kz.greetgo.nf36.core.Nf3ID;

@Nf3Entity
@SuppressWarnings("unused")
@Nf3Description("Description")
public class Transaction {
  @Nf3ID
  @Nf3Description("Description")
  public long id;

  @Nf3Description("Description")
  public String description;
}
