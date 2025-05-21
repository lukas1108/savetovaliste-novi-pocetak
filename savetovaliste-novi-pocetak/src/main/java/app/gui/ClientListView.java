package app.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ClientListView extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        ListView<String> clientList = new ListView<>();
        clientList.getItems().addAll(
                "Anja Aprcovic", "Anja Treba Mi Terapija", "Ubicu se"
        );

        Button backButton = new Button("Nazad");

        backButton.setOnAction(e -> {
            TherapistDashboardView dashboard = new TherapistDashboardView();
            try{
                dashboard.start(new Stage());
                stage.close();
            } catch (Exception ex){
                ex.printStackTrace();
            }
        });

        VBox layout = new VBox(10, clientList, backButton);
        layout.setStyle("-fx-padding: 20");

        Scene scene = new Scene(layout, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Lista klijenata");
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
