package com.fintrack;

import javafx.animation.FadeTransition;
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
        URL imageUrl = getClass().getResource("/FinTrackLogo.jpg");
        if (imageUrl != null) {
            logo.setImage(new Image(imageUrl.toExternalForm()));
        } else {
            System.err.println("Logo image not found!");
        }

        // Wait for 3 seconds, then switch to Login screen with fade transition
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event -> navigateToScreen("/Login.fxml", "FinTrack Login"));
        pause.play();
    }

    private void navigateToScreen(String fxmlPath, String title) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
            Stage stage = (Stage) logo.getScene().getWindow();
            Scene scene = new Scene(root);

            URL cssUrl = getClass().getResource("/style.css");
            if (cssUrl != null) {
                scene.getStylesheets().add(cssUrl.toExternalForm());
            } else {
                System.err.println("style.css not found for Login screen!");
            }

            root.getStyleClass().add("light");

            FadeTransition fadeOut = new FadeTransition(Duration.millis(200), logo.getScene().getRoot());
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.setOnFinished(e -> {
                stage.setScene(scene);
                stage.setTitle(title);

                FadeTransition fadeIn = new FadeTransition(Duration.millis(200), root);
                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);
                fadeIn.play();
                stage.show();
            });
            fadeOut.play();
        } catch (IOException e) {
            System.err.println("Failed to load " + fxmlPath);
            e.printStackTrace();
        }
    }
}