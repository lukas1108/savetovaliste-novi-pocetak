package app.start;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection connect() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/savetovalistenp";
        String user = "root";
        String password = "";
        return DriverManager.getConnection(url, user, password);
    }
}
