package app;

import java.sql.Connection;
import java.sql.SQLException;

import static javafx.application.Application.launch;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class App extends Application{
    @Override
    public void start(Stage stage) {
        Label label = new Label("JavaFX radi!");
        Scene scene = new Scene(label, 300, 200);
        stage.setScene(scene);
        stage.setTitle("Test");
        stage.show();
    }


    public static void main(String[] args) {
        launch();
        // DEO ZA KONEKCIJU SA BAZOM - ZA SAD IGNORISI, SAMO NAPRAVI I TESTIRAJ GUI
        /*try {
            Connection conn = DatabaseConnection.connect();
            System.out.println("Uspesna konekcija!");
        } catch (SQLException e) {
            e.printStackTrace();
        }*/


    }
}
