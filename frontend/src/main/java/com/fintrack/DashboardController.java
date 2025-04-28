package com.fintrack;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardController {

    @FXML
    private Label welcomeLabel;

    @FXML
    private PieChart pieChart;

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
    private Button themeToggleBtn;

    private boolean isDarkMode = false;

    @FXML
    private void toggleTheme() {
        Scene scene = themeToggleBtn.getScene();
        Parent root = scene.getRoot();

        // Clear previous theme style class
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
            // ✅ Correct path (Login.fxml is in /resources/)
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
            Stage stage = (Stage) welcomeLabel.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show(); // ✅ Show the stage after setting scene
        } catch (IOException e) {
            System.err.println("Failed to load Login.fxml");
            e.printStackTrace();
        }
    }
}
