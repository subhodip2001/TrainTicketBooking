package TrainTicketBooking;

public class Train {

    private int Train_number;
    private String Train_name;
    private String Source;
    private String Destination;
    private int Ticket_price;

    public Train() {

    }

    public Train(int Train_number, String Train_name, String Source, String Destination, int Ticket_price) {

        this.Train_number = Train_number;
        this.Train_name = Train_name;
        this.Source = Source;
        this.Destination = Destination;
        this.Ticket_price = Ticket_price;
    }

    public int getTrain_number() {
        return Train_number;
    }

    public void setTrain_number(int Train_number) {
        this.Train_number = Train_number;
    }

    public String getTrain_name() {
        return Train_name;
    }

    public void setTrain_Name(String Train_name) {
        this.Train_name = Train_name;
    }

    public String getSource() {
        return Source;
    }
    public void setSource(String Source) {
        this.Source = Source;
    }

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String Destination) {
        this.Destination = Destination;
    }

    public double getTicket_price() {
        return Ticket_price;
    }

    public void setTicket_price(int Ticket_price) {
        this.Ticket_price = Ticket_price;
    }



}