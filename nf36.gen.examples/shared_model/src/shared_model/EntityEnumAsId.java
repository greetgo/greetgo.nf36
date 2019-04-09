package shared_model;

import kz.greetgo.nf36.core.Description;
import kz.greetgo.nf36.core.Entity;
import kz.greetgo.nf36.core.ID;

@Entity
@SuppressWarnings("unused")
@Description("Энтря с ИД типа Enum")
public class EntityEnumAsId {
  @ID
  @Description("enum id")
  public SomeEnum id;

  @Description("some value")
  public String value;
}
