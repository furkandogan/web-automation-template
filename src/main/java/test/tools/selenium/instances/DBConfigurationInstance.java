package test.tools.selenium.instances;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import test.tools.selenium.util.DbUtility;

public class DBConfigurationInstance {

    final static Logger logger = LogManager.getLogger(DbUtility.class);

    private static DBConfigurationInstance instance = null;

    private long instanceId = 0L;

    private DbUtility dbUtility = null;

    public static synchronized DBConfigurationInstance getInstance(String dbDriver, String user, String password, String jdbcUrl) throws Exception {

        if (instance == null) {
            instance = new DBConfigurationInstance();

            // set rm id
            instance.instanceId = System.currentTimeMillis();
            instance.loadDbUtils(dbDriver, user, password, jdbcUrl);
        }

        return instance;
    }

    public DbUtility getDbUtility() {
        return dbUtility;
    }

    private void loadDbUtils(String dbDriver, String user, String password, String jdbcUrl) {

        try {
            dbUtility = new DbUtility(dbDriver, user, password, jdbcUrl);
        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
        }
    }

}
