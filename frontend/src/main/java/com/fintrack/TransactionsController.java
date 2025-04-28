package com.fintrack;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
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
    private void setExpenseType() {
        selectedType = "Expense";
    }

    @FXML
    private void setRevenueType() {
        selectedType = "Revenue";
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

        if (isDarkMode) {
            root.getStyleClass().remove("dark");
            root.getStyleClass().add("light");
        } else {
            root.getStyleClass().remove("light");
            root.getStyleClass().add("dark");
        }

        isDarkMode = !isDarkMode;
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
