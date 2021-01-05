package MyHallBooking2;

public class BadmintonHall extends Hall{
    final static int BADMINTON_COURT_NUMBER = 1;
    final static int HOURLY_RATE = 15;

    public BadmintonHall() {
        super("Badminton Hall","Badminton");
    }



    //TODO add 40% rate per day
    @Override
    public double getDeposit() {
        return 0.4 * Hall.MAX_DEPOSIT;
    }

    @Override
    public String toString() {
        String info = "\nHall Name :" + getHallName() + "\nHall Deposit :" +
                getDeposit() +"\nhall type : " + getHallType() + "\nTotal Badminton Court Number" +
                BADMINTON_COURT_NUMBER;

        return info;
    }
}
