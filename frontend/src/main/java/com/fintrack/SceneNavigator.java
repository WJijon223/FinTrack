package com.fintrack;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneNavigator {

    // ✅ Fixed size to match SplashScreen.fxml
    private static final int WINDOW_WIDTH = 1000;
    private static final int WINDOW_HEIGHT = 700;

    public static void navigateTo(Node sourceNode, String fxmlPath) throws IOException {
        Parent root = FXMLLoader.load(SceneNavigator.class.getResource(fxmlPath));

        // ✅ Apply fixed size here
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

        // ✅ Load CSS
        String cssPath = "/css/style.css";
        var cssURL = SceneNavigator.class.getResource(cssPath);

        if (cssURL == null) {
            System.err.println("⚠️ " + cssPath + " not found! Skipping stylesheet.");
        } else {
            scene.getStylesheets().add(cssURL.toExternalForm());
        }

        // ✅ Switch screen
        Stage stage = (Stage) sourceNode.getScene().getWindow();
        stage.setScene(scene);
        stage.show(); // <-- Don't forget this!
    }
}
