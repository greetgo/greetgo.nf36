package kz.greetgo.db.nf36.gen.structure.inner;

import kz.greetgo.nf36.core.DefaultNow;
import kz.greetgo.nf36.core.Description;
import kz.greetgo.nf36.core.ID;
import kz.greetgo.nf36.core.NotNullInDb;
import kz.greetgo.nf36.core.ReferencesTo;
import kz.greetgo.nf36.core.ShortLength;
import kz.greetgo.db.nf36.gen.structure.AddressType;
import kz.greetgo.db.nf36.gen.structure.Client;
import kz.greetgo.db.nf36.gen.structure.Street;

import java.util.Date;

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
