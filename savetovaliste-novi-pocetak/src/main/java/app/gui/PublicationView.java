package app.gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PublicationView extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // header
        Label header = new Label("📢 Objavljivanje podataka");
        header.setStyle("-fx-font-size: 20px; -fx-text-fill: #1976d2; -fx-font-weight: bold;");

        // lista
        ListView<String> publishedData = new ListView<>();
        publishedData.setPrefHeight(200);
        publishedData.setMaxWidth(500);
        publishedData.getItems().addAll(
                "Luka Stoiljkovic – 19.05.2025 – Objavljeno",
                "Anja Aprcovic – 10.05.2025 – Nije objavljeno"
        );
        publishedData.getStyleClass().add("publication-list");

        // dugmad
        Button publishButton = new Button("✅ Objavi");
        Button backButton = new Button("⬅️ Nazad");
        publishButton.setPrefWidth(150);
        backButton.setPrefWidth(150);

        HBox buttonBox = new HBox(20, publishButton, backButton);
        buttonBox.setAlignment(Pos.CENTER);
        VBox.setMargin(buttonBox, new Insets(10, 0, 0, 0));

        publishButton.setOnAction(e -> {
            showAlert(Alert.AlertType.INFORMATION, "Podaci su uspešno objavljeni!");
        });

        backButton.setOnAction(e -> {
            try {
                new TherapistDashboardView().start(new Stage());
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // kartica
        VBox card = new VBox(20, header, publishedData, buttonBox);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(30));
        card.getStyleClass().add("publication-card");

        VBox layout = new VBox(card);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(40));

        Scene scene = new Scene(layout, 700, 500);
        app.util.ThemeManager.applyTheme(scene);
        stage.setScene(scene);
        stage.setTitle("Objavljivanje podataka");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/novi-pocetak-logo.png")));
        stage.show();
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
}
