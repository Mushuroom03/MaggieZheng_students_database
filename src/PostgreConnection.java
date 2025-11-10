import java.sql.*;

// Class to use as object for App
public class PostgreConnection {

    private final String url; 
    private final String user; 
    private final String pass; 

    // Constructor
    public PostgreConnection(String url, String user, String pass) throws SQLException {
        this.url = url;
        this.user = user;
        this.pass = pass;
    }

    public Connection getConnection() throws SQLException {
        // Connect to postgre database
        return DriverManager.getConnection(url, user, pass);
    }

}



