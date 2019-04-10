package shared_model;

import kz.greetgo.ng36.ann.Description;
import kz.greetgo.ng36.ann.Entity;
import kz.greetgo.ng36.ann.ID;

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
