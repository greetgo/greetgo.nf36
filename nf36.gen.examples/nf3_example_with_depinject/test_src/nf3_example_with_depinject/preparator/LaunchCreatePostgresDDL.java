package nf3_example_with_depinject.preparator;

import nf3_example_with_depinject.bean_containers.BeanContainerPostgres;

public class LaunchCreatePostgresDDL {

  public static void main(String[] args) {
    BeanContainerPostgres container = BeanContainerPostgres.create();

    container.generators()
      .generateSqlFiles()
      .forEach(System.out::println);

  }

}
