package test.tools.selenium.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import test.tools.selenium.config.ConfigurationManager;
import test.tools.selenium.config.PropertyNames;

import java.sql.*;
public class DbUtility {

    private ComboPooledDataSource dataSource = null;
    private Connection connection = null;
    private String dbDriver = null;
    private String jdbcUrl = null;
    private String user = null;
    private String password = null;

    public ComboPooledDataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(ComboPooledDataSource dataSource) {
        this.dataSource = dataSource;
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
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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

    public ComboPooledDataSource initConnectionPool() throws Exception {

        Class.forName(getDbDriver());
        dataSource = new ComboPooledDataSource();
        dataSource.setJdbcUrl(getJdbcUrl());
        dataSource.setUser(getUser());
        dataSource.setPassword(getPassword());
        dataSource.setTestConnectionOnCheckin(true);
        dataSource.setTestConnectionOnCheckout(true);
        dataSource.setInitialPoolSize(3);
        dataSource.setMinPoolSize(3);
        dataSource.setMaxPoolSize(20);
        dataSource.setMaxStatements(50);
        dataSource.setAcquireIncrement(2);
        dataSource.setIdleConnectionTestPeriod(300);

        return dataSource;
    }

    public Connection initConnectionFromPool() throws Exception {

        connection = dataSource.getConnection();

        return connection;
    }

    public Connection initConnection() throws Exception {

        Class.forName(getDbDriver());
        connection = DriverManager.getConnection(getJdbcUrl(), getUser(), getPassword());

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

        if (dataSource != null) {
            dataSource.close();
        }
    }

}
