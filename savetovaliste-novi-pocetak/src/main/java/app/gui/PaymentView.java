package app.gui;

import app.dao.PlacanjeDAO;
import app.dao.SeansaDAO;
import app.dao.ValutaDAO;
import app.model.Osoba;
import app.model.Placanje;
import app.model.Seansa;
import app.model.Valuta;
import app.util.ThemeManager;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class PaymentView extends Application {
    @Override
    public void start(Stage stage) {

        // title
        Label titleLabel = new Label("💳 Uplate i dugovanja");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: #1976d2; -fx-font-weight: bold;");

        // lista placanja
        ListView<String> payments = new ListView<>();
        payments.setPrefHeight(300);
        payments.setMaxWidth(550);
        payments.getStyleClass().add("payment-list");

        PlacanjeDAO dao = new PlacanjeDAO();
        List<Placanje> sveUplate = dao.getAll();

        LocalDate danas = LocalDate.now(); // danasnji datum
        Osoba trenutni = app.util.Session.getCurrentUser(); // ulogovani user

        SeansaDAO seansaDAO = new SeansaDAO();

        // prolazi kroz sve uplate
        for (Placanje p : sveUplate) {
            Seansa seansa = seansaDAO.getById(p.getSeansaId());
            if (seansa == null || seansa.getOsobaId() != trenutni.getId()) continue; // filtriranje

            // za kompleksniji rad sa stringom:
            StringBuilder info = new StringBuilder();

            info.append("Seansa ID: ").append(p.getSeansaId()).append(" – ");

            if (p.isJePrvaRata() && p.getBrojRata() == 2) {
                Date rokDatum = p.getRokZaDruguRatu();
                if (rokDatum != null) {
                    LocalDate rok = ((java.sql.Date) rokDatum).toLocalDate();
                    if (danas.isAfter(rok)) {
                        info.append("Prekoracenje roka za drugu ratu: ").append(rok);
                    } else {
                        info.append("Rok za drugu ratu: ").append(rok);
                    }
                }
                 else {
                    info.append("Nedostaje rok za drugu ratu!");
                }
            } else {
                info.append("Plaćanje kompletirano");
            }

            // formatiranje valute
            ValutaDAO valutaDAO = new ValutaDAO();
            Valuta valuta = valutaDAO.getById(p.getValutaId());
            String oznaka = valuta != null ? valuta.getSkraceniNaziv() : "RSD";
            info.append(" – Iznos: ").append(p.getIznos()).append(" ").append(oznaka);

            payments.getItems().add(info.toString());
        }

        // back button
        Button backButton = new Button("⬅️ Nazad");
        backButton.setPrefWidth(180);
        VBox.setMargin(backButton, new Insets(20, 0, 0, 0));

        backButton.setOnAction(e -> {
            try {
                new TherapistDashboardView().start(new Stage());
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // layout i stage
        VBox card = new VBox(20, titleLabel, payments, backButton);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(30));
        card.getStyleClass().add("payment-card");

        VBox layout = new VBox(card);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(40));

        Scene scene = new Scene(layout, 750, 600);
        ThemeManager.applyTheme(scene);
        stage.setScene(scene);
        stage.setTitle("Uplate i dugovanja");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/novi-pocetak-logo.png")));
        stage.show();
    }
}