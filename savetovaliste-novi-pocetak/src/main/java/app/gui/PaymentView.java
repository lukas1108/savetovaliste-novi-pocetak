package app.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class PaymentView extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        Label titleLabel = new Label("Uplate i dugovanja klijenata");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        TableView<String> paymentTable = new TableView<>();
        paymentTable.setPlaceholder(new Label("Nema podataka za prikaz."));

        // za sada samo dummy tekst
        ListView<String> payments = new ListView<>();
        payments.setPrefHeight(200);
        payments.setPrefWidth(450);

        payments.getItems().addAll(
                "Anja Aprc – Dug: 2000 RSD",
                "Lili Duolingo – Uplaćeno: 3000 RSD",
                "RAAAAAAAAH – Dug: 0 RSD"
        );

        Button backButton = new Button("Nazad");
        backButton.setPrefWidth(150);
        VBox.setMargin(backButton, new javafx.geometry.Insets(20, 0, 0, 0));

        backButton.setOnAction(e -> {
            TherapistDashboardView dashboard = new TherapistDashboardView();
            try {
                dashboard.start(new Stage());
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        VBox layout = new VBox(20, titleLabel, payments, backButton);
        layout.setStyle("-fx-padding: 30; -fx-alignment: center;");

        Scene scene = new Scene(layout, 600, 400);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Uplate i dugovanja");
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
