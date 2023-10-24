package test.tools.selenium.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.jdbc.pool.DataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DbUtility {

    final static Logger logger = LogManager.getLogger(DbUtility.class);
    private DataSource ds = null;
    private Connection connection = null;
    private String dbDriver = null;
    private String jdbcUrl = null;
    private String username = null;
    private String password = null;

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

    public DataSource getDataSource() throws Exception {
        ds = new DataSource();
        ds.setDriverClassName(getDbDriver());
        ds.setUrl(getJdbcUrl());
        ds.setUsername(getUser());
        ds.setPassword(getPassword());
        ds.setInitialSize(5);
        ds.setMinIdle(1);
        ds.setMaxActive(100);
        ds.setMaxIdle(10);
        return ds;
    }

    public Connection getConnection() throws Exception {
        connection = ds.getConnection();
        connection.setAutoCommit(false);
        return connection;
    }

    public void disConnect(Connection connection) throws SQLException {

        if (connection != null) {
            connection.close();
        }
    }

    public void closeConnectionPool(DataSource dataSource) throws SQLException {

        if (dataSource != null) {
            dataSource.close();
        }
    }

}
