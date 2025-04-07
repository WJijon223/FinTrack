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

public class SplashScreenController {

    @FXML
    private ImageView logo;

    @FXML
    public void initialize() {
        // Load logo image from /resources (use .jpg or .png depending on your file)
        Image image = new Image(getClass().getResourceAsStream("/FinTrackLogo.jpg")); // or .png
        logo.setImage(image);

        //  Wait for 3 seconds, then switch to the main screen
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event -> {
            try {
                //  Replace MainView.fxml with your actual next screen (login, dashboard, etc.)
                Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
                Stage stage = (Stage) logo.getScene().getWindow();
                stage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        pause.play();
    }
}
