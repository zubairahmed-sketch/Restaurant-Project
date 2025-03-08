
import java.sql.*;

public class ResConn {
    public Connection c;
    public Statement s;
    public ResultSet rs;

    public ResConn() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        c = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant", "root", "root");
        s = c.createStatement();

    }

    public void close() {
        try {
            if (rs != null)
                rs.close();
            if (s != null)
                s.close();
            if (c != null)
                c.close();
        } catch (SQLException e) {
            System.out.println("Error while closing resources: " + e.getMessage());
        }
    }

}
