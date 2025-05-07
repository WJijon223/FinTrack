package com.fintrack.repositories;

import com.fintrack.models.SavingGoal;
import com.fintrack.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SavingGoalRepository extends JpaRepository<SavingGoal, Long> {
    List<SavingGoal> findByUser(User user);
}
