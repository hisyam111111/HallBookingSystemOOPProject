package MyHallBooking2;

import java.util.GregorianCalendar;

public class Booking {
    private Customer customer;
    private Hall hall;
    private int startHour;
    private int endHour;
    final int BOOKING_START_HOUR = 800;// OPEN SHOP
    final int BOOKING_END_HOUR = 2300;// CLOSE SHOP
    private GregorianCalendar dateBooking;// date of booking
    // default badminton hall
    public Booking() {
        customer = new Customer();
        hall = new BadmintonHall();
        dateBooking = new GregorianCalendar();
    }

    public Booking(String HallType) {
        if (HallType.equalsIgnoreCase("futsal"))
            hall = new FutsalCourt();
        else
            hall = new BadmintonHall();

        customer = new Customer();
        dateBooking = new GregorianCalendar();
    }

    public Booking(Hall newHall) {
        hall = newHall;
        customer = new Customer();
        dateBooking = new GregorianCalendar();
    }

    public Booking(Hall newHall, int year, int month, int day) {
        hall = newHall;
        customer = new Customer();
        // january is 0
        dateBooking = new GregorianCalendar(year,month-1,day);
    }

    //TODO build more constructor if needed


    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public void setDateBooking(GregorianCalendar dateBooking) {
        this.dateBooking = dateBooking;
    }

    public int getStartHour() {
        return startHour;
    }

    public int getEndHour() {
        return endHour;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Hall getHall() {
        return hall;
    }

    public GregorianCalendar getDateBooking() {
        return dateBooking;
    }

    public boolean isHourSettingCorrect() {
        boolean checkHour = (startHour < endHour) && (startHour >= BOOKING_START_HOUR) && (endHour < BOOKING_END_HOUR - 45);
        // no booking 45 minutes before close time
        return checkHour;
    }

    public double getTotalHour() {

        double startHourFraction = (startHour%100)/60.0;
        double endHourFraction = (endHour%100)/60.0;
        double hour =((int)(endHour/100) -(int)(startHour/100));
        return hour + startHourFraction + endHourFraction;
    }
     public double getTotalPayment() {
       if(hall instanceof BadmintonHall) {
           return getTotalHour()*BadmintonHall.HOURLY_RATE;
       } else {
           return getTotalHour()*FutsalCourt.HOURLY_RATE;
       }
     }

    public double getHallDeposit() {
        return hall.getDeposit();
    }

    public String getHalltoString() {
        return hall.toString();
    }



}
