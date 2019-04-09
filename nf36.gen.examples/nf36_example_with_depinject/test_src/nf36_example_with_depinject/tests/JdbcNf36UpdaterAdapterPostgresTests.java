package nf36_example_with_depinject.tests;

import kz.greetgo.nf36.core.Nf36Updater;
import kz.greetgo.nf36.core.SqlLogAcceptor;
import kz.greetgo.depinject.core.BeanGetter;
import kz.greetgo.depinject.testng.ContainerConfig;
import nf36_example_with_depinject.bean_containers.for_tests.BeanConfigForPostgresTests;
import nf36_example_with_depinject.errors.SqlError;
import nf36_example_with_depinject.jdbc.ByOne;
import nf36_example_with_depinject.jdbc.ByOneLast;
import nf36_example_with_depinject.jdbc.ByTwo;
import nf36_example_with_depinject.jdbc.ByTwoLast;
import nf36_example_with_depinject.jdbc.CountWhere;
import nf36_example_with_depinject.util.DbTypeSource;
import nf36_example_with_depinject.util.ParentDbTests;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import static kz.greetgo.nf36.Nf36Builder.newNf36Builder;
import static org.fest.assertions.api.Assertions.assertThat;

@SuppressWarnings("SameParameterValue")
@ContainerConfig(BeanConfigForPostgresTests.class)
public class JdbcNf36UpdaterAdapterPostgresTests extends ParentDbTests {

  public BeanGetter<DbTypeSource> dbTypeSource;
  public BeanGetter<SqlLogAcceptor> logAcceptor;

  protected void dropTable(String tableName) {
    try {
      exec("drop table " + tableName);
    } catch (SqlError e) {
      //noinspection StatementWithEmptyBody
      if (e.type == SqlError.Type.DROP_TABLE) {
        //ignore
      } else {
        throw e;
      }
    }
  }

  protected void createTableTmp1() {
    exec("create table tmp1 (" +
        "  id1   varchar(32)," +
        "  id2   varchar(32)," +
        "  name1 varchar(100)," +
        "  name2 varchar(100)," +
        "  f1    varchar(100) not null default 'def value'," +
        "  f2    varchar(100) not null default 'def value'," +
        "  primary key(id1, id2)" +
        ")");
  }

  protected void createTableTmp1f1() {
    exec("create table tmp1_f1 (" +
        "  id1   varchar(32)," +
        "  id2   varchar(32)," +
        "  ts    timestamp not null default clock_timestamp()," +
        "  f1    varchar(100) not null default 'def value'," +
        "  foreign key (id1, id2) references tmp1(id1, id2)," +
        "  primary key(id1, id2, ts)" +
        ")");
  }

  protected void createTableTmp1f2() {
    exec("create table tmp1_f2 (" +
        "  id1   varchar(32)," +
        "  id2   varchar(32)," +
        "  ts    timestamp not null default clock_timestamp()," +
        "  f2    varchar(100)," +
        "  foreign key (id1, id2) references tmp1(id1, id2)," +
        "  primary key(id1, id2, ts)" +
        ")");
  }

