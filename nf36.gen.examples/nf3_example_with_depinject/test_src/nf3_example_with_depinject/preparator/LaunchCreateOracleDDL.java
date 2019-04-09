package nf3_example_with_depinject.preparator;

import nf3_example_with_depinject.bean_containers.BeanContainerOracle;

public class LaunchCreateOracleDDL {
  public static void main(String[] args) {

    BeanContainerOracle container = BeanContainerOracle.create();

    container.generators()
      .generateSqlFiles()
      .forEach(System.out::println);

  }
}
