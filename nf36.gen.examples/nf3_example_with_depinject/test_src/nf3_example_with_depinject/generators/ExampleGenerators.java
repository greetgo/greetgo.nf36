package nf3_example_with_depinject.generators;

import kz.greetgo.depinject.core.Bean;
import kz.greetgo.depinject.core.BeanGetter;
import kz.greetgo.depinject.core.HasAfterInject;
import kz.greetgo.nf36.db.worker.db.DbParameters;
import kz.greetgo.ng36.gen.GeneratorBuilder;
import kz.greetgo.ng36.gen.ddl.GeneratorDDL;
import kz.greetgo.ng36.gen.java.GeneratorJava;
import kz.greetgo.ng36.gen.dialect.SqlDialect;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static kz.greetgo.nf36.db.worker.util.Places.nf3ExampleWithDepinject;

@Bean
public class ExampleGenerators implements HasAfterInject {

  public BeanGetter<DbParameters> dbParameters;

  public BeanGetter<SqlDialect> sqlDialect;

  private GeneratorDDL generatorDDL;
  private GeneratorJava generatorJava;

  @Override
  public void afterInject() {
    GeneratorBuilder builder = GeneratorBuilder
      .newInstance()
      .setSqlDialect(sqlDialect.get())
      //
      ;

    generatorDDL = builder.createGeneratorDDL();
    generatorJava = builder.createGeneratorJava();
  }

  public void generateJava() {
    generatorJava.generate();
  }

  public List<File> generateSqlFiles() {
    String dir = nf3ExampleWithDepinject() + "/build/gen_sql/" + sqlDialect.get().getClass().getSimpleName() + "/";
    List<File> sqlFileList = new ArrayList<>();

    {
      File outFile = new File(dir + "001_nf3_tables.sql");
      generatorDDL.generateNf3Tables(outFile);
      sqlFileList.add(outFile);
    }

    {
      File outFile = new File(dir + "002_sequences.sql");
      generatorDDL.generateSequences(outFile);
      sqlFileList.add(outFile);
    }

    {
      File outFile = new File(dir + "003_nf3_references.sql");
      generatorDDL.generateNf3References(outFile);
      sqlFileList.add(outFile);
    }

    {
      File outFile = new File(dir + "004_comments.sql");
      generatorDDL.generateComments(outFile);
      sqlFileList.add(outFile);
    }

    return sqlFileList;
  }
}
