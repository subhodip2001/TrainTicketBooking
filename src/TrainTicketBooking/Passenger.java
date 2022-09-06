package TrainTicketBooking;

public class Passenger {
    private String passenger_name;
    private int passenger_age;
    private String passenger_gender;


    public Passenger(String passenger_name, int passenger_age, String passenger_gender) {

        this.passenger_name = passenger_name;
        this.passenger_age = passenger_age;
        this.passenger_gender = passenger_gender;
    }

    public String getPassenger_name() {
        return passenger_name;
    }
    public void setPassenger_name(String passenger_name) {
        this.passenger_name= passenger_name;
    }
    public int getPassenger_age() {
        return passenger_age;
    }
    public void setPassenger_age(int passenger_age) {
        this.passenger_age= passenger_age;
    }
    public String getPassenger_gender() {
        return passenger_gender;
    }
    public void setPassenger_ender(String passenger_gender) {
        this.passenger_gender= passenger_gender;
    }


}
