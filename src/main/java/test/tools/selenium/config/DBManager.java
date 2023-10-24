package test.tools.selenium.config;

import test.tools.selenium.util.DbUtility;
import test.tools.selenium.instances.DBConfigurationInstance;

public class DBManager {

    public static DbUtility getOracleDb(String dbDriver, String user, String password, String jdbcUrl) throws Exception {

        return DBConfigurationInstance.getInstance(dbDriver, user, password, jdbcUrl).getDbUtility();
    }

}
