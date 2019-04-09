package shared_model.inner;

import kz.greetgo.nf36.core.DefaultNow;
import kz.greetgo.nf36.core.Description;
import kz.greetgo.nf36.core.Entity;
import kz.greetgo.nf36.core.ID;
import kz.greetgo.nf36.core.NotNullInDb;
import kz.greetgo.nf36.core.ReferencesTo;
import kz.greetgo.nf36.core.ShortLength;
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
