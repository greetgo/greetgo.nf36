package kz.greetgo.db.nf36.gen.structure;

import kz.greetgo.nf36.core.Description;
import kz.greetgo.nf36.core.GenerateHistorySelector;
import kz.greetgo.nf36.core.ID;
import kz.greetgo.nf36.core.NotNullInDb;
import kz.greetgo.nf36.core.ReferencesTo;
import kz.greetgo.nf36.core.BigTextInDb;
import kz.greetgo.db.nf36.gen.structure.inner.Chair;

@Description("Это клиент")
@SuppressWarnings("unused")
@GenerateHistorySelector
public class Client {
  @Description("Идентификатор клиента")
  @ID(seqFrom = 10_000_000)
  public long id;

  @Description("Фамилия")
  @NotNullInDb
  public String surname;

  @Description("Имя")
  public String name;

  @Description("Отчество")
  public String patronymic;

  @Description("Длинное описание")
  @BigTextInDb
  public String longDescription;

  @Description("Ссылка на мой стул")
  @ReferencesTo(Chair.class)
  public Long myChairId1;

  @ReferencesTo(Chair.class)
  public String myChairId2;

  @Description("Ссылка на его стул")
  @ReferencesTo(Chair.class)
  public Long hisChairLongId;

  @ReferencesTo(Chair.class)
  public String hisChairStrId;
}
