package app.gui;

import javafx.application.Application;
import javafx.geometry.Insets;
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
        nameLabel.setStyle("-fx-font-size: 16px;");
        surnameLabel.setStyle("-fx-font-size: 16px;");
        emailLabel.setStyle("-fx-font-size: 16px;");

        Button backButton = new Button("Nazad");
        backButton.setPrefWidth(150);
        VBox.setMargin(backButton, new Insets(20, 0, 0, 0));

        backButton.setOnAction(e -> {
            TherapistDashboardView dashboard = new TherapistDashboardView();
            try {
                dashboard.start(new Stage());
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        VBox layout = new VBox(15, nameLabel, surnameLabel, emailLabel, backButton);
        layout.setStyle("-fx-padding: 40; -fx-alignment: center;");

        Scene scene = new Scene(layout, 450, 300);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Moj profil");
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
