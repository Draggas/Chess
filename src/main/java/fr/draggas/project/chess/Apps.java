package fr.draggas.project.chess;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Apps extends Application {

    @Override
    public void start(Stage stage) {
        // Crée une étiquette
        Label label = new Label("Bienvenue dans JavaFX !");
        
        // Crée un bouton
        Button button = new Button("Cliquez-moi !");
        button.setOnAction(event -> label.setText("Bouton cliqué !"));

        // Ajoute les composants dans un conteneur VBox
        VBox root = new VBox(10, label, button);
        root.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        // Crée une scène et ajoute-la à la fenêtre
        Scene scene = new Scene(root, 400, 300);
        stage.setTitle("Test JavaFX - Chess");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
