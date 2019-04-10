package nf3_example_with_depinject.env;

import kz.greetgo.conf.sys_params.SysParams;

import static kz.greetgo.nf36.db.worker.utils.DbUtils.changeUrlDbName;

public class DbParamsPostgres {

  public static final String username = System.getProperty("user.name") + "_nf3_example";
  public static final String password = System.getProperty("user.name") + "_nf3_example_123";
  public static final String dbName = username;
  public static final String url = changeUrlDbName(SysParams.pgAdminUrl(), username);

}
