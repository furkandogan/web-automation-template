package test.tools.selenium.instances;

import test.tools.selenium.util.DbUtility;

public class DBConfigurationInstance {


    private static DBConfigurationInstance instance = null;

    private long instanceId = 0L;


    private DbUtility dbUtility = null;


    //public instance creater
    public static synchronized DBConfigurationInstance getInstance() throws Exception {

        if (instance == null) {
            instance = new DBConfigurationInstance();

            // set rm id
            instance.instanceId = System.currentTimeMillis();
            instance.loadDbUtils();
        }

        return instance;
    }

    public DbUtility getDbUtility() {
        return dbUtility;
    }


    private void loadDbUtils() {

        try {
            dbUtility = new DbUtility();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
