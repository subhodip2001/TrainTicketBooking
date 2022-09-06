package TrainTicketBooking;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class BookTicket{
    public static void main(String[] args) throws ParseException{

        Ticket ticket;
        Scanner scannerNumber = new Scanner(System.in);
        Scanner scannerText = new Scanner(System.in);

        System.out.println("Enter the Train Number");
        int trainNumber = scannerNumber.nextInt();
        Train train = AddTrain.findTrain(trainNumber);
        if(train==null) {
            System.out.println("Train with given train number does not exist");
            System.exit(0);
        }

        System.out.println("Enter Travel Date ");
        String sdate = scannerText.nextLine();
        Date TravelDate = new SimpleDateFormat("dd-MM-yyyy").parse(sdate);

        if(TravelDate.compareTo(new Date())<0) {
            System.out.println("Travel Date is before current date");
            System.exit(0);
        }


        System.out.println("Enter the number of passengers ");
        int numberOfPassengers = scannerNumber.nextInt();

        do {
            System.out.println("Enter Passenger Name ");
            String passenger_name = scannerText.nextLine();
            System.out.println("Enter Age ");
            int passenger_age = scannerNumber.nextInt();
            System.out.println("Enter Gender");
            String passenger_gender = scannerNumber.next();

            Passenger p = new Passenger(passenger_name,passenger_age,passenger_gender);
            AddPassenger pi = new AddPassenger();
            pi.insertIntoPassengers(p);

            ticket = new Ticket(TravelDate,train);
            ticket.addPassenger(passenger_name,passenger_age,passenger_gender);


            numberOfPassengers--;

        }while(numberOfPassengers!=0);

        System.out.println("Ticket Booked with PNR : "+ticket.generatePNR()+"\n");
        ticket.writeTicket();
        ticket.insertIntoTicketTable();

        scannerNumber.close();
        scannerText.close();
    }

}