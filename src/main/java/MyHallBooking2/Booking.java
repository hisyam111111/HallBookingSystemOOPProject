package MyHallBooking2;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;


public class Booking {
    private Customer customer;
    private Hall hall;
    private LocalTime startHour;
    private LocalTime endHour;
    final LocalTime BOOKING_START_HOUR = LocalTime.of(8,0) ;// OPEN SHOP
    final LocalTime BOOKING_END_HOUR = LocalTime.of(23,30); // CLOSE SHOP
    private LocalDate dateBooking;// date of booking
    // default badminton hall
    public Booking() {
        customer = new Customer();
        hall = new BadmintonHall();
        dateBooking = LocalDate.now();

    }

    public Booking(String HallType) {
        if (HallType.equalsIgnoreCase("Futsal Hall"))
            hall = new FutsalCourt();
        else
            hall = new BadmintonHall();

        customer = new Customer();
        dateBooking = LocalDate.now();
    }

    public Booking(Hall newHall) {
        hall = newHall;
        customer = new Customer();
        dateBooking = LocalDate.now();
    }



    //TODO build more constructor if needed


    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }
    //LocalTime.parse("10:15:30")
    public void setStartHour(LocalTime startHour) {
        this.startHour = startHour;
    }
    public void setStartHour(String str) {
        LocalTime StartHour = LocalTime.parse(str);
        this.startHour = StartHour;
    }

    public void setEndHour(LocalTime endHour) {
        this.endHour = endHour;
    }
    public void setEndHour(String str) {
        LocalTime EndHour = LocalTime.parse(str);
        this.endHour = EndHour;
    }

    public void setDateBooking(LocalDate dateBooking) {
        this.dateBooking = dateBooking;
    }
    public void setHallName(String name) {
        hall.setHallName(name);
    }

    public void setHallType(String type) {
        hall.setHallType(type);
    }

    public void setCustomerName(String name) {
        customer.setName(name);
    }

    public void setCustomerEmail(String email) {
        customer.setEmail(email);
    }
    public void setCustomerPhoneNumber(String phoneNum) {
        customer.setPhoneNumber(phoneNum);
    }

    public LocalTime getStartHour() {
        return startHour;
    }

    public LocalTime getEndHour() {
        return endHour;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Hall getHall() {
        return hall;
    }

    public String getHallName() {
        return hall.getHallName();
    }

    public String getHallType() {
        return hall.getHallType();
    }
    public String getCustomerName() {
        return customer.getName();
    }

    public String getCustomerEmail() {
        return customer.getEmail();
    }

    public String getCustomerPhoneNumber() {
        return customer.getPhoneNumber();
    }

    public LocalDate getDateBooking() {
        return dateBooking;
    }

    public boolean isHourSettingCorrect() {
        boolean checkHour = (startHour.compareTo(endHour) == -1 ) && (startHour.compareTo(BOOKING_START_HOUR) == 1 ) && (endHour.compareTo(BOOKING_END_HOUR)== -1 );
        return checkHour;
    }

    public double getTotalHour() {
        return (startHour.until(endHour, ChronoUnit.HOURS) + (((startHour.until(endHour,ChronoUnit.MINUTES))%60.0)/60.0));
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
