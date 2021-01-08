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

import java.util.ArrayList;

public class HallBookingJavaFX extends Application {
    Stage window;
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

        // Use Radio to select hall type
        RadioButton radioBadmintonHall = new RadioButton("Badminton Hall");
        RadioButton radioFutsalHall = new RadioButton("Futsal hall");
        ToggleGroup radioGroup = new ToggleGroup();
        radioBadmintonHall.setToggleGroup(radioGroup);
        radioBadmintonHall.setSelected(true);
        radioFutsalHall.setToggleGroup(radioGroup);
        HBox hallHbox = new HBox(20);
        hallHbox.getChildren().addAll(radioBadmintonHall,radioFutsalHall);
        RadioButton selectedRadioButton = (RadioButton) radioGroup.getSelectedToggle();
        String radioHallString = selectedRadioButton.getText();
        // TODO will use btn eventlistener later
        Button btnScene1 = new Button("Next");
        btnScene1.setMaxSize(100, 200);

        // create GridPane
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(30));
        grid.setVgap(15);
        grid.setHgap(10);
        GridPane.setHalignment(btnScene1, HPos.CENTER);
        GridPane.setHalignment(hallHbox, HPos.CENTER);
        grid.setAlignment(Pos.CENTER);

        // 0th Column
        GridPane.setConstraints(nameLabel,0,1);
        GridPane.setConstraints(emailLabel,0,2);
        GridPane.setConstraints(phoneLabel,0,3);
        GridPane.setConstraints(hallHbox,0,5,2,1);
        GridPane.setConstraints(btnScene1,0,7,2,1);

        // 1st Column
        GridPane.setConstraints(nameInput,1,1);
        GridPane.setConstraints(emailInput,1,2);
        GridPane.setConstraints(phoneInput,1,3);

        grid.getChildren().addAll(nameLabel,emailLabel,phoneLabel,hallHbox,btnScene1,nameInput,emailInput,phoneInput);

        // create Scene 1
        Scene scene1 = new Scene(grid,500,350);
        // Scene 1 END

        //set Scene 1 as default scene
        window.setScene(scene1);
        window.show();










    }

    // Down here method to manipulate array list or we could use another class

}
