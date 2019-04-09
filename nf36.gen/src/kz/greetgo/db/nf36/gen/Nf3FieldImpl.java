package kz.greetgo.db.nf36.gen;

import kz.greetgo.nf36.core.Description;
import kz.greetgo.nf36.core.ID;
import kz.greetgo.nf36.core.Ignore;
import kz.greetgo.nf36.core.ReferencesTo;
import kz.greetgo.nf36.model.DbType;
import kz.greetgo.nf36.model.Nf3Field;
import kz.greetgo.nf36.model.Nf3Table;
import kz.greetgo.nf36.model.Sequence;
import kz.greetgo.nf36.utils.UtilsNf36;

import java.lang.reflect.Field;
import java.util.List;

class Nf3FieldImpl implements Nf3Field {
  private final Nf3Table nf3Table;
  private final Object definer;
  private final Field source;
  private final ID ID;
  private final ModelCollector collector;

  public Nf3FieldImpl(Nf3Table nf3Table, Object definer, Field source, ModelCollector collector) {
    this.nf3Table = nf3Table;
    this.definer = definer;
    this.source = source;
    ID = source.getAnnotation(ID.class);
    this.collector = collector;
  }

  public boolean notIgnoring() {
    return source.getAnnotation(Ignore.class) == null;
  }

  @Override
  public Object definer() {
    return definer;
  }

  @Override
  public Field source() {
    return source;
  }

  @Override
  public boolean isId() {
    return ID != null;
  }

  @Override
  public int idOrder() {
    return ID == null ? 0 : ID.order();
  }

  @Override
  public String javaName() {
    return source.getName();
  }

  @Override
  public String dbName() {
    return UtilsNf36.javaNameToDbName(javaName());
  }

  @Override
  public Class<?> javaType() {
    return source.getType();
  }

  @Override
  public DbType dbType() {
    return SqlTypeUtil.extractDbType(source, collector);
  }

  @Override
  public Class<?> referenceToClass() {
    {
      ReferencesTo a = source.getAnnotation(ReferencesTo.class);
      if (a != null) {
        return a.value();
      }
    }
    {
      ID a = source.getAnnotation(ID.class);
      if (a != null && a.ref() != Object.class) {
        return a.ref();
      }
    }
    return null;
  }

  @Override
  public String nextPart() {
    {
      ReferencesTo a = source.getAnnotation(ReferencesTo.class);
      if (a != null && a.nextPart().length() > 0) {
        return a.nextPart();
      }
    }
    {
      ID a = source.getAnnotation(ID.class);
      if (a != null && a.nextPart().length() > 0) {
        return a.nextPart();
      }
    }
    return null;
  }

  @Override
  public boolean isReference() {
    return referenceToClass() != null;
  }

  boolean isRootReference = false;

  @Override
  public boolean isRootReference() {
    return isRootReference;
  }

  List<Nf3Field> referenceFields = null;

  @Override
  public List<Nf3Field> referenceFields() {
    if (referenceFields == null) {
      throw new RuntimeException("No referenceFields");
    }
    return referenceFields;
  }

  Nf3Table referenceTo = null;

  @Override
  public Nf3Table referenceTo() {
    if (referenceTo == null) {
      throw new RuntimeException("No referenceTo");
    }
    return referenceTo;
  }

  Nf3Field rootField = this;

  @Override
  public Nf3Field rootField() {
    return rootField;
  }

  @Override
  public boolean hasNextPart() {
    return nextPart() != null;
  }

  @Override
  public boolean notNullAndNotPrimitive() {
    return !dbType().nullable() && !source.getType().isPrimitive();
  }

  @Override
  public String commentQuotedForSql() {
    Description d = source.getAnnotation(Description.class);
    if (d == null) {
      throw new RuntimeException("No description for field " + definer.getClass().getSimpleName()
          + "." + source.getName());
    }
    return UtilsNf36.quoteForSql(d.value());
  }

  @Override
  public Sequence sequence() {
    if (ID == null) {
      return null;
    }
    if (!dbType().sequential()) {
      return null;
    }

    String sequenceName = collector.sequencePrefix() + nf3Table.tableName() + "_" + dbName();

    return new Sequence(sequenceName, ID.seqFrom());
  }
}
