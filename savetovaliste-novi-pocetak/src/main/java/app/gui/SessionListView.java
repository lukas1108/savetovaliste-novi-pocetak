package app.gui;

import app.dao.SeansaDAO;
import app.model.Osoba;
import app.model.Seansa;
import app.util.Session;
import app.util.ThemeManager;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.List;

public class SessionListView extends Application {

    public static Seansa selectedSeansa = null;
    private Osoba currentTherapist = Session.getCurrentUser();

    @Override
    public void start(Stage stage) {

        Label header = new Label("Seanse psihoterapeuta");
        header.setStyle("-fx-font-size: 20px; -fx-text-fill: #1976d2; -fx-font-weight: bold;");

        // dve liste - za prethodno odrzane i za buduce seanse
        Label pastLabel = new Label("Održane seanse:");
        Label upcomingLabel = new Label("Zakazane seanse:");

        ListView<String> pastList = new ListView<>();
        pastList.setPrefHeight(150);
        pastList.setMaxWidth(480);

        ListView<String> upcomingList = new ListView<>();
        upcomingList.setPrefHeight(150);
        upcomingList.setMaxWidth(480);

        SeansaDAO dao = new SeansaDAO();
        List<Seansa> pastSessions = dao.getPastSessionsForTherapist(currentTherapist.getId());
        List<Seansa> upcomingSessions = dao.getUpcomingSessionsForTherapist(currentTherapist.getId());

        for (Seansa s : pastSessions) pastList.getItems().add(s.toString());
        for (Seansa s : upcomingSessions) upcomingList.getItems().add(s.toString());

        // selekcija
        pastList.setOnMouseClicked(e -> selectedSeansa = findSeansaByDescription(pastList.getSelectionModel().getSelectedItem(), pastSessions));
        upcomingList.setOnMouseClicked(e -> selectedSeansa = findSeansaByDescription(upcomingList.getSelectionModel().getSelectedItem(), upcomingSessions));

        // dugmici
        Button notesButton = new Button("📝 Beleške i testovi");
        Button publicationButton = new Button("📢 Objavljivanje podataka");
        Button backButton = new Button("⬅️ Nazad");

        addHoverAnimation(notesButton);
        addHoverAnimation(publicationButton);
        addHoverAnimation(backButton);

        // za selektovanu seansu otvara notes
        notesButton.setOnAction(e -> {
            if (selectedSeansa == null) {
                showAlert(Alert.AlertType.WARNING, "Molimo selektujte seansu iz liste.");
                return;
            }
            try {
                new NotesView().start(new Stage());
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // isto to i za objavu
        publicationButton.setOnAction(e -> {
            try {
                new PublicationView().start(new Stage());
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // prethodni prozpr
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

        HBox buttonBox = new HBox(15, notesButton, publicationButton);
        buttonBox.setAlignment(Pos.CENTER);
        VBox.setMargin(buttonBox, new Insets(10, 0, 0, 0));

        VBox layout = new VBox(15, header,
                upcomingLabel, upcomingList,
                pastLabel, pastList,
                buttonBox, backButton);
        layout.setStyle("-fx-padding: 40;");
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 600, 550);
        ThemeManager.applyTheme(scene);
        stage.setScene(scene);
        stage.setTitle("Seanse");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/novi-pocetak-logo.png")));
        stage.show();
    }

    private void addHoverAnimation(Button button) {
        button.setOnMouseEntered(e -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(150), button);
            st.setToX(1.05);
            st.setToY(1.05);
            st.play();
        });
        button.setOnMouseExited(e -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(150), button);
            st.setToX(1.0);
            st.setToY(1.0);
            st.play();
        });
    }

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private Seansa findSeansaByDescription(String text, List<Seansa> list) {
        if (text == null) return null;
        for (Seansa s : list) {
            if (s.toString().equals(text)) return s;
        }
        return null;
    }

}