package com.fintrack;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

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

        try {


            // navigate to dashboard using SceneNavigator
            SceneNavigator.navigateTo(loginButton, "/fxml/Dashboard.fxml");
        }

        catch (IOException e) {
            e.printStackTrace();
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