package app.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NotesView extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        Label sessionLabel = new Label("Seansa: 21.05.2025 – Klijent: ");

        TextArea notesArea = new TextArea();
        notesArea.setPromptText("Unesi beljeske sa seanse...");
        notesArea.setPrefRowCount(8);

        TextArea resultsArea = new TextArea();
        notesArea.setPromptText("Unesi rezultate testa...");
        resultsArea.setPrefRowCount(4);

        Button saveButton = new Button("Sačuvaj");
        Button backButton = new Button("Nazad");

        saveButton.setOnAction(e -> {
            // ovdje kasnije ubacimo logiku za cuvanje u bazu
            showAlert(Alert.AlertType.INFORMATION, "Beleške i testovi sačuvani!");
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

        VBox layout = new VBox(10,
                sessionLabel,
                new Label("Beleške:"),
                notesArea,
                new Label("Test rezultati:"),
                resultsArea,
                new HBox(10, saveButton, backButton)
        );
        layout.setStyle("-fx-padding: 20");

        Scene scene = new Scene(layout, 500, 400);
        stage.setScene(scene);
        stage.setTitle("Beleške i testovi");
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
