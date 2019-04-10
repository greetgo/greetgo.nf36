package kz.greetgo.ng36.ann;

import java.util.function.Predicate;

public interface Saver {
  // Common methods

  Saver setNf3TableName(String nf3TableName);

  Saver setTimeFieldName(String timeFieldName);

  Saver setAuthorFieldNames(String nf3CreatedBy, String nf3ModifiedBy, String nf6InsertedBy);

  Saver addIdName(String idName);

  Saver addFieldName(String nf6TableName, String fieldName);

  Saver putUpdateToNow(String timestampFieldName);

  // Field preset methods

  Saver setAuthor(Object author);

  Saver presetValue(String fieldName, Object value);

  Saver addSkipIf(String fieldName, Predicate<?> predicate);

  Saver addAlias(String fieldName, String alias);

  // Main operation

  void save(Object objectWithData);
}
