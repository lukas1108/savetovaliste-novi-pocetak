package app.gui;

import app.dao.PsiholoskiTestDAO;
import app.model.PsiholoskiTest;
import app.model.Seansa;
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

import java.util.List;

public class NotesView extends Application {

    @Override
    public void start(Stage stage) {
        // trenutna seansa
        Seansa seansa = SessionListView.selectedSeansa;
        if (seansa == null) return;

        Label header = new Label("📝 Beleške i testovi");
        header.setStyle("-fx-font-size: 20px; -fx-text-fill: #1976d2; -fx-font-weight: bold;");

        Label sessionLabel = new Label("Seansa: " + seansa.getDatum() + " – ID: " + seansa.getSeansaId());
        sessionLabel.setStyle("-fx-font-size: 14px;");

        // beleske
        TextArea notesArea = new TextArea(seansa.getBeleske() != null ? seansa.getBeleske() : "");
        notesArea.setPromptText("Unesi beleške sa seanse...");
        notesArea.setPrefRowCount(8);
        notesArea.setPrefWidth(500);
        notesArea.getStyleClass().add("notes-area");

        // lista testova
        ListView<String> testList = new ListView<>();
        testList.setPrefHeight(140);
        testList.setPrefWidth(500);

        PsiholoskiTestDAO testDAO = new PsiholoskiTestDAO();
        List<PsiholoskiTest> tests = testDAO.getAll();

        for (PsiholoskiTest test : tests) {
            if (test.getSeansaId() == seansa.getSeansaId()) {
                testList.getItems().add("• " + test.getNaziv() + " (" + test.getOblast() + "): " + test.getRezultat());
            }
        }

        // dugmici:
        Button saveButton = new Button("💾 Sačuvaj");
        Button backButton = new Button("⬅️ Nazad");
        saveButton.setPrefWidth(150);
        backButton.setPrefWidth(150);

        HBox buttonBox = new HBox(20, saveButton, backButton);
        buttonBox.setAlignment(Pos.CENTER);

        saveButton.setOnAction(e -> {
            // akcija - updatovanje notes
            String noveBeleske = notesArea.getText().trim();
            if (noveBeleske.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Beleške ne mogu biti prazne.");
                return;
            }

            new app.dao.SeansaDAO().updateBeleske(seansa.getSeansaId(), noveBeleske);
            showAlert(Alert.AlertType.INFORMATION, "Beleške su uspešno sačuvane.");
        });

        // back button
        backButton.setOnAction(e -> {
            try {
                new SessionListView().start(new Stage());
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // layout
        VBox card = new VBox(15,
                header,
                sessionLabel,
                new Label("Beleške:"),
                notesArea,
                new Label("Testovi u toku seanse:"),
                testList,
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

}