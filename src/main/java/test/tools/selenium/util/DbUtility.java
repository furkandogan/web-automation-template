package test.tools.selenium.util;

import test.tools.selenium.config.ConfigurationManager;
import test.tools.selenium.config.PropertyNames;

import java.sql.*;

public class DbUtility {

    private Connection connection = null;
    private Statement statement = null;

    private static ResultSet rsCacheData;

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

    public static ResultSet getRsCacheData() {
        return rsCacheData;
    }

    /**
     *
     * @return
     * @throws Exception
     */
    public Connection initConnection() throws Exception {

        Class.forName(PropertyNames.DB_DRIVER);
        String user = ConfigurationManager.getConfigProperty(PropertyNames.DB_USER);
        String password = ConfigurationManager.getConfigProperty(PropertyNames.DB_PS);
        String jdbcUrl = ConfigurationManager.getConfigProperty(PropertyNames.DB_JDBC_URL);

        connection = DriverManager.getConnection(jdbcUrl, user, password);

        return connection;
    }

    /**
     *
     * @param dbDriver
     * @param user
     * @param password
     * @param jdbcUrl
     * @return
     * @throws Exception
     */
    public Connection initConnection(String dbDriver, String user, String password, String jdbcUrl) throws Exception {

        Class.forName(dbDriver);
        connection = DriverManager.getConnection(jdbcUrl, user, password);

        return connection;
    }

    /**
     *
     * @param query
     * @return
     * @throws SQLException
     */
    public ResultSet getAllResult(String query) throws SQLException {
        statement = connection.createStatement();
        ResultSet res = statement.executeQuery(query);
        return res;

    }

    /**
     * Gönderilen queryinin ilk rowunu geriye dönderir
     * @param query
     * @return
     * @throws SQLException
     */
    public ResultSet getFirstRowResult(String query) throws SQLException {
        statement = connection.createStatement();
        statement.setMaxRows(1);
        ResultSet res = statement.executeQuery(query);
        return res;
    }

    /**
     * Gönderilen queryinin ilk rowunun string typeli colonunu gönderir
     * @param query
     * @param columnName
     * @return
     * @throws SQLException
     */
    public String getFirstRowResultCellValue(String query, String columnName)
            throws SQLException {
        statement = connection.createStatement();
        statement.setMaxRows(1);
        ResultSet res = statement.executeQuery(query);
        res.next();

        return res.getString(columnName);
    }

    /**
     * Gönderilen queryinin ilk rowunun int type li colonunun degerini gönderir
     * @param query
     * @param columnName
     * @return
     * @throws SQLException
     */
    public int getFirstRowResultCellValue(String query, int columnName)
            throws SQLException {
        statement = connection.createStatement();
        statement.setMaxRows(1);
        ResultSet res = statement.executeQuery(query);
        res.next();

        return res.getInt(columnName);
    }

    /**
     *
     * @throws SQLException
     */
    public void disConnect() throws SQLException {

        if (connection != null) {
            connection.close();
        }
    }

    /**
     *
     * @param rowNumber
     * @param rowName
     * @param rowValue
     * @return
     * @throws Exception
     */
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

    /**
     *
     * @param selectQuery
     * @return
     * @throws Exception
     */
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

    public void updateRowValue(String query,int row,String value) {
        Connection pConn = this.getConnection();
        try {
            PreparedStatement stmt=pConn.prepareStatement(query);
            for(int i=1; i<=row; i++){
                stmt.setString(i, value);
            }
            stmt.executeUpdate();
        }
        catch (SQLException ex) {
            // TODO: handle exception
        }

    }

    public void updateRowValue(String query,int row,int value) {
        Connection pConn = this.getConnection();
        try {
            PreparedStatement stmt=pConn.prepareStatement(query);
            for(int i=1; i<=row; i++){
                stmt.setInt(i, value);
            }
            stmt.executeUpdate();
        }
        catch (SQLException ex) {
            // TODO: handle exception
        }

    }

    public void deleteRowValue(String query) {
        Connection pConn = this.getConnection();
        try {

            PreparedStatement st = pConn.prepareStatement(query);
            st.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
