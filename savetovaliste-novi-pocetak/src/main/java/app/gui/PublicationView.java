package app.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class PublicationView extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        Label titleLabel = new Label("Objavljivanje podataka sa seansi");

        ListView<String> publishedData = new ListView<>();
        publishedData.getItems().addAll(
                "📌 Luka Stoiljkovic – 19.05.2025 – Objavljeno",
                "📌 Anja Aprcovic – 10.05.2025 – Nije objavljeno"
        );

        Button publishButton = new Button("Objavi");
        Button backButton = new Button("Nazad");

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

        VBox layout = new VBox(10, titleLabel, publishedData, publishButton, backButton);
        layout.setStyle("-fx-padding: 20");

        Scene scene = new Scene(layout, 450, 300);
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
