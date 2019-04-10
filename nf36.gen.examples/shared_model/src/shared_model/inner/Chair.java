package shared_model.inner;

import kz.greetgo.ng36.ann.Description;
import kz.greetgo.ng36.ann.Entity;
import kz.greetgo.ng36.ann.GenerateHistorySelector;
import kz.greetgo.ng36.ann.ID;
import kz.greetgo.ng36.ann.BigTextInDb;

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
