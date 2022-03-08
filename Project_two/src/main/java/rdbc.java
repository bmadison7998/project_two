import java.sql.*;
import java.util.ResourceBundle;

class rdbc {
    // This class handles all query's sent to the database and returns data in the requested type

    public final ResourceBundle rd = ResourceBundle.getBundle("com/company/dbconfig");
    private final String url = rd.getString("url");
    private final String username = rd.getString("username");
    private final String password = rd.getString("password");
    public Connection connection = DriverManager.getConnection(url, username, password);
    // Stored procedure
    public Statement statement = connection.createStatement();
    ResultSet results;
    // Creating a stored procedure
    // lists all the userid in the user table

    rdbc() throws SQLException {
    }


    // Returns the Results from a query.
    public ResultSet runQuery(String _query) {
        try {
            results = statement.executeQuery(_query);
            results.next();
        } catch (Exception e) {
            System.out.println("ERROR: " + _query);
            results = null;
        }
        return results;
    }

    // Appends query like strings for the runQuery
    public ResultSet query(String _table, String _row, String _where, int _value) {
        return runQuery("SELECT " + _row + " FROM " + _table + " WHERE " + _where + " = " + _value + ";");
    }

    public ResultSet query(String _table, String _row) {
        return runQuery("SELECT " + _row + " FROM " + _table + " WHERE USERID IS NOT NULL;");
    }

    // These grab the first element from the query() and returns that type.
    public double queryDouble(String _table, String _row, String _value, int _id) throws SQLException {
        return query(_table, _row, _value, _id).getDouble(1);
    }

    public boolean queryBoolean(String _table, String _row, String _value, int _id) throws SQLException {
        return queryInt(_table, _row, _value, _id) == 1;
    }

    public int queryInt(String _table, String _row, String _value, int _id) throws SQLException {
        return query(_table, _row, _value, _id).getInt(1);
    }

    public int queryInt(String _table, String _row, int _accountid, int _userid) throws SQLException {
        return runQuery("SELECT " + _row + " FROM " + _table + " WHERE ACCOUNTID = " + _accountid + " AND USERID = " + _userid + ";").getInt(1);
    }


    public String queryString(String _table, String _row, String _value, int _id) throws SQLException {
        return query(_table, _row, _value, _id).getString(1);
    }

    public int queryCount(String _table) throws SQLException {
        ResultSet _results = query(_table, "COUNT(*)");
        return _results.getInt(1);
    }


    // Inserts value(s) into the specified class
    public boolean insertInto(String _table, String _values) {
        boolean flag;
        String sql = "INSERT INTO " + _table + " VALUES (" + _values + ");";
        try {
            statement.execute(sql);
            flag = true;
            // the statement compiled successfully
        } catch (Exception e) {
            System.out.println("InsertIntoFail: " + sql);
            flag = false;
        }
        return flag;
    }

    public boolean createUser(int userID, String password, String firstname, String middlename, String lastname, String email, int defaultacct) {
        String _query = userID + ",\"" + password + "\",\"" + firstname + "\",\"" + middlename + "\",\"" + lastname + "\",\"" + email + "\"," + defaultacct;
        return insertInto("USER", _query);
    }

    public void createAccount(int _accountid, int _userid, double _balance, String _accounttype) {
        String _query = _accountid + ",\"" + _userid + "\",\"" + 1 + "\",\"" + _balance + "\",\"" + _accounttype + "\"";
        insertInto("ACCOUNT", _query);
    }


    // Gives a new value to a single row with inside a table with the matching id.
    // Using overloaded methods for different update syntax
    public void update(String _table, String _column, int _value, String _where, int _id) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "UPDATE " + _table + " SET " + _column + " = " + _value + " WHERE " + _where + " like " + _id + ";";

        try {
            statement.execute(sql);
        } catch (Exception e) {
            System.out.println("ERROR: " + sql);
        }
    }

    public void update(String _table, String _column, int _value, String _where, int _match, int _userid) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "UPDATE " + _table + " SET " + _column + " = " + _value + " WHERE " + _where + " like " + _match + " AND USERID = " + _userid + ";";

        try {
            statement.execute(sql);
        } catch (Exception e) {
            System.out.println("ERROR: " + sql);
        }
    }

    public void update(String _table, String _column, double _value, String _where, int _id) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "UPDATE " + _table + " SET " + _column + " = " + _value + " WHERE " + _where + " like " + _id + ";";

        try {
            statement.execute(sql);
        } catch (Exception e) {
            System.out.println("ERROR: " + sql);
        }
    }

    public void update(String _table, String _column, String _value, String _where, int _id) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "UPDATE " + _table + " SET " + _column + " = " + _value + " WHERE " + _where + " like " + _id + ";";

        try {
            statement.execute(sql);
        } catch (Exception e) {
            System.out.println("ERROR: " + sql);
        }
    }
}