  @Test
  public void test_multipleIdUpdateWhere() {
    dropTable("tmp1_f1");
    dropTable("tmp1_f2");
    dropTable("tmp1");
    createTableTmp1();
    createTableTmp1f1();
    createTableTmp1f2();

    exec("insert into tmp1 (id1, id2, name1, name2) values ('1','1','ok1','ok2')");
    exec("insert into tmp1 (id1, id2, name1, name2) values ('1','2','ok1','ok2')");
    exec("insert into tmp1 (id1, id2, name1, name2) values ('2','1','ok1','ok2')");
    exec("insert into tmp1 (id1, id2, name1, name2) values ('2','2','ok1','ok2')");

    exec("insert into tmp1 (id1, id2, name1, name2) values ('100','100','left','ok2')");
    exec("insert into tmp1 (id1, id2, name1, name2) values ('101','101','ok1','left')");
    exec("insert into tmp1 (id1, id2, name1, name2) values ('102','102','left','left')");

    Nf36Updater whereUpdater = createUpdater()
        //.setAuthor("Сталина на вас нет")
        ;

    whereUpdater.setNf3TableName("tmp1");
    whereUpdater.setIdFieldNames("id1", "id2");
    whereUpdater.setField("tmp1_f1", "f1", "value 1");
    whereUpdater.setField("tmp1_f2", "f2", "value 2");
    whereUpdater.where("name1", "ok1");
    whereUpdater.where("name2", "ok2");

    assertTmp1("1", "1", "f1", "def value").assertTmp1("1", "1", "f2", "def value");
    assertTmp1("1", "2", "f1", "def value").assertTmp1("1", "2", "f2", "def value");
    assertTmp1("2", "1", "f1", "def value").assertTmp1("2", "1", "f2", "def value");
    assertTmp1("2", "2", "f1", "def value").assertTmp1("2", "2", "f2", "def value");

    whereUpdater.commit();

    assertTmp1("1", "1", "f1", "value 1").assertTmp1("1", "1", "f2", "value 2");
    assertTmp1("1", "2", "f1", "value 1").assertTmp1("1", "2", "f2", "value 2");
    assertTmp1("2", "1", "f1", "value 1").assertTmp1("2", "1", "f2", "value 2");
    assertTmp1("2", "2", "f1", "value 1").assertTmp1("2", "2", "f2", "value 2");

    assertTmp1("100", "100", "f1", "def value");
    assertTmp1("101", "101", "f1", "def value");
    assertTmp1("102", "102", "f1", "def value");

    assertThat(jdbc.get().execute(new CountWhere("tmp1_f1", null))).isEqualTo(4);
    assertThat(jdbc.get().execute(new CountWhere("tmp1_f2", null))).isEqualTo(4);
  }

  private String readByOne(String idField, String idValue, String tableName, String fieldName) {
    return jdbc.get().execute(new ByOne<>(idField, idValue, tableName, fieldName));
  }

  private JdbcNf36UpdaterAdapterPostgresTests assertTmp1(String id1, String id2, String field, String expectedValue) {

    {
      String actualValue = jdbc.get().execute(new ByTwo<>("id1", id1, "id2", id2, "tmp1", field));
      assertThat(actualValue)
          .describedAs("tmp1(.id1 = " + id1 + ", .id2 = " + id1 + ")." + field
              + " == '" + actualValue + "', but expected '" + expectedValue + "'")
          .isEqualTo(expectedValue);
    }

    if (!"def value".equals(expectedValue)) {
      String actualValue = jdbc.get().execute(new ByTwo<>("id1", id1, "id2", id2, "tmp1_" + field, field));
      assertThat(actualValue)
          .describedAs("tmp1_" + field + "(.id1 = " + id1 + ", .id2 = " + id1 + ")." + field
              + " == '" + actualValue + "', but expected '" + expectedValue + "'")
          .isEqualTo(expectedValue);
    }

    return this;
  }

  private String readLastByOne(String idName, String idValue, String tableName, String fieldName) {
    return jdbc.get().execute(new ByOneLast<>(idName, idValue, tableName, fieldName));
  }

  private String readLastByTwo(String idName1, String idValue1,
                               String idName2, String idValue2,
                               String tableName, String fieldName) {
    return jdbc.get().execute(new ByTwoLast<>(idName1, idValue1, idName2, idValue2, tableName, fieldName));
  }

  private String readByTwo(String idName1, String idValue1,
                           String idName2, String idValue2,
                           String tableName, String fieldName) {
    return jdbc.get().execute(new ByTwo<>(idName1, idValue1, idName2, idValue2, tableName, fieldName));
  }

  private Nf36Updater createUpdater() {
    return newNf36Builder()
        .updater()
        .database(dbTypeSource.get().currentDbType())
        .setJdbc(jdbc.get())
        .setLogAcceptor(logAcceptor.get())
        .build();
  }

