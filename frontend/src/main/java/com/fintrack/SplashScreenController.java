package com.fintrack;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
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


        // load logo image
        URL imageUrl = getClass().getResource("/images/FinTrackLogo.jpg");
        if (imageUrl != null) {
            logo.setImage(new Image(imageUrl.toExternalForm()));
        }

        else {
            System.err.println("Logo image not found!");
        }

        // fade in animation
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1.5), root);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();

        // set up "start" button
        getStartedButton.setOnAction(event -> goToLogin());
    }

    private void goToLogin() {
        try {

            SceneNavigator.navigateTo(getStartedButton, "/fxml/Login.fxml");
        } catch (IOException e) {
            System.err.println("Failed to load Login.fxml");
            e.printStackTrace();
        }
    }
}