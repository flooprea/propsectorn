package com.example.prospectron.controller;

import com.example.prospectron.exception.ApiRequestException;
import com.example.prospectron.model.Transaction;
import com.example.prospectron.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired //instantiates TransactionsService
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public Iterable<Transaction> getTransactions() {
        try {
            return transactionService.getTransactions();
        } catch (ApiRequestException ex) {
            throw new ApiRequestException("Failed to read from database", ex);
        }

    }

    @GetMapping(path = "{transactionId}")
    public Optional<Transaction> getTransactionById(@PathVariable("transactionId") Long transactionId) {
        try {
            return transactionService.getTransactionById(transactionId);
        } catch (ApiRequestException ex) {
            throw new ApiRequestException("Failed to read from database", ex);
        }
    }

    @PostMapping
    public List<Transaction> storeTransaction(@RequestBody List<Transaction> payload) {
            return transactionService.storeTransactionDetails(payload);
    }

    @DeleteMapping(path = "{transactionId}")
    public String deleteTransaction(@PathVariable("transactionId") Long id) {
        try {
            transactionService.deleteTransaction(id);
            return "Transaction deleted";
        } catch (ApiRequestException ex) {
            throw new ApiRequestException("Failed to read from database", ex);
        }
    }

}
