package com.fintrack;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneNavigator {

    //  dimensions if needed
    private static final int WINDOW_WIDTH = 1000;
    private static final int WINDOW_HEIGHT = 700;

    // default theme is light theme
    private static final String LIGHT_CSS_PATH = "/css/style.css";

    // dark theme path
    private static final String DARK_CSS_PATH = "/css/dark.css";

    // track current theme state
    private static boolean isDarkMode = false;

    public static void setDarkMode(boolean darkMode) {
        isDarkMode = darkMode;
    }

    public static boolean isDarkMode() {
        return isDarkMode;
    }

    public static void navigateTo(Node sourceNode, String fxmlPath) throws IOException {
        Stage stage = (Stage) sourceNode.getScene().getWindow();

        // save current fullscreen state before navigation
        boolean wasFullScreen = stage.isFullScreen();

        // detect current theme if not explicitly set
        if (sourceNode.getScene() != null && sourceNode.getScene().getStylesheets().size() > 0) {
            String currentStylesheet = sourceNode.getScene().getStylesheets().get(0);
            if (currentStylesheet.contains("dark.css")) {
                isDarkMode = true;
            }
        }

        Parent root = FXMLLoader.load(SceneNavigator.class.getResource(fxmlPath));

        // get current scene dimensions or use defaults if creating a new scene
        double sceneWidth = sourceNode.getScene() != null ?
                sourceNode.getScene().getWidth() : WINDOW_WIDTH;
        double sceneHeight = sourceNode.getScene() != null ?
                sourceNode.getScene().getHeight() : WINDOW_HEIGHT;

        Scene scene = new Scene(root, sceneWidth, sceneHeight);

        // apply appropriate CSS based on theme state
        String cssPath = isDarkMode ? DARK_CSS_PATH : LIGHT_CSS_PATH;
        var cssURL = SceneNavigator.class.getResource(cssPath);
        if (cssURL != null) {
            scene.getStylesheets().add(cssURL.toExternalForm());
        } else {
            System.err.println("⚠️ " + cssPath + " not found! Skipping stylesheet.");
        }

        stage.setScene(scene);

        // restore fullscreen state
        stage.setFullScreen(wasFullScreen);
    }
}