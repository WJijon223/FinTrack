package com.fintrack;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;

public class SplashScreenController {

    @FXML
    private ImageView logo;

    @FXML
    private Button getStartedButton;

    @FXML
    private StackPane root;

    @FXML
    public void initialize() {
        // Load logo image safely
        URL imageUrl = getClass().getResource("/FinTrackLogo.jpg");
        if (imageUrl != null) {
            logo.setImage(new Image(imageUrl.toExternalForm()));
        } else {
            System.err.println("Logo image not found!");
        }

        // Fade in the root pane for a smooth animation
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1.5), root);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();

        // Set up Get Started button
        getStartedButton.setOnAction(event -> goToLogin());
    }

    private void goToLogin() {
        try {
            Parent loginRoot = FXMLLoader.load(getClass().getResource("/Login.fxml"));
            Stage stage = (Stage) logo.getScene().getWindow();
            stage.setScene(new Scene(loginRoot));
            stage.show();
        } catch (IOException e) {
            System.err.println("Failed to load Login.fxml");
            e.printStackTrace();
        }
    }
}
