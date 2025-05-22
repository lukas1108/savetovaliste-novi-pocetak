package app.gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.animation.ScaleTransition;
import javafx.util.Duration;

public class SessionListView extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // naslov
        Label header = new Label("Lista seansi");
        header.setStyle("-fx-font-size: 20px; -fx-text-fill: #1976d2; -fx-font-weight: bold;");

        // lista seansi
        ListView<String> sessionList = new ListView<>();
        sessionList.setPrefHeight(200);
        sessionList.setMaxWidth(480);
        sessionList.getStyleClass().add("session-list");

        sessionList.getItems().addAll(
                "21.05.2025. - Klijent: Anja Aprcovic (uživo)",
                "21.05.2025. - Klijent: Tralalero Tralala (uživo)",
                "21.05.2025. - Klijent: Y/N (online)"
        );

        // dugmad notes i publication (kao u TherapistDashboardView)
        Button notesButton = new Button("📝 Beleške i testovi");
        Button publicationButton = new Button("📢 Objavljivanje podataka");

        //notesButton.setPrefWidth(180);
        //publicationButton.setPrefWidth(180);

        // dodaj hover animaciju na ova dugmad
        addHoverAnimation(notesButton);
        addHoverAnimation(publicationButton);

        // akcije za dugmad (pretpostavljam da otvaraju te view-ove, isto kao u TherapistDashboardView)
        notesButton.setOnAction(e -> {
            try {
                new NotesView().start(new Stage());
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        publicationButton.setOnAction(e -> {
            try {
                new PublicationView().start(new Stage());
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // dugme nazad
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
        addHoverAnimation(backButton);

        // raspored novih dugmadi pored liste (horizontalno)
        HBox buttonBox = new HBox(15, notesButton, publicationButton);
        buttonBox.setAlignment(Pos.CENTER);
        VBox.setMargin(buttonBox, new Insets(10, 0, 0, 0));

        // layout
        VBox layout = new VBox(20, header, sessionList, buttonBox, backButton);
        layout.setStyle("-fx-padding: 40;");
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 600, 480);
        app.util.ThemeManager.applyTheme(scene);
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

    public static void main(String[] args) {
        launch(args);
    }
}
