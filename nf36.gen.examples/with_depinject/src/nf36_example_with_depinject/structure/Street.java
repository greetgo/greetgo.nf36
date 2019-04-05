package nf36_example_with_depinject.structure;

import kz.greetgo.nf36.core.Nf3Description;
import kz.greetgo.nf36.core.Nf3Entity;
import kz.greetgo.nf36.core.Nf3ID;
import kz.greetgo.nf36.core.Nf3Ignore;
import kz.greetgo.nf36.core.Nf3NotNull;

@Nf3Entity
@Nf3Description("Справочник улиц")
@SuppressWarnings("unused")
public class Street {

  @Nf3Description("Идентификатор улицы")
  @Nf3ID
  public long id;

  @Nf3Description("Тип улицы")
  @Nf3NotNull
  public StreetType type = StreetType.STREET;

  @Nf3Description("Имя")
  @Nf3NotNull
  public String name;

  @Nf3Ignore
  public String createdBy;
}
