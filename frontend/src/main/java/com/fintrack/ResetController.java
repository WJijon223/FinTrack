package com.fintrack;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;

public class ResetController {

    @FXML
    private Hyperlink backToLoginLink;

    @FXML
    private PasswordField confirmPass;

    @FXML
    private PasswordField newPass;

    @FXML
    private VBox glassPane;

    @FXML
    private Button saveButton;

    @FXML
    private Label strengthLabel;

    @FXML
    public void initialize() {
        newPass.setOnKeyReleased(event -> checkStrength());
    }

    private void checkStrength() {
        String password = newPass.getText();

        if (password.length() < 6) {
            strengthLabel.setText("Strength: Weak");
            strengthLabel.setStyle("-fx-text-fill: red;");
        } else if (password.matches(".*[A-Z].*") && password.matches(".*[0-9].*")) {
            strengthLabel.setText("Strength: Strong");
            strengthLabel.setStyle("-fx-text-fill: limegreen;");
        } else {
            strengthLabel.setText("Strength: Medium");
            strengthLabel.setStyle("-fx-text-fill: orange;");
        }
    }

    @FXML
    private void handleSave() {
        String pass1 = newPass.getText();
        String pass2 = confirmPass.getText();

        if (pass1.isEmpty() || pass2.isEmpty()) {
            showAlert("Error", "Please fill out both fields.");
        } else if (!pass1.equals(pass2)) {
            showAlert("Mismatch", "Passwords do not match.");
        } else {
            showAlert("Success", "Your password has been reset!");
            goToLogin();
        }
    }

    @FXML
    private void handleBackToLogin() {
        goToLogin();
    }

    private void goToLogin() {
        try {
            // ✅ Fixed the path
            Parent root = FXMLLoader.load(getClass().getResource("/Login.fxml"));
            Stage stage = (Stage) backToLoginLink.getScene().getWindow();
            stage.setScene(new Scene(root));
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
