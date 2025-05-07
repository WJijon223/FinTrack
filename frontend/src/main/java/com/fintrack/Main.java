package com.fintrack;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image; // ✅ Import this
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // load FXML
        var splashURL = getClass().getResource("/fxml/SplashScreen.fxml");
        if (splashURL == null) {
            throw new RuntimeException("SplashScreen.fxml not found in resources!");
        }
        Parent root = FXMLLoader.load(splashURL);
        Scene scene = new Scene(root, 1000, 700);

        // load CSS (update path if needed)
        var cssURL = getClass().getResource("/css/style.css");
        if (cssURL == null) {
            System.err.println("⚠️ style.css not found! Skipping stylesheet.");
        } else {
            scene.getStylesheets().add(cssURL.toExternalForm());
        }

        // ✅ Set application icon (favicon)
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/FinTrackLogo.jpg")));

        stage.setTitle("FinTrack");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
