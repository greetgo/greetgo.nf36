package shared_model.inner;

import kz.greetgo.ng36.ann.DefaultNow;
import kz.greetgo.ng36.ann.Description;
import kz.greetgo.ng36.ann.Entity;
import kz.greetgo.ng36.ann.ID;
import kz.greetgo.ng36.ann.NotNullInDb;
import kz.greetgo.ng36.ann.ReferencesTo;
import kz.greetgo.ng36.ann.ShortLength;
import shared_model.AddressType;
import shared_model.Client;
import shared_model.Street;

import java.util.Date;

@Entity
@Description("Адрес клиента")
@SuppressWarnings("unused")
public class ClientAddress {

  @Description("Ссылка на клиента")
  @ID(ref = Client.class)
  public long clientId;

  @Description("Тип адреса")
  public AddressType type;

  @Description("Улица")
  @ReferencesTo(Street.class)
  public long streetId;

  @Description("Дом")
  @ShortLength
  public String house;

  @Description("Квартира")
  @ShortLength
  public String flat;

  @Description("День рождения")
  @NotNullInDb
  @DefaultNow
  public Date birthDate;
}
