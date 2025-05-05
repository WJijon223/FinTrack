package com.fintrack;

import com.fintrack.service.Report;
import com.fintrack.service.ReportService;
import com.fintrack.service.Report_Dialog;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import java.text.NumberFormat;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;
import java.util.Map;


public class GraphController {

    @FXML
    private PieChart categoryPieChart;

    @FXML
    private LineChart<String, Number> expenseTrendChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private Label totalExpensesLabel;

    @FXML
    private Label averageExpenseLabel;

    @FXML
    private Label largestExpenseLabel;

    @FXML
    private ComboBox<String> monthSelector;

    @FXML
    private ComboBox<Integer> yearSelector;

    @FXML
    private Button generateReportBtn;

    private Month currentMonth;
    private int currentYear;

    private Random random = new Random();

    // For animations
    private final Duration ANIMATION_DURATION = Duration.millis(800);

    private StackPane loadingOverlay;
    private ProgressIndicator progressIndicator;

    private final ReportService reportService = new ReportService();
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private boolean isDarkMode = false; // Set this based on your app's theme

    @FXML
    public void initialize() {
        // Initialize months dropdown
        monthSelector.setItems(FXCollections.observableArrayList(
                Arrays.stream(Month.values())
                        .map(this::formatMonth)
                        .collect(Collectors.toList())
        ));

        // Initialize years dropdown (e.g., last 5 years)
        int currentYear = LocalDate.now().getYear();
        yearSelector.setItems(FXCollections.observableArrayList(
                IntStream.rangeClosed(currentYear - 4, currentYear)
                        .boxed()
                        .collect(Collectors.toList())
        ));

        // Set current month and year as default
        this.currentMonth = LocalDate.now().getMonth();
        this.currentYear = LocalDate.now().getYear();
        monthSelector.getSelectionModel().select(formatMonth(this.currentMonth));
        yearSelector.getSelectionModel().select(Integer.valueOf(this.currentYear));

        // Initial data load
        loadMonthData();

        // Don't try to access the scene here - it's not ready yet
        // This will be set when updateTheme is called by the main controller
    }

    @FXML
    private void handleMonthChange() {
        String selectedMonth = monthSelector.getValue();
        if (selectedMonth == null) return;

        // Create a mapping of three-letter abbreviations to Month enum values
        Map<String, Month> monthMap = Map.ofEntries(
                Map.entry("JAN", Month.JANUARY),
                Map.entry("FEB", Month.FEBRUARY),
                Map.entry("MAR", Month.MARCH),
                Map.entry("APR", Month.APRIL),
                Map.entry("MAY", Month.MAY),
                Map.entry("JUN", Month.JUNE),
                Map.entry("JUL", Month.JULY),
                Map.entry("AUG", Month.AUGUST),
                Map.entry("SEP", Month.SEPTEMBER),
                Map.entry("OCT", Month.OCTOBER),
                Map.entry("NOV", Month.NOVEMBER),
                Map.entry("DEC", Month.DECEMBER)
        );


        String abbr = selectedMonth.toUpperCase(Locale.ENGLISH).substring(0, 3);
        Month month = monthMap.get(abbr);

        if (month != null && month != currentMonth) {
            currentMonth = month;
            animateDataChange();
        }
    }

    @FXML
    private void handleYearChange() {
        Integer year = yearSelector.getValue();
        if (year != currentYear) {
            currentYear = year;
            animateDataChange();
        }
    }

    @FXML
    private void handleGenerateReport() {
        // Show loading overlay
        if (loadingOverlay == null) {
            createLoadingOverlay();
        }

        loadingOverlay.setVisible(true);
        generateReportBtn.setDisable(true);

        // Use the controller's tracked month and year
        reportService.generateReport(currentYear, currentMonth)
                .thenAcceptAsync(report -> {
                    // When complete, hide loading and show report dialog
                    javafx.application.Platform.runLater(() -> {
                        loadingOverlay.setVisible(false);
                        generateReportBtn.setDisable(false);

                        // Create and show report dialog - adjust constructor params if needed
                        try {
                            Report_Dialog dialog = new Report_Dialog(report, isDarkMode);
                            dialog.show();
                        } catch (Exception e) {
                            System.err.println("Error showing report dialog: " + e.getMessage());
                            e.printStackTrace();
                        }
                    });
                })
                .exceptionally(e -> {
                    // Handle errors
                    javafx.application.Platform.runLater(() -> {
                        loadingOverlay.setVisible(false);
                        generateReportBtn.setDisable(false);

                        // Create error report
                        Report errorReport = new Report();
                        errorReport.setTitle("Error Generating Report");
                        errorReport.setGeneratedOn(LocalDate.now());
                        errorReport.setSummary("There was an error generating your report");
                        errorReport.setErrorMessage(e.getMessage());

                        try {
                            Report_Dialog dialog = new Report_Dialog(errorReport, isDarkMode);
                            dialog.show();
                        } catch (Exception ex) {
                            System.err.println("Error showing error report dialog: " + ex.getMessage());
                            ex.printStackTrace();
                        }
                    });
                    return null;
                });
    }

