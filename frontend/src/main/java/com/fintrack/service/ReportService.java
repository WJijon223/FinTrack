package com.fintrack.service;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

//service for generating the report
public class ReportService {

    private Random random = new Random();

    // generates a financial report for the specified month and year
    public CompletableFuture<Report> generateReport(int year, Month month) {
        return CompletableFuture.supplyAsync(() -> {

            //  processing time
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            // create  report
            Report report = new Report();

            // set report data
            report.setTitle("Financial Report: " +
                    month.getDisplayName(TextStyle.FULL, Locale.getDefault()) + " " + year);
            report.setGeneratedOn(LocalDate.now());

            // calculate report data - replace with actual data source
            YearMonth yearMonth = YearMonth.of(year, month);
            LocalDate startDate = yearMonth.atDay(1);
            LocalDate endDate = yearMonth.atEndOfMonth();

            // Mock data - meanwhile connect with actual database
            double totalIncome = generateTotalIncome();
            Map<String, Double> expenseCategories = generateExpenseCategories();
            double totalExpenses = expenseCategories.values().stream().mapToDouble(Double::doubleValue).sum();
            double savings = totalIncome - totalExpenses;
            double savingsRate = (savings / totalIncome) * 100;

            //  summary text
            StringBuilder summary = new StringBuilder();
            summary.append(String.format("Total Income: $%.2f\n", totalIncome));
            summary.append(String.format("Total Expenses: $%.2f\n", totalExpenses));
            summary.append(String.format("Total Savings: $%.2f\n", savings));
            summary.append(String.format("Savings Rate: %.1f%%\n", savingsRate));

            report.setSummary(summary.toString());

            //  detailed breakdown
            StringBuilder details = new StringBuilder();
            details.append("EXPENSE BREAKDOWN:\n\n");

            // add each expense category with its amount and percentage

            expenseCategories.forEach((category, amount) -> {
                double percentage = (amount / totalExpenses) * 100;
                details.append(String.format("%s: $%.2f (%.1f%%)\n",
                        category, amount, percentage));
            });
            details.append("\n");

            // add top spending categories
            details.append("TOP SPENDING CATEGORIES:\n\n");
            expenseCategories.entrySet().stream()
                    .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                    .limit(3)
                    .forEach(entry -> {
                        details.append(String.format("%s: $%.2f\n",
                                entry.getKey(), entry.getValue()));
                    });
            details.append("\n");

            // add budget recommendations
            details.append("BUDGET RECOMMENDATIONS:\n\n");
            if (savingsRate < 10) {
                details.append("Your savings rate is below recommended levels. Consider the following:\n");
                details.append("• Review your highest expense categories for potential reductions\n");
                details.append("• Establish a monthly budget target for each category\n");
                details.append("• Look for additional income sources\n");
            } else if (savingsRate < 20) {
                details.append("Your savings rate is good but could be improved. Consider:\n");
                details.append("• Increasing retirement contributions\n");
                details.append("• Building an emergency fund if you don't have one\n");
                details.append("• Reviewing subscription services for any unnecessary expenses\n");
            } else {
                details.append("Excellent savings rate! Some next steps to consider:\n");
                details.append("• Maximize tax-advantaged investment accounts\n");
                details.append("• Consider diversifying investments if you haven't already\n");
                details.append("• Review your insurance coverage to ensure adequate protection\n");
            }

            // add month-over-month comparison if applicable
            if (LocalDate.now().getMonth() != month || LocalDate.now().getYear() != year) {
                details.append("\nMONTH-OVER-MONTH COMPARISON:\n\n");
                double previousMonthExpenses = totalExpenses * (0.9 + random.nextDouble() * 0.2);
                double change = ((totalExpenses - previousMonthExpenses) / previousMonthExpenses) * 100;

                details.append(String.format("Previous month's expenses: $%.2f\n", previousMonthExpenses));
                details.append(String.format("Change: %.1f%%\n", change));

                if (change > 0) {
                    details.append("Your spending has increased compared to last month.\n");
                } else {
                    details.append("Your spending has decreased compared to last month.\n");
                }
            }

            report.setDetails(details.toString());

            return report;
        });
    }

    // generates report for a specific expense category

