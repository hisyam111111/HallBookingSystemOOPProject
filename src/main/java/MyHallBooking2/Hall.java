package MyHallBooking2;

public abstract class Hall {
    private String hallName;
    final static double MAX_DEPOSIT = 100;
    private String hallType;


    protected Hall() {
    }

    protected Hall(String HallName) {
        this();
        hallName = HallName;
    }

    protected  Hall(String HallName, String HallType) {
        hallName = HallName;

        hallType = HallType;
    }

    // getter

    public String getHallName() {
        return hallName;
    }


    public String getHallType() {
        return hallType;
    }

    // setter


    public void setHallName(String hallName) {
        this.hallName = hallName;
    }


    public void setHallType(String hallType) {
        this.hallType = hallType;
    }

    public abstract double getDeposit();
    public abstract String toString();
}
