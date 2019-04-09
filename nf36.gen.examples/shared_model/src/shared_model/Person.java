package shared_model;

import kz.greetgo.nf36.core.DefaultValue;
import kz.greetgo.nf36.core.Description;
import kz.greetgo.nf36.core.Entity;
import kz.greetgo.nf36.core.ID;
import kz.greetgo.nf36.core.Length;

import java.math.BigDecimal;

@Entity
@Description("person")
@SuppressWarnings("unused")
public class Person {
  @ID
  @Length(113)
  @Description("id")
  public String id;

  @Length(119)
  @Description("fio")
  @DefaultValue("Вселенское ' значение")
  public String fio;

  @Description("blocked")
  public boolean blocked = true;

  @Description("amount")
  public BigDecimal amount;

  @Description("amountRU")
  public BigDecimal amountRU;
}
