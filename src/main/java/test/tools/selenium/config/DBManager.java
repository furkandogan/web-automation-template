package test.tools.selenium.config;

import test.tools.selenium.instances.DBConfigurationInstance;
import test.tools.selenium.util.DbUtility;

public class DBManager {

    public static DbUtility getDb() throws Exception {

        return DBConfigurationInstance.getInstance().getDbUtility();
    }
}
