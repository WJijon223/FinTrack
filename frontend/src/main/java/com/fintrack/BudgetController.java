package com.fintrack;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

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
        System.out.println("Food budget button clicked!");
    }

    @FXML
    private void handleHousingBudget(ActionEvent event) {
        System.out.println("Housing budget button clicked!");
    }

    @FXML
    private void handleEntertainmentBudget(ActionEvent event) {
        System.out.println("Entertainment budget button clicked!");
    }

    @FXML
    private void handleTransportationBudget(ActionEvent event) {
        System.out.println("Transportation budget button clicked!");
    }

    @FXML
    private void handleHealthcareBudget(ActionEvent event) {
        System.out.println("Healthcare budget button clicked!");
    }

    @FXML
    private void handleUtilitiesBudget(ActionEvent event) {
        System.out.println("Utilities budget button clicked!");
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
