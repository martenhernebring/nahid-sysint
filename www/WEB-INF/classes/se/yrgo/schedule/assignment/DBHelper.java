package se.yrgo.schedule.assignment;

import java.sql.*;

/**
 * A class with a helper method to get a ResultSet from the database. Also
 * "handles" the Connection to the DB.
 */
public class DBHelper {

    private static Connection con;
    static {
        try {
            con = DriverManager.getConnection("jdbc:sqlite:www/WEB-INF/resources/vikarie.db");
        } catch (SQLException e) {
            System.err.println("Error getting connection: " + e.getMessage());
        }
    }

    /** Creates data from the SQL database
     * @param SQL Request to the database. Warning! SQL injection possible
     * @return The result from the database SQL query.
     * */
    public ResultSet fetch(String SQL) {
        try {
            Statement stm = con.createStatement();
            return stm.executeQuery(SQL);
        } catch (SQLException e) {
            System.err.println("Error reading from DB: " + e.getMessage());
            return null;
        }
    }
}
