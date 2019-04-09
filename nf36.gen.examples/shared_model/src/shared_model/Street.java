package shared_model;

import kz.greetgo.nf36.core.Description;
import kz.greetgo.nf36.core.Entity;
import kz.greetgo.nf36.core.ID;
import kz.greetgo.nf36.core.Ignore;
import kz.greetgo.nf36.core.NotNullInDb;

@Entity
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

  @Ignore
  public String createdBy;
}