  protected void createTableAdam() {
    exec("create table adam (" +
        "  id varchar(32)," +
        "  surname varchar(300)," +
        "  name varchar(300)," +
        "  patronymic varchar(300)," +
        "  primary key(id)" +
        ")");
  }

  protected void createTableAdamSurname() {
    exec("create table adam_surname (" +
        "  id varchar(32)," +
        "  ts timestamp default clock_timestamp()," +
        "  surname varchar(300)," +
        "  foreign key (id) references adam(id)," +
        "  primary key(id, ts)" +
        ")");
  }

  protected void createTableAdamName() {
    exec("create table adam_name (" +
        "  id varchar(32)," +
        "  ts timestamp default clock_timestamp()," +
        "  name varchar(300)," +
        "  foreign key (id) references adam(id)," +
        "  primary key(id, ts)" +
        ")");
  }

  protected void createTableAdamPatronymic() {
    exec("create table adam_Patronymic (" +
        "  id varchar(32)," +
        "  ts timestamp default clock_timestamp()," +
        "  Patronymic varchar(300)," +
        "  foreign key (id) references adam(id)," +
        "  primary key(id, ts)" +
        ")");
  }

  @Test
  public void test_simpleUpdate() {
    dropTable("adam_surname");
    dropTable("adam_name");
    dropTable("adam_patronymic");
    dropTable("adam");
    createTableAdam();
    createTableAdamSurname();
    createTableAdamName();
    createTableAdamPatronymic();

    String ins = "insert into adam (id,surname,name,patronymic)values";
    exec(ins + "('1','Иванов'  , 'Иван'      , 'Петрович' )");
    exec(ins + "('2','Сидоров' , 'Сидор'     , 'Петрович' )");
    exec(ins + "('3','Пушкин'  , 'Александр' , 'Сергеевич')");
    exec(ins + "('4','Панфилов', 'Георгий'   , 'Петрович' )");

    Nf36Updater whereUpdater1 = createUpdater();

    whereUpdater1.setNf3TableName("adam");
    whereUpdater1.setIdFieldNames("id");
    whereUpdater1.setField("adam_name", "name", "Пётр");
    whereUpdater1.where("patronymic", "Петрович");

    assertThat(readByOne("id", "1", "adam", "name")).isEqualTo("Иван");
    assertThat(readByOne("id", "2", "adam", "name")).isEqualTo("Сидор");
    assertThat(readByOne("id", "3", "adam", "name")).isEqualTo("Александр");
    assertThat(readByOne("id", "4", "adam", "name")).isEqualTo("Георгий");

    whereUpdater1.commit();

    assertThat(readByOne("id", "1", "adam", "name")).isEqualTo("Пётр");
    assertThat(readByOne("id", "2", "adam", "name")).isEqualTo("Пётр");
    assertThat(readByOne("id", "3", "adam", "name")).isEqualTo("Александр");
    assertThat(readByOne("id", "4", "adam", "name")).isEqualTo("Пётр");

    assertThat(readLastByOne("id", "1", "adam_name", "name")).isEqualTo("Пётр");
    assertThat(readLastByOne("id", "2", "adam_name", "name")).isEqualTo("Пётр");
    assertThat(readLastByOne("id", "4", "adam_name", "name")).isEqualTo("Пётр");

    Nf36Updater whereUpdater2 = createUpdater();

    whereUpdater2.setNf3TableName("adam");
    whereUpdater2.setIdFieldNames("id");
    whereUpdater2.setField("adam_name", "name", "Егор");
    whereUpdater2.where("patronymic", "Петрович");

    whereUpdater2.commit();

    assertThat(readByOne("id", "1", "adam", "name")).isEqualTo("Егор");
    assertThat(readByOne("id", "2", "adam", "name")).isEqualTo("Егор");
    assertThat(readByOne("id", "3", "adam", "name")).isEqualTo("Александр");
    assertThat(readByOne("id", "4", "adam", "name")).isEqualTo("Егор");

    assertThat(readLastByOne("id", "1", "adam_name", "name")).isEqualTo("Егор");
    assertThat(readLastByOne("id", "2", "adam_name", "name")).isEqualTo("Егор");
    assertThat(readLastByOne("id", "4", "adam_name", "name")).isEqualTo("Егор");
  }

