package nf36_example_with_depinject.generated.impl.postgres.upsert.transaction.more;

import java.lang.String;
import kz.greetgo.nf36.core.Nf36Upserter;
import nf36_example_with_depinject.generated.faces.upsert.transaction.more.SchoolUpsert;

public class SchoolUpsertImpl implements SchoolUpsert {
  private final Nf36Upserter upserter;

  public SchoolUpsertImpl(Nf36Upserter upserter, String id) {
    this.upserter = upserter;
    upserter.setNf3TableName("school");
    upserter.setTimeFieldName("ts");
    upserter.setAuthorFieldNames("created_by", "modified_by", "inserted_by");
    upserter.putId("id", id);
  }

  @Override
  public SchoolUpsert more(String id) {
    return new SchoolUpsertImpl(this.upserter.more(), id);
  }

  @Override
  public SchoolUpsert name(String name) {
    upserter.putField("memory_never_be_superfluous.school_name", "name", name);
    return this;
  }

  @Override
  public void commit() {
    upserter.putUpdateToNowWithParent("mod_at");
    upserter.commit();
  }
}
