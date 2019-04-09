package nf36_example_with_depinject.generated.faces.update;

import java.lang.String;
import shared_model.SomeEnum;

public interface EntityEnumAsIdUpdate {
  EntityEnumAsIdUpdate setValue(String value);


  EntityEnumAsIdUpdate whereIdIsEqualTo(SomeEnum id);

  EntityEnumAsIdUpdate whereValueIsEqualTo(String value);

  void commit();
}
