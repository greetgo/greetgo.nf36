package nf3_example_with_depinject.generated.impl.postgres;

import kz.greetgo.nf36.core.Saver;
import kz.greetgo.nf36.core.Upserter;
import kz.greetgo.nf36.core.SequenceNext;

public abstract class AbstractExampleUpserterPostgres {
  protected abstract Upserter createUpserter();
  protected abstract SequenceNext getSequenceNext();
  protected abstract Saver createSaver();
}
