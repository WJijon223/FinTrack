package com.fintrack;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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




        // dummy data
        XYChart.Series<Number, Number> incomeSeries = new XYChart.Series<>();
        incomeSeries.setName("Income");

        XYChart.Series<Number, Number> expensesSeries = new XYChart.Series<>();
        expensesSeries.setName("Expenses");




        // fake monthly data


        for (int month = 1; month <= 12; month++) {
            incomeSeries.getData().add(new XYChart.Data<>(month, 3000 + Math.random() * 1000));
            expensesSeries.getData().add(new XYChart.Data<>(month, 2000 + Math.random() * 800));
        }



        lineChart.getData().addAll(incomeSeries, expensesSeries);
    }

    private void setupButton() {
        generateReportButton.setOnAction(event -> {


            System.out.println(" Generate Report button clicked!");
            // later: generate actual report here
        });
    }
}