    private void createLoadingOverlay() {
        try {
            // Make sure the scene is available
            if (generateReportBtn.getScene() == null) {
                System.err.println("Scene is not available yet");
                return;
            }

            // Get the root BorderPane from the scene graph
            BorderPane rootPane = (BorderPane) generateReportBtn.getScene().getRoot();

            loadingOverlay = new StackPane();
            loadingOverlay.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");
            loadingOverlay.setPrefSize(rootPane.getWidth(), rootPane.getHeight());
            loadingOverlay.setVisible(false);

            progressIndicator = new ProgressIndicator();
            progressIndicator.setMaxSize(100, 100);
            loadingOverlay.getChildren().add(progressIndicator);

            // Add to your scene graph
            rootPane.getChildren().add(loadingOverlay);

            // Make sure it covers the entire area
            loadingOverlay.layoutXProperty().bind(rootPane.layoutXProperty());
            loadingOverlay.layoutYProperty().bind(rootPane.layoutYProperty());
            loadingOverlay.prefWidthProperty().bind(rootPane.widthProperty());
            loadingOverlay.prefHeightProperty().bind(rootPane.heightProperty());
        } catch (Exception e) {
            System.err.println("Error creating loading overlay: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void animateDataChange() {
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(
                new KeyFrame(ANIMATION_DURATION,
                        event -> loadMonthData()
                )
        );
        timeline.play();
    }

    private void loadMonthData() {
        // Get the start and end dates for the selected month
        YearMonth yearMonth = YearMonth.of(currentYear, currentMonth);
        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();

        // Update UI components with the new data
        updateCategoryPieChart(startDate, endDate);
        updateExpenseTrendChart(startDate, endDate);
        updateStatistics(startDate, endDate);
    }

    private String formatMonth(Month month) {
        return month.getDisplayName(TextStyle.FULL, Locale.getDefault());
    }

    private void updateCategoryPieChart(LocalDate startDate, LocalDate endDate) {
        // Sample data - replace with actual data from your data source
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Food", 25.0 + random.nextInt(50)),
                new PieChart.Data("Transportation", 20.0 + random.nextInt(40)),
                new PieChart.Data("Entertainment", 15.0 + random.nextInt(30)),
                new PieChart.Data("Housing", 30.0 + random.nextInt(60)),
                new PieChart.Data("Utilities", 10.0 + random.nextInt(20))
        );

        categoryPieChart.setData(pieChartData);
    }

    private void updateExpenseTrendChart(LocalDate startDate, LocalDate endDate) {
        // Clear existing data
        expenseTrendChart.getData().clear();

        // Create new series
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Daily Expenses");

        // Sample data - replace with actual data from your data source
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd");
        for (int i = 1; i <= endDate.getDayOfMonth(); i++) {
            LocalDate date = startDate.plusDays(i - 1);
            // Skip future dates
            if (date.isAfter(LocalDate.now())) {
                break;
            }
            double value = 10.0 + random.nextDouble() * 90.0;
            series.getData().add(new XYChart.Data<>(date.format(formatter), value));
        }

        // Add the series to the chart
        expenseTrendChart.getData().add(series);
    }

    private void updateStatistics(LocalDate startDate, LocalDate endDate) {
        // Sample total expenses - replace with actual calculation from your data
        double totalExpenses = 1200.0 + random.nextDouble() * 800.0;
        animateValueChange(totalExpensesLabel, totalExpenses);

        // Sample average daily expense
        int daysInMonth = endDate.getDayOfMonth();
        double averageDaily = totalExpenses / daysInMonth;
        animateValueChange(averageExpenseLabel, averageDaily);

        // Sample largest expense
        double largestExpense = 200.0 + random.nextDouble() * 100.0;
        animateValueChange(largestExpenseLabel, largestExpense);
    }

    private void animateValueChange(Label label, double newValue) {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();

        // Get the current value
        String currentText = label.getText().replaceAll("[^\\d.]", "");
        double startValue = 0.0;
        try {
            if (!currentText.isEmpty()) {
                startValue = Double.parseDouble(currentText);
            }
        } catch (NumberFormatException e) {
            // In case of parse error, just start from 0
            startValue = 0.0;
        }

        // Create a double property to animate
        DoubleProperty animatedValue = new SimpleDoubleProperty(startValue);

        // Add a listener to the property
        animatedValue.addListener((observable, oldValue, newVal) -> {
            label.setText(currencyFormat.format(newVal.doubleValue()));
        });

        // Create the animation
        Timeline timeline = new Timeline();

        // Create the keyframe that changes the property value
        KeyValue keyValue = new KeyValue(animatedValue, newValue, Interpolator.EASE_OUT);
        KeyFrame keyFrame = new KeyFrame(ANIMATION_DURATION, keyValue);

        // Add the keyframe to the timeline
        timeline.getKeyFrames().add(keyFrame);

        // Play the animation
        timeline.play();
    }

    public void updateTheme(boolean isDarkMode) {
        this.isDarkMode = isDarkMode;

        // Update charts and other UI elements for the new theme
        // Reload data to apply new styles
        loadMonthData();
    }

    public void cleanup() {
        if (executorService != null) {
            executorService.shutdown();
        }
    }
}