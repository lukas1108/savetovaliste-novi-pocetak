package app.gui;

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
        VBox layout = new VBox(20, header, sessionList, backButton);
        layout.setStyle("-fx-padding: 40;");
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 600, 420);
        app.util.ThemeManager.applyTheme(scene);
        stage.setScene(scene);
        stage.setTitle("Seanse");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/novi-pocetak-logo.png")));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
