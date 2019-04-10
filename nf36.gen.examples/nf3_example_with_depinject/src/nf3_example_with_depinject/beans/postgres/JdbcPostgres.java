package nf3_example_with_depinject.beans.postgres;

import kz.greetgo.db.ConnectionCallback;
import kz.greetgo.db.Jdbc;
import kz.greetgo.depinject.core.Bean;
import nf3_example_with_depinject.env.DbParamsPostgres;
import nf3_example_with_depinject.errors.SqlError;
import nf3_example_with_depinject.util.NoRef;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Bean
@NoRef
public class JdbcPostgres implements Jdbc {

  @Override
  public <T> T execute(ConnectionCallback<T> connectionCallback) {

    try {
      Class.forName("org.postgresql.Driver");

      try (Connection connection = DriverManager.getConnection(
          DbParamsPostgres.url, DbParamsPostgres.username, DbParamsPostgres.password
      )) {
        return connectionCallback.doInConnection(connection);
      }

    } catch (SQLException e) {
      throw new SqlError(e);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }

}
