package TrainTicketBooking;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public class AddPassenger {
    private DBConnection db = new DBConnection();

    public void insertIntoPassengers(Passenger p) {
        Connection connection = db.getConnection();

        try {

            String query = "insert into passenger values (?,?,?)";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1,p.getPassenger_name());
            pstmt.setInt(2, p.getPassenger_age());
            pstmt.setString(3, String.valueOf(p.getPassenger_gender()));

            pstmt.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();
        }finally{
            try {
                connection.close();

            } catch (SQLException e) {

                e.printStackTrace();
            }

        }

    }
}