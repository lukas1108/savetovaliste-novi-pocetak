package app.gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ProfileView extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // header ikona
        Label profileHeader = new Label("👤 Moj profil");
        profileHeader.setStyle("-fx-font-size: 20px; -fx-text-fill: #1976d2; -fx-font-weight: bold;");

        // podaci
        Label nameLabel = new Label("Ime: Luka");
        Label surnameLabel = new Label("Prezime: Stoiljkovic");
        Label emailLabel = new Label("Email: luka@mail.com");

        nameLabel.setStyle("-fx-font-size: 16px;");
        surnameLabel.setStyle("-fx-font-size: 16px;");
        emailLabel.setStyle("-fx-font-size: 16px;");

        // dugme
        Button backButton = new Button("⬅️ Nazad");
        backButton.setPrefWidth(180);
        backButton.setPrefHeight(40);
        VBox.setMargin(backButton, new Insets(20, 0, 0, 0));

        backButton.setOnAction(e -> {
            try {
                new TherapistDashboardView().start(new Stage());
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // kartica korisnika
        VBox card = new VBox(15, profileHeader, nameLabel, surnameLabel, emailLabel, backButton);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(30));
        card.getStyleClass().add("profile-card");

        VBox root = new VBox(card);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(60));

        Scene scene = new Scene(root, 500, 350);
        app.util.ThemeManager.applyTheme(scene);
        stage.setScene(scene);
        stage.setTitle("Moj profil");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/novi-pocetak-logo.png")));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
