package kz.greetgo.db.nf36.gen;

import kz.greetgo.nf36.errors.IllegalPackage;
import kz.greetgo.nf36.utils.UtilsNf36;
import org.testng.annotations.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ThreadLocalRandom;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.fest.assertions.api.Assertions.assertThat;

public class UtilsNf36Test {

  @Test
  public void calcSubPackage_1() {
    String subPackage = UtilsNf36.calcSubPackage("kz.greetgo.wow", "kz.greetgo.wow.asd.dsa");
    assertThat(subPackage).isEqualTo("asd.dsa");
  }

  @Test
  public void calcSubPackage_2() {
    String subPackage = UtilsNf36.calcSubPackage("kz.greetgo.wow", "kz.greetgo.wow");
    assertThat(subPackage).isNull();
  }

  @Test(expectedExceptions = IllegalPackage.class)
  public void calcSubPackage_3() {
    UtilsNf36.calcSubPackage("kz.greetgo.wow", "kz.greetgo.left.wow");
  }

  @Test
  public void resolvePackage_1() {
    String PackageName = UtilsNf36.resolvePackage("kz.greetgo.wow", "asd.dsa");
    assertThat(PackageName).isEqualTo("kz.greetgo.wow.asd.dsa");
  }

  @Test
  public void resolvePackage_2() {
    String PackageName = UtilsNf36.resolvePackage("kz.greetgo.wow", null);
    assertThat(PackageName).isEqualTo("kz.greetgo.wow");
  }

  @Test
  public void resolveJavaFile() {
    File file = UtilsNf36.resolveJavaFile("build/asd", "kz.greetgo.wow", "Client");
    assertThat(file.getPath()).isEqualTo("build/asd/kz/greetgo/wow/Client.java");
  }

  @Test
  public void extractSimpleName_1() {
    String name = UtilsNf36.extractSimpleName("asd.dsa.HelloWorld");
    assertThat(name).isEqualTo("HelloWorld");
  }

  @Test
  public void extractSimpleName_2() {
    String name = UtilsNf36.extractSimpleName("HelloWorld");
    assertThat(name).isEqualTo("HelloWorld");
  }

  @Test
  public void resolveFullName_1() {
    String fullName = UtilsNf36.resolveFullName("asd.dsa", "Hello");
    assertThat(fullName).isEqualTo("asd.dsa.Hello");
  }

  @Test
  public void resolveFullName_2() {
    String fullName = UtilsNf36.resolveFullName(null, "Hello");
    assertThat(fullName).isEqualTo("Hello");
  }

  @Test
  public void javaNameToDbName_1() {
    String dbName = UtilsNf36.javaNameToDbName("firstName");
    assertThat(dbName).isEqualTo("first_name");
  }

  @Test
  public void javaNameToDbName_2() {
    String dbName = UtilsNf36.javaNameToDbName("hello");
    assertThat(dbName).isEqualTo("hello");
  }

  @Test
  public void javaNameToDbName_3() {
    String dbName = UtilsNf36.javaNameToDbName("helloDTO");
    assertThat(dbName).isEqualTo("hello_dto");
  }

  @Test
  public void javaNameToDbName_4() {
    String dbName = UtilsNf36.javaNameToDbName("HelloWorld");
    assertThat(dbName).isEqualTo("hello_world");
  }

  @Test
  public void javaNameToDbName_5() {
    String dbName = UtilsNf36.javaNameToDbName("первоеИмя");
    assertThat(dbName).isEqualTo("первое_имя");
  }


  @Test
  public void javaNameToDbName_6() {
    String dbName = UtilsNf36.javaNameToDbName("привет");
    assertThat(dbName).isEqualTo("привет");
  }

  @Test
  public void javaNameToDbName_7() {
    String dbName = UtilsNf36.javaNameToDbName("приветСМИ");
    assertThat(dbName).isEqualTo("привет_сми");
  }

  @Test
  public void javaNameToDbName_8() {
    String dbName = UtilsNf36.javaNameToDbName("ПриветМир");
    assertThat(dbName).isEqualTo("привет_мир");
  }

  @Test
  public void javaNameToDbName_9() {
    String dbName = UtilsNf36.javaNameToDbName("helloWORLD");
    assertThat(dbName).isEqualTo("hello_world");
  }

  @Test
  public void javaNameToDbName_10() {
    String dbName = UtilsNf36.javaNameToDbName("helloWORLdMan");
    assertThat(dbName).isEqualTo("hello_world_man");
  }

  @Test
  public void packageDir_1() {
    String dir = UtilsNf36.packageDir("build/src", "kz.greetgo.asd.dsa");
    assertThat(dir).isEqualTo("build/src/kz/greetgo/asd/dsa");
  }

  @Test
  public void packageDir_2() {
    String dir = UtilsNf36.packageDir("build/src", null);
    assertThat(dir).isEqualTo("build/src");
  }

  @Test
  public void extractAfterDot_noDots() {
    String actual = UtilsNf36.extractAfterDot("name without a dot");
    assertThat(actual).isEqualTo("name without a dot");
  }

  @Test
  public void extractAfterDot_oneDot() {
    String actual = UtilsNf36.extractAfterDot("hello.world");
    assertThat(actual).isEqualTo("world");
  }

  @Test
  public void extractAfterDot_threeDots() {
    String actual = UtilsNf36.extractAfterDot("the_east.The north.Hello_World");
    assertThat(actual).isEqualTo("Hello_World");
  }

  private static void newFile(Path path, String subName, String content) throws Exception {
    for (String sub : subName.split("/")) {
      path = path.resolve(sub);
    }

    path.toFile().getParentFile().mkdirs();
    Files.write(path, content.getBytes(UTF_8));
  }

  private void assertContent(Path path, String subName, String content) throws Exception {
    for (String sub : subName.split("/")) {
      path = path.resolve(sub);
    }

    assertThat(path.toFile()).exists();

    String actualContent = Files.readString(path);

    assertThat(actualContent).describedAs("File " + path).isEqualTo(content);
  }

  @Test
  public void copyDirContent() throws Exception {

    int i = ThreadLocalRandom.current().nextInt();
    if (i < 0) i = -i;

    Path tmp = Paths.get("build").resolve(UtilsNf36Test.class.getSimpleName()).resolve("copyDirContent_" + i);

    Path from = tmp.resolve("from");
    Path to = tmp.resolve("to").resolve("status").resolve("example");

    newFile(from, "sinus.txt", "hello from sinus");
    newFile(from, ".hidden.txt", "hello from hidden");
    newFile(from, "hello/dem.txt", "hello from hello/dem");
    newFile(from, "hello/.hid.txt", "hello from hello/.hid");
    newFile(from, "pin/status/quads.txt", "hello from pin/status/quads");
    newFile(from, "wow/current/life/.hin.txt", "hello from wow/current/life/.hin");

    //
    //
    UtilsNf36.copyDirContent(from.toString(), to.toString());
    //
    //

    assertContent(to, "sinus.txt", "hello from sinus");
    assertContent(to, ".hidden.txt", "hello from hidden");
    assertContent(to, "hello/dem.txt", "hello from hello/dem");
    assertContent(to, "hello/.hid.txt", "hello from hello/.hid");
    assertContent(to, "pin/status/quads.txt", "hello from pin/status/quads");
    assertContent(to, "wow/current/life/.hin.txt", "hello from wow/current/life/.hin");

  }

}
