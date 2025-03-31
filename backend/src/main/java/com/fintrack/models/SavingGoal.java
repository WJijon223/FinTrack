package com.fintrack.models;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "saving_goal")
public class SavingGoal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String goalName;

    private BigDecimal targetAmount;

    private BigDecimal currentAmount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Getters and Setters
}
