package com.fintrack.service;

import java.time.LocalDate;


public class Report {

    private String title;
    private LocalDate generatedOn;
    private String summary;
    private String details;
    private String errorMessage;


    public Report() {
        // default constructor
    }


    //create a new report with basic information

    public Report(String title, LocalDate generatedOn, String summary) {
        this.title = title;
        this.generatedOn = generatedOn;
        this.summary = summary;
    }

    // getter and setters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getGeneratedOn() {
        return generatedOn;
    }

    public void setGeneratedOn(LocalDate generatedOn) {
        this.generatedOn = generatedOn;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "Report{" +
                "title='" + title + '\'' +
                ", generatedOn=" + generatedOn +
                ", summary length=" + (summary != null ? summary.length() : 0) +
                ", details length=" + (details != null ? details.length() : 0) +
                ", hasError=" + (errorMessage != null && !errorMessage.isEmpty()) +
                '}';
    }
}