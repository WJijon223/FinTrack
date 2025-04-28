package com.fintrack;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Transaction {
    private final StringProperty amount;
    private final StringProperty type;
    private final StringProperty category;
    private final StringProperty description;
    private final StringProperty date;

    public Transaction(String amount, String type, String category, String description, String date) {
        this.amount = new SimpleStringProperty(amount);
        this.type = new SimpleStringProperty(type);
        this.category = new SimpleStringProperty(category);
        this.description = new SimpleStringProperty(description);
        this.date = new SimpleStringProperty(date);
    }

    public StringProperty amountProperty() { return amount; }
    public StringProperty typeProperty() { return type; }
    public StringProperty categoryProperty() { return category; }
    public StringProperty descriptionProperty() { return description; }
    public StringProperty dateProperty() { return date; }
}
