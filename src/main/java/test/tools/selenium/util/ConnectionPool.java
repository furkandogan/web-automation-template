package test.tools.selenium.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

public class ConnectionPool {

    private ComboPooledDataSource cpds;
    private final String driverClass;
    private final String jdbcUrl;
    private final String username;
    private final String password;
    private final int initialPoolSize;
    private final int minPoolSize;
    private final int acquireIncrement;
    private final int maxPoolSize;
    private final int maxIdleTime;
    private final int unreturnedConnectionTimeout;
    private final int acquireRetryAttempts;
    private final int checkoutTimeout;

    public ConnectionPool(String driverClass, String jdbcUrl, String username, String password, int initialPoolSize, int minPoolSize, int acquireIncrement, int maxPoolSize, int maxIdleTime, int unreturnedConnectionTimeout) {
        this.driverClass = driverClass;
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;
        this.initialPoolSize = initialPoolSize;
        this.minPoolSize = minPoolSize;
        this.acquireIncrement = acquireIncrement;
        this.maxPoolSize = maxPoolSize;
        this.maxIdleTime = maxIdleTime;
        this.unreturnedConnectionTimeout = unreturnedConnectionTimeout;
        this.acquireRetryAttempts = 30; // default value
        this.checkoutTimeout = 0; // default value
        prepareConnectionPool();
    }

    public ConnectionPool(String driverClass, String jdbcUrl, String username, String password, int initialPoolSize, int minPoolSize, int acquireIncrement, int maxPoolSize, int maxIdleTime, int unreturnedConnectionTimeout, int acquireRetryAttempts, int checkoutTimeout) {
        this.driverClass = driverClass;
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;
        this.initialPoolSize = initialPoolSize;
        this.minPoolSize = minPoolSize;
        this.acquireIncrement = acquireIncrement;
        this.maxPoolSize = maxPoolSize;
        this.maxIdleTime = maxIdleTime;
        this.unreturnedConnectionTimeout = unreturnedConnectionTimeout;
        this.acquireRetryAttempts = acquireRetryAttempts;
        this.checkoutTimeout = checkoutTimeout;
        prepareConnectionPool();
    }

    private void prepareConnectionPool() {
        try {
            cpds = new ComboPooledDataSource();
            cpds.setDriverClass(driverClass);
            cpds.setJdbcUrl(jdbcUrl);
            cpds.setUser(username);
            cpds.setPassword(password);
            cpds.setInitialPoolSize(initialPoolSize);
            cpds.setMinPoolSize(minPoolSize);
            cpds.setAcquireIncrement(acquireIncrement);
            cpds.setMaxPoolSize(maxPoolSize);
            cpds.setMaxIdleTime(maxIdleTime);
            cpds.setUnreturnedConnectionTimeout(unreturnedConnectionTimeout);
            cpds.setAcquireRetryAttempts(acquireRetryAttempts);
            cpds.setCheckoutTimeout(checkoutTimeout);

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

                    }
                }
            }, 1000, 1000);

        } catch (Exception e) {

        }
    }

    public ComboPooledDataSource getDatasource() {
        return cpds;
    }

    public Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            connection = getDatasource().getConnection();
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw e;
        } catch (Exception e) {

        } finally {

        }
        return connection;
    }

    public static void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {

        }
    }

    public static void rollbackConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (Exception e) {

        }
    }

    public static void commitConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.commit();
            }
        } catch (Exception e) {

        }
    }

    public String getStatus() {
        String s = "";
        try {
            s = "Pool: " + username
                    + ", Max: " + cpds.getNumConnections()
                    + ", Busy: " + cpds.getNumBusyConnections()
                    + ", Idle: " + cpds.getNumIdleConnections();

        } catch (Exception e) {
            s += e.toString();
        }
        return s;
    }
}

