package com.fintrack;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextArea;

public class GraphController {

    @FXML
    private LineChart<String, Number> lineChart;

    @FXML
    private TextArea summaryText;

    @FXML
    public void initialize() {
        XYChart.Series<String, Number> rent = new XYChart.Series<>();
        rent.setName("Rent");
        rent.getData().add(new XYChart.Data<>("Jan", 750));
        rent.getData().add(new XYChart.Data<>("Feb", 750));
        rent.getData().add(new XYChart.Data<>("Mar", 750));

        XYChart.Series<String, Number> groceries = new XYChart.Series<>();
        groceries.setName("Restaurants & Groceries");
        groceries.getData().add(new XYChart.Data<>("Jan", 500));
        groceries.getData().add(new XYChart.Data<>("Feb", 450));
        groceries.getData().add(new XYChart.Data<>("Mar", 570));

        lineChart.getData().addAll(rent, groceries);
    }

    @FXML
    private void handleGenerateReport() {
        summaryText.setText("Report generated: Rent and groceries stayed fairly consistent with a slight uptick in March.");
    }
}
