package com.fintrack;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class SignupController {

    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private Button signupButton;
    @FXML private Hyperlink backToLoginLink;

    @FXML
    private void handleSignup() {
        String name = nameField.getText();
        String email = emailField.getText();
        String pass1 = passwordField.getText();
        String pass2 = confirmPasswordField.getText();

        if (name.isEmpty() || email.isEmpty() || pass1.isEmpty() || pass2.isEmpty()) {
            showAlert("Missing Info", "Please fill in all fields.");
            return;
        }

        if (!pass1.equals(pass2)) {
            showAlert("Password Mismatch", "Passwords must match.");
            return;
        }

        showAlert("Signup Successful", "Account created for " + name + "!");
        goToLogin();
    }

    @FXML
    private void handleBackToLogin() {
        goToLogin();
    }

    private void goToLogin() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Login.fxml"));
            Stage stage = (Stage) nameField.getScene().getWindow();
            Scene scene = new Scene(root);

            URL cssUrl = getClass().getResource("/style.css");
            if (cssUrl != null) {
                scene.getStylesheets().add(cssUrl.toExternalForm());
            }

            stage.setScene(scene);
            stage.setTitle("FinTrack Login");
            stage.show();
        } catch (IOException e) {
            System.err.println("Could not load Login.fxml");
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}