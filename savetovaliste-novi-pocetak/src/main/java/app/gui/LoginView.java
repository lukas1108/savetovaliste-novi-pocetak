package app.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginView extends Application {
    @Override
    public void start(Stage stage) {
        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        emailField.setMaxWidth(250);

        Label passwordLabel = new Label("Lozinka:");
        PasswordField passwordField = new PasswordField();
        passwordField.setMaxWidth(250);

        Button loginButton = new Button("Prijavi se");
        loginButton.setOnAction(e -> {
            TherapistDashboardView dashboard = new TherapistDashboardView();
            try {
                dashboard.start(new Stage()); // Otvori dashboard
                stage.close();                // Zatvori login prozor
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Button goToSignupButton = new Button("Registruj se");
        // Akcija za registraciju
        goToSignupButton.setOnAction(e -> {
            SignUpView singUpView = new SignUpView();
            try {
                singUpView.start(new Stage());
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Horizontalan raspored dugmadi
        HBox buttonBox = new HBox(10, loginButton, goToSignupButton);

        VBox layout = new VBox(15, emailLabel, emailField, passwordLabel, passwordField, buttonBox);
        layout.setStyle("-fx-padding: 30");

        Scene scene = new Scene(layout, 400, 250);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}