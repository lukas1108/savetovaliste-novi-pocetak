package app.gui;

import app.util.ThemeManager;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NotesView extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // header
        Label header = new Label("📝 Beleške i testovi");
        header.setStyle("-fx-font-size: 20px; -fx-text-fill: #1976d2; -fx-font-weight: bold;");

        // info
        Label sessionLabel = new Label("Seansa: 21.05.2025 – Klijent: ");
        sessionLabel.setStyle("-fx-font-size: 14px;");

        // polja
        TextArea notesArea = new TextArea();
        notesArea.setPromptText("Unesi beleške sa seanse...");
        notesArea.setPrefRowCount(8);
        notesArea.setPrefWidth(500);
        notesArea.getStyleClass().add("notes-area");

        TextArea resultsArea = new TextArea();
        resultsArea.setPromptText("Unesi rezultate testa...");
        resultsArea.setPrefRowCount(4);
        resultsArea.setPrefWidth(500);
        resultsArea.getStyleClass().add("notes-area");

        // dugmad
        Button saveButton = new Button("💾 Sačuvaj");
        Button backButton = new Button("⬅️ Nazad");
        saveButton.setPrefWidth(150);
        backButton.setPrefWidth(150);

        HBox buttonBox = new HBox(20, saveButton, backButton);
        buttonBox.setAlignment(Pos.CENTER);

        saveButton.setOnAction(e -> showAlert(Alert.AlertType.INFORMATION, "Beleške i testovi sačuvani!"));

        backButton.setOnAction(e -> {
            try {
                new SessionListView().start(new Stage());
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // glavna kartica
        VBox card = new VBox(15,
                header,
                sessionLabel,
                new Label("Beleške:"),
                notesArea,
                new Label("Test rezultati:"),
                resultsArea,
                buttonBox
        );
        card.setPadding(new Insets(30));
        card.setAlignment(Pos.CENTER);
        card.getStyleClass().add("notes-card");

        VBox layout = new VBox(card);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(40));

        Scene scene = new Scene(layout, 750, 620);
        ThemeManager.applyTheme(scene);

        stage.setScene(scene);
        stage.setTitle("Beleške i testovi");
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
