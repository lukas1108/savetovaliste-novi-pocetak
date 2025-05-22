package app.gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PaymentView extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // naslov
        Label titleLabel = new Label("💳 Uplate i dugovanja");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: #1976d2; -fx-font-weight: bold;");

        // lista uplata
        ListView<String> payments = new ListView<>();
        payments.setPrefHeight(200);
        payments.setMaxWidth(500);
        payments.getItems().addAll(
                "Anja Aprc – Dug: 2000 RSD",
                "Lili Duolingo – Uplaćeno: 3000 RSD",
                "RAAAAAAAAH – Dug: 0 RSD"
        );
        payments.getStyleClass().add("payment-list");

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

        // kartica
        VBox card = new VBox(20, titleLabel, payments, backButton);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(30));
        card.getStyleClass().add("payment-card");

        VBox layout = new VBox(card);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(40));

        Scene scene = new Scene(layout, 700, 500);
        app.util.ThemeManager.applyTheme(scene);
        stage.setScene(scene);
        stage.setTitle("Uplate i dugovanja");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/novi-pocetak-logo.png")));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
