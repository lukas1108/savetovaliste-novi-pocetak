package app.gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ClientListView extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        ListView<String> clientList = new ListView<>();
        clientList.setPrefHeight(200);
        clientList.setPrefWidth(350);

        clientList.getItems().addAll(
                "Anja Aprcovic", "Anja Treba Mi Terapija", "Ubicu se"
        );

        Button backButton = new Button("Nazad");
        backButton.setPrefWidth(150);

        backButton.setOnAction(e -> {
            TherapistDashboardView dashboard = new TherapistDashboardView();
            try{
                dashboard.start(new Stage());
                stage.close();
            } catch (Exception ex){
                ex.printStackTrace();
            }
        });

        VBox layout = new VBox(20, clientList, backButton);
        layout.setStyle("-fx-padding: 30; -fx-alignment: center;");
        VBox.setMargin(backButton, new Insets(20, 0, 0, 0));

        Scene scene = new Scene(layout, 500, 400);
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        stage.setTitle("Lista klijenata");
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
