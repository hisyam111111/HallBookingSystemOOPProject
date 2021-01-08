package MyHallBooking2;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class HallBookingJavaFX extends Application {
    Stage window;
    Scene scene1, scene2;
    Customer tempCustomer;
    Hall tempHall;
    Booking tempBooking;
    ArrayList<Booking> bookingList = new ArrayList<>(5);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        String customerInfo = "Customer Info";
        window = primaryStage;
        window.setTitle(customerInfo);

        // Scene 1 nodes
        // User Name
        Label nameLabel = new Label("Name:");
        TextField nameInput = new TextField();
        nameInput.setPromptText("Enter Your Name");

        // User Email
        Label emailLabel = new Label("Email:");
        TextField emailInput = new TextField();
        emailInput.setPromptText("Enter Your Email");

        // User Phone Number
        Label phoneLabel = new Label("Phone Number:");
        TextField phoneInput = new TextField();
        phoneInput.setPromptText("Enter Your Contact Info");


        // TODO will use btn eventlistener later
        Button btnScene1 = new Button("Next");
        btnScene1.setMaxSize(100, 200);
        btnScene1.setOnAction(e -> createCustomerObject(nameInput,emailInput,phoneInput));

        // create GridPane
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(30));
        grid.setVgap(15);
        grid.setHgap(10);
        GridPane.setHalignment(btnScene1, HPos.CENTER);
        grid.setAlignment(Pos.CENTER);

        // 0th Column
        GridPane.setConstraints(nameLabel,0,1);
        GridPane.setConstraints(emailLabel,0,2);
        GridPane.setConstraints(phoneLabel,0,3);
        GridPane.setConstraints(btnScene1,0,7,2,1);

        // 1st Column
        GridPane.setConstraints(nameInput,1,1);
        GridPane.setConstraints(emailInput,1,2);
        GridPane.setConstraints(phoneInput,1,3);

        grid.getChildren().addAll(nameLabel,emailLabel,phoneLabel,btnScene1,nameInput,emailInput,phoneInput);

        // create Scene 1
        scene1 = new Scene(grid,500,350);
        // Scene 1 END


        // Scene 2 Hall info and booking infos
        // Label Radio
        Label hallTypeLabel = new Label("Hall Type");
        // Use Radio to select hall type
        RadioButton radioBadmintonHall = new RadioButton("Badminton Hall");
        RadioButton radioFutsalHall = new RadioButton("Futsal hall");
        ToggleGroup radioGroup = new ToggleGroup();
        radioBadmintonHall.setToggleGroup(radioGroup);
        radioBadmintonHall.setSelected(true);
        radioFutsalHall.setToggleGroup(radioGroup);
        HBox hallHbox = new HBox(20);
        HBox.setMargin(radioBadmintonHall,new Insets(10));
        HBox.setMargin(radioFutsalHall,new Insets(10));
        hallHbox.getChildren().addAll(radioBadmintonHall,radioFutsalHall);


        // Date Picker
        Label dateBookingLabel = new Label("Select Date :");
        DatePicker dateInput = new DatePicker();

        // Start Hour
        Label startHourLabel = new Label("Start Time:");
        TextField startHourInput = new TextField();
        startHourInput.setPromptText("Enter Start Time (eg: 20:00)");

        // End Hour
        Label endHourLabel = new Label("End Time:");
        TextField endHourInput = new TextField();
        endHourInput.setPromptText("Enter End Time: (eg: 20:00)");

        // Button
        Button resetButton = new Button("Reset");
        Button addButton = new Button("Add Booking");
        Button confirmButton = new Button("Confirm");

        //  GridPane
        GridPane grid2 = new GridPane();
        grid2.setPadding(new Insets(30));
        grid2.setVgap(15);
        grid2.setHgap(10);

        // 0th Column
        GridPane.setConstraints(hallTypeLabel,0,1);
        GridPane.setConstraints(dateBookingLabel,0,2);
        GridPane.setConstraints(startHourLabel,0,3);
        GridPane.setConstraints(endHourLabel,0,4);

        // 1st Column
        GridPane.setConstraints(hallHbox,1,1);
        GridPane.setConstraints(dateInput,1,2);
        GridPane.setConstraints(startHourInput,1,3);
        GridPane.setConstraints(endHourInput,1,4);

        // Button Position
        HBox buttonHBox = new HBox(20);
        buttonHBox.getChildren().addAll(resetButton,addButton,confirmButton);
        GridPane.setConstraints(buttonHBox,0,7);
        // Button event
        //TODO add more parameter
        addButton.setOnAction(e -> createBookingObject(radioGroup,dateInput,startHourInput,endHourInput));
        resetButton.setOnAction(e -> resetAllData());
        confirmButton.setOnAction(e -> resetAllData());//TODO change later
        // insert all node into GridPane
        grid2.getChildren().addAll(hallTypeLabel,dateBookingLabel,startHourLabel,endHourLabel
        ,hallHbox,dateInput,startHourInput,endHourInput,buttonHBox);

        // init Scene 2
        scene2 = new Scene(grid2,700,450);

        // Gridpane Alignment
        grid2.setAlignment(Pos.CENTER);
        GridPane.setHalignment(hallHbox, HPos.CENTER);

        //set Scene 1 as default scene
        window.setScene(scene1);
        window.show();


    }

    private void createCustomerObject( TextField nameBox,TextField emailBox,TextField phoneBox) {
        String name = nameBox.getText();
        String email = emailBox.getText();
        String phone = phoneBox.getText();
        tempCustomer = new Customer(name,email,phone);
        window.setScene(scene2);
        //System.out.println(tempCustomer);// for testing purpose only.
    }

    private void createBookingObject(ToggleGroup radioGroup,DatePicker datePicker,TextField startTime,TextField endTime) {
        RadioButton selectedRadioButton = (RadioButton) radioGroup.getSelectedToggle();
        String radioHallString = selectedRadioButton.getText();
        //System.out.println(radioHallString);//testing
        LocalDate date = datePicker.getValue();
        //System.out.println(date);//testing
        LocalTime timeStart = LocalTime.parse(startTime.getText());
        //System.out.println(timeStart);//testing
        LocalTime timeEnd = LocalTime.parse(endTime.getText());
        //System.out.println(timeEnd);//testing

        // create Booking Object
        tempBooking = new Booking(radioHallString);
        tempBooking.setDateBooking(date);
        tempBooking.setStartHour(timeStart);
        tempBooking.setEndHour(timeEnd);
        tempBooking.setCustomer(tempCustomer);
        bookingList.add(tempBooking);
        //System.out.println(bookingList.size());
        tempBooking = null;
        printArrayList();//testing
    }

    public void resetAllData() {
        bookingList.clear();
        tempCustomer = null;
        tempBooking = null;
        window.setScene(scene1);
    }
    //testing arraylist content.
    public void printArrayList() {
        int count = 0;
        for(Booking i: bookingList) {
            System.out.println("Position:" + count++);
            System.out.println("Size ArrayList : "+bookingList.size());
            System.out.println("Name: "+ i.getCustomer().getName());
            System.out.println("Email: "+i.getCustomer().getEmail());
            System.out.println("Phone Number: "+i.getCustomer().getPhoneNumber());
            System.out.println("Hall Type: "+i.getHall().getHallType());
            System.out.println("Start Hour: "+i.getStartHour());
            System.out.println("End Hour: "+i.getEndHour());
            System.out.println("Date : "+i.getDateBooking());
        }
    }

    // Down here method to manipulate array list or we could use another class

}
