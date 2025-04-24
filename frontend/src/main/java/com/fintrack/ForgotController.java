package com.fintrack;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class ForgotController {

    @FXML
    private Hyperlink backToLoginLink;

    @FXML
    private TextField emailField;

    @FXML
    private VBox glassPane;

    @FXML
    private Button resetButton;

    @FXML
    private void handleReset(ActionEvent event) {
        String email = emailField.getText();

        if (email.isEmpty()) {
            showAlert("Error", "Please enter your email.");
            return;
        }

        showAlert("Email Sent", "A password reset link has been sent to: " + email);

        // Simulate delay and transition to Reset.fxml
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(e -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/Reset.fxml")); // ✅ fixed path
                Stage stage = (Stage) emailField.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException ex) {
                System.err.println("Could not load Reset.fxml");
                ex.printStackTrace();
            }
        });
        delay.play();
    }

    @FXML
    private void handleBackToLogin(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Login.fxml")); // ✅ fixed path
            Stage stage = (Stage) backToLoginLink.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.err.println("Could not load Login.fxml");
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
