package com.fintrack;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;

public class ProfileController {

    @FXML
    private Label nameLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private ImageView profileImage;

    @FXML
    private Button logoutButton;

    @FXML
    private Button editProfileButton;

    @FXML
    public void initialize() {
        nameLabel.setText("Dieunie Gousse");
        emailLabel.setText("dieunie@example.com");
    }

    @FXML
    private void handleLogout() {
        navigateToScreen("/Login.fxml", "FinTrack Login");
    }

    @FXML
    private void handleEditProfile() {
        // Placeholder for edit profile functionality
        System.out.println("Edit Profile clicked.");
    }

    private void navigateToScreen(String fxmlPath, String title) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
            Stage stage = (Stage) logoutButton.getScene().getWindow();
            Scene scene = new Scene(root);

            URL cssUrl = getClass().getResource("/style.css");
            if (cssUrl != null) {
                scene.getStylesheets().add(cssUrl.toExternalForm());
            }

            root.getStyleClass().add("light");

            FadeTransition fadeOut = new FadeTransition(Duration.millis(200), logoutButton.getScene().getRoot());
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