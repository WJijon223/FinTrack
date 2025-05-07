package com.fintrack.controllers;

import com.fintrack.models.Transaction;
import com.fintrack.models.User;
import com.fintrack.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userID}/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // GET all transactions for a user
    @GetMapping
    public List<Transaction> getTransactions(@PathVariable Long userID) {
        User user = new User();
        user.setId(userID);
        return transactionService.getTransactionsByUser(user);
    }

    // POST new transaction
    @PostMapping
    public Transaction createTransaction(@PathVariable Long userID, @RequestBody Transaction transaction) {
        User user = new User();
        user.setId(userID);
        transaction.setUser(user);
        return transactionService.saveTransaction(transaction);
    }

    // PUT update transaction
    @PutMapping("/{id}")
    public Transaction updateTransaction(@PathVariable Long id, @RequestBody Transaction updated) {
        return transactionService.updateTransaction(id, updated);
    }

    // DELETE transaction
    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
    }
}
