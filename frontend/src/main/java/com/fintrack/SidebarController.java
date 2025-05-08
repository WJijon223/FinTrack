package com.fintrack;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Parent;
import java.io.IOException;
import java.util.List;
import java.util.Arrays;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class SidebarController {

    @FXML private Button homeButton;
    @FXML private Button budgetButton;
    @FXML private Button analyticsButton;
    @FXML private Button transactionsButton;


    @FXML private Button themeToggleBtn;
    @FXML private Label themeIcon;

    private boolean isDarkMode = false;
    private List<Button> navButtons;

    @FXML
    private void initialize() {


        //  initialiaze the list of navigation buttons
        navButtons = Arrays.asList(
                homeButton, budgetButton, analyticsButton,
                transactionsButton
        );


        setActiveButton(homeButton);
    }

    public void setup(String currentScreen) {
        setActiveButton(currentScreen);
    }

    public void setActiveButton(String screenName) {
        switch (screenName.toLowerCase()) {

            case "home":
            case "dashboard":
                setActiveButton(homeButton);
                break;
            case "budget":
                setActiveButton(budgetButton);
                break;

            case "analytics":
            case "graph":
                setActiveButton(analyticsButton);
                break;
            case "transactions":
                setActiveButton(transactionsButton);
                break;

        }
    }

    @FXML
    private void handleHome() {
        setActiveButton(homeButton);
        try {
            SceneNavigator.navigateTo(homeButton, "/fxml/Dashboard.fxml");
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBudget() {
        setActiveButton(budgetButton);
        try {
            SceneNavigator.navigateTo(budgetButton, "/fxml/Budget.fxml");
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAnalytics() {
        setActiveButton(analyticsButton);
        try {
            SceneNavigator.navigateTo(analyticsButton, "/fxml/Graph.fxml");
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleTransactions() {
        setActiveButton(transactionsButton);
        try {
            SceneNavigator.navigateTo(transactionsButton, "/fxml/Transactions.fxml");
        }


        catch (IOException e) {
            e.printStackTrace();
        }
    }





    @FXML
    private ImageView themeIconImage;

    private Runnable onThemeChanged;

    public void setOnThemeChanged(Runnable callback) {

        this.onThemeChanged = callback;
    }



    @FXML
    private void toggleTheme() {
        isDarkMode = !isDarkMode;

        // update SceneNavigator's dark mode state to persist theme across page navigations
        SceneNavigator.setDarkMode(isDarkMode);

        if (isDarkMode) {
            // switch to dark theme
            themeToggleBtn.getScene().getStylesheets().clear(); // clear existing
            themeToggleBtn.getScene().getStylesheets().add(
                    getClass().getResource("/css/dark.css").toExternalForm()
            );

            // show light mode icon in dark mode
            themeIconImage.setImage(new Image(getClass().getResourceAsStream("/images/sidebar_lightmode.png")));
        }
        else {
            themeToggleBtn.getScene().getStylesheets().clear();
            themeToggleBtn.getScene().getStylesheets().add(
                    getClass().getResource("/css/style.css").toExternalForm()
            );

            // show dark mode icon light mode
            themeIconImage.setImage(new Image(getClass().getResourceAsStream("/images/sidebar_darkmode.png")));

            //notify parent controller
            if (onThemeChanged != null) {
                onThemeChanged.run();
            }
        }
    }


    private void setActiveButton(Button activeButton) {

        for (Button button : navButtons) {
            button.getStyleClass().remove("nav-button-active");
        }


        activeButton.getStyleClass().add("nav-button-active");
    }

    public boolean isDarkMode() {
        return isDarkMode;
    }


}