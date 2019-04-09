package shared_model;

import kz.greetgo.nf36.core.CommitMethodName;
import kz.greetgo.nf36.core.Description;
import kz.greetgo.nf36.core.Entity;
import kz.greetgo.nf36.core.GenerateHistorySelector;
import kz.greetgo.nf36.core.ID;
import kz.greetgo.nf36.core.DepricatedNf3MoreMethodName;
import kz.greetgo.nf36.core.NotNullInDb;
import kz.greetgo.nf36.core.ReferencesTo;
import kz.greetgo.nf36.core.SaveMethodName;
import kz.greetgo.nf36.core.BigTextInDb;
import shared_model.inner.Chair;
import shared_model.inner.Charm;

@Entity
@Description("Это клиент")
@CommitMethodName("commitAll")
@DepricatedNf3MoreMethodName("moreAnother")
@SaveMethodName("saveAll")
@SuppressWarnings("unused")
@GenerateHistorySelector(atMethodName = "atMoment")
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

  @ReferencesTo(Charm.class)
  @Description("hi")
  public String charmId;

  @Description("Длинное описание")
  @BigTextInDb
  public String longDescription;

  @Description("Ссылка на мой стул")
  @ReferencesTo(value = Chair.class, nextPart = "myChairId2")
  public Long myChairId1;

  @ReferencesTo(Chair.class)
  @Description("hi")
  public String myChairId2;

  @Description("Ссылка на его стул")
  @ReferencesTo(value = Chair.class, nextPart = "hisChairStrId")
  public Long hisChairLongId;

  @ReferencesTo(Chair.class)
  @Description("hi")
  public String hisChairStrId;
}
