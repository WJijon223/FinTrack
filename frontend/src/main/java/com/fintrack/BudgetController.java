package com.fintrack;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class BudgetController {

    // Charts
    @FXML
    private PieChart expenseChart;
    @FXML
    private PieChart incomeChart;

    // Summaries
    @FXML
    private VBox expenseSummary;
    @FXML
    private VBox incomeSummary;

    // Add Category Inputs
    @FXML
    private TextField categoryNameInput;
    @FXML
    private TextField categoryAmountInput;
    @FXML
    private ComboBox<String> categoryTypeDropdown;
    @FXML
    private Button addCategoryButton;

    // Save/Cancel Buttons
    @FXML
    private Button cancelButton;
    @FXML
    private Button saveButton;

    // Data Lists
    private final ObservableList<PieChart.Data> expenseData = FXCollections.observableArrayList();
    private final ObservableList<PieChart.Data> incomeData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Setup initial categories
        setupInitialData();

        // Setup dropdown options
        categoryTypeDropdown.setItems(FXCollections.observableArrayList("Expense", "Income"));

        // Setup Add Category Button
        addCategoryButton.setOnAction(e -> handleAddCategory());
    }

    private void setupInitialData() {
        // Add default expenses
        expenseData.addAll(
                new PieChart.Data("Rent", 1200),
                new PieChart.Data("Food", 800),
                new PieChart.Data("Transportation", 500)
        );

        // Add default income
        incomeData.addAll(
                new PieChart.Data("Spent", 2500),
                new PieChart.Data("Saved", 1000)
        );

        expenseChart.setData(expenseData);
        incomeChart.setData(incomeData);

        updateSummaries();
    }

    private void handleAddCategory() {
        String name = categoryNameInput.getText();
        String amountText = categoryAmountInput.getText();
        String type = categoryTypeDropdown.getValue();

        if (name == null || name.isEmpty() || amountText == null || amountText.isEmpty() || type == null) {
            System.out.println("Please fill all fields correctly.");
            return;
        }

        double amount;
        try {
            amount = Double.parseDouble(amountText);
            if (amount <= 0) {
                System.out.println("Amount must be positive.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount format.");
            return;
        }

        if (type.equals("Expense")) {
            expenseData.add(new PieChart.Data(name, amount));
        } else if (type.equals("Income")) {
            incomeData.add(new PieChart.Data(name, amount));
        }

        // Refresh charts
        expenseChart.setData(expenseData);
        incomeChart.setData(incomeData);

        updateSummaries();

        // Clear fields
        categoryNameInput.clear();
        categoryAmountInput.clear();
        categoryTypeDropdown.setValue(null);
    }

    private void updateSummaries() {
        // Clear old summaries
        expenseSummary.getChildren().clear();
        incomeSummary.getChildren().clear();

        // Update Expense Summary
        for (PieChart.Data data : expenseData) {
            Label label = new Label(data.getName() + ": $" + String.format("%.2f", data.getPieValue()));
            label.setStyle("-fx-font-size: 14px; -fx-text-fill: #555;");
            expenseSummary.getChildren().add(label);
        }

        // Update Income Summary
        for (PieChart.Data data : incomeData) {
            Label label = new Label(data.getName() + ": $" + String.format("%.2f", data.getPieValue()));
            label.setStyle("-fx-font-size: 14px; -fx-text-fill: #555;");
            incomeSummary.getChildren().add(label);
        }
    }
}
