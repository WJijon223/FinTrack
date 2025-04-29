package com.fintrack;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.StringConverter;

public class GraphController {

    @FXML
    private Label titleLabel;

    @FXML
    private LineChart<Number, Number> lineChart;

    @FXML
    private Button generateReportButton;

    @FXML
    private Label summaryLabel;

    @FXML
    private void initialize() {
        setupChart();
        setupButton();
    }

    private void setupChart() {
        // First, clear any existing data
        lineChart.getData().clear();

        // Get the x-axis and set a string converter to display month names
        NumberAxis xAxis = (NumberAxis) lineChart.getXAxis();

        // Set tick marks to ensure we hit each month number
        xAxis.setAutoRanging(false);
        xAxis.setLowerBound(1);
        xAxis.setUpperBound(12);
        xAxis.setTickUnit(1);

        // Create a converter to transform numbers into month names
        xAxis.setTickLabelFormatter(new StringConverter<Number>() {
            private final String[] months = {
                    "Jan", "Feb", "Mar", "Apr", "May", "Jun",
                    "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
            };

            @Override
            public String toString(Number value) {
                int month = value.intValue();
                if (month >= 1 && month <= 12) {
                    return months[month - 1];
                }
                return "";
            }

            @Override
            public Number fromString(String string) {
                for (int i = 0; i < months.length; i++) {
                    if (months[i].equals(string)) {
                        return i + 1;
                    }
                }
                return null;
            }
        });

        // Create Income series
        XYChart.Series<Number, Number> incomeSeries = new XYChart.Series<>();
        incomeSeries.setName("Income");

        // Add data points for income (using month numbers 1-12)
        incomeSeries.getData().add(new XYChart.Data<>(1, 3000)); // Jan
        incomeSeries.getData().add(new XYChart.Data<>(2, 3200)); // Feb
        incomeSeries.getData().add(new XYChart.Data<>(3, 3100)); // Mar
        incomeSeries.getData().add(new XYChart.Data<>(4, 3300)); // Apr
        incomeSeries.getData().add(new XYChart.Data<>(5, 3500)); // May
        incomeSeries.getData().add(new XYChart.Data<>(6, 3400)); // Jun
        incomeSeries.getData().add(new XYChart.Data<>(7, 3600)); // Jul
        incomeSeries.getData().add(new XYChart.Data<>(8, 3700)); // Aug
        incomeSeries.getData().add(new XYChart.Data<>(9, 3600)); // Sep
        incomeSeries.getData().add(new XYChart.Data<>(10, 3800)); // Oct
        incomeSeries.getData().add(new XYChart.Data<>(11, 3900)); // Nov
        incomeSeries.getData().add(new XYChart.Data<>(12, 4100)); // Dec

        // Create Expenses series
        XYChart.Series<Number, Number> expensesSeries = new XYChart.Series<>();
        expensesSeries.setName("Expenses");

        // Add data points for expenses (using month numbers 1-12)
        expensesSeries.getData().add(new XYChart.Data<>(1, 2500)); // Jan
        expensesSeries.getData().add(new XYChart.Data<>(2, 2600)); // Feb
        expensesSeries.getData().add(new XYChart.Data<>(3, 2700)); // Mar
        expensesSeries.getData().add(new XYChart.Data<>(4, 2800)); // Apr
        expensesSeries.getData().add(new XYChart.Data<>(5, 2750)); // May
        expensesSeries.getData().add(new XYChart.Data<>(6, 2900)); // Jun
        expensesSeries.getData().add(new XYChart.Data<>(7, 3000)); // Jul
        expensesSeries.getData().add(new XYChart.Data<>(8, 3100)); // Aug
        expensesSeries.getData().add(new XYChart.Data<>(9, 3000)); // Sep
        expensesSeries.getData().add(new XYChart.Data<>(10, 3200)); // Oct
        expensesSeries.getData().add(new XYChart.Data<>(11, 3300)); // Nov
        expensesSeries.getData().add(new XYChart.Data<>(12, 3500)); // Dec

        // Add both series to the chart
        lineChart.getData().add(incomeSeries);
        lineChart.getData().add(expensesSeries);

        // Make sure the lines are visible
        lineChart.setCreateSymbols(true);
        lineChart.setAnimated(false); // Sometimes animations can cause display issues
    }

    private void setupButton() {
        generateReportButton.setOnAction(event -> {
            // Report generation logic
            double totalIncome = 42200; // Calculate this based on your actual data
            double totalExpenses = 35350; // Calculate this based on your actual data
            double netSavings = totalIncome - totalExpenses;

            summaryLabel.setText(String.format(
                    "Year to Date Summary: Total Income: $%.2f, Total Expenses: $%.2f, Net Savings: $%.2f",
                    totalIncome, totalExpenses, netSavings));
        });
    }
}