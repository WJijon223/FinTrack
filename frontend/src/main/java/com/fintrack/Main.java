package com.fintrack;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Load the splash screen or dashboard (you can switch this as needed)
        URL fxmlUrl = getClass().getResource("/SplashScreen.fxml");
        if (fxmlUrl == null) {
            throw new RuntimeException("SplashScreen.fxml not found!");
        }

        Parent root = FXMLLoader.load(fxmlUrl);
        Scene scene = new Scene(root);

        // Load the stylesheet
        URL cssUrl = getClass().getResource("/style.css");
        if (cssUrl != null) {
            scene.getStylesheets().add(cssUrl.toExternalForm());
        } else {
            System.err.println("⚠ style.css not found in resources!");
        }

        // Start the app
        stage.setTitle("FinTrack Dashboard");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
