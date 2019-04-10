package kz.greetgo.ng36.ann;

public interface SequenceNext {
  long nextLong(String sequenceName);

  int nextInt(String sequenceName);

  default int nextInteger(String sequenceName) {
    return nextInt(sequenceName);
  }
}
