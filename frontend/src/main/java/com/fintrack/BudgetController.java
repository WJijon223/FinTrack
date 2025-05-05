package com.fintrack;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import java.util.Optional;



public class BudgetController {



    @FXML
    private ImageView foodIcon;

    @FXML

    private ImageView housingIcon;

    @FXML
    private ImageView entertainmentIcon;

    @FXML
    private ImageView transportationIcon;

    @FXML
    private ImageView healthcareIcon;



    @FXML
    private ImageView utilitiesIcon;


    @FXML
    private void handleFoodBudget(ActionEvent event) {

        // Get the current budget amount from your data source
        double currentAmount = 500.0; // Replace with actual amount from your data source
        showBudgetAdjustmentDialog("Food", currentAmount);

    }

    @FXML
    private void handleHousingBudget(ActionEvent event) {

        double currentAmount = 1000.0; // Replace with actual amount from your data source
        showBudgetAdjustmentDialog("Housing", currentAmount);


    }

    @FXML
    private void handleEntertainmentBudget(ActionEvent event) {

        double currentAmount = 200.0; // Replace with actual amount from your data source
        showBudgetAdjustmentDialog("Entertainment", currentAmount);

    }

    @FXML
    private void handleTransportationBudget(ActionEvent event) {

        double currentAmount = 150.0; // Replace with actual amount from your data source
        showBudgetAdjustmentDialog("Transportation", currentAmount);

    }

    @FXML
    private void handleHealthcareBudget(ActionEvent event) {

        double currentAmount = 300.0; // Replace with actual amount from your data source
        showBudgetAdjustmentDialog("Healthcare", currentAmount);

    }

    @FXML
    private void handleUtilitiesBudget(ActionEvent event) {

        double currentAmount = 250.0; // Replace with actual amount from your data source
        showBudgetAdjustmentDialog("Utilities", currentAmount);

    }

    @FXML
    private void handleDashboard(ActionEvent event) {


        System.out.println("Dashboard clicked!");
    }

    @FXML
    private void handleBudget(ActionEvent event) {


        System.out.println("Budget clicked!");
    }

    @FXML
    private void handleReport(ActionEvent event) {


        System.out.println("Report clicked!");
    }

    private void showBudgetAdjustmentDialog(String budgetType, double currentAmount) {


        Dialog<Double> dialog = new Dialog<>();
        dialog.setTitle("Adjust Budget");
        dialog.setHeaderText("Modify " + budgetType + " Budget");


        ButtonType saveButtonType = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);


        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));


        TextField amountField = new TextField(String.valueOf(currentAmount));
        amountField.setPromptText("Amount");


        grid.add(new Label("Budget Amount:"), 0, 0);
        grid.add(amountField, 1, 0);

        dialog.getDialogPane().getStylesheets().add(
                getClass().getResource("/css/dark.css").toExternalForm());

        dialog.getDialogPane().setContent(grid);


        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                try {
                    return Double.parseDouble(amountField.getText());
                } catch (NumberFormatException e) {
                    // Handle invalid input
                    return null;
                }
            }
            return null;
        });

        Optional<Double> result = dialog.showAndWait();
        result.ifPresent(newAmount -> {
            // Save the updated budget to your data store
            saveBudget(budgetType, newAmount);
        });
    }


    private void saveBudget(String budgetType, double amount) {
        // TODO: Replace this with your actual save logic
        System.out.println("Saving " + budgetType + " budget: $" + amount);


        // Here you would update your database or storage with the new amount
        // For example:
        // budgetRepository.updateBudget(budgetType, amount);
    }


    @FXML
    public void initialize() {


        housingIcon.setImage(new Image(getClass().getResource("/images/house_icon.png").toExternalForm()));
        foodIcon.setImage(new Image(getClass().getResource("/images/restaurant_icon.png").toExternalForm()));
        entertainmentIcon.setImage(new Image(getClass().getResource("/images/entertainment_icon.png").toExternalForm()));
        transportationIcon.setImage(new Image(getClass().getResource("/images/transportation_icon.png").toExternalForm()));
        utilitiesIcon.setImage(new Image(getClass().getResource("/images/utilities_icon.png").toExternalForm()));
        healthcareIcon.setImage(new Image(getClass().getResource("/images/healthcare_icon.png").toExternalForm()));
    }




}
