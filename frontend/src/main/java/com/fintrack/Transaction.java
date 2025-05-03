package com.fintrack;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Transaction {
    private final StringProperty amount = new SimpleStringProperty(this, "amount", "");
    private final StringProperty type = new SimpleStringProperty(this, "type", "");
    private final StringProperty category = new SimpleStringProperty(this, "category", "");
    private final StringProperty description = new SimpleStringProperty(this, "description", "");
    private final StringProperty date = new SimpleStringProperty(this, "date", "");

    // constructor
    public Transaction(String amount, String type, String category, String description, String date) {
        setAmount(amount);
        setType(type);
        setCategory(category);
        setDescription(description);
        setDate(date);
    }

    // amount property methods
    public StringProperty amountProperty() {
        return amount;
    }

    public String getAmount() {
        return amount.get();
    }

    public void setAmount(String value) {
        amount.set(value);
    }

    // type property methods
    public StringProperty typeProperty() {
        return type;
    }

    public String getType() {
        return type.get();
    }

    public void setType(String value) {
        type.set(value);
    }

    // category property methods
    public StringProperty categoryProperty() {
        return category;
    }

    public String getCategory() {
        return category.get();
    }

    public void setCategory(String value) {
        category.set(value);
    }

    // descriptpm property methods
    public StringProperty descriptionProperty() {
        return description;
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String value) {
        description.set(value);
    }

    // date property methods
    public StringProperty dateProperty() {
        return date;
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(String value) {
        date.set(value);
    }
}