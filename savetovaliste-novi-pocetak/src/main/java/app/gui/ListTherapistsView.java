package app.gui;

import app.dao.OsobaDAO;
import app.model.Osoba;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.List;

public class ListTherapistsView extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        // naslov
        Label titleLabel = new Label("Lista psihoterapeuta");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: #1976d2; -fx-font-weight: bold;");

        // lista terapeuta
        ListView<String> therapistList = new ListView<>();
        therapistList.setPrefHeight(200);
        therapistList.setMaxWidth(500);

        // uzimanje svih osoba (terapeuta)
        OsobaDAO dao = new OsobaDAO();
        List<Osoba> osobe = dao.getAll();
        for (Osoba o : osobe) {
            therapistList.getItems().add(o.toString());
        }
        therapistList.getStyleClass().add("payment-list");

        // dugme
        Button backButton = new Button("⬅️ Nazad");
        backButton.setPrefWidth(180);
        VBox.setMargin(backButton, new Insets(20, 0, 0, 0));

        backButton.setOnAction(e -> {
            try {
                new LoginView().start(new Stage());
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // kartica, layout, stage i scena
        VBox card = new VBox(20, titleLabel, therapistList, backButton);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(30));
        card.getStyleClass().add("payment-card");

        VBox layout = new VBox(card);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(40));

        Scene scene = new Scene(layout, 700, 500);
        app.util.ThemeManager.applyTheme(scene);
        stage.setScene(scene);
        stage.setTitle("Lista psihoterapeuta");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/novi-pocetak-logo.png")));
        stage.show();
        Platform.runLater(stage::centerOnScreen);

        FadeTransition fade = new FadeTransition(Duration.millis(600), layout);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();

    }

}
