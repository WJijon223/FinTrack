package com.fintrack;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class SidebarController {

    @FXML
    private void handleDashboard(ActionEvent event) {

        System.out.println("Sidebar: Dashboard button clicked!");
    }

    @FXML
    private void handleBudget(ActionEvent event) {

        System.out.println("Sidebar: Budget button clicked!");
    }

    @FXML
    private void handleReport(ActionEvent event) {

        System.out.println("Sidebar: Report button clicked!");
    }
}
