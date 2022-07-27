package test.tools.selenium.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import test.tools.selenium.config.ConfigurationManager;
import test.tools.selenium.config.PropertyNames;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

public class DbUtility {

    private ComboPooledDataSource cpds = null;
    private Connection connection = null;
    private String dbDriver = null;
    private String jdbcUrl = null;
    private String username = null;
    private String password = null;

    public ComboPooledDataSource getDataSource() {
        return cpds;
    }

    public void setDataSource(ComboPooledDataSource cpds) {
        this.cpds = cpds;
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

    public ComboPooledDataSource initConnectionPool() throws Exception {
        try {
            Class.forName(getDbDriver());
            cpds = new ComboPooledDataSource();
            cpds.setJdbcUrl(getJdbcUrl());
            cpds.setUser(getUser());
            cpds.setPassword(getPassword());
            cpds.setTestConnectionOnCheckin(true);
            cpds.setTestConnectionOnCheckout(true);
            cpds.setInitialPoolSize(3);
            cpds.setMinPoolSize(3);
            cpds.setMaxPoolSize(20);
            cpds.setMaxIdleTime(600);
            cpds.setMaxStatements(50);
            cpds.setAcquireIncrement(2);
            cpds.setIdleConnectionTestPeriod(300);

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    try {
                        if (cpds.getNumBusyConnections() > 0) {
                            System.out.println(String.format("[CONNECTION POOL] [{%s}], Max: {%d}, Busy: {%d}, Idle: {%d}",
                                    username,
                                    cpds.getNumConnections(),
                                    cpds.getNumBusyConnections(),
                                    cpds.getNumIdleConnections())
                            );
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, 1000, 1000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cpds;
    }

    public Connection initConnectionFromPool() throws Exception {

        connection = cpds.getConnection();
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

        if (cpds != null) {
            cpds.close();
        }
    }

}
