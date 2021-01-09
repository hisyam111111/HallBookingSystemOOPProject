package MyHallBooking2;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class HallBookingJavaFX extends Application {
    Stage window;
    Scene scene1, scene2;
    Customer tempCustomer;
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
        //button create Customer Object
        Button btnScene1 = new Button("Next");
        btnScene1.setMaxSize(100, 200);


        // create GridPane
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(30));
        grid.setVgap(15);
        grid.setHgap(10);
        GridPane.setHalignment(btnScene1, HPos.CENTER);
        grid.setAlignment(Pos.CENTER);

        //add event listener
        btnScene1.setOnAction(e -> createCustomerObject(grid,nameInput,emailInput,phoneInput));

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

        // Hall name with ComboBox
        Label hallNameLabel = new Label("Select Hall Name :");
        ComboBox<String> hallNameInput = new ComboBox<>();
        hallNameInput.getItems().addAll(
                "Kompleks Sukan Pak Meon",
                "IIUM Sports Centre","Kompleks Sukan Komuniti"
        );
        hallNameInput.setPromptText("Select Location");// hall name refers to hall location

        // Date Picker
        Label dateBookingLabel = new Label("Select Date :");
        DatePicker dateInput = new DatePicker();

        // Start Hour
        Label startHourLabel = new Label("Start Time:");
        TextField startHourInput = new TextField();
        startHourInput.setPromptText("Enter Start Time (eg: 09:00)");

        // End Hour
        Label endHourLabel = new Label("End Time:");
        TextField endHourInput = new TextField();
        endHourInput.setPromptText("Enter End Time: (eg: 20:00)");

        // Button
        Button resetButton = new Button("Reset");
        Button addButton = new Button("Add Booking");
        Button confirmButton = new Button("Confirm");
        Button displayTableButton = new Button("Display Booking List");

        //  GridPane
        GridPane grid2 = new GridPane();
        grid2.setPadding(new Insets(30));
        grid2.setVgap(15);
        grid2.setHgap(10);

        // 0th Column
        GridPane.setConstraints(hallTypeLabel,0,1);
        GridPane.setConstraints(hallNameLabel,0,2);
        GridPane.setConstraints(dateBookingLabel,0,3);
        GridPane.setConstraints(startHourLabel,0,4);
        GridPane.setConstraints(endHourLabel,0,5);

        // 1st Column
        GridPane.setConstraints(hallHbox,1,1);
        GridPane.setConstraints(hallNameInput,1,2);
        GridPane.setConstraints(dateInput,1,3);
        GridPane.setConstraints(startHourInput,1,4);
        GridPane.setConstraints(endHourInput,1,5);

        // Button Position
        HBox buttonHBox = new HBox(20);
        buttonHBox.getChildren().addAll(resetButton,addButton,confirmButton,displayTableButton);
        GridPane.setConstraints(buttonHBox,0,7);
        // Button event
        addButton.setOnAction(e -> createBookingObject(radioGroup,hallNameInput,dateInput,startHourInput,endHourInput));
        resetButton.setOnAction(e -> resetAllData());
        confirmButton.setOnAction(e -> confirmPayment());
        displayTableButton.setOnAction(e -> ConfirmBox.displayTableBooking(getListAllBooking()));

        // insert all node into GridPane
        grid2.getChildren().addAll(hallTypeLabel,hallNameLabel,dateBookingLabel,startHourLabel,endHourLabel
        ,hallHbox,hallNameInput,dateInput,startHourInput,endHourInput,buttonHBox);

        // init Scene 2
        scene2 = new Scene(grid2,900,450);

        // Gridpane Alignment
        grid2.setAlignment(Pos.CENTER);
        GridPane.setHalignment(hallHbox, HPos.CENTER);

        // Scene 2 END

        // close Windows
        window.setOnCloseRequest(e -> {
            e.consume();// tell jvm we will handle close req manually
            closeProgram();
        });

        //set Scene 1 as default scene
        window.setScene(scene1);
        window.show();


    }

    private void createCustomerObject( GridPane gridPane,TextField nameBox,TextField emailBox,TextField phoneBox) {
        String name = nameBox.getText();
        String email = emailBox.getText();
        String phone = phoneBox.getText();

        if(nameBox.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Invalid input", "Please insert your name correctly");
            return;
        }
        if(emailBox.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Invalid input", "Please insert your E-mail correctly");
            return;
        }

        if(phoneBox.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Invalid input", "Please insert your Phone Number correctly");
            return;
        }


        tempCustomer = new Customer(name,email,phone);
        window.setScene(scene2);
        //System.out.println(tempCustomer);// for testing purpose only.
    }

    private void createBookingObject(ToggleGroup radioGroup,ComboBox<String> combo,DatePicker datePicker,TextField startTime,TextField endTime) {
        RadioButton selectedRadioButton = (RadioButton) radioGroup.getSelectedToggle();
        String radioHallString = selectedRadioButton.getText();
        //System.out.println(radioHallString);//testing
        String hallName = combo.getValue();
        LocalDate date = datePicker.getValue();
        //System.out.println(date);//testing
        LocalTime timeStart = LocalTime.parse(startTime.getText());
        //System.out.println(timeStart);//testing
        LocalTime timeEnd = LocalTime.parse(endTime.getText());
        if(timeEnd.isBefore(timeStart)) {
            timeEnd = timeStart.plusHours(1);
            // if end hour earlier than start set end hour = start + 1hr
            //TODO add popup window tellin user this.
            ConfirmBox.display("Time Conflict!", "new time end :" , timeEnd);
        }
        //System.out.println(timeEnd);//testing

        // create Booking Object
        tempBooking = new Booking(radioHallString);
        tempBooking.setHallName(hallName);
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
            System.out.println("Hall Name: "+ i.getHall().getHallName());
            System.out.println("Hall Type: "+i.getHall().getHallType());
            System.out.println("Start Hour: "+i.getStartHour());
            System.out.println("End Hour: "+i.getEndHour());
            System.out.println("Date : "+i.getDateBooking());
        }
    }

    public double getPaymentAmount() {
        double amount = 0;
        for(Booking i: bookingList) {
            amount += i.getTotalPayment();
        }
        System.out.println("Amount :" + amount);//testing
        return amount;
    }

    public double getDepositAmount() {
        double amount = 0;
        for(Booking i: bookingList) {
            amount += i.getHallDeposit();
        }
        System.out.println("Amount deposit:" + amount);//testing
        return amount;
    }

    private void confirmPayment() {
        String payment = Double.toString(getPaymentAmount());
        String deposit = Double.toString(getDepositAmount());
        ConfirmBox.display("Payment Info","Total payment",payment,"Total deposit",deposit);
        window.close();

    }

    private void  closeProgram(){
        boolean answer = ConfirmBox.display("title","Sure to Close?");
        if(answer) {
            window.close();
        }
    }

    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    // table View
    public ObservableList<Booking> getListAllBooking() {

        ObservableList<Booking> list = FXCollections.observableArrayList();

        for (Booking i : bookingList) {
            list.add(i);
        }
        return list;

    }



}
