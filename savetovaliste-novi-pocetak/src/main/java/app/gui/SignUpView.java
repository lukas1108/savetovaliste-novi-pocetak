package app.gui;

import app.dao.OsobaDAO;
import app.model.Osoba;
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
import java.time.LocalDate;

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

        // polja za unos
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

        DatePicker datumSertifikacijePicker = new DatePicker();

        TextField centarNazivField = new TextField();
        TextField fakultetIdField = new TextField();
        TextField univerzitetIdField = new TextField();
        TextField supervizijaIdField = new TextField();

        ComboBox<String> tipOsobeCombo = new ComboBox<>();
        tipOsobeCombo.getItems().addAll("kandidat", "psihoterapeut");

        TextField oblastIdField = new TextField();

        PasswordField lozinkaField = new PasswordField();
        PasswordField confirmLozinkaField = new PasswordField();

        // layout za sva polja
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(15);
        grid.setPadding(new Insets(20));

        int row = 0;
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

        grid.add(new Label("Datum sertifikacije:"), 0, row);
        grid.add(datumSertifikacijePicker, 1, row++);

        grid.add(new Label("* Centar ID:"), 0, row);
        grid.add(centarNazivField, 1, row++);

        grid.add(new Label("* Fakultet ID:"), 0, row);
        grid.add(fakultetIdField, 1, row++);

        grid.add(new Label("* Univerzitet ID:"), 0, row);
        grid.add(univerzitetIdField, 1, row++);

        grid.add(new Label("* Supervizija ID:"), 0, row);
        grid.add(supervizijaIdField, 1, row++);

        grid.add(new Label("* Psihoterapeut/Kandidat:"), 0, row);
        grid.add(tipOsobeCombo, 1, row++);

        grid.add(new Label("* Oblast ID:"), 0, row);
        grid.add(oblastIdField, 1, row++);

        grid.add(new Label("* Lozinka:"), 0, row);
        grid.add(lozinkaField, 1, row++);

        grid.add(new Label("* Potvrdi lozinku:"), 0, row);
        grid.add(confirmLozinkaField, 1, row++);

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
            // validacija polja
            if (imeField.getText().isEmpty() || prezimeField.getText().isEmpty() || jmbgField.getText().isEmpty() ||
                    datumRodjenjaPicker.getValue() == null || polCombo.getValue() == null || emailField.getText().isEmpty() ||
                    lozinkaField.getText().isEmpty() || confirmLozinkaField.getText().isEmpty()) {

                showAlert(Alert.AlertType.ERROR, "Molimo popunite sva obavezna polja označena sa zvezdicom.");
                return;
            }

            if (!lozinkaField.getText().equals(confirmLozinkaField.getText())) {
                showAlert(Alert.AlertType.ERROR, "Lozinke se ne poklapaju!");
                return;
            }

            // kreiranje objekta osobe
            Osoba osoba = new Osoba();
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
            osoba.setDatumSertifikacije(datumSertifikacijePicker.getValue() == null ? null : Date.valueOf(datumSertifikacijePicker.getValue()));
            osoba.setCentarId(parseIntSafeNullable(centarNazivField.getText()));
            osoba.setFakultetId(parseIntSafe(fakultetIdField.getText(), -1));
            osoba.setUniverzitetId(parseIntSafe(univerzitetIdField.getText(), -1));
            osoba.setSupervizijaId(parseIntSafeNullable(supervizijaIdField.getText()));
            osoba.setTipOsobe(tipOsobeCombo.getValue());
            osoba.setOblastId(parseIntSafeNullable(oblastIdField.getText()));
            osoba.setLozinka(lozinkaField.getText()); // obavezno hashirati pre cuvanja!

            // poziv metode za upis u bazu
            boolean uspeh = OsobaDAO.add(osoba);
            if (uspeh) {
                showAlert(Alert.AlertType.INFORMATION, "Registracija uspešna!");
                // idi na login ili zatvori
                stage.close();
                try {
                    new LoginView().start(new Stage());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                showAlert(Alert.AlertType.ERROR, "Došlo je do greške prilikom registracije. Proverite ponovo podatke");
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

    private Integer parseIntSafeNullable(String s) {
        try {
            return (s == null || s.trim().isEmpty()) ? null : Integer.parseInt(s);
        } catch (Exception e) {
            return null;
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

    public static void main(String[] args) {
        launch();
    }
}
