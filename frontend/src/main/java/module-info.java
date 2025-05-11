module com.fintrack.frontend {
    requires javafx.controls;
    requires javafx.fxml;
    requires itextpdf;


    opens com.fintrack to javafx.fxml;
    exports com.fintrack;
}