package TrainTicketBooking;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddTrain{
    public static Train findTrain(int train) {
        Train tr = null;
        DBConnection db = new DBConnection();
        try {

            Connection connection = db.getConnection();
            //database connection
            String query = "select * from trains where TRAIN_NO = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, train);

            ResultSet rs =stmt.executeQuery();

            while(rs.next()) {
                int Train_number= rs.getInt("TRAIN_NO");
                String Train_name = rs.getString("TRAIN_NAME");
                String Source = rs.getString("SOURCE");
                String Destination = rs.getString("DESTINATION");
                int Ticket_price = rs.getInt("TICKET_PRICE");

                tr = new Train(Train_number,Train_name,Source,Destination,Ticket_price);
                return tr;

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tr;
    }

}