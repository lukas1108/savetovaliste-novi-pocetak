package app.gui;

import app.dao.OsobaDAO;
import app.model.Osoba;
import app.util.Session;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.animation.FadeTransition;
import javafx.util.Duration;
import javafx.animation.ScaleTransition;

import java.util.List;

public class LoginView extends Application {
    @Override
    public void start(Stage stage) {

        // ucitaj logo
        Image logoImage = new Image(getClass().getResourceAsStream("/novi-pocetak-logo.png"));
        ImageView logoView = new ImageView(logoImage);
        logoView.setFitWidth(150);
        logoView.setPreserveRatio(true);
        VBox logoBox = new VBox(logoView);
        logoBox.setAlignment(Pos.CENTER);

        // email i lozinka
        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        emailField.setMaxWidth(250);

        Label passwordLabel = new Label("Lozinka:");
        PasswordField passwordField = new PasswordField();
        passwordField.setMaxWidth(250);

        // dugmici
        Button loginButton = new Button("Prijavi se");
        Button goToSignupButton = new Button("Registruj se");

        HBox buttonBox = new HBox(15, loginButton, goToSignupButton);
        buttonBox.setAlignment(Pos.CENTER);

        // akcije
        loginButton.setOnAction(e -> {
            // uzimanje unosa
            String email = emailField.getText().trim();
            String password = passwordField.getText();

            if (email.isEmpty() || password.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Unesite i email i lozinku.");
                return;
            }

            OsobaDAO osobaDAO = new OsobaDAO();
            List<Osoba> svi = osobaDAO.getAll();

            // prolazi kroz sve korisnike i trazi unos koji se podudara
            for (Osoba o : svi) {
                if (o.getEmail() != null && o.getLozinka() != null && email.equalsIgnoreCase(o.getEmail()) && password.equals(o.getLozinka())) {
                    Session.setCurrentUser(o); // zapamti ulogovanog korisnika
                    try {
                        new TherapistDashboardView().start(new Stage());
                        stage.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    return;
                }
            }

            showAlert(Alert.AlertType.ERROR, "Pogrešan email ili lozinka.");
        });

        // otvaranje sign up
        goToSignupButton.setOnAction(e -> {
            try {
                new SignUpView().start(new Stage());
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // lista psihoterapeuta, otvaranje prozora
        Label therapistLink = new Label("Lista psihoterapeuta");
        therapistLink.setStyle("-fx-text-fill: #1976d2; -fx-underline: true; -fx-cursor: hand;");
        therapistLink.setOnMouseClicked(e -> {
            try {
                new ListTherapistsView().start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // layout
        VBox layout = new VBox(16,
                logoBox,
                emailLabel, emailField,
                passwordLabel, passwordField,
                buttonBox, therapistLink
        );
        layout.setStyle("-fx-padding: 40;");
        layout.setAlignment(Pos.CENTER);

        VBox.setMargin(buttonBox, new Insets(40, 0, 0, 0));

        // stage i scene

        Scene scene = new Scene(layout, 400, 460);
        app.util.ThemeManager.applyTheme(scene);
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/novi-pocetak-logo.png")));
        stage.show();

        FadeTransition fade = new FadeTransition(Duration.millis(600), layout);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
        addHoverAnimation(loginButton);
        addHoverAnimation(goToSignupButton);
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

    private void showAlert(Alert.AlertType type, String poruka) {
        Alert alert = new Alert(type);
        alert.setTitle("Greška");
        alert.setHeaderText(null);
        alert.setContentText(poruka);
        alert.showAndWait();
    }

}


