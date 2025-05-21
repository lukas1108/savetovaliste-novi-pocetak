package app.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SessionListView extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        ListView<String> sessionList = new ListView<>();
        sessionList.getItems().addAll(
                " 21.05.2025. - Klijent: Anja Aprcovic (uzivo)",
                "21.05.2025. - Klijent: Tralalero tralala (uzivo)",
                "21.05.2025. - Klijent: Y/N (online)"
        );

        Button backButton = new Button("Nazad");
        backButton.setOnAction(e -> {
            TherapistDashboardView dashboard = new TherapistDashboardView();
            try {
                dashboard.start(new Stage());
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        VBox layout = new VBox(10, sessionList, backButton);
        layout.setStyle("-fx-padding: 20");

        Scene scene = new Scene(layout, 500, 300);
        stage.setScene(scene);
        stage.setTitle("Seanse");
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
