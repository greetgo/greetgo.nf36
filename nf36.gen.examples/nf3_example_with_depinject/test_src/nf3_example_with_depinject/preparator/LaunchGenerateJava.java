package nf3_example_with_depinject.preparator;

import nf3_example_with_depinject.bean_containers.BeanContainerOracle;
import nf3_example_with_depinject.bean_containers.BeanContainerPostgres;

public class LaunchGenerateJava {
  public static void main(String[] args) {
    BeanContainerPostgres.create().generators().generateJava();
    BeanContainerOracle.create().generators().generateJava();
  }
}
