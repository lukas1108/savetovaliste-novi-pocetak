package app.gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.util.Duration;

public class TherapistDashboardView extends Application {

    @Override
    public void start(Stage stage) {
        // ikonica aplikacije
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/novi-pocetak-logo.png")));

        // korisnički info (gore desno)
        Label userLabel = new Label("👤 Luka Stoiljkovic");
        userLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #777;");
        HBox userInfoBox = new HBox(userLabel);
        userInfoBox.setAlignment(Pos.TOP_RIGHT);
        userInfoBox.setPadding(new Insets(10, 20, 0, 0));

        // header
        Label headerLabel = new Label("Kontrolna tabla");
        headerLabel.getStyleClass().add("dashboard-header");

        // dobrodoslica
        Label welcomeLabel = new Label("Dobrodošli, Luka");
        welcomeLabel.getStyleClass().add("dashboard-title");

        // dugmad
        Button profileButton = new Button("👤 Moj profil");
        Button clientsButton = new Button("🧑‍🤝‍🧑 Klijenti");
        Button sessionsButton = new Button("📅 Seanse");
        //Button notesButton = new Button("📝 Beleške i testovi");
        Button paymentsButton = new Button("💸 Uplate i dugovanja");
        //Button publicationButton = new Button("📢 Objavljivanje podataka");
        Button logoutButton = new Button("🚪 Odjava");
        Button switchThemeButton = new Button("🎨 Promeni temu");
        switchThemeButton.setOnAction(e -> {
            app.util.ThemeManager.switchTheme();
            app.util.ThemeManager.applyTheme(stage.getScene());
        });
        switchThemeButton.getStyleClass().add("theme-toggle");

        // raspored dugmadi STARI
        /*
        VBox leftColumn = new VBox(10, profileButton, sessionsButton, publicationButton);
        VBox rightColumn = new VBox(10, clientsButton, notesButton, paymentsButton);*/

        // raspored dugmadi
        VBox leftColumn = new VBox(10, profileButton, sessionsButton);
        VBox rightColumn = new VBox(10, clientsButton, paymentsButton);

        leftColumn.getStyleClass().add("dashboard-button-column");
        rightColumn.getStyleClass().add("dashboard-button-column");

        HBox buttonLayout = new HBox(30, leftColumn, rightColumn);
        buttonLayout.getStyleClass().add("dashboard-buttons");

        VBox card = new VBox(20, headerLabel, welcomeLabel, buttonLayout, logoutButton, switchThemeButton);
        card.getStyleClass().add("dashboard-container");

        VBox.setMargin(logoutButton, new Insets(30, 0, 0, 0));
        logoutButton.setPrefWidth(220);
        logoutButton.setPrefHeight(40);

        VBox contentBox = new VBox(userInfoBox, card);
        contentBox.setSpacing(10);

        VBox root = new VBox(contentBox);
        root.setStyle("-fx-alignment: top_center; -fx-padding: 30;");
        root.getStyleClass().add("dashboard-root");

        Scene scene = new Scene(root, 800, 620);
        app.util.ThemeManager.applyTheme(scene);
        stage.setScene(scene);
        stage.setTitle("Dashboard psihoterapeuta");
        stage.show();

        // akcije
        profileButton.setOnAction(e -> openView(new ProfileView(), stage));
        clientsButton.setOnAction(e -> openView(new ClientListView(), stage));
        sessionsButton.setOnAction(e -> openView(new SessionListView(), stage));
        //notesButton.setOnAction(e -> openView(new NotesView(), stage));
        paymentsButton.setOnAction(e -> openView(new PaymentView(), stage));
        //publicationButton.setOnAction(e -> openView(new PublicationView(), stage));
        logoutButton.setOnAction(e -> {
            try {
                new LoginView().start(new Stage());
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

       /* Button[] allButtons = {
                profileButton, clientsButton, sessionsButton, notesButton,
                paymentsButton, publicationButton, logoutButton, switchThemeButton
        };*/

        Button[] allButtons = {
                profileButton, clientsButton, sessionsButton,
                paymentsButton, logoutButton, switchThemeButton
        };

        for (Button btn : allButtons) {
            addHoverAnimation(btn);
        }

        FadeTransition fade = new FadeTransition(Duration.millis(600), root);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }

    private void openView(Application view, Stage currentStage) {
        try {
            view.start(new Stage());
            currentStage.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
