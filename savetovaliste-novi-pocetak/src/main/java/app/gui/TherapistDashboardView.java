package app.gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TherapistDashboardView extends Application {
    @Override
    public void start(Stage stage) {
        Label welcomeLabel = new Label("Dobrodošli, Luka");
        welcomeLabel.getStyleClass().add("dashboard-title");

        Button profileButton = new Button("Moj profil");
        Button clientsButton = new Button("Klijenti");
        Button sessionsButton = new Button("Seanse");
        Button notesButton = new Button("Beleške i testovi");
        Button paymentsButton = new Button("Uplate i dugovanja");
        Button logoutButton = new Button("Odjava");
        Button publicationButton = new Button("Objavljivanje podataka");

        VBox leftColumn = new VBox(10, profileButton, sessionsButton, publicationButton);
        VBox rightColumn = new VBox(10, clientsButton, notesButton, paymentsButton);
        leftColumn.getStyleClass().add("dashboard-button-column");
        rightColumn.getStyleClass().add("dashboard-button-column");

        HBox buttonLayout = new HBox(30, leftColumn, rightColumn);
        buttonLayout.getStyleClass().add("dashboard-buttons");

        VBox card = new VBox(25, welcomeLabel, buttonLayout, logoutButton);
        card.getStyleClass().add("dashboard-container");

        VBox root = new VBox(card);
        root.setStyle("-fx-alignment: center; -fx-padding: 60;");
        root.getStyleClass().add("dashboard-root");

        logoutButton.setPrefWidth(220);
        logoutButton.setPrefHeight(40);
        VBox.setMargin(logoutButton, new Insets(30, 0, 0, 0));

        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Dashboard psihoterapeuta");
        stage.show();

        // akcije
        clientsButton.setOnAction(e -> openView(new ClientListView(), stage));
        profileButton.setOnAction(e -> openView(new ProfileView(), stage));
        sessionsButton.setOnAction(e -> openView(new SessionListView(), stage));
        notesButton.setOnAction(e -> openView(new NotesView(), stage));
        paymentsButton.setOnAction(e -> openView(new PaymentView(), stage));
        publicationButton.setOnAction(e -> openView(new PublicationView(), stage));
    }

    private void openView(Application view, Stage currentStage) {
        try {
            view.start(new Stage());
            currentStage.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
