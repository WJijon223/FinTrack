package com.fintrack.services;

import com.fintrack.models.Transaction;
import com.fintrack.models.User;
import com.fintrack.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    // Get all transactions for a user
    public List<Transaction> getTransactionsByUser(User user) {
        return transactionRepository.findByUser(user);
    }

    // Create a new transaction
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    // Update a transaction by ID
    public Transaction updateTransaction(Long id, Transaction updatedTransaction) {
        Optional<Transaction> optional = transactionRepository.findById(id);
        if (optional.isPresent()) {
            Transaction existing = optional.get();
            existing.setAmount(updatedTransaction.getAmount());
            existing.setCategory(updatedTransaction.getCategory());
            existing.setDate(updatedTransaction.getDate());
            existing.setDescription(updatedTransaction.getDescription());
            existing.setType(updatedTransaction.getType());
            return transactionRepository.save(existing);
        } else {
            throw new RuntimeException("Transaction not found with ID: " + id);
        }
    }

    // Delete transaction by ID
    public void deleteTransaction(Long id) {
        if (!transactionRepository.existsById(id)) {
            throw new RuntimeException("Transaction not found with ID: " + id);
        }
        transactionRepository.deleteById(id);
    }
}
