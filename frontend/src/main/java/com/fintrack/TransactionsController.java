package com.fintrack;

import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;

public class TransactionsController {

    @FXML
    private Button homeButton, transactionsButton, expenseBtn, revenueBtn, themeToggleBtn;
    @FXML
    private TextField amountField, categoryField, descriptionField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TableView<Transaction> transactionTable;
    @FXML
    private TableColumn<Transaction, String> amountCol, typeCol, categoryCol, descriptionCol, dateCol;

    private String selectedType = "Expense"; // Default
    private boolean isDarkMode = false;
    private final ObservableList<Transaction> transactions = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        amountCol.setCellValueFactory(cellData -> cellData.getValue().amountProperty());
        typeCol.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        categoryCol.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
        descriptionCol.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        dateCol.setCellValueFactory(cellData -> cellData.getValue().dateProperty());

        transactionTable.setItems(transactions);
    }

    @FXML
    private void navigateToHome() {
        navigateToScreen("/Dashboard.fxml", "FinTrack Dashboard");
    }

    @FXML
    private void navigateToTransactions() {
        // Already on Transactions screen
        System.out.println("Already on Transactions screen.");
    }

    @FXML
    private void setExpenseType() {
        selectedType = "Expense";
        expenseBtn.setStyle("-fx-background-color: #FF5733; -fx-text-fill: white;");
        revenueBtn.setStyle("-fx-background-color: #4682B4; -fx-text-fill: white;");
    }

    @FXML
    private void setRevenueType() {
        selectedType = "Revenue";
        revenueBtn.setStyle("-fx-background-color: #FF5733; -fx-text-fill: white;");
        expenseBtn.setStyle("-fx-background-color: #4682B4; -fx-text-fill: white;");
    }

    @FXML
    private void handleSave() {
        String amount = amountField.getText();
        String category = categoryField.getText();
        String description = descriptionField.getText();
        String date = (datePicker.getValue() != null) ? datePicker.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")) : "";

        if (amount.isEmpty() || category.isEmpty() || description.isEmpty() || date.isEmpty()) {
            showAlert("Missing Fields", "Please fill out all fields before saving.");
            return;
        }

        Transaction transaction = new Transaction(amount, selectedType, category, description, date);
        transactions.add(transaction);

        clearForm();
    }

    @FXML
    private void handleCancel() {
        clearForm();
    }

    private void clearForm() {
        amountField.clear();
        categoryField.clear();
        descriptionField.clear();
        datePicker.setValue(null);
    }

    @FXML
    private void toggleTheme() {
        BorderPane root = (BorderPane) amountField.getScene().getRoot();

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

    private void navigateToScreen(String fxmlPath, String title) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
            Stage stage = (Stage) homeButton.getScene().getWindow();
            Scene scene = new Scene(root);

            URL cssUrl = getClass().getResource("/style.css");
            if (cssUrl != null) {
                scene.getStylesheets().add(cssUrl.toExternalForm());
            }

            root.getStyleClass().add(isDarkMode ? "dark" : "light");

            FadeTransition fadeOut = new FadeTransition(Duration.millis(200), homeButton.getScene().getRoot());
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

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}