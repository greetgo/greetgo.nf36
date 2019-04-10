package shared_model;

import kz.greetgo.ng36.ann.Description;
import kz.greetgo.ng36.ann.Entity;
import kz.greetgo.ng36.ann.ID;
import kz.greetgo.ng36.ann.Ignore;
import kz.greetgo.ng36.ann.NotNullInDb;

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
