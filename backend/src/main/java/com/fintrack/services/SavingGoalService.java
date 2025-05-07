package com.fintrack.services;

import com.fintrack.models.SavingGoal;
import com.fintrack.models.User;
import com.fintrack.repositories.SavingGoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SavingGoalService {

    private final SavingGoalRepository savingGoalRepository;

    @Autowired
    public SavingGoalService(SavingGoalRepository savingGoalRepository) {
        this.savingGoalRepository = savingGoalRepository;
    }

    public List<SavingGoal> getSavingGoalsByUser(User user) {
        return savingGoalRepository.findByUser(user);
    }

    public SavingGoal saveSavingGoal(SavingGoal savingGoal) {
        return savingGoalRepository.save(savingGoal);
    }

    public SavingGoal updateSavingGoal(Long id, SavingGoal updated) {
        Optional<SavingGoal> optional = savingGoalRepository.findById(id);
        if (optional.isPresent()) {
            SavingGoal existing = optional.get();
            existing.setGoalName(updated.getGoalName());
            existing.setTargetAmount(updated.getTargetAmount());
            existing.setCurrentAmount(updated.getCurrentAmount());
            return savingGoalRepository.save(existing);
        } else {
            throw new RuntimeException("Saving goal not found with ID: " + id);
        }
    }

    public void deleteSavingGoal(Long id) {
        if (!savingGoalRepository.existsById(id)) {
            throw new RuntimeException("Saving goal not found with ID: " + id);
        }
        savingGoalRepository.deleteById(id);
    }
}
