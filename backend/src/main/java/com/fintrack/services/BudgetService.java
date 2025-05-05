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

    // Create a budget
    public Budget saveBudget(Budget budget) {
        return budgetRepository.save(budget);
    }

    // Update budget by ID
    public Budget updateBudget(Long id, Budget updatedBudget) {
        Optional<Budget> optionalBudget = budgetRepository.findById(id);
        if (optionalBudget.isPresent()) {
            Budget existingBudget = optionalBudget.get();
            existingBudget.setMonth(updatedBudget.getMonth());
            existingBudget.setTotalBudget(updatedBudget.getTotalBudget());
            existingBudget.setTotalAmountSpent(updatedBudget.getTotalAmountSpent());
            return budgetRepository.save(existingBudget);
        } else {
            throw new RuntimeException("Budget not found with ID: " + id);
        }
    }

    // Retrieve a budget by ID
    public Optional<Budget> getBudgetById(Long id) {
        return budgetRepository.findById(id);
    }

    // ✅ Retrieve all budgets for a specific user (safe fix)
    public List<Budget> getBudgetsByUser(User user) {
        return budgetRepository.findByUser(user); // No exception if empty
    }

    // Delete a budget by ID
    public void deleteBudgetById(Long id) {
        if (!budgetRepository.existsById(id)) {
            throw new RuntimeException("Budget not found with ID: " + id);
        }
        budgetRepository.deleteById(id);
    }
}
