package app.gui;

import app.dao.ObjavljivanjeDAO;
import app.model.Objavljivanje;
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

public class PublicationView extends Application {

    @Override
    public void start(Stage stage) {
        Seansa seansa = SessionListView.selectedSeansa;
        if (seansa == null) return;

        Label header = new Label("📢 Objavljivanje podataka");
        header.setStyle("-fx-font-size: 20px; -fx-text-fill: #1976d2; -fx-font-weight: bold;");

        VBox objavaBox = new VBox(10);
        objavaBox.setAlignment(Pos.CENTER);

        // prikaz objave ukoliko je ima
        if (seansa.getObjavaId() != null) {
            ObjavljivanjeDAO dao = new ObjavljivanjeDAO();
            Objavljivanje objava = dao.getById(seansa.getObjavaId());

            if (objava != null) {
                objavaBox.getChildren().addAll(
                        new Label("📅 Datum objave: " + objava.getDatum()),
                        new Label("📨 Primalac: " + objava.getPrimalac()),
                        new Label("📝 Razlog: " + objava.getRazlogObjave())
                );
            } else {
                objavaBox.getChildren().add(new Label("⚠️ Došlo je do greške prilikom učitavanja podataka."));
            }
        } else {
            objavaBox.getChildren().add(new Label("📭 Nema objave za ovu seansu."));
        }

        // dugmad
        Button publishButton = new Button("✅ Objavi");
        Button backButton = new Button("⬅️ Nazad");
        publishButton.setPrefWidth(150);
        backButton.setPrefWidth(150);

        HBox buttonBox = new HBox(20, publishButton, backButton);
        buttonBox.setAlignment(Pos.CENTER);
        VBox.setMargin(buttonBox, new Insets(10, 0, 0, 0));

        // dugme za  objavljivanje
        publishButton.setOnAction(e -> {
            if (seansa.getObjavaId() != null) {
                showAlert(Alert.AlertType.INFORMATION, "Ova seansa je već objavljena.");
                return;
            }
            try {
                new PublicationFormView().start(new Stage());
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        backButton.setOnAction(e -> {
            try {
                new SessionListView().start(new Stage());
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        VBox card = new VBox(20, header, objavaBox, buttonBox);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(30));
        card.getStyleClass().add("publication-card");

        VBox layout = new VBox(card);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(40));

        Scene scene = new Scene(layout, 700, 500);
        ThemeManager.applyTheme(scene);
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

}