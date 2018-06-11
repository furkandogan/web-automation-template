package test.tools.selenium.util;

import test.tools.selenium.config.ConfigurationManager;
import test.tools.selenium.config.PropertyNames;

import java.sql.*;

public class DbUtility {


    private static ResultSet rsCacheData;

    private Connection connection = null;
    private Statement statement = null;


    public static ResultSet getRsCacheData() {
        return rsCacheData;
    }


    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public Connection initConnection() throws Exception {

        Class.forName(PropertyNames.DB_DRIVER);

        String user = ConfigurationManager.getConfigProperty(PropertyNames.DB_USER);
        String password = ConfigurationManager.getConfigProperty(PropertyNames.DB_PS);
        String jdbcUrl = ConfigurationManager.getConfigProperty(PropertyNames.DB_JDBC_URL);

        connection = DriverManager.getConnection(jdbcUrl, user, password);

        return connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }


    public ResultSet getAllResult(String query) throws SQLException {
        statement = connection.createStatement();
        ResultSet res = statement.executeQuery(query);
        return res;

    }

    // Gönderilen queryinin ilk rowunu geriye dönderir
    public ResultSet getFirstRowResult(String query) throws SQLException {
        statement = connection.createStatement();
        statement.setMaxRows(1);
        ResultSet res = statement.executeQuery(query);
        return res;
    }

    // Gönderilen queryinin ilk rowunun string typeli colonunu gönderir
    public String getFirstRowResultCellValue(String query, String columnName)
            throws SQLException {
        statement = connection.createStatement();
        statement.setMaxRows(1);
        ResultSet res = statement.executeQuery(query);
        res.next();

        return res.getString(columnName);
    }

    // Gönderilen queryinin ilk rowunun int type li colonunun degerini gönderir
    public int getFirstRowResultCellValue(String query, int columnName)
            throws SQLException {
        statement = connection.createStatement();
        statement.setMaxRows(1);
        ResultSet res = statement.executeQuery(query);
        res.next();

        return res.getInt(columnName);
    }

    public void disConnect() throws SQLException {

        if (connection != null) {
            connection.close();
        }
    }

    // public int fetchSizeSalesData() {
    // int fetchSize = 0;
    // if(rsSalesData != null){
    // fetchSize = rsSalesData.getFetchSize();
    // }
    // return fetchSize;
    // }


    public String fetchData(int rowNumber, String rowName, String rowValue) throws Exception {
        if (rsCacheData != null) {

            int fetchSize = rsCacheData.getFetchSize();

            if (rowNumber > 0 && rowNumber <= fetchSize) {


                for (int i = 1; i <= fetchSize; i++) {

                    rsCacheData.next();
                    if (i == rowNumber) {
                        rowValue = rsCacheData.getString(rowName);
                        break;
                    }
                }
            }
        }
        return rowValue;
    }

    public int cacheData(String selectQuery) throws Exception {
        int fetchSize = 0;

        Connection pConn = this.getConnection();
        try {
            Statement stmt = null;
            stmt = pConn.createStatement();

            rsCacheData = stmt.executeQuery(selectQuery);
            if (rsCacheData != null) {
                fetchSize = rsCacheData.getFetchSize();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return fetchSize;

    }

}
