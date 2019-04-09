package shared_model.inner;

import kz.greetgo.nf36.core.Description;
import kz.greetgo.nf36.core.Entity;
import kz.greetgo.nf36.core.GenerateHistorySelector;
import kz.greetgo.nf36.core.ID;
import kz.greetgo.nf36.core.BigTextInDb;

@Entity
@Description("Это стул ' и апостроф")
@GenerateHistorySelector(toSuffix = "Into")
@SuppressWarnings("unused")
public class Chair {
  @ID(seqFrom = 10_000_000)
  @Description("Первый идентификатор стула")
  public long id1;

  @ID(order = 2)
  @Description("Второй идентификатор стула")
  public String id2;

  @Description("Имя стула")
  public String name = "Мой стул";

  @BigTextInDb
  @Description("Описание стула")
  public String description = "Длинное описание стула";
}
