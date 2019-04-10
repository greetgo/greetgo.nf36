package kz.greetgo.nf36.bridges;

import kz.greetgo.nf36.core.Saver;
import kz.greetgo.nf36.core.Upserter;

public class SaverBuilder {
  private Upserter upserter = null;

  public SaverBuilder setUpserter(Upserter upserter) {
    this.upserter = upserter;
    return this;
  }

  private void check() {
    if (built) {
      throw new RuntimeException("Already built. Please create new builder to build new saver");
    }

    if (upserter == null) {
      throw new RuntimeException("upserter == null. Please, define it");
    }
  }

  private boolean built = false;

  public Saver build() {
    check();
    built = true;
    return new SaverUpserterBridge(upserter);
  }
}
