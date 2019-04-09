package shared_model;

import kz.greetgo.nf36.core.Description;
import kz.greetgo.nf36.core.Entity;
import kz.greetgo.nf36.core.ID;

@Entity
@Description("Stone is rock")
public class Stone {

  @Description("id of stone")
  @ID
  public String id;

  @Description("name of stone")
  public String name;

  @Description("actuality of stone")
  public boolean actual;
}
