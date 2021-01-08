package MyHallBooking2;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

// untuk test function je
public class testfunction {
    public static void main(String[] args) {

        System.out.println((int)(1750/100) -(int)(1300/100));

        LocalTime lt1 = LocalTime.parse("10:15:30");
        LocalTime lt2 = LocalTime.parse("12:21:30");
        System.out.println("The first LocalTime is: " + lt1);
        System.out.println("The second LocalTime is: " + lt2);
        System.out.println("\nThe difference between two LocalTimes in hours is: " + lt1.until(lt2, ChronoUnit.HOURS));
        System.out.println("\nThe difference between two LocalTimes in min is: " + (lt1.until(lt2, ChronoUnit.MINUTES)%60));// get minute diff
    }
}
