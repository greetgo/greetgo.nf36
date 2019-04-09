package kz.greetgo.db.nf36.gen.structure.inner;

import kz.greetgo.nf36.core.Description;
import kz.greetgo.nf36.core.ID;

@Description("Это стул")
@SuppressWarnings("unused")
public class Chair {
  @Description("Первый идентификатор стула")
  @ID(seqFrom = 10_000_000)
  public long id1;

  @Description("Второй идентификатор стула")
  @ID(order = 2)
  public String id2;

  @Description("Имя стула")
  public String name = "Мой стул";
}
