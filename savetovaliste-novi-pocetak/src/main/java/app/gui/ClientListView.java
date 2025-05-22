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

public class ClientListView extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // header
        Label header = new Label("📋 Lista klijenata");
        header.setStyle("-fx-font-size: 20px; -fx-text-fill: #1976d2; -fx-font-weight: bold;");

        // lista klijenata
        ListView<String> clientList = new ListView<>();
        clientList.setPrefHeight(200);
        clientList.setMaxWidth(350);
        clientList.getStyleClass().add("client-list");

        clientList.getItems().addAll(
                "Anja Aprcovic", "Anja Treba Mi Terapija", "Ubicu se"
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
        VBox layout = new VBox(20, header, clientList, backButton);
        layout.setStyle("-fx-padding: 40;");
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 500, 400);
        app.util.ThemeManager.applyTheme(scene);
        stage.setScene(scene);
        stage.setTitle("Lista klijenata");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/novi-pocetak-logo.png")));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
