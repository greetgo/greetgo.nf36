package kz.greetgo.ng36.ann;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;

@Documented
@Target({FIELD, TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Description {
  String value();
}
