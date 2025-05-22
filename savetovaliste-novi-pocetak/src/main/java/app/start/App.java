package app.start;

import java.sql.Connection;
import java.sql.SQLException;

import static javafx.application.Application.launch;

import app.gui.LoginView;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application{
    @Override
    public void start(Stage stage) {
        new LoginView().start(stage);
    }


    public static void main(String[] args) {
        launch();

        // provera konekcije sa bazom:
        try {
            Connection conn = DatabaseConnection.connect();
            System.out.println("Uspesna konekcija!");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
