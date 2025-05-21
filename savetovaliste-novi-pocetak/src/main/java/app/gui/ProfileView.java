package app.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProfileView extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        //dummy podaci
        Label nameLabel = new Label("Ime: Luka");
        Label surnameLabel = new Label("Prezime: Stoiljkovic");
        Label emailLabel = new Label("Email: luka@mail.com");

        Button backButton = new Button("Nazad");

        backButton.setOnAction(e -> {
            TherapistDashboardView dashboard = new TherapistDashboardView();
            try {
                dashboard.start(new Stage());
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        VBox layout = new VBox(10, nameLabel, surnameLabel, emailLabel, backButton);
        layout.setStyle("-fx-padding: 30");

        Scene scene = new Scene(layout, 350, 200);
        stage.setScene(scene);
        stage.setTitle("Moj profil");
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
