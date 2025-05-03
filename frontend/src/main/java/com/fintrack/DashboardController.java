package com.fintrack;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
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
    private PieChart pieChart;

    @FXML
    private Label expensesLabel;

    @FXML
    private Label savingsLabel;

    @FXML
    private Button homeButton;

    @FXML
    private Label revenueLabel;

    @FXML
    private Button themeToggleBtn;

    private boolean isDarkMode = false;

    @FXML
    private SidebarController sidebarController;


    @FXML
    public void initialize() {
        // set welcome message
        welcomeLabel.setText("Welcome back, Dieunie!");

        // example data for the pie chart
        PieChart.Data slice1 = new PieChart.Data("Housing", 25);
        PieChart.Data slice2 = new PieChart.Data("Food", 20);
        PieChart.Data slice3 = new PieChart.Data("Transport", 15);
        PieChart.Data slice4 = new PieChart.Data("Utilities", 10);
        PieChart.Data slice5 = new PieChart.Data("Entertainment", 30);

        // Add data to the pie chart
        pieChart.getData().addAll(slice1, slice2, slice3, slice4, slice5);

        // Format financial values with currency
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);

        if (expensesLabel != null) {
            expensesLabel.setText(currencyFormat.format(2348.50));
        }

        if (savingsLabel != null) {
            savingsLabel.setText(currencyFormat.format(5420.75));
        }

        if (revenueLabel != null) {
            revenueLabel.setText(currencyFormat.format(7769.25));
        }

        sidebarController.setup("home");

        isDarkMode = false;

        sidebarController.setup("home");

        // sync theme state

        sidebarController.setOnThemeChanged(() -> {
            this.isDarkMode = sidebarController.isDarkMode();
            // additional theme sync logic if needed
        });





    }

    @FXML
    private void toggleTheme() {
        isDarkMode = !isDarkMode;
        Parent root = welcomeLabel.getScene().getRoot();

        if (isDarkMode) {
            root.getStyleClass().remove("light");
            root.getStyleClass().add("dark");
            themeToggleBtn.setText("☀️"); // Sun emoji for light mode toggle
        }

        else {
            root.getStyleClass().remove("dark");
            root.getStyleClass().add("light");
            themeToggleBtn.setText("🌙"); // Moon emoji for dark mode toggle
        }
    }

    @FXML
    private void handleLogout() {
        try {
            // laod the login scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            // apply CSS
            URL cssUrl = getClass().getResource("/css/style.css");
            if (cssUrl != null) {
                scene.getStylesheets().add(cssUrl.toExternalForm());
            } else {
                System.err.println("⚠️ CSS file not found!");
            }

            // get the stage from the current scene
            Stage stage = (Stage) welcomeLabel.getScene().getWindow();

            // ensure the scene starts with the light theme
            root.getStyleClass().add("light");

            // add fade transition
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
        }

        catch (IOException e) {
            System.err.println("Failed to load Login.fxml");
            e.printStackTrace();
        }
    }

    @FXML
    private void navigateToHome() {
        // already on home, so no navigation needed
        showAlert("Home", "You're already on the Dashboard!");
    }

    @FXML
    private void openTransactions() {
        try {
            // load the transactions scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Transactions.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            // apply CSS
            URL cssUrl = getClass().getResource("/css/style.css");
            if (cssUrl != null) {
                scene.getStylesheets().add(cssUrl.toExternalForm());
            }

            else {
                System.err.println("⚠️ CSS file not found!");
            }

            // get the stage from  current scene
            Stage stage = (Stage) welcomeLabel.getScene().getWindow();

            // ensure the scene starts with  light theme
            root.getStyleClass().add("light");

            // add fade transition
            FadeTransition fadeOut = new FadeTransition(Duration.millis(200), welcomeLabel.getScene().getRoot());
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.setOnFinished(e -> {
                stage.setScene(scene);
                stage.setTitle("FinTrack Transactions");

                FadeTransition fadeIn = new FadeTransition(Duration.millis(200), root);
                fadeIn.setFromValue(0.0);
                fadeIn.setToValue(1.0);
                fadeIn.play();
                stage.show();
            });
            fadeOut.play();
        }

        catch (IOException e) {
            System.err.println("Failed to load Transactions.fxml");
            e.printStackTrace();
        }
    }

    @FXML
    private void navigateToReports() {
        showAlert("Coming Soon", "Reports feature is coming soon!");
    }

    @FXML
    private void navigateToCalendar() {
        showAlert("Coming Soon", "Calendar feature is coming soon!");
    }

    @FXML
    private void navigateToAnalytics() {
        showAlert("Coming Soon", "Analytics feature is coming soon!");
    }

    @FXML
    private void navigateToProfile() {
        showAlert("Coming Soon", "Profile feature is coming soon!");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
