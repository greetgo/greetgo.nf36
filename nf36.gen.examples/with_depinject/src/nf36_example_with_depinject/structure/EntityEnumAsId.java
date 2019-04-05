package nf36_example_with_depinject.structure;

import kz.greetgo.nf36.core.Nf3Description;
import kz.greetgo.nf36.core.Nf3Entity;
import kz.greetgo.nf36.core.Nf3ID;

@Nf3Entity
@SuppressWarnings("unused")
@Nf3Description("Энтря с ИД типа Enum")
public class EntityEnumAsId {
  @Nf3ID
  @Nf3Description("enum id")
  public SomeEnum id;

  @Nf3Description("some value")
  public String value;
}
