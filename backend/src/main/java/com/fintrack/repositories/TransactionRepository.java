package com.fintrack.repositories;

import com.fintrack.models.Transaction;
import com.fintrack.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUser(User user);  // Make sure User is imported correctly
}
