package app.gui;

import app.dao.*;
import app.model.Osoba;
import app.model.Supervizija;
import app.util.Session;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProfileView extends Application {

    @Override
    public void start(Stage stage) {

        Osoba o = Session.getCurrentUser(); // trenutno ulogovani

        Label profileHeader = new Label("👤 Moj profil");
        profileHeader.setStyle("-fx-font-size: 20px; -fx-text-fill: #1976d2; -fx-font-weight: bold;");

        // dohvatanje naziva na osnovu ID
        String fakultetNaziv = FakultetDAO.getNazivById(o.getFakultetId());
        String univerzitetNaziv = UniverzitetDAO.getNazivById(o.getUniverzitetId());
        String oblastNaziv = o.getOblastId() != null ? new OblastDAO().getNazivById(o.getOblastId()) : "X";

        // centar - samo kandidati imaju
        String centarNaziv = "X";
        if("kandidat".equalsIgnoreCase(o.getTipOsobe()) && o.getCentarId() != null) {
            centarNaziv = CentarZaObukuDAO.getNazivById(o.getCentarId());
        }

        // supervizor - samo kandidati imaju
        String supervizorNaziv = "X";
        if ("kandidat".equalsIgnoreCase(o.getTipOsobe()) && o.getSupervizijaId() != null) {
            SupervizijaDAO supervizijaDAO = new SupervizijaDAO();
            Supervizija s = supervizijaDAO.getById(o.getSupervizijaId());
            if (s != null) {
                Osoba psihoterapeut = new OsobaDAO().getById(s.getPsihoterapeutId());
                if (psihoterapeut != null) {
                    supervizorNaziv = psihoterapeut.getIme() + " " + psihoterapeut.getPrezime();
                }
            }
        }

        // ostali podaci
        Label nameLabel = new Label("Ime: " + o.getIme());
        Label surnameLabel = new Label("Prezime: " + o.getPrezime());
        Label emailLabel = new Label("Email: " + o.getEmail());
        Label jmbgLabel = new Label("JMBG: " + o.getJmbg());
        Label datumRodjenjaLabel = new Label("Datum rođenja: " + o.getDatumRodjenja());
        Label polLabel = new Label("Pol: " + o.getPol());
        Label telefonLabel = new Label("Telefon: " + o.getTelefon());
        Label ulicaLabel = new Label("Ulica: " + o.getUlica() + " " + o.getBroj());
        Label opstinaLabel = new Label("Opština: " + o.getOpstina());
        Label stepenLabel = new Label("Stepen studija: " + o.getStepenStudija());
        Label datumSertifikacijeLabel = new Label("Datum sertifikacije: " +
                (o.getDatumSertifikacije() != null ? o.getDatumSertifikacije().toString() : "X"));
        Label fakultetLabel = new Label("Fakultet: " + fakultetNaziv);
        Label univerzitetLabel = new Label("Univerzitet: " + univerzitetNaziv);
        Label oblastLabel = new Label("Oblast: " + oblastNaziv);
        Label tipLabel = new Label("Tip korisnika: " + o.getTipOsobe());
        Label centarLabel = new Label("Centar: " + centarNaziv);
        Label supervizijaLabel = new Label("Supervizor: " + supervizorNaziv);

        // back btton
        Button backButton = new Button("⬅️ Nazad");
        backButton.setPrefWidth(180);
        backButton.setPrefHeight(40);
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
        VBox card = new VBox(10,
                profileHeader,
                nameLabel, surnameLabel, emailLabel, jmbgLabel, datumRodjenjaLabel, polLabel,
                telefonLabel, ulicaLabel, opstinaLabel, stepenLabel, datumSertifikacijeLabel,
                fakultetLabel, univerzitetLabel, oblastLabel, tipLabel,
                centarLabel, supervizijaLabel,
                backButton
        );
        card.setAlignment(Pos.CENTER_LEFT);
        card.setPadding(new Insets(30));
        card.getStyleClass().add("profile-card");

        VBox root = new VBox(card);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(60));

        Scene scene = new Scene(root, 550, 700);
        app.util.ThemeManager.applyTheme(scene);
        stage.setScene(scene);
        stage.setTitle("Moj profil");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/novi-pocetak-logo.png")));
        stage.show();
    }

}