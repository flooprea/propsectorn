package com.example.prospectron.repository;

import com.example.prospectron.model.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    Optional<Transaction> findTransactionByOrderRef(String orderRef);
}
