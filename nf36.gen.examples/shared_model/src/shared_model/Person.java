package shared_model;

import kz.greetgo.ng36.ann.DefaultValue;
import kz.greetgo.ng36.ann.Description;
import kz.greetgo.ng36.ann.Entity;
import kz.greetgo.ng36.ann.ID;
import kz.greetgo.ng36.ann.Length;

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
