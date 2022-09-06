package TrainTicketBooking;
        import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.SQLException;

public class DBConnection {

    private static String url= "jdbc:mysql://localhost:3306/trainticketpnr?useSSL=false";
    private static String user ="root";
    private static String password = "2001";
    private static Connection connection = null;

    public  Connection getConnection() {
        try {
            connection = DriverManager.getConnection(url, user, password);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}