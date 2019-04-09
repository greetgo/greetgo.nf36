package kz.greetgo.db.nf36.gen.structure;

import kz.greetgo.nf36.core.Description;
import kz.greetgo.nf36.core.ID;
import kz.greetgo.nf36.core.NotNullInDb;

@Description("Справочник улиц")
@SuppressWarnings("unused")
public class Street {

  @Description("Идентификатор улицы")
  @ID
  public long id;

  @Description("Тип улицы")
  @NotNullInDb
  public StreetType type = StreetType.STREET;

  @Description("Имя")
  @NotNullInDb
  public String name;
}
