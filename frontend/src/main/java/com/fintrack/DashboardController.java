package com.fintrack;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;

public class DashboardController {

    @FXML
    private Label welcomeLabel;

    @FXML
    private Label expensesLabel;

    @FXML
    private Label savingsLabel;

    @FXML
    private Label revenueLabel;

    @FXML
    private PieChart pieChart;

    @FXML
    private Button themeToggleBtn;
    @FXML

    private boolean isDarkMode = false;

    @FXML
    public void initialize() {
        welcomeLabel.setText("Welcome back, Dieunie!");

        // Format currency values
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
        expensesLabel.setText(currencyFormat.format(2750)); // $2,750
        savingsLabel.setText(currencyFormat.format(1200));  // $1,200
        revenueLabel.setText(currencyFormat.format(4500));  // $4,500

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

        // Add fade transition for smooth theme change
        FadeTransition fadeOut = new FadeTransition(Duration.millis(200), root);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.8);
        fadeOut.setOnFinished(event -> {
            if (isDarkMode) {
                root.getStyleClass().remove("dark");
                root.getStyleClass().add("light");
                themeToggleBtn.setText("🌙");
            } else {
                root.getStyleClass().remove("light");
                root.getStyleClass().add("dark");
                themeToggleBtn.setText("☀");
            }

            FadeTransition fadeIn = new FadeTransition(Duration.millis(200), root);
            fadeIn.setFromValue(0.8);
            fadeIn.setToValue(1.0);
            fadeIn.play();
        });
        fadeOut.play();

        isDarkMode = !isDarkMode;
    }

    @FXML
    private void handleLogout() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
            Stage stage = (Stage) welcomeLabel.getScene().getWindow();
            Scene scene = new Scene(root);

            URL cssUrl = getClass().getResource("/style.css");
            if (cssUrl != null) {
                scene.getStylesheets().add(cssUrl.toExternalForm());
            }

            // Ensure the scene starts with the light theme
            root.getStyleClass().add("light");

            // Add fade transition
            FadeTransition fadeOut = new FadeTransition(Duration.millis(200), welcomeLabel.getScene().getRoot());
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.setOnFinished(e -> {
                stage.setScene(scene);
                stage.setTitle("FinTrack Login");

                FadeTransition fadeIn = new FadeTransition(Duration.millis(200), root);
                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);
                fadeIn.play();
                stage.show();
            });
            fadeOut.play();
        } catch (IOException e) {
            System.err.println("Failed to load Login.fxml");
            e.printStackTrace();
        }
    }

    @FXML
    private void navigateToHome() {
        // Already on the Dashboard (Home) screen, no action needed
        System.out.println("Already on Home screen.");
    }

    @FXML
    private void navigateToReports() {
        navigateToScreen("/Reports.fxml", "FinTrack Reports");
    }

    @FXML
    private void navigateToCalendar() {
        navigateToScreen("/Calendar.fxml", "FinTrack Calendar");
    }

    @FXML
    private void navigateToAnalytics() {
        navigateToScreen("/Analytics.fxml", "FinTrack Analytics");
    }

    @FXML
    private void navigateToProfile() {
        navigateToScreen("/Profile.fxml", "FinTrack Profile");
    }

    private void navigateToScreen(String fxmlPath, String title) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
            Stage stage = (Stage) welcomeLabel.getScene().getWindow();
            Scene scene = new Scene(root);

            URL cssUrl = getClass().getResource("/style.css");
            if (cssUrl != null) {
                scene.getStylesheets().add(cssUrl.toExternalForm());
            }

            // Apply the current theme
            root.getStyleClass().add(isDarkMode ? "dark" : "light");

            // Add fade transition
            FadeTransition fadeOut = new FadeTransition(Duration.millis(200), welcomeLabel.getScene().getRoot());
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.setOnFinished(e -> {
                stage.setScene(scene);
                stage.setTitle(title);

                FadeTransition fadeIn = new FadeTransition(Duration.millis(200), root);
                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);
                fadeIn.play();
                stage.show();
            });
            fadeOut.play();
        } catch (IOException e) {
            System.err.println("Failed to load " + fxmlPath);
            e.printStackTrace();
        }
    }
}