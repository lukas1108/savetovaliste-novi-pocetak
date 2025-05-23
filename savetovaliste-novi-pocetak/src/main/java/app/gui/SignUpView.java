package app.gui;

import app.dao.*;
import app.model.CentarZaObuku;
import app.model.FakultetOblast;
import app.model.Osoba;
import app.model.Supervizija;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.util.Duration;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class SignUpView extends Application {

    @Override
    public void start(Stage stage) {

        // logo
        Image logoImage = new Image(getClass().getResourceAsStream("/novi-pocetak-logo.png"));
        ImageView logoView = new ImageView(logoImage);
        logoView.setFitWidth(150);
        logoView.setPreserveRatio(true);
        VBox logoBox = new VBox(logoView);
        logoBox.setAlignment(Pos.CENTER);

        // combo box
        ComboBox<String> tipOsobeCombo = new ComboBox<>();
        tipOsobeCombo.getItems().addAll("kandidat", "psihoterapeut");

        // polja:

        TextField imeField = new TextField();
        TextField prezimeField = new TextField();
        TextField jmbgField = new TextField();
        DatePicker datumRodjenjaPicker = new DatePicker();

        ComboBox<String> polCombo = new ComboBox<>();
        polCombo.getItems().addAll("M", "Z");

        TextField emailField = new TextField();
        TextField telefonField = new TextField();
        TextField ulicaField = new TextField();
        TextField brojField = new TextField();
        TextField opstinaField = new TextField();

        ComboBox<String> stepenStudijaCombo = new ComboBox<>();
        stepenStudijaCombo.getItems().addAll("O", "M", "D");

        Label datumSertifikacijeLabel = new Label("Datum sertifikacije:");
        DatePicker datumSertifikacijePicker = new DatePicker();

        TextField centarNazivField = new TextField();
        TextField fakultetNazivField = new TextField();
        TextField univerzitetNazivField = new TextField();
        TextField oblastNazivField = new TextField();

        ComboBox<Osoba> psihoterapeutComboBox = new ComboBox<>();
        psihoterapeutComboBox.setPromptText("Odaberi supervizora");

        Label centarLabel = new Label("* Centar:");
        Label supervizijaLabel = new Label("* Supervizija ID:");

        PasswordField lozinkaField = new PasswordField();
        PasswordField confirmLozinkaField = new PasswordField();

        // da budu uzi text boxovi
        setMaxFieldWidths(200,
                tipOsobeCombo, imeField, prezimeField, jmbgField, datumRodjenjaPicker,
                polCombo, emailField, telefonField, ulicaField, brojField, opstinaField,
                stepenStudijaCombo, datumSertifikacijePicker, fakultetNazivField,
                univerzitetNazivField, oblastNazivField, lozinkaField,
                confirmLozinkaField, centarNazivField, psihoterapeutComboBox
        );
        
        // layout za sva polja
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(15);
        grid.setPadding(new Insets(20));

        int row = 0;

        grid.add(new Label("* Registrujem se kao:"), 0, row);
        grid.add(tipOsobeCombo, 1, row++);

        grid.add(new Label("* Ime:"), 0, row);
        grid.add(imeField, 1, row++);

        grid.add(new Label("* Prezime:"), 0, row);
        grid.add(prezimeField, 1, row++);

        grid.add(new Label("* JMBG:"), 0, row);
        grid.add(jmbgField, 1, row++);

        grid.add(new Label("* Datum rođenja:"), 0, row);
        grid.add(datumRodjenjaPicker, 1, row++);

        grid.add(new Label("* Pol (M/Z):"), 0, row);
        grid.add(polCombo, 1, row++);

        grid.add(new Label("* Email:"), 0, row);
        grid.add(emailField, 1, row++);

        grid.add(new Label("* Telefon:"), 0, row);
        grid.add(telefonField, 1, row++);

        grid.add(new Label("* Ulica:"), 0, row);
        grid.add(ulicaField, 1, row++);

        grid.add(new Label("* Broj:"), 0, row);
        grid.add(brojField, 1, row++);

        grid.add(new Label("* Opština:"), 0, row);
        grid.add(opstinaField, 1, row++);

        grid.add(new Label("* Stepen studija:"), 0, row);
        grid.add(stepenStudijaCombo, 1, row++);

        grid.add(new Label("* Fakultet:"), 0, row);
        grid.add(fakultetNazivField, 1, row++);

        grid.add(new Label("* Univerzitet:"), 0, row);
        grid.add(univerzitetNazivField, 1, row++);

        grid.add(new Label("* Oblast:"), 0, row);
        grid.add(oblastNazivField, 1, row++);

        grid.add(new Label("* Lozinka:"), 0, row);
        grid.add(lozinkaField, 1, row++);

        grid.add(new Label("* Potvrdi lozinku:"), 0, row);
        grid.add(confirmLozinkaField, 1, row++);

        grid.add(centarLabel, 0, row);
        grid.add(centarNazivField, 1, row++);

        grid.add(supervizijaLabel, 0, row);
        grid.add(psihoterapeutComboBox, 1, row++);

        grid.add(datumSertifikacijeLabel, 0, row);
        grid.add(datumSertifikacijePicker, 1, row++);

        // dugmad
        Button signupButton = new Button("Registruj se");
        Button backButton = new Button("Nazad");

        HBox buttonBox = new HBox(15, signupButton, backButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(15));

        VBox layout = new VBox(15, logoBox, new ScrollPane(grid), buttonBox);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(10));

        Scene scene = new Scene(layout, 550, 700);
        app.util.ThemeManager.applyTheme(scene);
        stage.setScene(scene);
        stage.setTitle("Registracija korisnika");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/novi-pocetak-logo.png")));
        stage.show();

        addHoverAnimation(signupButton);
        addHoverAnimation(backButton);

        FadeTransition fade = new FadeTransition(Duration.millis(600), layout);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();

        // prolazak kroz sve osobe
        List<Osoba> svi = new OsobaDAO().getAll();
        for (Osoba o : svi) {
            if ("psihoterapeut".equalsIgnoreCase(o.getTipOsobe())) {
                psihoterapeutComboBox.getItems().add(o);
            }
        }

        // vizibilnost u odnosu na to da li je kandidat ili psihoterapeut
        tipOsobeCombo.setOnAction(e -> {
            boolean jeKandidat = "kandidat".equalsIgnoreCase(tipOsobeCombo.getValue());
            supervizijaLabel.setVisible(jeKandidat);
            psihoterapeutComboBox.setVisible(jeKandidat);
            centarLabel.setVisible(jeKandidat);
            centarNazivField.setVisible(jeKandidat);
            datumSertifikacijeLabel.setVisible(!jeKandidat);
            datumSertifikacijePicker.setVisible(!jeKandidat);
        });

        // akcije dugmadi
        backButton.setOnAction(e -> {
            try {
                new LoginView().start(new Stage());
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        signupButton.setOnAction(e -> {

            // VALIDACIJE PODATAKA:

            // da li je sve popunjeno
            if (imeField.getText().isEmpty() || prezimeField.getText().isEmpty() || jmbgField.getText().isEmpty() ||
                    datumRodjenjaPicker.getValue() == null || polCombo.getValue() == null || emailField.getText().isEmpty() ||
                    lozinkaField.getText().isEmpty() || confirmLozinkaField.getText().isEmpty()) {

                showAlert(Alert.AlertType.ERROR, "Molimo popunite sva obavezna polja označena sa zvezdicom.");
                return;
            }

            String tip = tipOsobeCombo.getValue();
            if (tip == null) {
                showAlert(Alert.AlertType.ERROR, "Izaberite da li se registrujete kao kandidat ili psihoterapeut.");
                return;
            }

            if ("kandidat".equalsIgnoreCase(tip)) {
                if (centarNazivField.getText().trim().isEmpty() || psihoterapeutComboBox.getSelectionModel().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, "Kandidat mora uneti naziv centra i odabrati psihoterapeuta.");
                    return;
                }
            }

            if (!lozinkaField.getText().equals(confirmLozinkaField.getText())) {
                showAlert(Alert.AlertType.ERROR, "Lozinke se ne poklapaju!");
                return;
            }

            // 🔐 dodatna validacija
            if (!jmbgField.getText().matches("\\d{13}")) {
                showAlert(Alert.AlertType.ERROR, "JMBG mora sadržati tačno 13 cifara.");
                return;
            }

            if (!imeField.getText().matches("[\\p{L} ]+")) {
                showAlert(Alert.AlertType.ERROR, "Ime sme sadržavati samo slova.");
                return;
            }

            if (!prezimeField.getText().matches("[\\p{L} ]+")) {
                showAlert(Alert.AlertType.ERROR, "Prezime sme sadržavati samo slova.");
                return;
            }

            if (!ulicaField.getText().matches("[\\p{L} ]+")) {
                showAlert(Alert.AlertType.ERROR, "Ulica sme sadržavati samo slova.");
                return;
            }

            if (!fakultetNazivField.getText().matches("[\\p{L} ]+")) {
                showAlert(Alert.AlertType.ERROR, "Fakultet sme sadržavati samo slova.");
                return;
            }

            if (!univerzitetNazivField.getText().matches("[\\p{L} ]+")) {
                showAlert(Alert.AlertType.ERROR, "Univerzitet sme sadržavati samo slova.");
                return;
            }

            if (!oblastNazivField.getText().matches("[\\p{L} ]+")) {
                showAlert(Alert.AlertType.ERROR, "Oblast sme sadržavati samo slova.");
                return;
            }

            if (lozinkaField.getText().length() < 8) {
                showAlert(Alert.AlertType.ERROR, "Lozinka mora imati najmanje 8 karaktera.");
                return;
            }

            // kreiranje objekta osobe
            Osoba osoba = new Osoba();

            // uzmanje imena na osnovu ID-a
            try {
                int univerzitetId = UniverzitetDAO.nadjiIliKreiraj(univerzitetNazivField.getText().trim());
                int fakultetId = FakultetDAO.nadjiIliKreiraj(fakultetNazivField.getText().trim(), univerzitetId);
                int oblastId = OblastDAO.nadjiIliKreiraj(oblastNazivField.getText().trim());

                osoba.setUniverzitetId(univerzitetId);
                osoba.setFakultetId(fakultetId);
                osoba.setOblastId(oblastId);

                // povezivanje u tabeli fakultet_oblast
                FakultetOblast fo = new FakultetOblast();
                fo.setFakultetId(osoba.getFakultetId());
                fo.setUniverzitetId(osoba.getUniverzitetId());
                fo.setOblastId(osoba.getOblastId());

                // ako ta kombinacija (fakultet-univerzitet-oblast) ne postoji, dodaj je
                if (!new FakultetOblastDAO().exists(fo)) {
                    new FakultetOblastDAO().insert(fo);
                }

            } catch (SQLException ex) {
                showAlert(Alert.AlertType.ERROR, "Greška prilikom obrade fakulteta, univerziteta ili oblasti: " + ex.getMessage());
                return;
            }

            // konacno postavljanje za objekat osobe
            osoba.setIme(imeField.getText());
            osoba.setPrezime(prezimeField.getText());
            osoba.setJmbg(jmbgField.getText());
            osoba.setDatumRodjenja(Date.valueOf(datumRodjenjaPicker.getValue()));
            osoba.setPol(polCombo.getValue());
            osoba.setEmail(emailField.getText());
            osoba.setTelefon(telefonField.getText());
            osoba.setUlica(ulicaField.getText());
            osoba.setBroj(parseIntSafe(brojField.getText(), 0));
            osoba.setOpstina(opstinaField.getText());
            osoba.setStepenStudija(stepenStudijaCombo.getValue());
            osoba.setTipOsobe(tip);
            osoba.setLozinka(lozinkaField.getText()); // hashiraj u produkciji

            if ("psihoterapeut".equalsIgnoreCase(tip)) {
                osoba.setDatumSertifikacije(datumSertifikacijePicker.getValue() == null ? null : Date.valueOf(datumSertifikacijePicker.getValue()));
            } else {
                osoba.setDatumSertifikacije(null); // kandidati ne popunjavaju
            }

            if ("kandidat".equalsIgnoreCase(tip)) {
                try {
                    CentarZaObuku centar = CentarZaObukuDAO.nadjiIliKreirajCentar(
                            centarNazivField.getText().trim(), "", "", "", 0, ""
                    );
                    osoba.setCentarId(centar.getCentarId());
                } catch (SQLException ex) {
                    showAlert(Alert.AlertType.ERROR, "Greska prilikom obrade centra: " + ex.getMessage());
                    return;
                }
            }

            // upis osobe bez supervizije_id
            Integer kandidatId = OsobaDAO.addAndReturnId(osoba);
            if (kandidatId == null) {
                showAlert(Alert.AlertType.ERROR, "Greska prilikom registracije osobe.");
                return;
            }

            // ako je kandidat, sada kreiraj superviziju
            if ("kandidat".equalsIgnoreCase(tip)) {
                Osoba psihoterapeut = psihoterapeutComboBox.getValue();

                Supervizija s = new Supervizija();
                s.setKandidatId(kandidatId);
                s.setPsihoterapeutId(psihoterapeut.getId());
                s.setPocetak(Date.valueOf(LocalDate.now()));
                s.setKraj(Date.valueOf(LocalDate.now().plusMonths(6)));

                Integer supervizijaId = new SupervizijaDAO().insertAndReturnId(s);
                if (supervizijaId == null) {
                    showAlert(Alert.AlertType.ERROR, "Greška prilikom kreiranja supervizije!");
                    return;
                }

                boolean povezano = new OsobaDAO().updateSupervizijaId(kandidatId, supervizijaId);
                if (!povezano) {
                    showAlert(Alert.AlertType.ERROR, "Greška pri povezivanju supervizije sa osobom.");
                    return;
                }
            }

            showAlert(Alert.AlertType.INFORMATION, "Registracija uspešna!");
            stage.close();
            try {
                new LoginView().start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
                }
        });
    }

    private void showAlert(Alert.AlertType type, String poruka) {
        Alert alert = new Alert(type);
        alert.setContentText(poruka);
        alert.showAndWait();
    }

    private int parseIntSafe(String s, int defaultValue) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    private void addHoverAnimation(Button btn) {
        ScaleTransition st = new ScaleTransition(Duration.millis(200), btn);
        btn.setOnMouseEntered(e -> {
            st.setToX(1.1);
            st.setToY(1.1);
            st.playFromStart();
        });
        btn.setOnMouseExited(e -> {
            st.setToX(1);
            st.setToY(1);
            st.playFromStart();
        });
    }

    private void setMaxFieldWidths(double width, Control... controls) {
        for (Control c : controls) {
            c.setMaxWidth(width);
        }
    }
}