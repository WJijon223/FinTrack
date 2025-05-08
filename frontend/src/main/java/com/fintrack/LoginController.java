package com.fintrack;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

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

        new Thread(() -> {
            try {
                // ✅ Backend returns the name of the user on successful login
                String response = ApiService.sendPostRequest("http://localhost:8080/api/users/login", json);

                Platform.runLater(() -> {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Dashboard.fxml"));
                        Parent root = loader.load();

                        DashboardController controller = loader.getController();
                        controller.setUserName(response); // ✅ Show user's actual name, not email

                        Scene scene = new Scene(root);

                        URL cssUrl = getClass().getResource("/css/style.css");
                        if (cssUrl != null) {
                            scene.getStylesheets().add(cssUrl.toExternalForm());
                        }

                        Stage stage = (Stage) loginButton.getScene().getWindow();
                        stage.setScene(scene);
                        stage.setTitle("FinTrack Dashboard");
                        stage.show();

                    } catch (IOException e) {
                        e.printStackTrace();
                        showAlert(Alert.AlertType.ERROR, "Navigation Error", "Unable to load Dashboard.");
                    }
                });

            } catch (IOException e) {
                Platform.runLater(() -> showAlert(Alert.AlertType.ERROR, "Login Failed", "Error: " + e.getMessage()));
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
