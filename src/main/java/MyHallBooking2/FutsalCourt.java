package MyHallBooking2;

public class FutsalCourt extends Hall {
    final static int FUTSAL_COURT_NUMBER = 1;
    final static int HOURLY_RATE = 15;

    public FutsalCourt() {
        super("Futsal Hall","Futsal");
    }


    @Override
    public double getDeposit() {
        return 0.35 * Hall.MAX_DEPOSIT;
    }

    @Override
    public String toString() {
        String info = "\nHall Name :" + getHallName() + "\nHall Deposit :" +
                getDeposit() +"\nHall type : " + getHallType() + "\nTotal Futsal Court Number :" +
                FUTSAL_COURT_NUMBER;

        return info;
    }
}
