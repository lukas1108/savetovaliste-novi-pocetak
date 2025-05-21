package app.gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SessionListView extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        ListView<String> sessionList = new ListView<>();
        sessionList.setPrefHeight(200);
        sessionList.setPrefWidth(450);

        sessionList.getItems().addAll(
                " 21.05.2025. - Klijent: Anja Aprcovic (uzivo)",
                "21.05.2025. - Klijent: Tralalero tralala (uzivo)",
                "21.05.2025. - Klijent: Y/N (online)"
        );

        Button backButton = new Button("Nazad");
        backButton.setPrefWidth(150);
        VBox.setMargin(backButton, new Insets(20, 0, 0, 0));

        backButton.setOnAction(e -> {
            TherapistDashboardView dashboard = new TherapistDashboardView();
            try {
                dashboard.start(new Stage());
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        VBox layout = new VBox(20, sessionList, backButton);
        layout.setStyle("-fx-padding: 30; -fx-alignment: center;");

        Scene scene = new Scene(layout, 600, 400);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Seanse");
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
