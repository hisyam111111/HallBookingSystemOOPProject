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

    public void testTextArea() {
        /*ArrayList<LocalTime>List = new ArrayList<LocalTime>();
        Button btn = new Button ("Make note of current time");
        TextArea text = new TextArea ("");
        // Declare a field of type String for display and name the variable display.
        String display = "";// Set "" "as the initial value (If this is not done, null will be displayed)
        @Override
        public void start (Stage primaryStage) {
            btn.setOnAction (event->{
                List.add (LocalTime.now ());
                for (LocalTime i: List) {
                    // "display is the original display with a line break and String.format" i ". "
                    display = display + "\ r \ n" + String.format ("% 02d:% 02d:% 02d",
                            i.getHour (),
                            i.getMinute (),
                            i.getSecond ());
                }
                // Call the setText method after exiting the for statement loop
                text.setText (display);
                // After that, erase the contents of the display
                display = "";
            });*/
    }
}
