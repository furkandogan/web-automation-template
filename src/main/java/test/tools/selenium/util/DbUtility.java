package test.tools.selenium.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import test.tools.selenium.config.ConfigurationManager;
import test.tools.selenium.config.PropertyNames;

import java.sql.*;
public class DbUtility {

    private Connection connection = null;
    private Statement statement = null;
    private ComboPooledDataSource dataSource = null;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public ComboPooledDataSource initConnectionPool() throws Exception {

        Class.forName(ConfigurationManager.getConfigProperty(PropertyNames.DB_DRIVER));
        String user = ConfigurationManager.getConfigProperty(PropertyNames.DB_USER);
        String password = ConfigurationManager.getConfigProperty(PropertyNames.DB_PS);
        String jdbcUrl = ConfigurationManager.getConfigProperty(PropertyNames.DB_JDBC_URL);

        String driverName = "oracle.jdbc.driver.OracleDriver";
        Class.forName(driverName);
        dataSource = new ComboPooledDataSource();
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setIdleConnectionTestPeriod(60 * 5);
        dataSource.setMinPoolSize(3);
        dataSource.setInitialPoolSize(3);
        dataSource.setAcquireIncrement(1);
        dataSource.setMaxPoolSize(10);

        return dataSource;
    }

    public Connection initConnection() throws Exception {

        connection = dataSource.getConnection();

        return connection;
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
