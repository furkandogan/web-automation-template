package test.tools.selenium.config;

import test.tools.selenium.util.DbUtility;
import test.tools.selenium.instances.DBConfigurationInstance;

public class DBManager {

    public static DbUtility getDb() throws Exception {

        return DBConfigurationInstance.getInstance().getDbUtility();
    }
}
