package com.fintrack;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Login.fxml"));
            Stage stage = (Stage) backToLoginLink.getScene().getWindow();
            Scene scene = new Scene(root);

            URL cssUrl = getClass().getResource("/style.css");
            if (cssUrl != null) {
                scene.getStylesheets().add(cssUrl.toExternalForm());
            }

            stage.setScene(scene);
            stage.setTitle("FinTrack Login");
            stage.show();
        } catch (IOException e) {
            System.err.println("Failed to load Login.fxml");
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