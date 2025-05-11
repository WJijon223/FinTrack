package com.fintrack.service;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class Report_Dialog {

    private final Report report;
    private final boolean isDarkMode;
    private Alert alert;

    public Report_Dialog(Report report, boolean isDarkMode) {
        this.report = report;
        this.isDarkMode = isDarkMode;
        this.createDialog();
    }

    private void createDialog() {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(report.getTitle());
        alert.setHeaderText("Report generated on " +
                report.getGeneratedOn().format(DateTimeFormatter.ofPattern("MMMM d, yyyy")));

        GridPane contentPane = new GridPane();
        contentPane.setHgap(10);
        contentPane.setVgap(10);

        Label summaryLabel = new Label("Summary:");
        summaryLabel.setStyle("-fx-font-weight: bold;");
        TextArea summaryArea = new TextArea(report.getSummary());
        summaryArea.setEditable(false);
        summaryArea.setWrapText(true);
        summaryArea.setPrefRowCount(4);

        GridPane.setHgrow(summaryArea, Priority.ALWAYS);
        contentPane.add(summaryLabel, 0, 0);
        contentPane.add(summaryArea, 0, 1);

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
        alert.getDialogPane().setMinWidth(500);
        alert.getDialogPane().setPrefWidth(600);

        if (isDarkMode) {
            applyDarkTheme(alert.getDialogPane());
        }

        // Add Export to PDF button
        ButtonType exportButton = new ButtonType("Export to PDF", ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().add(exportButton);

        // Handle button click
        alert.setOnCloseRequest(event -> {
            if (alert.getResult() == exportButton) {
                exportToPDF();
            }
        });
    }

    public Optional<ButtonType> show() {
        return alert.showAndWait();
    }

    private void applyDarkTheme(DialogPane dialogPane) {
        dialogPane.getStyleClass().add("dark-theme");
        String darkThemeCSS =
                "-fx-background-color: #2d2d2d;" +
                        "-fx-text-fill: #e8e8e8;";
        dialogPane.setStyle(darkThemeCSS);

        if (dialogPane.getHeader() != null) {
            dialogPane.getHeader().setStyle(darkThemeCSS + "-fx-font-weight: bold;");
        }

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

        if (dialogPane.getScene() != null && dialogPane.getScene().getWindow() instanceof Stage) {
            Stage stage = (Stage) dialogPane.getScene().getWindow();
            stage.setTitle(report.getTitle());
        }
    }

    private void exportToPDF() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Report as PDF");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        java.io.File file = fileChooser.showSaveDialog(stage);

        if (file != null) {
            try (OutputStream outputStream = new FileOutputStream(file)) {
                Document document = new Document();
                PdfWriter.getInstance(document, outputStream);
                document.open();

                document.add(new Paragraph(report.getTitle()));
                document.add(new Paragraph("Generated on: " +
                        report.getGeneratedOn().format(DateTimeFormatter.ofPattern("MMMM d, yyyy"))));
                document.add(new Paragraph("\nSummary:\n" + report.getSummary()));

                if (report.getDetails() != null && !report.getDetails().isEmpty()) {
                    document.add(new Paragraph("\nDetails:\n" + report.getDetails()));
                }

                if (report.getErrorMessage() != null && !report.getErrorMessage().isEmpty()) {
                    document.add(new Paragraph("\nError:\n" + report.getErrorMessage()));
                }

                document.close();
            } catch (Exception e) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Export Error");
                errorAlert.setHeaderText("Failed to export report to PDF");
                errorAlert.setContentText(e.getMessage());
                errorAlert.showAndWait();
            }
        }
    }
}