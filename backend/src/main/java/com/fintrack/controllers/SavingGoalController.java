package com.fintrack.controllers;

import com.fintrack.models.SavingGoal;
import com.fintrack.models.User;
import com.fintrack.services.SavingGoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userID}/goals")
public class SavingGoalController {

    private final SavingGoalService savingGoalService;

    @Autowired
    public SavingGoalController(SavingGoalService savingGoalService) {
        this.savingGoalService = savingGoalService;
    }

    // GET all saving goals for a user
    @GetMapping
    public List<SavingGoal> getSavingGoals(@PathVariable Long userID) {
        User user = new User();
        user.setId(userID);
        return savingGoalService.getSavingGoalsByUser(user);
    }

    // POST a new saving goal
    @PostMapping
    public SavingGoal createGoal(@PathVariable Long userID, @RequestBody SavingGoal goal) {
        User user = new User();
        user.setId(userID);
        goal.setUser(user);
        return savingGoalService.saveSavingGoal(goal);
    }

    // PUT update an existing goal
    @PutMapping("/{id}")
    public SavingGoal updateGoal(@PathVariable Long id, @RequestBody SavingGoal updatedGoal) {
        return savingGoalService.updateSavingGoal(id, updatedGoal);
    }

    // DELETE a goal
    @DeleteMapping("/{id}")
    public void deleteGoal(@PathVariable Long id) {
        savingGoalService.deleteSavingGoal(id);
    }
}
