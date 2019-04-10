package shared_model;

import kz.greetgo.ng36.ann.CommitMethodName;
import kz.greetgo.ng36.ann.Description;
import kz.greetgo.ng36.ann.GenerateHistorySelector;
import kz.greetgo.ng36.ann.ID;
import kz.greetgo.ng36.ann.NotNullInDb;
import kz.greetgo.ng36.ann.ReferencesTo;
import kz.greetgo.ng36.ann.SaveMethodName;
import kz.greetgo.ng36.ann.BigTextInDb;
import kz.greetgo.ng36.ann.Entity;
import shared_model.inner.Chair;
import shared_model.inner.Charm;

@Entity
@Description("Это клиент")
@CommitMethodName("commitAll")
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
