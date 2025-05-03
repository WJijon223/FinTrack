package com.fintrack;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.List;

public class TransactionsController {

    // sidebar controller reference
    @FXML
    private SidebarController sidebarController;

    // table and columns
    @FXML
    private TableView<Transaction> transactionTable;

    @FXML
    private TableColumn<Transaction, String> descriptionColumn;

    @FXML
    private TableColumn<Transaction, Double> amountColumn;

    @FXML
    private TableColumn<Transaction, String> categoryColumn;

    @FXML
    private TableColumn<Transaction, LocalDate> dateColumn;

    // form fields for adding transactions
    @FXML
    private TextField descriptionField;

    @FXML
    private TextField amountField;

    @FXML
    private ComboBox<String> categoryComboBox;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Button addButton;

    // sample data for visual
    private ObservableList<Transaction> transactions = FXCollections.observableArrayList();

    @FXML
    public void initialize() {


        // set up sidebar
        if (sidebarController != null) {
            sidebarController.setup("transactions");

            // set  up theme change callback


            sidebarController.setOnThemeChanged(() -> {

                // spply theme changes if needed
                updateTheme(sidebarController.isDarkMode());
            });
        }

        // configue table columns


        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        // set up category options
        categoryComboBox.getItems().addAll(
                "Food", "Transportation", "Housing", "Entertainment",
                "Utilities", "Healthcare", "Education", "Other"
        );

        // set default date to today
        datePicker.setValue(LocalDate.now());

        // load sample transactions (replace with actual data loading)
        loadTransactions();
    }

    private void loadTransactions() {
        // sample data - replace with actual data loading logic
        transactions.add(new Transaction("Grocery shopping", 45.67, "Food", LocalDate.now().minusDays(2)));
        transactions.add(new Transaction("Gas station", 35.50, "Transportation", LocalDate.now().minusDays(5)));
        transactions.add(new Transaction("Monthly rent", 1200.00, "Housing", LocalDate.now().minusDays(10)));
        transactions.add(new Transaction("Movie tickets", 24.99, "Entertainment", LocalDate.now().minusDays(3)));
        transactions.add(new Transaction("Electric bill", 78.35, "Utilities", LocalDate.now().minusDays(7)));

        // set the table data
        transactionTable.setItems(transactions);
    }

    @FXML
    private void handleAddTransaction() {
        try {



            String description = descriptionField.getText().trim();
            if (description.isEmpty()) {
                showAlert("Error", "Please enter a description");
                return;
            }

            Double amount;
            try {
                amount = Double.parseDouble(amountField.getText().trim());
            } catch (NumberFormatException e) {
                showAlert("Error", "Please enter a valid amount");
                return;
            }

            String category = categoryComboBox.getValue();
            if (category == null || category.isEmpty()) {
                showAlert("Error", "Please select a category");
                return;
            }

            LocalDate date = datePicker.getValue();
            if (date == null) {
                showAlert("Error", "Please select a date");
                return;
            }

            // create and add transaction
            Transaction newTransaction = new Transaction(description, amount, category, date);
            transactions.add(newTransaction);

            // clear  fields
            clearForm();

        } catch (Exception e) {
            showAlert("Error", "An error occurred: " + e.getMessage());
        }
    }

    @FXML
    private void handleDeleteTransaction() {
        Transaction selectedTransaction = transactionTable.getSelectionModel().getSelectedItem();
        if (selectedTransaction != null) {
            transactions.remove(selectedTransaction);
        }

        else {
            showAlert("Error", "Please select a transaction to delete");
        }
    }

    private void clearForm() {
        descriptionField.clear();
        amountField.clear();
        categoryComboBox.setValue(null);
        datePicker.setValue(LocalDate.now());
    }

    private void showAlert(String title, String message) {


        System.out.println(title + ": " + message);
        // Replace with actual alert dialog implementation
    }

    private void updateTheme(boolean isDarkMode) {

        if (isDarkMode) {
            //implement later
        } else {

        }
    }

    // transaction class
    public static class Transaction {
        private String description;
        private double amount;
        private String category;
        private LocalDate date;

        public Transaction(String description, double amount, String category, LocalDate date) {
            this.description = description;
            this.amount = amount;
            this.category = category;
            this.date = date;
        }

        // getters and setters
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }

        public double getAmount() { return amount; }
        public void setAmount(double amount) { this.amount = amount; }

        public String getCategory() { return category; }
        public void setCategory(String category) { this.category = category; }

        public LocalDate getDate() { return date; }
        public void setDate(LocalDate date) { this.date = date; }
    }
}