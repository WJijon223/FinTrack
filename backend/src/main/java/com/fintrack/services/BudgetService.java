package com.fintrack.services;

import com.fintrack.models.Budget;
import com.fintrack.models.User;
import com.fintrack.repositories.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BudgetService {

    private final BudgetRepository budgetRepository;

    @Autowired
    public BudgetService(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    // Create or update a budget
    public Budget saveBudget(Budget budget) {
        return budgetRepository.save(budget);
    }

    // Retrieve a budget by ID
    public Optional<Budget> getBudgetById(Long id) {
        return budgetRepository.findById(id);
    }

    // Retrieve all budgets for a specific user
    public List<Budget> getBudgetsByUser(Long userID) {
        List<Budget> budgets = budgetRepository.findByUser(userID);
        if (budgets.isEmpty()) {
            throw new RuntimeException("No budgets found for user with ID: " + userID);
        }
        return budgets;
    }

    // Delete a budget by ID
    public void deleteBudgetById(Long id) {
        if (!budgetRepository.existsById(id)) {
            throw new RuntimeException("Budget not found with ID: " + id);
        }
        budgetRepository.deleteById(id);
    }
}
