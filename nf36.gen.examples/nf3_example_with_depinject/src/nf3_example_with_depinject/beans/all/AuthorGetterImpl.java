package nf3_example_with_depinject.beans.all;

import kz.greetgo.depinject.core.Bean;
import nf3_example_with_depinject.util.AuthorGetter;
import nf3_example_with_depinject.util.NoRef;

@Bean
@NoRef
public class AuthorGetterImpl implements AuthorGetter {

  public String author;

  @Override
  public String getAuthor() {
    return author;
  }
}
