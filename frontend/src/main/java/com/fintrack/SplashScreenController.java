package com.fintrack;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;

public class SplashScreenController {

    @FXML
    private ImageView logo;

    @FXML
    public void initialize() {
        // Load logo image safely
        URL imageUrl = getClass().getResource("/FinTrackLogo.jpg");
        if (imageUrl != null) {
            logo.setImage(new Image(imageUrl.toExternalForm()));
        } else {
            System.err.println("Logo image not found!");
        }

        // Wait for 3 seconds, then switch to Login screen
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event -> {
            try {
                // Load the login screen
                Parent root = FXMLLoader.load(getClass().getResource("/Login.fxml"));
                Stage stage = (Stage) logo.getScene().getWindow(); // Get the current stage
                Scene scene = new Scene(root);

                // Load the CSS for the login screen
                URL cssUrl = getClass().getResource("/style.css");
                if (cssUrl != null) {
                    scene.getStylesheets().add(cssUrl.toExternalForm());
                } else {
                    System.err.println("style.css not found for Login screen!");
                }

                stage.setScene(scene);
                stage.setTitle("FinTrack Login");
                stage.show();
            } catch (IOException e) {
                System.err.println("Failed to load Login.fxml");
                e.printStackTrace();
            }
        });
        pause.play();
    }
}