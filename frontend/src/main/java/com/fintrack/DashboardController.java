package com.fintrack;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class DashboardController {

    @FXML
    private Label welcomeLabel;

    @FXML
    private PieChart pieChart;
    @FXML
    private Button homeButton;


    @FXML
    private Button themeToggleBtn;

    private boolean isDarkMode = false;

    @FXML
    public void initialize() {
        welcomeLabel.setText("Welcome back, Dieunie!");

        pieChart.getData().addAll(
                new PieChart.Data("Rent", 900),
                new PieChart.Data("Food", 500),
                new PieChart.Data("Shopping", 350)
        );
    }

    @FXML
    private void toggleTheme() {
        Scene scene = themeToggleBtn.getScene();
        Parent root = scene.getRoot();

        if (isDarkMode) {
            root.getStyleClass().remove("dark");
            root.getStyleClass().add("light");
        } else {
            root.getStyleClass().remove("light");
            root.getStyleClass().add("dark");
        }

        isDarkMode = !isDarkMode;
    }

    @FXML
    private void handleLogout() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Login.fxml"));
            Stage stage = (Stage) welcomeLabel.getScene().getWindow();
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
    @FXML
    private void openTransactions() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Transactions.fxml"));
            Stage stage = (Stage) homeButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("FinTrack - Transactions");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Could not load Transactions screen.");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

}