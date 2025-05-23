package app.gui;

import app.dao.ObjavljivanjeDAO;
import app.dao.SeansaDAO;
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

import java.sql.Date;
import java.time.LocalDate;

public class PublicationFormView extends Application {

    @Override
    public void start(Stage stage) {

        Seansa seansa = SessionListView.selectedSeansa;
        if (seansa == null) return;

        // text field za objavljivanje
        Label header = new Label("📝 Novi unos objave");
        header.setStyle("-fx-font-size: 20px; -fx-text-fill: #1976d2; -fx-font-weight: bold;");

        DatePicker datePicker = new DatePicker(LocalDate.now());
        TextField primalacField = new TextField();
        primalacField.setPromptText("Unesite primaoca...");

        TextArea razlogArea = new TextArea();
        razlogArea.setPromptText("Unesite razlog objave...");
        razlogArea.setPrefRowCount(4);

        // buttoni
        Button submitButton = new Button("✅ Sačuvaj objavu");
        Button cancelButton = new Button("⬅️ Nazad");
        submitButton.setPrefWidth(180);
        cancelButton.setPrefWidth(180);

        HBox buttonBox = new HBox(15, submitButton, cancelButton);
        buttonBox.setAlignment(Pos.CENTER);

        // cuvanje
        submitButton.setOnAction(e -> {
            String primalac = primalacField.getText().trim();
            String razlog = razlogArea.getText().trim();
            LocalDate datum = datePicker.getValue();

            if (primalac.isEmpty() || razlog.isEmpty() || datum == null) {
                showAlert(Alert.AlertType.WARNING, "Sva polja moraju biti popunjena.");
                return;
            }

            // pravljenje nove objave
            Objavljivanje objava = new Objavljivanje();
            objava.setDatum(Date.valueOf(datum));
            objava.setPrimalac(primalac);
            objava.setRazlogObjave(razlog);

            ObjavljivanjeDAO dao = new ObjavljivanjeDAO();
            int newObjavaId = dao.insertAndReturnId(objava);

            // povezivanje sa seansom
            if (newObjavaId != -1) {
                new SeansaDAO().updateObjavaId(seansa.getSeansaId(), newObjavaId);
                seansa.setObjavaId(newObjavaId);
                showAlert(Alert.AlertType.INFORMATION, "Objava uspešno sačuvana!");
                try {
                    new PublicationView().start(new Stage());
                    stage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                showAlert(Alert.AlertType.ERROR, "Greška pri čuvanju objave.");
            }
        });

        cancelButton.setOnAction(e -> {
            try {
                new PublicationView().start(new Stage());
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        VBox layout = new VBox(15,
                header,
                new Label("Datum objave:"), datePicker,
                new Label("Primalac:"), primalacField,
                new Label("Razlog objave:"), razlogArea,
                buttonBox
        );
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));

        Scene scene = new Scene(layout, 600, 500);
        ThemeManager.applyTheme(scene);
        stage.setScene(scene);
        stage.setTitle("Unos objave");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/novi-pocetak-logo.png")));
        stage.show();
    }

    private void showAlert(Alert.AlertType type, String msg) {
        Alert alert = new Alert(type);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}