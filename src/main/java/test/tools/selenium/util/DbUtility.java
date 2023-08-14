package test.tools.selenium.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.jdbc.pool.DataSource;
import test.tools.selenium.config.ConfigurationManager;
import test.tools.selenium.config.PropertyNames;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

public class DbUtility {

    final static Logger logger = LogManager.getLogger(DbUtility.class);
    private DataSource ds = null;
    private Connection connection = null;
    private String dbDriver = null;
    private String jdbcUrl = null;
    private String username = null;
    private String password = null;

    public DataSource getDataSource() {
        return ds;
    }

    public void setDataSource(DataSource ds) {
        this.ds = ds;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public String getDbDriver() {
        return dbDriver;
    }

    public void setDbDriver(String dbDriver) {
        this.dbDriver = dbDriver;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getUser() {
        return username;
    }

    public void setUser(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public DbUtility() {
        try {
            setDbDriver(ConfigurationManager.getConfigProperty(PropertyNames.DB_DRIVER));
            setUser(ConfigurationManager.getConfigProperty(PropertyNames.DB_USER));
            setPassword(ConfigurationManager.getConfigProperty(PropertyNames.DB_PS));
            setJdbcUrl(ConfigurationManager.getConfigProperty(PropertyNames.DB_JDBC_URL));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DbUtility(String dbDriver, String user, String password, String jdbcUrl) {
        try {
            setDbDriver(dbDriver);
            setUser(user);
            setPassword(password);
            setJdbcUrl(jdbcUrl);
        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
        }
    }

    public DataSource initConnectionPool() throws Exception {
        ds = new DataSource();
        ds.setDriverClassName(getDbDriver());
        ds.setUrl(getJdbcUrl());
        ds.setUsername(getUser());
        ds.setPassword(getPassword());
        ds.setInitialSize(5);
        ds.setMinIdle(1);
        ds.setMaxActive(100);
        ds.setMaxIdle(10);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    if (ds.getNumActive() > 0) {
                        logger.info(String.format("[CONNECTION POOL] [{%s}], Max: {%d}, Busy: {%d}, Idle: {%d}",
                                username,
                                ds.getMaxActive(),
                                ds.getNumActive(),
                                ds.getNumIdle())
                        );
                    }
                } catch (Exception e) {
                    logger.error(e);
                }
            }
        }, 1000, 1000);

        return ds;
    }

    public Connection initConnectionFromPool() throws Exception {

        connection = ds.getConnection();
        connection.setAutoCommit(false);
        return connection;
    }

    public Connection initConnection() throws Exception {

        Class.forName(getDbDriver());
        connection = DriverManager.getConnection(getJdbcUrl(), getUser(), getPassword());
        connection.setAutoCommit(false);
        return connection;
    }

    public void disConnect() throws SQLException {

        if (connection != null) {
            connection.close();
        }
    }

    public void disConnect(Connection connection) throws SQLException {

        if (connection != null) {
            connection.close();
        }
    }

    public void closeConnectionPool() throws SQLException {

        if (ds != null) {
            ds.close();
        }
    }

}
