package com.fintrack;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class LoginController {

    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Button loginButton;
    @FXML private Hyperlink forgotLink;
    @FXML private Hyperlink signupLink;
    @FXML private ImageView logo;

    @FXML
    void handleLogin(ActionEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();

        if (email.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Missing Information", "Please enter both email and password.");
            return;
        }

        String json = String.format("{\"email\":\"%s\",\"password\":\"%s\"}", email, password);

        // Background thread to avoid freezing UI
        new Thread(() -> {
            try {
                String response = ApiService.sendPostRequest("http://localhost:8080/api/users/login", json);

                Platform.runLater(() -> {
                    showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Welcome! " + response);
                    try {
                        SceneNavigator.navigateTo(loginButton, "/fxml/Dashboard.fxml");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

            } catch (IOException e) {
                Platform.runLater(() ->
                        showAlert(Alert.AlertType.ERROR, "Login Failed", "Error: " + e.getMessage())
                );
            }
        }).start();
    }

    @FXML
    void handleForgotPassword(ActionEvent event) {
        try {
            SceneNavigator.navigateTo(forgotLink, "/fxml/Forgot.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleSignup(ActionEvent event) {
        try {
            SceneNavigator.navigateTo(signupLink, "/fxml/Signup.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(Alert.AlertType type, String title, String msg) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
