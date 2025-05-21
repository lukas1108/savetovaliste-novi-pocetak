package app.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SignUpView extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        Label nameLabel = new Label("Ime:");
        TextField nameField = new TextField();
        Label surnameLabel = new Label("Prezime:");
        TextField surnameField = new TextField();

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();

        Label passwordLabel= new Label("Lozinka:");
        PasswordField passwordField = new PasswordField();

        Label confirmPasswordLabel = new Label("Potvrdi lozinku:");
        PasswordField confirmPasswordField = new PasswordField();

        Button signupButton = new Button("Registruj se");
        Button backButton = new Button("Nazad");

        VBox layout = new VBox(10,
                nameLabel, nameField,
                surnameLabel, surnameField,
                emailLabel, emailField,
                passwordLabel, passwordField,
                confirmPasswordLabel, confirmPasswordField,
                new HBox(10, signupButton, backButton)
        );
        layout.setStyle("-fx-padding: 20");

        signupButton.setOnAction(e -> {
            String name = nameField.getText();
            String surname = surnameField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();
            String confirmPassword = confirmPasswordField.getText();

            if (name.isEmpty() || surname.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Sva polja moraju biti popunjena!");
                return;
            }

            if (!password.equals(confirmPassword)) {
                showAlert(Alert.AlertType.ERROR, "Lozinke se ne poklapaju!");
                return;
            }

            showAlert(Alert.AlertType.INFORMATION, "Registracija uspešna!");
            // ovdje dodajemo slanje podataka u bazu
        });

        backButton.setOnAction(e -> {
            LoginView loginView = new LoginView();
            try {
                loginView.start(new Stage());
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Scene scene = new Scene(layout, 350, 400);
        stage.setScene(scene);
        stage.setTitle("Registracija");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
