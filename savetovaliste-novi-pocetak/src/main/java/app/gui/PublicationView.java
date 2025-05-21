package app.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class PublicationView extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        Label titleLabel = new Label("Objavljivanje podataka sa seansi");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        ListView<String> publishedData = new ListView<>();
        publishedData.setPrefHeight(200);
        publishedData.setPrefWidth(500);

        publishedData.getItems().addAll(
                "Luka Stoiljkovic – 19.05.2025 – Objavljeno",
                "Anja Aprcovic – 10.05.2025 – Nije objavljeno"
        );

        Button publishButton = new Button("Objavi");
        Button backButton = new Button("Nazad");
        publishButton.setPrefWidth(150);
        backButton.setPrefWidth(150);

        HBox buttonBox = new HBox(20, publishButton, backButton);
        buttonBox.setStyle("-fx-alignment: center;");

        publishButton.setOnAction(e -> {
            // ovdje logika za azuriranje statusa u bazi
            showAlert(Alert.AlertType.INFORMATION, "Podaci su uspešno objavljeni!");
        });

        backButton.setOnAction(e -> {
            TherapistDashboardView dashboard = new TherapistDashboardView();
            try {
                dashboard.start(new Stage());
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        VBox layout = new VBox(20, titleLabel, publishedData, buttonBox);
        layout.setStyle("-fx-padding: 30; -fx-alignment: center;");

        Scene scene = new Scene(layout, 650, 450);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Objavljivanje podataka");
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
