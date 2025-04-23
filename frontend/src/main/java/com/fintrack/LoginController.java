package com.fintrack;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

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

        if ("admin@example.com".equals(email) && "1234".equals(password)) {
            showAlert("Login Successful", "Welcome back to FinTrack!");
            // TODO: Navigate to dashboard
        } else {
            showAlert("Login Failed", "Invalid email or password.");
        }
    }

    @FXML
    void handleForgotPassword(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("forgot.fxml"));
            Stage stage = (Stage) forgotLink.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleSignup(ActionEvent event) {
        showAlert("Signup", "Redirect to signup screen (coming soon).");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}