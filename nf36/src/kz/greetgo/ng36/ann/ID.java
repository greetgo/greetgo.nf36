package kz.greetgo.ng36.ann;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;

@Documented
@Target(FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ID {
  long seqFrom() default 1;

  Class<?> ref() default Object.class;

  String nextPart() default "";

  int order() default 1;
}
