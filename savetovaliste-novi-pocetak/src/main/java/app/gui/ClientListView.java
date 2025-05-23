package app.gui;

import app.dao.KlijentDAO;
import app.dao.PrijavaDAO;
import app.model.Klijent;
import app.model.Prijava;
import app.util.Session;
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

import java.util.List;
import java.util.stream.Collectors;

public class ClientListView extends Application {
    @Override
    public void start(Stage stage) {

        // header
        Label header = new Label("📋 Lista klijenata");
        header.setStyle("-fx-font-size: 20px; -fx-text-fill: #1976d2; -fx-font-weight: bold;");

        // lista klijenata
        ListView<String> clientList = new ListView<>();
        clientList.setPrefHeight(200);
        clientList.setMaxWidth(350);
        clientList.getStyleClass().add("client-list");

        // preuzmi trenutnog terapeuta
        int terapeutId = Session.getCurrentUser().getId();

        // ucitavanja svih prijava
        PrijavaDAO prijavaDAO = new PrijavaDAO();
        KlijentDAO klijentDAO = new KlijentDAO();

        List<Prijava> prijaveTerapeuta = prijavaDAO.getAll().stream()
                .filter(p -> p.getOsobaId() == terapeutId)
                .collect(Collectors.toList());

        for (Prijava prijava : prijaveTerapeuta) {
            Klijent klijent = klijentDAO.getAll().stream()
                    .filter(k -> k.getPrijavaId() == prijava.getPrijavaId())
                    .findFirst()
                    .orElse(null);
            if (klijent != null) {
                clientList.getItems().add(klijent.getIme() + " " + klijent.getPrezime() + " – " + klijent.getOpisProblema() + " (" + klijent.getTelefon() + " | " + klijent.getEmail() + ")");
            }
        }

        // dugme
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

        // layout
        VBox layout = new VBox(20, header, clientList, backButton);
        layout.setStyle("-fx-padding: 40;");
        layout.setAlignment(Pos.CENTER);

        // scena i stage
        Scene scene = new Scene(layout, 500, 400);
        app.util.ThemeManager.applyTheme(scene);
        stage.setScene(scene);
        stage.setTitle("Lista klijenata");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/novi-pocetak-logo.png")));
        stage.show();
    }
}