package lk.ijse.Dbtest.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbconnection {

    private static Dbconnection dbconnection;

    private Connection connection;

    private  Dbconnection(){

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc","root","1234");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static Dbconnection getInstance() throws SQLException {
        if(dbconnection == null) {
            dbconnection = new Dbconnection();
            return dbconnection;
        } else {
            return dbconnection;
        }
    }

    public Connection getConnection() {
        return connection;
    }
}