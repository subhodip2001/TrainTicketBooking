package TrainTicketBooking;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;




public class Ticket {

    private String pnr;
    private Date TravelDate;
    private static HashMap<Passenger,Double> passengers = new HashMap<>();
    private Train train;


    public Ticket(Date TravelDate,Train train) {
        this.TravelDate=TravelDate;
        this.train=train;

    }



    public String generatePNR() {

        String Source = train.getSource();
        String Destination = train.getDestination();

        int year= TravelDate.getYear()+1900;
        int month = TravelDate.getMonth()+1;
        int date = TravelDate.getDate();


        char s = Source.charAt(0);
        char d = Destination.charAt(0);
        StringBuilder sb = new StringBuilder();

        //get the last counter
        final int Counter = getLastCounter();
        pnr = sb.append(s).append(d).append("_").append(year).append("-").append(month).append("-").append(date).append("_").append(Counter).toString();

        return pnr;
    }




    public double calcPassengerFare(Passenger p) {
        if(p.getPassenger_gender()=="FEMALE")
            return train.getTicket_price()*0.25;
        else if(p.getPassenger_age()<=12) {
            return train.getTicket_price()*.50;
        }
        else if(p.getPassenger_age()>=60) {
            return train.getTicket_price()*.60;
        }

        return train.getTicket_price();
    }


    public void addPassenger(String passenger_name,int passenger_age,String passenger_gender) {
        Passenger passenger = new Passenger(passenger_name,passenger_age,passenger_gender);
        double fair = calcPassengerFare(passenger);
        passengers.put(passenger, fair);
    }

    public double calculateTotalTicketPrice() {
        double sum = 0;
        for(Map.Entry<Passenger, Double> entry : passengers.entrySet()){
            Double value = entry.getValue();
            sum += value;
        }
        return sum;
    }
    public StringBuilder generateTicket() {

        StringBuilder sb = new StringBuilder();

        String pnr = generatePNR();
        int trainNo = train.getTrain_number();
        String trainName=train.getTrain_name();
        String from = train.getSource();
        String to = train.getDestination();

        String date= String.valueOf(TravelDate.getDate());
        String month = String.valueOf(TravelDate.getMonth()+1);
        String year=String.valueOf(TravelDate.getYear()+1900);

        //for printing the date in the given format
        String newDate = date+"/"+month+"/"+year;

        sb.append("PNR\t\t\t\t:\t"+pnr+"\nTrainNo\t\t\t:\t"+trainNo+"\nTrain Name\t\t:\t"+trainName+"\nFrom\t\t\t:\t"+from+"\nTo\t\t\t\t:\t"+to+"\nTravel Date\t\t:\t"+newDate);
        sb.append("\n\nPassengers:\n");
        sb.append("---------------------------------------------------------\n");
        sb.append("Name\t\tAge\t\tGender\t\tFair\n");
        sb.append("---------------------------------------------------------\n");
        for(Map.Entry<Passenger,Double> entry : passengers.entrySet()) {
            Passenger p = entry.getKey();
            sb.append(p.getPassenger_name()+"\t\t"+p.getPassenger_age()+"\t\t"+p.getPassenger_gender()+"\t\t"+entry.getValue()+"\n");
        }

        sb.append("\nTotal Price: "+calculateTotalTicketPrice());

        System.out.println(sb.toString());


        return sb;

    }
    public void writeTicket() {
        //create a file with PNR number

        StringBuilder s = generateTicket();
        try {
            FileWriter writer = new FileWriter(generatePNR()+".txt");

            writer.write(s.toString());
            writer.close();
        } catch (IOException e) {

            e.printStackTrace();
        }


    }

    public void insertIntoTicketTable() {

        DBConnection db = new DBConnection();
        Connection connection = db.getConnection();
        try {

            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            String strDate = formatter.format(TravelDate);

            String query = "insert into tickets(PNR,TRAVEL_DATE,TRAIN_NO,TRAIN_NAME,SOURCE,DESTINATION,PRICE) values (?,?,?,?,?,?,?)";
            PreparedStatement pstmt = connection.prepareStatement(query);

            pstmt.setString(1, generatePNR());
            pstmt.setString(2, strDate);
            pstmt.setInt(3, train.getTrain_number());
            pstmt.setString(4, train.getTrain_name());
            pstmt.setString(5, train.getSource());
            pstmt.setString(6, train.getDestination());
            pstmt.setDouble(7, calculateTotalTicketPrice());

            pstmt.executeUpdate();

            pstmt.close();
            connection.close();
        }catch(Exception e) {
            e.printStackTrace();
        }

    }

    public int getLastCounter() {
        int Counter=0;

        try {
            DBConnection db = new DBConnection();
            Connection connection = db.getConnection();
            String query="SELECT * FROM tickets WHERE Counter=(SELECT max(Counter) FROM tickets)";
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                Counter=rs.getInt(1);
            }
            return Counter;
        }catch(Exception e) {
            e.printStackTrace();
        }
        return Counter;
    }

}