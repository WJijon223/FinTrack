package com.fintrack;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneNavigator {



    public static void navigateTo(Node sourceNode, String fxmlPath) throws IOException {
        Parent root = FXMLLoader.load(SceneNavigator.class.getResource(fxmlPath));
        Scene scene = new Scene(root);


        String cssPath = "/css/style.css";
        var cssURL = SceneNavigator.class.getResource(cssPath);

        if (cssURL == null) {
            System.err.println("⚠️ " + cssPath + " not found! Skipping stylesheet.");
        }


        else {
            scene.getStylesheets().add(cssURL.toExternalForm());
        }

        Stage stage = (Stage) sourceNode.getScene().getWindow();
        stage.setScene(scene);
    }
}