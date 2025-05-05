package com.fintrack.service;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

import java.time.format.DateTimeFormatter;
import java.util.Optional;


//dialog for displaying financial report

public class Report_Dialog {

    private final Report report;
    private final boolean isDarkMode;
    private Alert alert;

    // creates a new report dialog
    public Report_Dialog(Report report, boolean isDarkMode) {
        this.report = report;
        this.isDarkMode = isDarkMode;
        this.createDialog();
    }

    //create the dialog with report content

    private void createDialog() {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(report.getTitle());
        alert.setHeaderText("Report generated on " +
                report.getGeneratedOn().format(DateTimeFormatter.ofPattern("MMMM d, yyyy")));

        //  content area
        GridPane contentPane = new GridPane();
        contentPane.setHgap(10);
        contentPane.setVgap(10);

        //  report summary
        Label summaryLabel = new Label("Summary:");
        summaryLabel.setStyle("-fx-font-weight: bold;");
        TextArea summaryArea = new TextArea(report.getSummary());
        summaryArea.setEditable(false);
        summaryArea.setWrapText(true);
        summaryArea.setPrefRowCount(4);

        GridPane.setHgrow(summaryArea, Priority.ALWAYS);
        contentPane.add(summaryLabel, 0, 0);
        contentPane.add(summaryArea, 0, 1);

        //  details if available
        if (report.getDetails() != null && !report.getDetails().isEmpty()) {
            Label detailsLabel = new Label("Details:");
            detailsLabel.setStyle("-fx-font-weight: bold;");
            TextArea detailsArea = new TextArea(report.getDetails());
            detailsArea.setEditable(false);
            detailsArea.setWrapText(true);
            detailsArea.setPrefRowCount(12);

            GridPane.setHgrow(detailsArea, Priority.ALWAYS);
            contentPane.add(detailsLabel, 0, 2);
            contentPane.add(detailsArea, 0, 3);
        }

        // error message if available
        if (report.getErrorMessage() != null && !report.getErrorMessage().isEmpty()) {
            Label errorLabel = new Label("Error:");
            errorLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #d32f2f;");
            TextArea errorArea = new TextArea(report.getErrorMessage());
            errorArea.setEditable(false);
            errorArea.setWrapText(true);
            errorArea.setStyle("-fx-text-fill: #d32f2f;");

            GridPane.setHgrow(errorArea, Priority.ALWAYS);
            contentPane.add(errorLabel, 0, 4);
            contentPane.add(errorArea, 0, 5);
        }

        alert.getDialogPane().setContent(contentPane);

        // set minimum width for dialog
        alert.getDialogPane().setMinWidth(500);
        alert.getDialogPane().setPrefWidth(600);

        // apply theme styling
        if (isDarkMode) {
            applyDarkTheme(alert.getDialogPane());
        }
    }

    //s how the report dialog when button pressed

    public Optional<ButtonType> show() {
        return alert.showAndWait();
    }

    // dark theme application
    private void applyDarkTheme(DialogPane dialogPane) {
        dialogPane.getStyleClass().add("dark-theme");

        // apply dark theme to dialog and components
        String darkThemeCSS =
                "-fx-background-color: #2d2d2d;" +
                        "-fx-text-fill: #e8e8e8;";

        dialogPane.setStyle(darkThemeCSS);

        // apply to header
        if (dialogPane.getHeader() != null) {
            dialogPane.getHeader().setStyle(darkThemeCSS + "-fx-font-weight: bold;");
        }

        // apply to text areas and labels
        for (javafx.scene.Node node : dialogPane.getChildren()) {
            if (node instanceof GridPane) {
                GridPane grid = (GridPane) node;
                for (javafx.scene.Node child : grid.getChildren()) {
                    if (child instanceof TextArea) {
                        TextArea textArea = (TextArea) child;
                        textArea.setStyle("-fx-control-inner-background: #3d3d3d; -fx-text-fill: #e8e8e8;");
                    } else if (child instanceof Label) {
                        Label label = (Label) child;
                        if (!label.getText().equals("Error:")) {
                            label.setStyle("-fx-text-fill: #e8e8e8; -fx-font-weight: bold;");
                        }
                    }
                }
            }
        }

        // set the icon to match dark theme
        if (dialogPane.getScene() != null && dialogPane.getScene().getWindow() instanceof Stage) {
            Stage stage = (Stage) dialogPane.getScene().getWindow();
            stage.setTitle(report.getTitle());
        }
    }
}