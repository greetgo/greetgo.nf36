package kz.greetgo.ng36.ann;

public interface Updater {

  void setNf3TableName(String tableName);

  void setAuthorFieldNames(String nf3ModifiedBy, String nf6InsertedBy);

  Updater setAuthor(Object author);

  void setIdFieldNames(String... idFieldNames);

  void updateFieldToNow(String fieldName);

  void setField(String nf6TableName, String fieldName, Object fieldValue);

  void where(String fieldName, Object fieldValue);

  void commit();
}
