package kz.greetgo.ng36.util;

import org.testng.annotations.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class UtilsNf36Test {

  @Test
  public void javaNameToDbName() {

    String helloWorld = UtilsNf36.javaNameToDbName("helloWorld");

    assertThat(helloWorld).isEqualTo("hello_world");

  }

}
