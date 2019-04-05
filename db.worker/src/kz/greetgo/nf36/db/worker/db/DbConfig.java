package kz.greetgo.nf36.db.worker.db;

public interface DbConfig {
  String url();

  String username();

  String password();

  String dbName();
}
