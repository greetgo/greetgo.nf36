package kz.greetgo.nf36.core;

import java.util.Date;
import java.util.function.Consumer;

public interface HistorySelector {
  HistorySelector setTimeFieldName(String timeFieldName);

  HistorySelector setInsertedAtFieldName(String insertedAtFieldName);

  HistorySelector setNf3TableName(String nf3TableName);

  HistorySelector field(String nf6TableName, String dbFieldName, String authorFieldName);

  HistorySelector addFieldAlias(String dbFieldName, String aliasName);

  HistorySelector onAbsent(Consumer<Object> destinationObjectConsumer);

  boolean putTo(Object destinationObject);

  HistorySelector addId(String idName);

  HistorySelector addIdAlias(String idName, String idAlias);

  HistorySelector at(Date date);
}
