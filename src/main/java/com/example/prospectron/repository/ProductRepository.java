package com.example.prospectron.repository;

import com.example.prospectron.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
