package com.fintrack;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;

public class ResetController {

    @FXML
    private Hyperlink backToLoginLink;

    @FXML
    private TextField emailField;

    @FXML
    private VBox glassPane;

    @FXML
    private Button saveButton;

    @FXML
    public void initialize() {
        emailField.requestFocus();
    }

    @FXML
    private void handleSave() {
        String email = emailField.getText();

        if (email.isEmpty()) {
            showAlert("Error", "Please enter your email.");
        } else if (!email.contains("@") || !email.contains(".")) {
            showAlert("Invalid Email", "Please enter a valid email address.");
        } else {
            showAlert("Success", "An email reset link has been sent!");
            goToLogin();
        }
    }

    @FXML
    private void handleBackToLogin() {
        goToLogin();
    }

    private void goToLogin() {
        navigateToScreen("/Login.fxml", "FinTrack Login");
    }

    private void navigateToScreen(String fxmlPath, String title) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
            Stage stage = (Stage) backToLoginLink.getScene().getWindow();
            Scene scene = new Scene(root);

            URL cssUrl = getClass().getResource("/style.css");
            if (cssUrl != null) {
                scene.getStylesheets().add(cssUrl.toExternalForm());
            }

            root.getStyleClass().add("light");

            FadeTransition fadeOut = new FadeTransition(Duration.millis(200), backToLoginLink.getScene().getRoot());
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

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}