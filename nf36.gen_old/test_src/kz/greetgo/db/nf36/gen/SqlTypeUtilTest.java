package kz.greetgo.db.nf36.gen;

import kz.greetgo.nf36.core.DefaultNow;
import kz.greetgo.nf36.core.ID;
import kz.greetgo.nf36.core.LongLength;
import kz.greetgo.nf36.core.NotNullInDb;
import kz.greetgo.nf36.core.ShortLength;
import kz.greetgo.nf36.core.BigTextInDb;
import kz.greetgo.nf36.model.DbType;
import kz.greetgo.nf36.model.SqlType;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static kz.greetgo.db.nf36.gen.ModelCollector.newCollector;
import static org.fest.assertions.api.Assertions.assertThat;

public class SqlTypeUtilTest {

  public static class Data {
    List<Object[]> dataList = new ArrayList<>();

    void add(Class<?> sourceClass, String fieldName, SqlType expectedType, int expectedLen, boolean expectedNullable) {
      try {
        dataList.add(new Object[]{sourceClass.getField(fieldName), expectedType, expectedLen, expectedNullable});
      } catch (NoSuchFieldException e) {
        e.printStackTrace();
        throw new RuntimeException(e);
      }
    }

    public Iterator<Object[]> result() {
      return dataList.iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> convertType_DP() {
    Data data = new Data();
    prepareTestData_String(data);
    prepareTestData_int(data);
    prepareTestData_long(data);
    prepareTestData_Date(data);
    prepareTestData_Bool(data);
    prepareTestData_Enum(data);
    return data.result();
  }

  private static final int ENUM_LENGTH = 9017361;

  @Test(dataProvider = "convertType_DP")
  public void convertType(Field field, SqlType expectedType,
                          int expectedLen, boolean expectedNullable) {

    DbType dbType = SqlTypeUtil.extractDbType(field, newCollector()
      .setDefaultLength(300)
      .setShortLength(50)
      .setLongLength(2000)
      .setIdLength(30)
      .setEnumLength(ENUM_LENGTH));

    assertThat(dbType).isNotNull();
    assertThat(dbType.sqlType()).isEqualTo(expectedType);
    assertThat(dbType.len()).isEqualTo(expectedLen);
    assertThat(dbType.nullable()).isEqualTo(expectedNullable);

  }

  @SuppressWarnings("unused")
  static class FieldStringSource {
    public String field1;

    @BigTextInDb
    public String field2;

    @ShortLength
    public String field3;

    @LongLength
    public String field4;

    @NotNullInDb
    public String field1nn;

    @BigTextInDb
    @NotNullInDb
    public String field2nn;

    @ShortLength
    @NotNullInDb
    public String field3nn;

    @LongLength
    @NotNullInDb
    public String field4nn;

    @ID
    public String fieldId;//must be not null
  }

  private void prepareTestData_String(Data data) {
    data.add(FieldStringSource.class, "field1", SqlType.VARCHAR, 300, true);
    data.add(FieldStringSource.class, "field2", SqlType.CLOB, 0, true);
    data.add(FieldStringSource.class, "field3", SqlType.VARCHAR, 50, true);
    data.add(FieldStringSource.class, "field4", SqlType.VARCHAR, 2000, true);
    data.add(FieldStringSource.class, "field1nn", SqlType.VARCHAR, 300, false);
    data.add(FieldStringSource.class, "field2nn", SqlType.CLOB, 0, false);
    data.add(FieldStringSource.class, "field3nn", SqlType.VARCHAR, 50, false);
    data.add(FieldStringSource.class, "field4nn", SqlType.VARCHAR, 2000, false);
    data.add(FieldStringSource.class, "fieldId", SqlType.VARCHAR, 30, false);
  }

  @SuppressWarnings("unused")
  static class FieldIntSource {
    public int field1;
    public Integer field2;

    @ShortLength
    public int field3;
    @ShortLength
    public Integer field4;
  }

  private void prepareTestData_int(Data data) {
    data.add(FieldIntSource.class, "field1", SqlType.INT, 4, false);
    data.add(FieldIntSource.class, "field2", SqlType.INT, 4, true);
    data.add(FieldIntSource.class, "field3", SqlType.INT, 8, false);
    data.add(FieldIntSource.class, "field4", SqlType.INT, 8, true);
  }

  @SuppressWarnings("unused")
  static class FieldLongSource {
    public long field1;
    public Long field2;
  }

  private void prepareTestData_long(Data data) {
    data.add(FieldLongSource.class, "field1", SqlType.BIGINT, 0, false);
    data.add(FieldLongSource.class, "field2", SqlType.BIGINT, 0, true);
  }

  @SuppressWarnings("unused")
  static class FieldDateSource {
    @NotNullInDb
    public Date field1;
    public Date field2;

    @DefaultNow
    public Date field3;
  }

  private void prepareTestData_Date(Data data) {
    data.add(FieldDateSource.class, "field1", SqlType.TIMESTAMP, 0, false);
    data.add(FieldDateSource.class, "field2", SqlType.TIMESTAMP, 0, true);
  }

  @SuppressWarnings("unused")
  static class FieldBoolSource {
    public boolean field1;
    public Boolean field2;
  }

  private void prepareTestData_Bool(Data data) {
    data.add(FieldBoolSource.class, "field1", SqlType.BOOL, 0, false);
    data.add(FieldBoolSource.class, "field2", SqlType.BOOL, 0, true);
  }

  @Test
  public void convertType_Date_field2() throws Exception {
    DbType dbType = SqlTypeUtil.extractDbType(
      FieldDateSource.class.getField("field2"), newCollector().setEnumLength(ENUM_LENGTH));
    assertThat(dbType).isNotNull();
  }

  @Test
  public void convertType_Date_field3() throws Exception {
    DbType dbType = SqlTypeUtil.extractDbType(
      FieldDateSource.class.getField("field3"), newCollector().setEnumLength(ENUM_LENGTH));
    assertThat(dbType).isNotNull();
  }

  public enum Wow {}

  @SuppressWarnings("unused")
  static class FieldEnumSource {
    @NotNullInDb
    public Wow field1;
    public Wow field2;
  }

  private void prepareTestData_Enum(Data data) {
    data.add(FieldEnumSource.class, "field1", SqlType.VARCHAR, ENUM_LENGTH, false);
    data.add(FieldEnumSource.class, "field2", SqlType.VARCHAR, ENUM_LENGTH, true);
  }

  @Test(expectedExceptions = RuntimeException.class)
  public void convertType_EnumLengthIdZero() throws Exception {
    SqlTypeUtil.extractDbType(FieldEnumSource.class.getField("field1"), newCollector());
  }
}
