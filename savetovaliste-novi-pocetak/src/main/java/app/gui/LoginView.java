package app.gui;

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

        // dugmad
        Button loginButton = new Button("Prijavi se");
        Button goToSignupButton = new Button("Registruj se");

        HBox buttonBox = new HBox(15, loginButton, goToSignupButton);
        buttonBox.setAlignment(Pos.CENTER);

        // akcije
        loginButton.setOnAction(e -> {
            try {
                new TherapistDashboardView().start(new Stage());
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        goToSignupButton.setOnAction(e -> {
            try {
                new SignUpView().start(new Stage());
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Label therapistLink = new Label("Lista psihoterapeuta");
        therapistLink.setStyle("-fx-text-fill: #1976d2; -fx-underline: true; -fx-cursor: hand;");
        therapistLink.setOnMouseClicked(e -> {
            try {
                new ListTherapistsView().start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // glavni layout
        VBox layout = new VBox(16,
                logoBox,
                emailLabel, emailField,
                passwordLabel, passwordField,
                buttonBox, therapistLink
        );
        layout.setStyle("-fx-padding: 40;");
        layout.setAlignment(Pos.CENTER);

        VBox.setMargin(buttonBox, new Insets(40, 0, 0, 0));



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


