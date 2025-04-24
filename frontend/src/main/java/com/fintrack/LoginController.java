package com.fintrack;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private Hyperlink forgotLink;

    @FXML
    private Button loginButton;

    @FXML
    private ImageView logo;

    @FXML
    private TextField passwordField;

    @FXML
    private Hyperlink signupLink;

    @FXML
    void handleLogin(ActionEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();

        if (email.isEmpty() || password.isEmpty()) {
            showAlert("Missing Information", "Please enter both email and password.");
            return;
        }

        try {
            // ✅ Corrected path
            Parent root = FXMLLoader.load(getClass().getResource("/Dashboard.fxml"));
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Could not load dashboard.");
        }
    }

    @FXML
    void handleForgotPassword(ActionEvent event) {
        try {
            // ✅ Corrected case and path
            Parent root = FXMLLoader.load(getClass().getResource("/Forgot.fxml"));
            Stage stage = (Stage) forgotLink.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Could not load forgot password screen.");
        }
    }

    @FXML
    private void handleSignup(ActionEvent event) {
        try {
            // ✅ Corrected case and path
            Parent root = FXMLLoader.load(getClass().getResource("/Signup.fxml"));
            Stage stage = (Stage) signupLink.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Could not load signup screen.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
