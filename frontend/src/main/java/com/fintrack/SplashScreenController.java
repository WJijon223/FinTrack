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
                // Fixed: FXML path must begin with '/' and match resources root
                Parent root = FXMLLoader.load(getClass().getResource("/Login.fxml"));
                Stage stage = (Stage) logo.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show(); // Important: show the stage!
            } catch (IOException e) {
                System.err.println("Failed to load Login.fxml");
                e.printStackTrace();
            }
        });
        pause.play();
    }
}