  protected void createTableTmp2() {
    exec("create table tmp2 (" +
        "  id1   varchar(32)," +
        "  id2   varchar(32)," +
        "  name1 varchar(100)," +
        "  name2 varchar(100)," +
        "  f1    varchar(100)," +
        "  f2    varchar(100)," +
        "  last_modified_by varchar(100) default 'no soul'," +
        "  last_modified_at timestamp not null default clock_timestamp()," +
        "  mod_at timestamp," +
        "  primary key(id1, id2)" +
        ")");
  }

  protected void createTableTmp2_f1() {
    exec("create table tmp2_f1 (" +
        "  id1   varchar(32)," +
        "  id2   varchar(32)," +
        "  ts    timestamp not null default clock_timestamp()," +
        "  f1    varchar(100)," +
        "  inserted_by varchar(100) default 'no soul'," +
        "  primary key(id1, id2, ts)" +
        ")");
  }

  protected void createTableTmp2_f2() {
    exec("create table tmp2_f2 (" +
        "  id1   varchar(32)," +
        "  id2   varchar(32)," +
        "  ts    timestamp not null default clock_timestamp()," +
        "  f2    varchar(100)," +
        "  inserted_by varchar(100)," +
        "  primary key(id1, id2, ts)" +
        ")");
  }

  private static long gotMillis(Object d) {
    if (d == null) {
      return 0;
    }

    if (d instanceof Date) {
      return ((Date) d).getTime();
    }

    if (d.getClass().getName().equals("oracle.sql.TIMESTAMP")) {
      try {
        return ((Date) d.getClass().getMethod("timestampValue").invoke(d)).getTime();
      } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
        throw new RuntimeException(e);
      }
    }

