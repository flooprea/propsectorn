package com.example.prospectron.repository;

import com.example.prospectron.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findByTransactionId(Long transactionId);
}
