import java.sql.*;

public class PostgreConnection {

    private final String url; 
    private final String user; 
    private final String pass; 
    
    public PostgreConnection(String url, String user, String pass) throws SQLException {
        this.url = url;
        this.user = user;
        this.pass = pass;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }

}

