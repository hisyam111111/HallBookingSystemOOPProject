package MyHallBooking2;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class ConfirmBox
{
    static boolean answer;
    public static boolean display(String title,String message)
    {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);//force user to close it down first
        window.setTitle(title);
        window.setMinWidth(250);
        Label label = new Label(message);

        Button yesBtn = new Button("Yes");
        Button noBtn = new Button("No");

        yesBtn.setOnAction(e -> {
            answer = true;
            window.close();
        });


        noBtn.setOnAction(e -> {
            answer = false;
            window.close();
        });


        VBox layout = new VBox(20);
        layout.getChildren().addAll(label,yesBtn,noBtn);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout,250,200);
        window.setTitle("Close the Program?");
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }

    public static void displayPayment(String title,String message1,String variable1,String message2,String variable2) {


            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);//force user to close it down first
            window.setTitle(title);
            window.setMinWidth(250);
            Label label = new Label( message1 +": "+ variable1);
            Label label2 = new Label( message2 +" : "+ variable2);
            Button btnOK = new Button("OK");
            btnOK.setOnAction(e -> window.close());


            VBox layout = new VBox(30);
            layout.getChildren().addAll(label, label2,btnOK);
            layout.setAlignment(Pos.CENTER);
            Scene scene = new Scene(layout, 250, 200);
            window.setScene(scene);
            window.showAndWait();


    }

    public static void displayPayment(String title,String message1,double variable1,String message2,double variable2) {


        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);//force user to close it down first
        window.setTitle(title);
        window.setMinWidth(250);
        Label label = new Label( message1 +": "+ variable1);
        Label label2 = new Label( message2 +" : "+ variable2);
        Button btnOK = new Button("OK");
        btnOK.setOnAction(e -> window.close());


        VBox layout = new VBox(30);
        layout.getChildren().addAll(label, label2,btnOK);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout, 250, 200);
        window.setScene(scene);
        window.showAndWait();


    }

    public static void displayTableBooking(ObservableList<Booking> list) {
        Stage window = new Stage();
        window.setTitle("TableView");

        // Hall name Column
        TableColumn<Booking, Hall> hallNameColumn = new TableColumn<>("Hall Name");// Header title
        hallNameColumn.setMinWidth(200);
        hallNameColumn.setCellValueFactory(new PropertyValueFactory<>("hallName"));

        // date Column
        TableColumn<Booking, LocalDate> dateColumn = new TableColumn<>("Date");// Header title
        dateColumn.setMinWidth(200);
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateBooking"));


        // Start Time Column
        TableColumn<Booking, LocalTime> startColumn = new TableColumn<>("Start Time");// Header title
        startColumn.setMinWidth(200);
        startColumn.setCellValueFactory(new PropertyValueFactory<>("startHour"));


        // End Time Column
        TableColumn<Booking, LocalTime> endColumn = new TableColumn<>("End Time");// Header title
        endColumn.setMinWidth(200);
        endColumn.setCellValueFactory(new PropertyValueFactory<>("endHour"));


        // set table
        TableView<Booking> table = new TableView<>();
        table.setItems(list);
        table.getColumns().addAll(hallNameColumn,dateColumn,startColumn,endColumn);



        VBox vbox = new VBox(20);
        vbox.getChildren().addAll(table);

        Scene scene = new Scene(vbox);
        window.setScene(scene);
        window.show();

    }
}