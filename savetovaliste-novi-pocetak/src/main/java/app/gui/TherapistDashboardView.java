package app.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TherapistDashboardView extends Application {
    @Override
    public void start(Stage stage) {
        Button profileButton = new Button("Moj profil");
        Button clientsButton = new Button("Klijenti");
        Button sessionsButton = new Button("Seanse");
        Button notesButton = new Button("Beleške i testovi");
        Button paymentsButton = new Button("Uplate i dugovanja");
        Button logoutButton = new Button("Odjava");
        Button publicationButton = new Button("Objavljivanje podataka");

        clientsButton.setOnAction(e -> {
            ClientListView clientListView = new ClientListView();
            try {
                clientListView.start(new Stage());
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        profileButton.setOnAction(e -> {
            ProfileView profileView = new ProfileView();
            try {
                profileView.start(new Stage());
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        sessionsButton.setOnAction(e -> {
            SessionListView sessionListView = new SessionListView();
            try {
                sessionListView.start(new Stage());
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        notesButton.setOnAction(e -> {
            NotesView notesView = new NotesView();
            try {
                notesView.start(new Stage());
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        paymentsButton.setOnAction(e -> {
            PaymentView paymentView = new PaymentView();
            try {
                paymentView.start(new Stage());
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        publicationButton.setOnAction(e -> {
            PublicationView publicationView = new PublicationView();
            try {
                publicationView.start(new Stage());
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        VBox layout = new VBox(15,
                profileButton,
                clientsButton,
                sessionsButton,
                notesButton,
                paymentsButton,
                publicationButton,
                logoutButton
        );
        layout.setStyle("-fx-padding: 30");

        Scene scene = new Scene(layout, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Dashboard psihoterapeuta");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}