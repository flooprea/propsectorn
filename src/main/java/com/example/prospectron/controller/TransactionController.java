package com.example.prospectron.controller;

import com.example.prospectron.model.Transaction;
import com.example.prospectron.service.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;


    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public Iterable<Transaction> getTransactions(){
        return transactionService.getTransactions();
    }

    @GetMapping(path = "{transactionId}")
    public Optional<Transaction> getTransactionById(@PathVariable("transactionId") Long transactionId){
        return transactionService.getTransactionById(transactionId);
    }

    @PostMapping
    public void storeTransaction(@RequestBody Map<String, Object> payload)throws Exception {

        JSONObject transactionData= new JSONObject(new ObjectMapper().writeValueAsString(payload));
        transactionService.storeTransactionDetails(transactionData);

    }
    @DeleteMapping(path = "{transactionId}")
    public void deleteTransaction(@PathVariable("transactionId") Long id){
        transactionService.deleteTransaction(id);
    }
}