    public CompletableFuture<Report> generateCategoryReport(int year, Month month, String category) {
        return CompletableFuture.supplyAsync(() -> {


            //  processing time
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            // create report
            Report report = new Report();

            // set report data
            report.setTitle(category + " Expense Report: " +
                    month.getDisplayName(TextStyle.FULL, Locale.getDefault()) + " " + year);
            report.setGeneratedOn(LocalDate.now());

            // generate mock data for the category
            double categoryTotal = 200 + random.nextDouble() * 800;
            double previousMonthTotal = categoryTotal * (0.9 + random.nextDouble() * 0.2);
            double yearToDateTotal = categoryTotal * (5 + random.nextDouble() * 3);
            double monthlyAverage = yearToDateTotal / LocalDate.now().getMonthValue();

            //  summary
            StringBuilder summary = new StringBuilder();
            summary.append(String.format("Total %s Expenses: $%.2f\n", category, categoryTotal));
            summary.append(String.format("Previous Month: $%.2f\n", previousMonthTotal));
            summary.append(String.format("Year to Date: $%.2f\n", yearToDateTotal));
            summary.append(String.format("Monthly Average: $%.2f\n", monthlyAverage));

            report.setSummary(summary.toString());

            //  details
            StringBuilder details = new StringBuilder();
            details.append("TOP TRANSACTIONS:\n\n");

            //mock transactions
            for (int i = 1; i <= 5; i++) {
                double amount = 20 + random.nextDouble() * 100;
                int day = 1 + random.nextInt(28);
                details.append(String.format("%s %d: $%.2f - %s\n",
                        month.getDisplayName(TextStyle.SHORT, Locale.getDefault()),
                        day,
                        amount,
                        generateMockMerchant(category)));
            }

            details.append("\nTRENDS:\n\n");

            // calculate trend percentage
            double trendPercentage = -10 + random.nextDouble() * 20;
            if (trendPercentage > 0) {
                details.append(String.format("Your %s spending is up %.1f%% compared to your average.\n",
                        category.toLowerCase(), trendPercentage));
            } else {
                details.append(String.format("Your %s spending is down %.1f%% compared to your average.\n",
                        category.toLowerCase(), Math.abs(trendPercentage)));
            }

            details.append("\nRECOMMENDATIONS:\n\n");
            if (trendPercentage > 5) {
                details.append("Your spending in this category has increased significantly. Consider:\n");
                details.append("• Reviewing recent transactions for unnecessary expenses\n");
                details.append("• Setting a budget cap for this category\n");
                details.append("• Looking for discounts or alternatives for regular purchases\n");
            } else if (trendPercentage < -5) {
                details.append("You've reduced spending in this category. Keep it up by:\n");
                details.append("• Continuing your current savings strategies\n");
                details.append("• Consider redirecting some savings to investments\n");
                details.append("• Reviewing if any essential items were postponed that might need attention\n");
            } else {
                details.append("Your spending in this category is stable. To optimize further:\n");
                details.append("• Review recurring subscriptions or services for better rates\n");
                details.append("• Look for loyalty programs or cashback opportunities\n");
                details.append("• Consider if any planned purchases can be made more efficiently\n");
            }

            report.setDetails(details.toString());

            return report;
        });
    }

    //error message
    public Report generateErrorReport(String errorMessage) {

        Report report = new Report();
        report.setTitle("Error Report");
        report.setGeneratedOn(LocalDate.now());
        report.setSummary("An error occurred while generating your report.");
        report.setErrorMessage(errorMessage);
        return report;
    }

    //mock total income
    private double generateTotalIncome() {

        // generate realistic income
        return 3000 + random.nextDouble() * 3000;
    }

    //generate mock expenses
    private Map<String, Double> generateExpenseCategories() {
        Map<String, Double> categories = new HashMap<>();

        categories.put("Housing", 800 + random.nextDouble() * 700);
        categories.put("Food", 300 + random.nextDouble() * 300);
        categories.put("Transportation", 150 + random.nextDouble() * 200);
        categories.put("Utilities", 100 + random.nextDouble() * 150);
        categories.put("Entertainment", 50 + random.nextDouble() * 150);
        categories.put("Healthcare", 100 + random.nextDouble() * 200);
        categories.put("Personal Care", 50 + random.nextDouble() * 100);
        categories.put("Education", 50 + random.nextDouble() * 150);
        categories.put("Savings", 200 + random.nextDouble() * 300);
        categories.put("Other", 100 + random.nextDouble() * 200);

        return categories;
    }

    //generate mock merchants
    private String generateMockMerchant(String category) {
        Map<String, String[]> merchantsByCategory = new HashMap<>();

        merchantsByCategory.put("Housing", new String[]{"Apartment Rental", "Mortgage Co.", "Home Depot", "IKEA", "Property Management Inc."});
        merchantsByCategory.put("Food", new String[]{"Grocery Store", "Restaurant", "Starbucks", "McDonald's", "Local Cafe"});
        merchantsByCategory.put("Transportation", new String[]{"Gas Station", "Uber", "Public Transit", "Auto Repair", "Parking Garage"});
        merchantsByCategory.put("Utilities", new String[]{"Electric Company", "Water Service", "Internet Provider", "Gas Utility", "Phone Company"});
        merchantsByCategory.put("Entertainment", new String[]{"Movie Theater", "Streaming Service", "Concert Tickets", "Gaming Store", "Theme Park"});
        merchantsByCategory.put("Healthcare", new String[]{"Pharmacy", "Doctor's Office", "Dental Clinic", "Optometrist", "Health Insurance"});
        merchantsByCategory.put("Personal Care", new String[]{"Salon", "Spa", "Gym Membership", "Beauty Supply", "Wellness Center"});
        merchantsByCategory.put("Education", new String[]{"University", "Bookstore", "Online Course", "Tutoring Service", "School Supplies"});
        merchantsByCategory.put("Savings", new String[]{"Bank Transfer", "Investment Account", "Retirement Fund", "Emergency Fund", "Savings Account"});
        merchantsByCategory.put("Other", new String[]{"Amazon", "Department Store", "Specialty Shop", "Hardware Store", "Gift Shop"});

        String[] merchants = merchantsByCategory.getOrDefault(category,
                new String[]{"Unknown Merchant", "General Store", "Retail Shop", "Online Purchase", "Local Business"});

        return merchants[random.nextInt(merchants.length)];
    }
}