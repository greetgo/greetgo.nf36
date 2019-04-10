package kz.greetgo.ng36.gen;

public class GeneratorBuilder {

  private GeneratorBuilder() {}

  public static GeneratorBuilder newInstance() {
    return new GeneratorBuilder();
  }

  public GeneratorDDL createGeneratorDDL() {
    return null;
  }

  public GeneratorJava createGeneratorJava() {
    return new GeneratorJava() {
      @Override
      public void generate() {
        System.out.println("Hello world WOW");
      }
    };
  }
}
