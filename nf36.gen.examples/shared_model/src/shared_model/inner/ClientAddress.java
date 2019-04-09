package shared_model.inner;

import kz.greetgo.nf36.core.Nf3DefaultNow;
import kz.greetgo.nf36.core.Nf3Description;
import kz.greetgo.nf36.core.Nf3Entity;
import kz.greetgo.nf36.core.Nf3ID;
import kz.greetgo.nf36.core.Nf3NotNull;
import kz.greetgo.nf36.core.Nf3ReferenceTo;
import kz.greetgo.nf36.core.Nf3Short;
import shared_model.AddressType;
import shared_model.Client;
import shared_model.Street;

import java.util.Date;

@Nf3Entity
@Nf3Description("Адрес клиента")
@SuppressWarnings("unused")
public class ClientAddress {

  @Nf3Description("Ссылка на клиента")
  @Nf3ID(ref = Client.class)
  public long clientId;

  @Nf3Description("Тип адреса")
  public AddressType type;

  @Nf3Description("Улица")
  @Nf3ReferenceTo(Street.class)
  public long streetId;

  @Nf3Description("Дом")
  @Nf3Short
  public String house;

  @Nf3Description("Квартира")
  @Nf3Short
  public String flat;

  @Nf3Description("День рождения")
  @Nf3NotNull
  @Nf3DefaultNow
  public Date birthDate;
}
