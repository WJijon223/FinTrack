package com.fintrack.models;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "budget")
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String month;

    private BigDecimal totalBudget;

    private BigDecimal totalAmountSpent;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getMonth() {
        return month;
    }
    public void setMonth(String month) {
        this.month = month;
    }

    public BigDecimal getTotalBudget() {
        return totalBudget;
    }
    public void setTotalBudget(BigDecimal totalBudget) {
        this.totalBudget = totalBudget;
    }

    public BigDecimal getTotalAmountSpent() {
        return totalAmountSpent;
    }
    public void setTotalAmountSpent(BigDecimal totalAmountSpent) {
        this.totalAmountSpent = totalAmountSpent;
    }
}
