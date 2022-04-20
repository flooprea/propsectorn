package com.example.prospectron.repository;

import com.example.prospectron.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    Optional<Transaction> findTransactionByOrderRef(String orderRef);
}