    throw new IllegalArgumentException("Cannot got millis from " + d.getClass() + " with value = " + d);
  }

  @Test
  public void test_timesModificationsAndAuthor() throws Exception {
    dropTable("tmp2_f1");
    dropTable("tmp2_f2");
    dropTable("tmp2");
    createTableTmp2();
    createTableTmp2_f1();
    createTableTmp2_f2();

    String ins = "insert into tmp2 (id1, id2, name1, name2, f1, f2) values";
    exec(ins + "('1',  '11',  'john1',  'john2',  'old val 11',  'old val 12')");
    exec(ins + "('2',  '22',  'john1',  'john2',  'old val 21',  'old val 22')");
    exec(ins + "('3',  '33',  'left ',  'left ',  'old val 31',  'old val 32')");
    exec(ins + "('4',  '44',  'left ',  'left ',  'old val 41',  'old val 42')");

    Thread.sleep(10);

    long lma1 = gotMillis(jdbc.get().execute(new ByTwo<>("id1", "1", "id2", "11", "tmp2", "last_modified_at")));
    long lma2 = gotMillis(jdbc.get().execute(new ByTwo<>("id1", "2", "id2", "22", "tmp2", "last_modified_at")));
    long lma3 = gotMillis(jdbc.get().execute(new ByTwo<>("id1", "3", "id2", "33", "tmp2", "last_modified_at")));
    long lma4 = gotMillis(jdbc.get().execute(new ByTwo<>("id1", "4", "id2", "44", "tmp2", "last_modified_at")));

    long mod1 = gotMillis(jdbc.get().execute(new ByTwo<>("id1", "1", "id2", "11", "tmp2", "mod_at")));
    long mod2 = gotMillis(jdbc.get().execute(new ByTwo<>("id1", "2", "id2", "22", "tmp2", "mod_at")));
    long mod3 = gotMillis(jdbc.get().execute(new ByTwo<>("id1", "3", "id2", "33", "tmp2", "mod_at")));
    long mod4 = gotMillis(jdbc.get().execute(new ByTwo<>("id1", "4", "id2", "44", "tmp2", "mod_at")));

    Nf36Updater whereUpdater = createUpdater();

    whereUpdater.setAuthor("Сталина на вас нет");
    whereUpdater.setNf3TableName("tmp2");
    whereUpdater.setIdFieldNames("id1", "id2");
    whereUpdater.setAuthorFieldNames("last_modified_by", "inserted_by");
    whereUpdater.setField("tmp2_f1", "f1", "new value 1");
    whereUpdater.setField("tmp2_f2", "f2", "new value 2");
    whereUpdater.where("name1", "john1");
    whereUpdater.where("name2", "john2");
    whereUpdater.updateFieldToNow("last_modified_at");
    whereUpdater.updateFieldToNow("mod_at");

    whereUpdater.commit();

    long lma1after = gotMillis(jdbc.get().execute(new ByTwo<>("id1", "1", "id2", "11", "tmp2", "last_modified_at")));
    long lma2after = gotMillis(jdbc.get().execute(new ByTwo<>("id1", "2", "id2", "22", "tmp2", "last_modified_at")));
    long lma3after = gotMillis(jdbc.get().execute(new ByTwo<>("id1", "3", "id2", "33", "tmp2", "last_modified_at")));
    long lma4after = gotMillis(jdbc.get().execute(new ByTwo<>("id1", "4", "id2", "44", "tmp2", "last_modified_at")));

    System.out.println("lma1      " + lma1);
    System.out.println("lma1after " + lma1after);
    System.out.println("lma2      " + lma2);
    System.out.println("lma2after " + lma2after);
    System.out.println("lma3      " + lma3);
    System.out.println("lma3after " + lma3after);
    System.out.println("lma4      " + lma4);
    System.out.println("lma4after " + lma4after);

    assertThat(lma1).isLessThan(lma1after);
    assertThat(lma2).isLessThan(lma2after);
    assertThat(lma3).isEqualTo(lma3after);
    assertThat(lma4).isEqualTo(lma4after);

    long mod1after = gotMillis(jdbc.get().execute(new ByTwo<>("id1", "1", "id2", "11", "tmp2", "mod_at")));
    long mod2after = gotMillis(jdbc.get().execute(new ByTwo<>("id1", "2", "id2", "22", "tmp2", "mod_at")));
    long mod3after = gotMillis(jdbc.get().execute(new ByTwo<>("id1", "3", "id2", "33", "tmp2", "mod_at")));
    long mod4after = gotMillis(jdbc.get().execute(new ByTwo<>("id1", "4", "id2", "44", "tmp2", "mod_at")));

    assertThat(mod1).isLessThan(mod1after);
    assertThat(mod2).isLessThan(mod2after);
    assertThat(mod3).isEqualTo(mod3after);
    assertThat(mod4).isEqualTo(mod4after);

    assertThat(readByTwo("id1", "1", "id2", "11", "tmp2", "last_modified_by")).isEqualTo("Сталина на вас нет");
    assertThat(readByTwo("id1", "2", "id2", "22", "tmp2", "last_modified_by")).isEqualTo("Сталина на вас нет");
    assertThat(readByTwo("id1", "3", "id2", "33", "tmp2", "last_modified_by")).isEqualTo("no soul");
    assertThat(readByTwo("id1", "4", "id2", "44", "tmp2", "last_modified_by")).isEqualTo("no soul");

    assertThat(readLastByTwo("id1", "1", "id2", "11", "tmp2_f1", "inserted_by")).isEqualTo("Сталина на вас нет");
    assertThat(readLastByTwo("id1", "2", "id2", "22", "tmp2_f1", "inserted_by")).isEqualTo("Сталина на вас нет");

    assertThat(readLastByTwo("id1", "1", "id2", "11", "tmp2_f2", "inserted_by")).isEqualTo("Сталина на вас нет");
    assertThat(readLastByTwo("id1", "2", "id2", "22", "tmp2_f2", "inserted_by")).isEqualTo("Сталина на вас нет");
  }
}
