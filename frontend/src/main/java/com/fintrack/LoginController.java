package com.fintrack;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import com.fintrack.ApiService;
import javafx.scene.control.Alert;


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
    private PasswordField passwordField;

    @FXML
    private Hyperlink signupLink;

    @FXML
    void handleLogin(ActionEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();

        if (email.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Missing Information");
            alert.setContentText("Please enter both email and password.");
            alert.showAndWait();
            return;
        }

        // Create JSON string for request
        String json = String.format("{\"email\":\"%s\",\"password\":\"%s\"}", email, password);

        try {
            // Send POST request to backend
            String response = ApiService.sendPostRequest("http://137.125.154.102:8080/api/users/register", json);


            // Show success message
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Login Successful");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Welcome! " + response);
            successAlert.showAndWait();

            // Navigate to Dashboard
            SceneNavigator.navigateTo(loginButton, "/fxml/Dashboard.fxml");

        } catch (IOException e) {
            // Show error alert
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Login Failed");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Error: " + e.getMessage());
            errorAlert.showAndWait();
        }
    }


    @FXML
    void handleForgotPassword(ActionEvent event) {
        try {

            SceneNavigator.navigateTo(forgotLink, "/fxml/Forgot.fxml");
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSignup(ActionEvent event) {
        try {

            SceneNavigator.navigateTo(signupLink, "/fxml/Signup.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}