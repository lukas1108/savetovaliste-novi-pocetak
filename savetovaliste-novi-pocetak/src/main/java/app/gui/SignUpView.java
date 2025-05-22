package app.gui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.util.Duration;

public class SignUpView extends Application {

    @Override
    public void start(Stage stage) {
        // logo
        Image logoImage = new Image(getClass().getResourceAsStream("/novi-pocetak-logo.png"));
        ImageView logoView = new ImageView(logoImage);
        logoView.setFitWidth(150);
        logoView.setPreserveRatio(true);

        VBox logoBox = new VBox(logoView);
        logoBox.setAlignment(Pos.CENTER);

        // polja za unos
        Label nameLabel = new Label("Ime:");
        TextField nameField = new TextField();

        Label surnameLabel = new Label("Prezime:");
        TextField surnameField = new TextField();

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();

        Label passwordLabel = new Label("Lozinka:");
        PasswordField passwordField = new PasswordField();

        Label confirmPasswordLabel = new Label("Potvrdi lozinku:");
        PasswordField confirmPasswordField = new PasswordField();

        // dugmad
        Button signupButton = new Button("Registruj se");
        Button backButton = new Button("Nazad");

        HBox buttonBox = new HBox(15, signupButton, backButton);
        buttonBox.setAlignment(Pos.CENTER);

        // akcije dugmadi
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
            // TODO: slanje podataka u bazu
        });

        backButton.setOnAction(e -> {
            try {
                new LoginView().start(new Stage());
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // glavni layout
        VBox layout = new VBox(12,
                logoBox,
                nameLabel, nameField,
                surnameLabel, surnameField,
                emailLabel, emailField,
                passwordLabel, passwordField,
                confirmPasswordLabel, confirmPasswordField,
                buttonBox
        );
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-padding: 40;");

        Scene scene = new Scene(layout, 400, 590);
        app.util.ThemeManager.applyTheme(scene);
        stage.setScene(scene);
        stage.setTitle("Registracija");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/novi-pocetak-logo.png")));
        stage.show();

        addHoverAnimation(signupButton);
        addHoverAnimation(backButton);

        FadeTransition fade = new FadeTransition(Duration.millis(600), layout);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void addHoverAnimation(Button button) {
        button.setOnMouseEntered(e -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(150), button);
            st.setToX(1.05);
            st.setToY(1.05);
            st.play();
        });
        button.setOnMouseExited(e -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(150), button);
            st.setToX(1.0);
            st.setToY(1.0);
            st.play();
        });
    }

}
