package com.fintrack.controllers;

import com.fintrack.models.Budget;
import com.fintrack.models.User;
import com.fintrack.services.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userID}/budgets")
public class BudgetController {

    private final BudgetService budgetService;

    @Autowired
    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    // Get all budgets for a specific user
    @GetMapping
    public List<Budget> getBudgetsByUser(@PathVariable Long userID) {
        User user = new User();
        user.setId(userID);
        return budgetService.getBudgetsByUser(user);
    }

    // Get a budget by ID
    @GetMapping("/{id}")
    public Budget getBudgetById(@PathVariable Long id) {
        return budgetService.getBudgetById(id)
                .orElseThrow(() -> new RuntimeException("Budget not found with ID: " + id));
    }

    // Create a new budget for the user
    @PostMapping
    public Budget saveBudget(@PathVariable Long userID, @RequestBody Budget budget) {
        User user = new User();
        user.setId(userID);
        budget.setUser(user);
        return budgetService.saveBudget(budget);
    }

    // Delete a budget by ID
    @DeleteMapping("/{id}")
    public void deleteBudgetById(@PathVariable Long id) {
        budgetService.deleteBudgetById(id);
    }

    // Update an existing budget
    @PutMapping("/{id}")
    public Budget updateBudget(@PathVariable Long id, @RequestBody Budget updatedBudget) {
        return budgetService.updateBudget(id, updatedBudget);
    }
}
