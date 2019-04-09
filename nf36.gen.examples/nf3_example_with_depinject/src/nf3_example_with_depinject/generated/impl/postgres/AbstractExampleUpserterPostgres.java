package nf3_example_with_depinject.generated.impl.postgres;

import kz.greetgo.nf36.core.Nf36Saver;
import kz.greetgo.nf36.core.Nf36Upserter;
import kz.greetgo.nf36.core.SequenceNext;

public abstract class AbstractExampleUpserterPostgres {
  protected abstract Nf36Upserter createUpserter();
  protected abstract SequenceNext getSequenceNext();
  protected abstract Nf36Saver createSaver();
}
