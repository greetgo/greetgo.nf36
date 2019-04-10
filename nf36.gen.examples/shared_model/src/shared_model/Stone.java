package shared_model;

import kz.greetgo.ng36.ann.Description;
import kz.greetgo.ng36.ann.Entity;
import kz.greetgo.ng36.ann.ID;

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
